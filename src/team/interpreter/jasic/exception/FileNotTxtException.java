package team.interpreter.jasic.exception;

public class FileNotTxtException extends Exception{

    public FileNotTxtException() {
        super("上传文件类型不是TXT！");
    }

    public FileNotTxtException(String message) {
        super(message);
    }

    public FileNotTxtException(Throwable cause) {
        super(cause);
    }

}
