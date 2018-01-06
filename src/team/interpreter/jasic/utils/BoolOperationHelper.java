package team.interpreter.jasic.utils;

import team.interpreter.jasic.exception.CalculateSyntaxException;

public class BoolOperationHelper {
	private CalculateHelper calhelper = new CalculateHelper();
	
	public boolean getResult(double l_num,String s,double r_num) throws CalculateSyntaxException {

		//System.out.println(s);
		boolean flag = false;
		
		if(s.equals("<")){
			flag = l_num < r_num;
		}else if(s.equalsIgnoreCase("<=")){
			flag = l_num <= r_num;
		}else if(s.equalsIgnoreCase(">")){
			flag = l_num > r_num;
		}else if(s.equalsIgnoreCase(">=")){
			flag = l_num >= r_num;
		}else if(s.equalsIgnoreCase("=")){
			flag = (l_num == r_num);
		}
		return flag;
	}
}
