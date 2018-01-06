package team.interpreter.jasic.exception;
/**
 * 
 * @author 郝祥超
 *
 */
public class ParameterNumberNotEqualException extends Exception{

    public ParameterNumberNotEqualException() {
        super("输入参数个数与程序定义不符");
        // TODO Auto-generated constructor stub
    }

    public ParameterNumberNotEqualException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ParameterNumberNotEqualException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
