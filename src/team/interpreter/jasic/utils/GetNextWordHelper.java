package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;

public class GetNextWordHelper {
	
	public void execute(ProgramHelper helper){
		char currentChar;
		//共识别7种类型：
		//NUMBER STRING VARIABLE COMMAND FUNC MARK OPERATOR
		//程序结束 和空白
		
		//初始化
		helper.getNextWordHelper();
		
		assert (helper.index>=0&&helper.index<helper.program.length):"程序的下标越界！";
		//程序结束
		if(helper.index == helper.program.length-1){
			//结束程序
			helper.currentWord = "\0";
			helper.currentType = WordType.EOP.ordinal();
			return;
		}
		//跳过所有空白
		while(helper.program[helper.index] == ' ' && helper.index < helper.program.length){
			helper.index++;
		}
		
		//调到下一行
		if(helper.program[helper.index] == '\n' || helper.program[helper.index] == '\r'){
			helper.index += 1;
			helper.currentWord = "\n";
			helper.currentType = WordType.EOL.ordinal();
		}
		
		if(helper.index == helper.program.length-1){
			helper.currentWord = "\0";
			helper.currentType = WordType.EOP.ordinal();
			return;
		}
		
		//NUMBER
		if(Character.isDigit(helper.program[helper.index]) || helper.program[helper.index] == '-'&& Character.isDigit(helper.program[helper.index+1])){
			do{
				//System.out.println("(xxx)");
				
				if(Character.isLetter(helper.program[helper.index])){
					//throw
				}
				helper.currentWord+= helper.program[helper.index];
				helper.index++;
			}while(helper.op.operator.indexOf((helper.program[helper.index])) == -1);
			helper.currentKey = KeyWordType.NUMBER.ordinal(); //余康注意！！！！！
			helper.currentType = WordType.NUMBER.ordinal();
			return;
		}
		
		//两个字符OPERATOR
		currentChar = helper.program[helper.index];
		if(helper.op.operator.indexOf(helper.program[helper.index]) != -1){
			switch(currentChar){
				case '<':
					if(helper.program[helper.index+1] == '>' || helper.program[helper.index+1] == '='){
						helper.currentWord += currentChar;
						helper.index++;
						helper.currentWord += helper.program[helper.index];
						break;
					}
				case '>':
					if(helper.program[helper.index+1] == '='){
						helper.currentWord += currentChar;
						helper.index++;
						helper.currentWord += helper.program[helper.index];
						break;
					}
				default:
					helper.currentWord += currentChar;
			}
			helper.index++;
			helper.currentType = WordType.OPERATOR.ordinal();
			return;
		}
		//单个字符OPERATOR
		if(helper.op.operator.indexOf(helper.program[helper.index]) != -1){
			helper.currentWord += helper.program[helper.index];
			helper.currentType = WordType.OPERATOR.ordinal();
			helper.index++;
		}
		
		//STRING
		currentChar = helper.program[helper.index];
		if(currentChar == '"'){
			helper.index++;
			while(helper.program[helper.index] != '"' && helper.program[helper.index] != '\n' && helper.index < helper.program.length){
				helper.currentWord += helper.program[helper.index];
				helper.index++;
			}
			if(helper.program[helper.currentWord.length()-1] != '"'){
				//throw
			}
			helper.currentType = WordType.STRING.ordinal(); //余康注意！！！！！
			helper.currentKey = KeyWordType.STRING.ordinal();
			helper.index++;//把最后的引号跳过
			return;
			//在while循环里已经加1了
		}
		
		//VARIABLE COMMAND FUNC MARK
		currentChar = helper.program[helper.index];
		if(Character.isLetter(currentChar)){
			//System.out.println(helper.op.operator.indexOf(helper.program[helper.index]));
			while(helper.op.operator.indexOf(helper.program[helper.index]) == -1){
				//System.out.println(helper.program[helper.index]);
				//因为:不属于运算符
				if(helper.program[helper.index] == ':'){
					//MARK
					helper.currentType = WordType.MARK.ordinal();
					helper.index++;
					return;
				}
				helper.currentWord += helper.program[helper.index];
				helper.index++;
				if(helper.index == 1000){
					//System.out.println("下标为1000");
				}
			}
			if(helper.program[helper.index] == '('){
				helper.currentKey = helper.getKeyWordType(helper.currentWord);
				//FUNC
				helper.currentType = WordType.FUNC.ordinal();
				return;
			}
			helper.currentKey = helper.getKeyWordType(helper.currentWord);
			//System.out.println(helper.getKeyWordType(helper.currentWord));
			//System.out.println(helper.currentWord);
			if(helper.currentKey == KeyWordType.UNCERTAIN.ordinal()){
				//VARIABLE
				helper.currentType = WordType.VARIABLE.ordinal();
			}else{
				//COMMAND
				helper.currentType = WordType.COMMAND.ordinal();
			}
		}
	}
	
	//跳到下一行
	public void jumpToNextLine(ProgramHelper helper){
		while(helper.program[helper.index] != '\n' && helper.index < helper.program.length){
			helper.index++;
		}
		if(helper.program.length == helper.index){
			helper.currentType = WordType.EOP.ordinal();
		}
		if(helper.program[helper.index] == '\n'){
			helper.currentType = WordType.EOL.ordinal();
			helper.index++;
		}
	}
	
}
