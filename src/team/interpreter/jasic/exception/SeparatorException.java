package team.interpreter.jasic.exception;
/**
 * 
 * @author 郝祥超
 *
 */
public class SeparatorException extends Exception{

    public SeparatorException() {
        super("分割符应为逗号或分号");
        // TODO Auto-generated constructor stub
    }

    public SeparatorException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SeparatorException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
