package team.interpreter.jasic.main;

import team.interpreter.jasic.exception.FileNotTxtException;

public class Client {

    public static void main(String[] args) {
        String path = "D:\\java_project/test.txt";
    	//String path = "D:\\java_project/dowhile.txt";
        FileInput fi  = new FileInput();
        fi.setFile_path(path);
        try {
			fi.execute();
		} catch (FileNotTxtException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Execute execute = new Execute();
        try {
            execute.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
