package team.interpreter.jasic.utils;

import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;

public class LoopHelper {
	public static void execute(ProgramHelper ph,
			GetNextWordHelper getNext) throws CalculateSyntaxException{
		assert ph.whileStack.empty()==false:"do-while栈中为空";
		DoWhileHelper dwh = (DoWhileHelper)ph.whileStack.pop();
		dwh.readLoop(ph, getNext);
	}
}
