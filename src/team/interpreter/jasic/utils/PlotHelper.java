package team.interpreter.jasic.utils;

import java.util.Map;
import java.util.Set;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CommandTypeException;
import team.interpreter.jasic.exception.KeyWordException;
import team.interpreter.jasic.exception.SplitSignException;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;
import team.interpreter.jasic.utils.StringUtil;

public class PlotHelper {

    // 获取画函数的方式 例如：x f(x-1)
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws Exception{
        // ghelper.execute(phelper);
        System.out.println("进入plot");
        if (phelper.currentKey != KeyWordType.PLOT.ordinal()) {
        	throw new KeyWordException("缺少PLOT");
        }
        ghelper.execute(phelper);
        if (phelper.currentKey != KeyWordType.LINES.ordinal()) {
        	throw new KeyWordException("缺少LINES");
        }
        ghelper.execute(phelper);
        /*if (!phelper.currentWord.equals(":")) {
            return;
        }*/
        //ghelper.execute(phelper);
        Store store = new Store().getStore();
        if (!store.var.equals(phelper.currentWord)) {
            //throw Exception
            throw new AssignSyntaxException("变量未定义");
        }
        ghelper.execute(phelper);
        if (!phelper.currentWord.equals(",")) {
            throw new SplitSignException("分隔符不为，");
        }
        ghelper.execute(phelper);
        if (phelper.currentType != WordType.FUNC.ordinal()) {
            // ("x后不是函数");
            throw new CommandTypeException("变量后不是函数");
        }
        ghelper.execute(phelper);
        // 获取传参形式
        getValueForm(phelper, ghelper);
    }

    private static void getValueForm(ProgramHelper phelper, GetNextWordHelper ghelper) {
        ghelper.execute(phelper);
        String expression = "";
        while (!phelper.currentWord.equals(")")) {
            expression += phelper.currentWord;
            ghelper.execute(phelper);
        }
        //System.out.println("exp:" + expression);
        String tempString = expression.replaceAll(" ", "");
        //System.out.println("exp2:" + tempString);
        char[] charArray = tempString.toCharArray();
        int max = StringUtil.getMax(charArray);
        String afterPara = StringUtil.getAfterString(charArray, max);
        Store store = new Store().getStore();
        //store.max = max;
        store.paraString = afterPara;
        System.out.println(afterPara);
    }

}
