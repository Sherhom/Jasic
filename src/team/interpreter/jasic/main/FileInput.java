package team.interpreter.jasic.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import team.interpreter.jasic.exception.FileNotTxtException;
import team.interpreter.jasic.utils.ExceptionObserver;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.Observer;
import team.interpreter.jasic.utils.ProgramHelper;

public class FileInput {

    public final static FileInput fileinput = new FileInput();

    private File file = null;
    private String file_path = "";

    private Observer observer = new ExceptionObserver();
    
    public FileInput getFileInput() {
        return fileinput;
    }

    private boolean isTxt(String postfix) {
        if ("txt".equals(postfix.toLowerCase())) {
            return true;
        }
        return false;
    }
    
    public void execute() throws FileNotTxtException {
        ProgramHelper phelper = ProgramHelper.getHelper();
        GetNextWordHelper ghelper = new GetNextWordHelper();
        char[] tempArray = new char[1024];
        File tempFile = null;
        if (file != null) {
            tempFile = file;
        } else if (!"".equals(file_path) && !"".equals(file_path.trim()) && file_path != null) {
            tempFile = new File(file_path);
        } else {
            //throw Exception;
        }
        String fileName = tempFile.getName();
        int index = fileName.lastIndexOf(".");
        boolean isTxt = isTxt(fileName.substring(index + 1));
        if (!isTxt) {
            observer.execute(new FileNotTxtException());
            throw new FileNotTxtException("上传文件类型不是TXT！");
        }
        FileReader fr;
        int size = 0;
        try {
            fr = new FileReader(tempFile);
            BufferedReader br = new BufferedReader(fr);
            size = br.read(tempArray, 0, 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.arraycopy(tempArray, 0, phelper.program, 0, size);
    }
    /*
    public void execute(String path) {
        ProgramHelper phelper = ProgramHelper.getHelper();
        GetNextWordHelper ghelper = new GetNextWordHelper();
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
        System.arraycopy(tempArray, 0, phelper.program, 0, size);
    }
    */
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

}
