package team.interpreter.jasic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.CommandTypeException;
import team.interpreter.jasic.exception.FileNotTxtException;
import team.interpreter.jasic.main.FileInput;
import team.interpreter.jasic.utils.AssignmentHelper;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.PrintHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class SelectHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws FileNotTxtException, AssignSyntaxException, CalculateSyntaxException, CommandTypeException {
		 FileInput file  = new FileInput();
	        file.setFile_path("D:\\java_project/select-case.txt");
	        file.execute();
	        ProgramHelper phelper = ProgramHelper.getHelper();
	        GetNextWordHelper ghelper = new GetNextWordHelper();
	        ghelper.execute(phelper);
	        while (phelper.currentType != WordType.EOP.ordinal()) {
	        if(phelper.currentWord.equals("let") 
	        		|| phelper.currentType == WordType.VARIABLE.ordinal()){
        		AssignmentHelper.execute(phelper, ghelper);
	        }
        	
        	if(phelper.currentKey == KeyWordType.CASE.ordinal() 
        			|| phelper.currentKey == KeyWordType.END.ordinal()){
        		return;
        	}
        	
        	if(phelper.currentWord.equals("print") ){
        		PrintHelper.execute(phelper, ghelper);
	        }
	        }
			

	   }
	
}
