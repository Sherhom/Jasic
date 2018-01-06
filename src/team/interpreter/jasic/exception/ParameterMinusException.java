package team.interpreter.jasic.exception;
/**
 * 
 * @author 叶飞
 *
 */
public class ParameterMinusException extends Exception{

	public ParameterMinusException() {
		super("软件构造第八组友情提示：参数不能为负数哦~~~(^v^)~~~");
		// TODO Auto-generated constructor stub
	}

	public ParameterMinusException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ParameterMinusException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
