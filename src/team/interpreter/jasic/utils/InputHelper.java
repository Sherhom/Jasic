package team.interpreter.jasic.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import team.interpreter.jasic.domain.WordType;

public class InputHelper {

    private static Map<String, Object> varMap;
    
    private static void init() {
        varMap = new HashMap<>();
    }
    
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) {
        if (phelper.currentType != WordType.COMMAND.ordinal()) {
            //throw Exception
            System.out.println("出错");
        } else {
            System.out.println("正确");
        }
        ghelper.execute(phelper);
        //保存变量名，此处假设变量均为定义
        ArrayList<String> varList = new ArrayList<>();
        while (!phelper.currentWord.equals("\n\n")) {
            if (phelper.currentType == WordType.VARIABLE.ordinal()) {
                System.out.println(phelper.currentWord);
                varList.add(phelper.currentWord);
                ghelper.execute(phelper);
                if (!phelper.currentWord.equals(",")) {
                    //throw Exception
                } else {
                    ghelper.execute(phelper);
                }
            }
            if (phelper.currentWord.equals("\0")) {
                break;
            }
        }
        int listSize = varList.size();
        System.out.println("请输入" + listSize + "个变量:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] strArray = str.split(",");
        if (strArray.length != listSize) {
            //throw Exception
            System.out.println("出错");
        } else {
            for (int i = 0; i < listSize; i++) {
                String key = varList.get(i);
                //double value = Double.parseDouble(strArray[i]);
                Object value = strArray[i];
                varMap.put(key, value);
            }
        }
        input.close();

    }

    public static Map<String, Object> getVarMap() {
        return varMap;
    }

    public static void setVarMap(Map<String, Object> varMap) {
        InputHelper.varMap = varMap;
    }
    
}
