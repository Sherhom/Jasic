package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.NullMarkException;

public class GotoHelper {
	//帮助找到所有的Mark
	public static void searchAllMarks(ProgramHelper phelper,GetNextWordHelper ghelper){
		ghelper.execute(phelper);
		if(phelper.currentType == WordType.MARK.ordinal()){
			phelper.markLoc.put(phelper.currentWord, phelper.index);
			System.out.println("有标记1！！！！");
		}
		//调到下一行 
		ghelper.jumpToNextLine(phelper);
		while(phelper.currentType != WordType.EOP.ordinal()){
			ghelper.execute(phelper);
			if(phelper.currentType == WordType.MARK.ordinal()){
				phelper.markLoc.put(phelper.currentWord, phelper.index);
				//换到下一行
				ghelper.jumpToNextLine(phelper);
				//System.out.println("有标记2！！！！");
			}
		}
		phelper.currentWord = "";
		phelper.currentKey = KeyWordType.NONE.ordinal();
		phelper.currentType = WordType.NONE.ordinal();
		phelper.index = 0;
	}
	
	public static void execute(ProgramHelper phelper,GetNextWordHelper ghelper) throws NullMarkException{
		//如果找的到则把索引改为MARK对应的位置
		ghelper.execute(phelper);
		//System.out.println(phelper.currentWord);
		if(phelper.markLoc.get(phelper.currentWord) != null){//注意，是否MAP找不到为NULL
			phelper.index = phelper.markLoc.get(phelper.currentWord);
		}else{
			//抛出异常
			throw new NullMarkException();
		}
	}
}
