package team.interpreter.jasic.main;
import java.lang.reflect.Method;

import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class Factory {
    
    public static void main(String[] args) throws Exception{
        String currentWord = "input";
        String tempString = currentWord.toLowerCase();
        int num = tempString.charAt(0);
        char c = (char)(num - 32);
        String helperName = c + tempString.substring(1)+ "Helper";
        Class<?> cl = Class.forName("team.interpreter.jasic.utils." + helperName);
        ProgramHelper phelper = ProgramHelper.getHelper();
        GetNextWordHelper ghelper = new GetNextWordHelper();
        Method method = cl.getMethod("execute", phelper.getClass(), ghelper.getClass());
        method.invoke(cl.newInstance(), phelper, ghelper);
    }
    
}
