package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;

/**
 * 
 * @author 余康
 * updated on 18/1/2
 * 
 */
public class AssignmentHelper {

	//为number型变量或者string型变量赋值
	public static void execute(ProgramHelper phelper,GetNextWordHelper ghelper) throws AssignSyntaxException, CalculateSyntaxException{
		
		//先处理声明并初始化的情形或只声明的情形
		CalculateHelper chelper=new CalculateHelper();
		JumpBackWordHelper jbhelper=new JumpBackWordHelper();
		
		int curkey=phelper.currentKey;
		int curtype=phelper.currentType;
		String curWord="";
		
		if(curkey==KeyWordType.LET.ordinal()){
			ghelper.execute(phelper);
		    curWord=phelper.currentWord;//获取变量名
		 
			if(phelper.stringMap.containsKey(curWord)||phelper.numberMap.containsKey(curWord)){//变量已声明
				throw new AssignSyntaxException("变量重复声明错误！"); 
			}else{
				ghelper.execute(phelper);
				if(phelper.currentWord.charAt(0)!='='){//如果得到的不是=,报错
					throw new AssignSyntaxException("赋值缺少等号错误！"); 
				}//得到的是=，继续进行赋值
					/*if(curtype==WordType.NUMBER.ordinal()){
						double result=chelper.excute(phelper, ghelper);
						phelper.numberMap.put(curWord, result);
					}else if(curtype==WordType.STRING.ordinal()){
						String s=cal_string(phelper, ghelper);
						phelper.stringMap.put(curWord, s);
					}
					return;*/
					
			}
		}else if(curkey==KeyWordType.UNCERTAIN.ordinal()){//如果得到的是直接赋值，而不是申明赋值
			jbhelper.execute(phelper);//往回跳一个
			ghelper.execute(phelper);
		    curWord=phelper.currentWord;//获取变量名
		    
		    if(!(phelper.stringMap.containsKey(curWord)||phelper.numberMap.containsKey(curWord))){
		    	throw new AssignSyntaxException("要赋值的变量不存在！"); 
		    }
		    
		    ghelper.execute(phelper);//取得等号
		    if(phelper.currentWord.charAt(0)!='='){//如果得到的不是=,报错
				throw new AssignSyntaxException("赋值缺少等号错误！"); 
			}
		   // phelper.numberMap.remove(curWord);
		    //phelper.stringMap.remove(curWord);
		    ghelper.execute(phelper);
			 curtype=phelper.currentType;
			// System.out.println(phelper.currentType);
			 jbhelper.execute(phelper);//先取类型，如果是字符串就是字符串，否则按数字处理
		   
		    
		}
		else{
			throw new AssignSyntaxException("非赋值语句错误！"); 
		}
		
		
		 if(curtype==WordType.STRING.ordinal()){
			 String s=cal_string(phelper, ghelper);
			 //System.out.println("字符串赋值成功！"+curWord+"值为"+s);
			 phelper.stringMap.put(curWord, s);
			 phelper.numberMap.remove(curWord);
		 }else{
			 double result=chelper.execute(phelper, ghelper);
			 //System.out.println("数字赋值成功！"+curWord+"值为"+result);
			 phelper.numberMap.put(curWord, result);
			 phelper.stringMap.remove(curWord);
		 }
		 return;
		
	}
	
	 static String cal_string(ProgramHelper phelper,GetNextWordHelper ghelper){//得到赋值中字符串的值
	    	ghelper.execute(phelper);
	    	return phelper.currentWord;
	    	
	    }
}
