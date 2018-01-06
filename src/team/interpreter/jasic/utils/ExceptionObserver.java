package team.interpreter.jasic.utils;

public class ExceptionObserver implements Observer {

    @Override
    public void execute(Exception e) {
        /*StackTraceElement[] trace = e.getStackTrace();
        String exceptionMessage = "";
        for (StackTraceElement s : trace) {
            exceptionMessage += s + "\r\n";
        }*/
        String exceptionMessage = "";
        exceptionMessage = e.getMessage();
        Observer.store.programOutput += exceptionMessage;
    }

}
