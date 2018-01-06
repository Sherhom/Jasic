package team.interpreter.jasic.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import team.interpreter.jasic.domain.WordType;

public class PrintHelper {

    
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) {
        /*Set<String> set = varMap.keySet();
        for (String str: set) {
            System.out.println(str + " " + varMap.get(str));
        }*/
        ghelper.execute(phelper);
        //单独print
        if (phelper.currentWord.equals("\n\n")) {
            System.out.println("");
        }
        int length = 0;
        int spaceCount = 0;
        
        assert phelper.currentWord.equals("\n\n")==false:"print语法错误";
        while (!phelper.currentWord.equals("\n\n")) {
            //字符串
            if (phelper.currentType == WordType.STRING.ordinal()) {
                length += phelper.currentWord.length();
                System.out.print(phelper.currentWord);
                ghelper.execute(phelper);
            }
            //数字
            if (phelper.currentType == WordType.NUMBER.ordinal()) {
                length += phelper.currentWord.length();
                System.out.print(phelper.currentWord);
                ghelper.execute(phelper);
            }
            //变量
            if (phelper.currentType == WordType.VARIABLE.ordinal()) {
                length += phelper.currentWord.length();
                if(phelper.numberMap.get(phelper.currentWord)!=null){
                	System.out.print(phelper.numberMap.get(phelper.currentWord));
                }else{
                System.out.print(phelper.stringMap.get(phelper.currentWord));
                }
                ghelper.execute(phelper);
            }
            //表达式
            if (phelper.currentType == WordType.OPERATOR.ordinal()) {
                
            }
            if (phelper.currentWord.equals(",")) {
                spaceCount = 8 - (length % 8);
                length += spaceCount;
                while (spaceCount > 0) {
                    System.out.print(" ");
                    spaceCount--;
                }
                ghelper.execute(phelper);
            } else if (phelper.currentWord.equals(";")){
                System.out.print(" ");
                length++;
                ghelper.execute(phelper);
            } else if (phelper.currentWord.equals("\n\n")) {
                System.out.println();
            } else {
              
            }
            if (phelper.currentWord.equals("\0")) {
                break;
            }
        }
    }



    
}
