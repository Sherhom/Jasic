package team.interpreter.jasic.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class Test {
	public static void main(String[]args) throws FileNotFoundException{
		
		ProgramHelper helper = ProgramHelper.getHelper();
		GetNextWordHelper ghelper = new GetNextWordHelper();
		
		
		String path = "D:\\java_project/test.txt";
        char[] tempArray = new char[1024];
        File file = new File(path);
        FileReader fr;
        int size = 0;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            size = br.read(tempArray, 0, 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.arraycopy(tempArray, 0, helper.program, 0, size);
		//System.out.println(helper.program.length);
		/*for(int i = 0; i < helper.program.length; i++){
			System.out.print(helper.program[i]);
		}*/
		while(helper.currentType != WordType.EOP.ordinal()){
			ghelper.execute(helper);
			System.out.println(helper.currentWord);
		}
		/*FileReader fr = new FileReader("");

        BufferedReader br = new BufferedReader(fr);
        br.read( , 0);
*/
	}
}
