package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.DoWhileToolException;

public class DoWhileHelper {
	private int beginIndex;
	private int endIndex;
	private boolean flag;
	private CalculateHelper chelper = new CalculateHelper();
	private JumpBackWordHelper jumpback = new JumpBackWordHelper();
	private BoolOperationHelper boolHelper = new BoolOperationHelper();

	private void numCompare(ProgramHelper ph, GetNextWordHelper getNext) throws CalculateSyntaxException {
		double l_num = chelper.execute(ph, getNext);
		
		String s = ph.currentWord;
		
		double r_num = chelper.execute(ph, getNext);
		this.flag = boolHelper.getResult(l_num,s,r_num);
		
	}

	// getToken() case"do"
	// getToken() if( ==“while”)
	// new DoWhile().readDoWhile
	// while已经读完
	public void execute(ProgramHelper ph, GetNextWordHelper getNext) throws DoWhileToolException, CalculateSyntaxException{
		
		assert ph!=null:"传入的ProgramHelper参数为空";
		//从判断条件开始重新读
		getNext.execute(ph);
		if (ph.currentKey != KeyWordType.WHILE.ordinal() ) {
			throw new DoWhileToolException("Do-while语法错误");
		}
		getNext.execute(ph);
		if (ph.currentType == WordType.VARIABLE.ordinal() 
					&& ph.numberMap.get(ph.currentWord) != null) {
			jumpback.execute(ph);
			this.beginIndex = ph.index;
			assert flag==false:"判断条件初始设置错误";
			numCompare(ph,getNext);
		}else{
			throw new DoWhileToolException("判断条件不符合语法");
			//throwError("判断条件不符合语法");
		}
		
		//assert flag==false:"出错";
		if(flag){
			ph.whileStack.push(this);
			assert ph.whileStack.empty()==false:"do-while对象未放入栈中";
		}else{
			
			while(ph.currentKey != KeyWordType.LOOP.ordinal()) {
				getNext.execute(ph);
				//如果读到程序结束都没有Loop 报错
        		if(ph.currentType == WordType.EOP.ordinal())
        			throw new DoWhileToolException("Do-while语法错误");
        			//throwError("缺少LOOP");
        	}
			getNext.execute(ph);
		}
	}
	
	//读到loop就去读DoWhileStack中最上面那个info的信息
	//case"NEXT"
	// DoWhileTool dwt = (DoWhileTool)DoWhileStack.pop();
	//if() emptystack
	//dwt.readLoop();
	//LOOP读完
	public void readLoop(ProgramHelper ph, GetNextWordHelper getNext) throws CalculateSyntaxException{
		//不用numCompare(ph,getNext); 因为运算evaluate也用了getToken
		this.endIndex = ph.index;
		ph.index = this.beginIndex;
		numCompare(ph,getNext);
		
		if(flag) {
		        ph.whileStack.push(this);
		        assert ph.whileStack.empty()==false:"do-while对象未放入栈中";
        }else{
        	ph.index = this.endIndex;
//        	对象自动删除
//        	this.varsMap.clear();
//        	this.varsStringMap.clear();
        }
	}
		
	
	
}
