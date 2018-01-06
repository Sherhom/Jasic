package team.interpreter.jasic.exception;
/**
 * 
 * @author 叶飞
 *
 */
public class ParenthesesLackException extends Exception{

	public ParenthesesLackException() {
		super("软件构造第八组友情提示：括号要成对出现哦~~~(^v^)~~~");
		// TODO Auto-generated constructor stub
	}

	public ParenthesesLackException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ParenthesesLackException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
