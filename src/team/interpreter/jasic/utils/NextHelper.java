package team.interpreter.jasic.utils;

import team.interpreter.jasic.exception.ForToolException;

public class NextHelper {
	public static void execute(ProgramHelper ph,
			GetNextWordHelper getNext) throws ForToolException{
		assert ph.forStack.empty()==false:"for-next栈中为空";
		ForHelper fh = (ForHelper)ph.forStack.pop();
		fh.readNext(ph, getNext);
	}
}
