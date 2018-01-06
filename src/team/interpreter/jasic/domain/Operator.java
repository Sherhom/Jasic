package team.interpreter.jasic.domain;
/**
 * 
 * @author 叶飞
 *
 */
public class Operator {
	private static Operator op;
	public String operator = "\r\0\n; ,<>+-*%/^=()";
	
	public Operator(){
		
	}
	
	public static synchronized Operator getOperator(){
		if(op == null){
			op = new Operator();
		}
		return op;
	}
}