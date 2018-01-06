package team.interpreter.jasic.utils;

import java.util.HashMap;
import java.util.Stack;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.CalculateSyntaxException;


public class CalculateHelper {
    
	
	private boolean isDelim(char c){
		if((" \r,;<>+-/*%^=()!").indexOf(c)!=-1)//!代表负号
			return true;
		return false;
	}
	
	public boolean is_exp_ch(char c){//是数字表达式里的字符，注意，少了右括号
		if(c=='('||c=='+'||c=='-'||c=='*'||c=='/'||c=='%'||c=='^'||c=='!'){
			return true;
		}else
			return false;
		
	}
	

    public boolean isDigit(char c){//判断是否为数字字符
    	int a=c-'0';
    	if(a>=0&&a<10){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    int priority(char c){//操作符的优先级
    	int pri=0;
    	switch (c) {
			case '+':
				pri=1;
				break;
			case '-':
				pri=1;
				break;
			case '*':
				pri=2;
				break;
			case '/':
				pri=2;
				break;
			case '%':
				pri=2;
				break;
			case '^':
				pri=3;
				break;
			case '!':
				pri=4;
				break;
			case '(':
				pri=5;
				break;
		default:
			break;
		}
    	return pri;
    }
    
    String turnString(String temp){
    	//将正号删除，将负号变成!
    	
    	assert (temp!=null||temp!=""):"输入的字符串为null或者空";
    	//System.out.println("中缀："+temp);
    	StringBuffer sb=new StringBuffer(temp);
    	for(int i=sb.length()-1;i>=1;i--){
    		if(sb.charAt(i)=='+'||sb.charAt(i)=='-'){
    			if(is_exp_ch(sb.charAt(i-1))){//前面是这些符号，则是正负号
    				if(sb.charAt(i)=='+'){
    					sb.deleteCharAt(i);//是正号则删除
    				}else{
    					sb.setCharAt(i, '!');//负号则替换为!
    				}
    			}
    		}
    	}
    	if(sb.charAt(0)=='+'){
    		sb.deleteCharAt(0);
    	}else if(sb.charAt(0)=='-'){
    		sb.setCharAt(0, '!');
    	}
		return sb.toString();
    	
    }
    
    String getString(ProgramHelper phelper,GetNextWordHelper ghelper){//得到一个数字表达式（不包括关系运算符）
    	
    	assert phelper!=null:"传入的参数phelper为null";
		
		assert ghelper!=null:"传入的参数ghelper为null";
		
    	StringBuffer sb=new StringBuffer();
    	ghelper.execute(phelper);
    	String curWord=phelper.currentWord;
    	char operator=curWord.charAt(0);
    	int curType=phelper.currentType;
    	//此时的变量应该只能是变量名，而不能是number和string两种类型
    	
    	
    	while(curType==WordType.NUMBER.ordinal()||curType==WordType.VARIABLE.ordinal()||is_exp_ch(operator)||operator==')'){
    		if(curType==WordType.NUMBER.ordinal()){
    			sb.append(phelper.currentWord);
    			sb.append(",");
    		}else if(curType==WordType.VARIABLE.ordinal()){//如果是变量名，把它替换为其值
    			double value=phelper.numberMap.get(curWord);
    			String s=Double.toString(value);
    			sb.append(s);
    			sb.append(",");
    		}
    		else{
    			sb.append(operator);
    		}
    		ghelper.execute(phelper);
    		curWord=phelper.currentWord;
    		operator=curWord.charAt(0);
    		curType=phelper.currentType;
    	}
    	return sb.toString();
	
    }
    
    String inToPos(String infix){//中缀转后缀
    	
    	Stack<Character> op_stack=new Stack<Character>();
    	StringBuffer posfix=new StringBuffer();
    	for(int i=0;i<infix.length();i++){
    	    char c=infix.charAt(i);
    		if(isDigit(c)){//数字直接输出
    			int index=infix.indexOf(',',i);
    			String s=infix.substring(i,index);//获得一个整数字符串
    			posfix.append(s);
    			posfix.append(",");
    			i=index;//改变i的值
    			if(!op_stack.empty()&&op_stack.peek()=='!'){//如果栈顶为单目运算符，则输出
    				posfix.append('!');
    				op_stack.pop();
    			}
    			continue;
    		}else if(is_exp_ch(c)||c==')'){//如果是操作符
    			if(op_stack.isEmpty()){//栈为空，则符号直接入栈
    				op_stack.push(c);
    				
    			}else if(c=='('){//如果为左括号，直接入栈
    				op_stack.push(c);
    			}else if(c==')'){//如果为,弹出所有直到（
    				while(!op_stack.empty()&&op_stack.peek()!='('){
    					posfix.append(op_stack.peek());
    					op_stack.pop();
    				}
    				op_stack.pop();
    				
    			}else if(c=='!'){//单目-直接入栈
    				op_stack.push(c);
    			}else if(!op_stack.empty()&&op_stack.peek()=='('){
    				op_stack.push(c);
    			}
    			else if(priority(c)>priority(op_stack.peek())){//如果当前优先级大于栈顶
    				op_stack.push(c);
    			}else if(priority(c)<=priority(op_stack.peek())){//当前的小于等于栈顶
    				while(!op_stack.empty()&&priority(c)<=priority(op_stack.peek())){
    					posfix.append(op_stack.peek());
    					op_stack.pop();
    				}
    				op_stack.push(c);
    			}
    		}
    	}
    	while(!op_stack.empty()){//弹出栈内所有字符
    		posfix.append(op_stack.peek());
    		op_stack.pop();
    	}
		return posfix.toString();
    	
    }
    
    double op_cal(double a,double b,char c){//根据运算符计算
    	double ans=0;
    	switch (c) {
		case '+':
			ans=a+b;
			break;
		case '-':
			ans=a-b;
			break;
		case '*':
			ans=a*b;
			break;
		case '/':
			ans=a/b;
			break;
		case '%':
			ans=a%b;
			break;
		case '^':
			ans=Math.pow(a, b);
			break;
		default:
			break;
		}
		return ans;
    	
    }
    
    public double excuteString(String temp) throws  CalculateSyntaxException{
    	//System.out.println("hello");
    	temp=turnString(temp);
    	temp=inToPos(temp);
    	String posfix=temp;
    	//System.out.println(posfix);
    	Stack<Double> double_stack=new Stack<Double>();
    	double ans=0;
    	for(int i=0;i<posfix.length();i++){
    		char c=posfix.charAt(i);
    		if(isDigit(c)){//数字则入栈
    			int index=posfix.indexOf(',',i);
    			String s=posfix.substring(i, index);
    			double value=Double.parseDouble(s);
    			double_stack.push(value);
    			i=index;
    			continue;
    		}else{
    			if(c=='!'){//单目运算符
    				double result=double_stack.peek();
    				result=-result;
    				double_stack.pop();
    				double_stack.push(result);
    				
    			}else{//双目运算符
    				double a=double_stack.pop();
    				double b=double_stack.pop();
    				double result=op_cal(b, a, c);
    				double_stack.push(result);
    			}
    		}
    	}
    	if(double_stack.size()!=1){
    		throw new CalculateSyntaxException("运算表达式错误！");
    	}else{
    		ans=double_stack.pop();
    	}
    	//System.out.println("运算结果为："+ans);
		return ans;
    	
    }
    
    public double execute(ProgramHelper phelper,GetNextWordHelper ghelper) throws CalculateSyntaxException{
    	   //计算后缀表达式
    	String temp=getString(phelper,ghelper);
    	//System.out.println("测试一："+temp);
    	double ans=excuteString(temp);
    	return ans;
    }
    

}
