package team.interpreter.jasic.exception;
/**
 * 
 * @author 郝祥超
 *
 */
public class SeparatorNotCommaException extends Exception{

    public SeparatorNotCommaException() {
        super("分隔符须为逗号");
        // TODO Auto-generated constructor stub
    }

    public SeparatorNotCommaException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SeparatorNotCommaException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
