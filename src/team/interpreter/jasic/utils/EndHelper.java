package team.interpreter.jasic.utils;

import team.interpreter.jasic.draw.MyExecute;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class EndHelper {

    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) {
        MyExecute myexecute = new MyExecute().getMyExecute();
        myexecute.add();
    }
    
}
