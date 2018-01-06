package team.interpreter.jasic.utils;


import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.IfToolException;

public class IfHelper {
	private boolean flag = false;
	private JumpBackWordHelper jumpback = new JumpBackWordHelper();
	private CalculateHelper calhelper = new CalculateHelper();
	
	public void numCompare(ProgramHelper ph, GetNextWordHelper getNext, JumpBackWordHelper jumpback) throws AssignSyntaxException, CalculateSyntaxException {
		jumpback.execute(ph);
		BoolOperationHelper boolHelper = new BoolOperationHelper();
		
		double l_num = calhelper.execute(ph, getNext);
		String s = ph.currentWord;
		double r_num = calhelper.execute(ph, getNext);
				
		flag = boolHelper.getResult(l_num, s,r_num);
		// 获取表达式的值
		//if (result != 0.0)
			//this.flag = true;
		//System.out.println(ph.currentWord);已换行
	}
	
	public void strCompare(ProgramHelper ph, GetNextWordHelper getNext, JumpBackWordHelper jumpback) throws IfToolException{
		jumpback.execute(ph);
		String var = ph.stringMap.get(ph.currentWord);
		getNext.execute(ph);
		// "abc" = "abc"
		if (ph.currentWord == "=") {
			getNext.execute(ph);
			String s = ph.currentWord;
			if (var.equals(s))
				this.flag = true;
		} else {
			//throwError("字符串比较方式出错");
			throw new IfToolException("字符串比较方式错误");
		}
	}
	
	public void readIf(ProgramHelper ph, GetNextWordHelper getNext) throws IfToolException, AssignSyntaxException, CalculateSyntaxException {
		
		getNext.execute(ph);
		
		
		// x>5 , x="abc" 这两种
		// 如果是变量
		if (ph.currentType == WordType.VARIABLE.ordinal()) {
			// 是一个英文变量 判断是String还是数字
			// IF x="abc"
			if (ph.stringMap.get(ph.currentWord) != null) {
				
				strCompare(ph,getNext,jumpback);
				
			// IF x>5
			} else if (ph.numberMap.get(ph.currentWord) != null) {
				
				numCompare(ph, getNext, jumpback);
				
			} else {
				//throwError("之前未出现这个变量");
				throw new IfToolException("变量未定义");
			}

		// 5>4
		} else if (ph.currentType == WordType.NUMBER.ordinal()) {

			numCompare(ph, getNext, jumpback);

		// 起始是符号+-等错误格式
		} else {
			//throwError("if判断条件语句出错");
			throw new IfToolException("if-then语法错误");
		}
	}

	public void execute(ProgramHelper ph, GetNextWordHelper getNext) throws IfToolException, AssignSyntaxException, CalculateSyntaxException {
		
		assert ph!=null:"传入的ProgramHelper参数为空";
		readIf(ph, getNext);
		// 读取Then
		//System.out.println(this.flag);
		
		getNext.execute(ph);
		//System.out.println(ph.currentWord);//THENflag = l_num >= r_num;
		if (ph.currentKey != KeyWordType.THEN.ordinal()) {
			//throwError("缺少Then");
			throw new IfToolException("if-then语法错误");
		}
		if (!this.flag) {
			getNext.jumpToNextLine(ph);
			//System.out.println(ph.currentWord);
		}
		// ifHelper对象在if-then过程中存在(then后面的语句即不存在了) 后面直接往下读
	}
}
