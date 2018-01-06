package team.interpreter.jasic.exception;
/**
 * 
 * @author 叶飞
 *
 */
public class NullMarkException extends Exception{

	public NullMarkException() {
		super("没有这个标记！！！！！");
		// TODO Auto-generated constructor stub
	}

	public NullMarkException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NullMarkException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NullMarkException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
}
