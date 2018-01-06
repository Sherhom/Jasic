package team.interpreter.jasic.utils;

import java.util.Map;
import java.util.Set;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.ForToolException;
import team.interpreter.jasic.exception.KeyWordException;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class ForDrawHelper {

    private static String var;// 未知数x
    private static int start;// 循环开始
    private static int end;// 循环结束条件

    // 获取函数图像起点和终点
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws Exception{
        // ghelper.execute(phelper);
        // 判断FOR
        //System.out.println("进入for ");
    	assert phelper.currentKey == KeyWordType.FOR.ordinal():"当前读取的关键字不为FOR";
        ghelper.execute(phelper);
        // 判断不是关键字

        
        if (phelper.currentType != WordType.VARIABLE.ordinal()) {
        	throw new KeyWordException("变量是关键字");
        }
        var = phelper.currentWord;
        Store store = new Store().getStore();
        store.var = var;
        Map<String, String> funcMap = store.funcMap;
        Set<String> set = funcMap.keySet();
        /*
        if (!set.contains(var)) {
            //throw Exception
            System.out.println("变量未定义！");
            return;
        }*/
        // 读取等号
        ghelper.execute(phelper);
        if (!phelper.currentWord.equals("=")) {
            throw new ForToolException("FOR语法错误");
        }
        // 获取等号后数值
        ghelper.execute(phelper);
        if (phelper.currentType != WordType.NUMBER.ordinal()) {
        	throw new ForToolException("FOR语法错误");
        }
        start = Integer.valueOf(phelper.currentWord);
        // 读取TO
        ghelper.execute(phelper);
        if (phelper.currentKey != KeyWordType.TO.ordinal()) {
        	throw new ForToolException("FOR语法错误");
        }
        // 读取TO后的结束值
        ghelper.execute(phelper);
        if (phelper.currentType != WordType.NUMBER.ordinal()) {
        	throw new ForToolException("FOR语法错误");
        }
        end = Integer.valueOf(phelper.currentWord);

        // 判断起点和终点是否符合大小关系
        if (start <= end) {
            /*
             * getDrawWay(phelper, ghelper);// 获得画函数方式 // 一直读到NEXT后 while
             * (phelper.currentKey != KeyWordType.NEXT.ordinal()) { //
             * 如果读到程序结束都没有NEXT 报错 if (phelper.currentKey ==
             * WordType.EOP.ordinal()) { // 缺少NEXT return; }
             * ghelper.execute(phelper); } // NEXT后的变量x
             * ghelper.execute(phelper); if (phelper.currentWord != this.var) {
             * // ("NEXT后的 x与之前的不对应"); }
             */
            store.start = start * 100;
            store.end = end * 100;
        } else {
        	throw new ForToolException("函数定义域左值大于右值");
        }

    }

}
