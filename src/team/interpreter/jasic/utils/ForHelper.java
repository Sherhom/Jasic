package team.interpreter.jasic.utils;



import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.ForToolException;


public class ForHelper {
	
	
	private String var ;
	private double terminal;
	private int index;
	private CalculateHelper chelper = new CalculateHelper();
	private JumpBackWordHelper jumpback = new JumpBackWordHelper();
	
    
    //for语句只读取一次 剩下在next中执行
	public void execute(ProgramHelper ph,GetNextWordHelper getNext) throws ForToolException, CalculateSyntaxException{
		
		assert ph!=null:"传入的ProgramHelper参数为空";
		getNext.execute(ph);
		
		//判断不是关键字
		if(ph.currentType != WordType.VARIABLE.ordinal() ){
				throw new ForToolException("变量是关键字");//throwError("变量是关键字");
		}
		this.var = ph.currentWord;
		
		//读取等号
		getNext.execute(ph);
		if(ph.currentWord.charAt(0) != '=') {
			throw new ForToolException("For语法错误");//throwError("缺少=");  
        }
		
		double initialvalue = chelper.execute(ph, getNext);
		//System.out.println(initialvalue);
		ph.numberMap.put(this.var,initialvalue);
		jumpback.execute(ph);
		//读取TO
		getNext.execute(ph);
		//System.out.println(ph.currentKey);//TO
        if(ph.currentKey != KeyWordType.TO.ordinal()) {
				throw new ForToolException("For-Next语法错误");//throwError("缺少TO");
        }
        
        this.terminal = chelper.execute(ph, getNext);
        
        //第一次进行判断 循环体中语句第一次是否执行(循环体是否有意义) 循环大于等于2
        if(initialvalue <= this.terminal) {
        	//保存循环体初始位置指针
        	this.index = ph.index;
        	//防止多层for嵌套
        	ph.forStack.push(this);
        }else{
        	//一直读到NEXT后
        	while(ph.currentKey != KeyWordType.NEXT.ordinal()) {
        		
        		//如果读到程序结束都没有NEXT 报错
        		if(ph.currentKey == WordType.EOP.ordinal()){
						throw new ForToolException("For语法错误");//throwError("缺少NEXT");
        		}
        		getNext.execute(ph);
        	}
        	//NEXT后的变量x
        	getNext.execute(ph);
        	if(ph.currentWord != this.var)
					throw new ForToolException("For语法错误");
        			//throwError("NEXT后的 x与之前的不对应");
        }
	}
	
	//读到next就去读fStack中最上面那个info的信息
	//case"NEXT"
	// ForHelper fh = (ForHelper)forStack.pop();
	//if() emptystack
	//fh.runNext();
	public void readNext(ProgramHelper ph,GetNextWordHelper getNext) throws ForToolException{
		//读x
		getNext.execute(ph);
		//System.out.println(ph.currentWord);
		if(!ph.currentWord .equals(this.var) )
			throw new ForToolException("For语法错误");
			//throwError("NEXT后的 x与之前的不对应");
			
		//更改递增值num
         Double num = ph.numberMap.get(this.var);
         num++;
         //未到达上限
         if(num <= this.terminal) {
        	 ph.numberMap.put(this.var,num);
             ph.index = this.index;//往后读 就会再次遇到Next
             ph.forStack.push(this);
        	 
         }else{
        	 ph.numberMap.remove(this.var);
        	//Map中var要删掉 作用域结束
        	 return;
        	 //forStack.remove(this); pop已经remove
         }
         
	}
		
}
