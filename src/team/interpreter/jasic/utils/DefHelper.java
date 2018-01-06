package team.interpreter.jasic.utils;

import java.util.HashMap;
import java.util.Map;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.DefException;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;
import team.interpreter.jasic.utils.StringUtil;

public class DefHelper {
    
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws DefException {
        //System.out.println("进入def");
    	assert phelper.currentKey == KeyWordType.DEF.ordinal():"读取的不是DEF关键字";
        
        ghelper.execute(phelper);
        String funcName = "";
        String expression = "";
        while (!phelper.currentWord.equals("\n\n")) {
            //函数
            if (phelper.currentType == WordType.FUNC.ordinal()) {
                funcName = phelper.currentWord;
                while(!phelper.currentWord.equals(")")) {
                    ghelper.execute(phelper);
                }
                ghelper.execute(phelper);
                if (!phelper.currentWord.equals("=")) {
                    //throw Exception
                } else {
                    ghelper.execute(phelper);
                }
                expression = "";
                while (!phelper.currentWord.equals("\n\n")) {
                    expression += phelper.currentWord;
                    ghelper.execute(phelper);
                }
            }
        }
        //System.out.println("expression: " + expression);
        String tempString = expression.replaceAll(" ", "");
        char[] charArray = tempString.toCharArray();
        int max = StringUtil.getMax(charArray);
        String afterExp = StringUtil.getAfterString(charArray, max);
        //System.out.println("afterExp:" + afterExp);
        Store store = new Store().getStore();
        store.max = max;
        store.expString = afterExp;
        Map<String, String> funcMap = store.funcMap;
        funcMap.put(funcName, afterExp);
    }
    
}
