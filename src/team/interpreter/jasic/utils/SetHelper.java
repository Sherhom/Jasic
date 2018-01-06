package team.interpreter.jasic.utils;

import java.util.Vector;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.draw.Window;
import team.interpreter.jasic.exception.ParameterNumberNotEqualException;
import team.interpreter.jasic.exception.SetWindowException;
import team.interpreter.jasic.exception.SplitSignException;
import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class SetHelper {

	//判断是否是set window开头
	public static void ifSetWindow(ProgramHelper phelper, GetNextWordHelper ghelper) throws SetWindowException {
		int[] entranceType = {KeyWordType.SET.ordinal(),KeyWordType.WINDOW.ordinal()};
        for(int i = 0;i < entranceType.length;i++) {
        	if(phelper.currentKey == entranceType[i])
        	{
        		ghelper.execute(phelper);
        	}else {
        		throw new SetWindowException("不是以 SET WINDOW开头");
        	}
        }
	}
	
	//判断是否取到的是4个参数
	public static void ifFourPara(Window window, Vector<Integer> sizeParameter) throws ParameterNumberNotEqualException {
		if (sizeParameter.size() != 4) {
            throw new ParameterNumberNotEqualException("窗口大小参数个数不相符");
        } else {
            window.left = sizeParameter.elementAt(0);
            window.right = sizeParameter.elementAt(1);
            window.buttom = sizeParameter.elementAt(2);
            window.top = sizeParameter.elementAt(3);
        }

	}
	
    // 获取窗口大小参数
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws Exception {
        // 判断是不是完整的SET WINDOW
        // ghelper.execute(phelper);
        System.out.println("进入set");
        Store store = new Store().getStore();
        Vector<Integer> sizeParameter = store.sizeParameter;
        
        //判断是否是set window开头
        ifSetWindow(phelper,ghelper);
        
        while (!phelper.currentWord.equals("\n\n")) {
            if (phelper.currentType == WordType.NUMBER.ordinal()) {
                sizeParameter.addElement(Integer.parseInt(phelper.currentWord));
            } else {
                // throw Exception
            	throw new ParameterNumberNotEqualException("参数不为数字");
            }
            ghelper.execute(phelper);
            //System.out.println("2:" + phelper.currentWord);
            if (!phelper.currentWord.equals("\n\n") && !phelper.currentWord.equals(",")) {
                // throw Exception
                throw new SplitSignException("分隔符不为','");
            } else {
                ghelper.execute(phelper);
            }
            
        }
     // 判断是否读到了四个参数
        Window window = new Window().getWindow();
        ifFourPara(window, sizeParameter);
    }
}
