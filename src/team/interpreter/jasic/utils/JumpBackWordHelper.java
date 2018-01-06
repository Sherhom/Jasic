package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;

public class JumpBackWordHelper {
	
	public void execute(ProgramHelper helper){
		for(int i = 0; i < helper.currentWord.length(); i++){
			helper.index--;
		}
		if(helper.currentType==WordType.STRING.ordinal()){
			helper.index-=2;
		}
	}
	
}
