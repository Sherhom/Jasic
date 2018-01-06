package team.interpreter.jasic.exception;

public class CommandTypeException extends Exception{

    public CommandTypeException() {
        super("关键字类型须为命令");
        // TODO Auto-generated constructor stub
    }

    public CommandTypeException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public CommandTypeException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    
    
}
