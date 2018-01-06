package team.interpreter.jasic.domain;

import java.util.HashMap;
/**
 * 
 * @author 叶飞
 *
 */
public class KeyWord {
	
	public static HashMap<String, Integer> keyWordMap;
	
	private static KeyWord keyWord;
	

	public KeyWord(){
		keyWordMap = new HashMap<String , Integer>();
		keyWordMap.put("input", KeyWordType.INPUT.ordinal());
		keyWordMap.put("print", KeyWordType.PRINT.ordinal());
		keyWordMap.put("if", KeyWordType.IF.ordinal());
		keyWordMap.put("then", KeyWordType.THEN.ordinal());
		keyWordMap.put("for", KeyWordType.FOR.ordinal());
		keyWordMap.put("to", KeyWordType.TO.ordinal());
		keyWordMap.put("next", KeyWordType.NEXT.ordinal());
		keyWordMap.put("goto", KeyWordType.GOTO.ordinal());
		keyWordMap.put("select", KeyWordType.SELECT.ordinal());
		keyWordMap.put("case", KeyWordType.CASE.ordinal());
		keyWordMap.put("do", KeyWordType.DO.ordinal());
		keyWordMap.put("while", KeyWordType.WHILE.ordinal());
		keyWordMap.put("loop", KeyWordType.LOOP.ordinal());
		keyWordMap.put("set", KeyWordType.SET.ordinal());
		keyWordMap.put("window", KeyWordType.WINDOW.ordinal());
		keyWordMap.put("plot", KeyWordType.PLOT.ordinal());
		keyWordMap.put("lines", KeyWordType.LINES.ordinal());
		keyWordMap.put("draw", KeyWordType.DRAW.ordinal());
		keyWordMap.put("def", KeyWordType.DEF.ordinal());
		keyWordMap.put("sin", KeyWordType.SIN.ordinal());
		keyWordMap.put("cos", KeyWordType.COS.ordinal());
		keyWordMap.put("tan", KeyWordType.TAN.ordinal());
		keyWordMap.put("atn", KeyWordType.ATN.ordinal());
		keyWordMap.put("log", KeyWordType.LOG.ordinal());
		keyWordMap.put("exp", KeyWordType.EXP.ordinal());
		keyWordMap.put("sqr", KeyWordType.SQR.ordinal());
		keyWordMap.put("circle", KeyWordType.CIRCLE.ordinal());
		keyWordMap.put("end", KeyWordType.END.ordinal());
		keyWordMap.put("let", KeyWordType.LET.ordinal());
		keyWordMap.put("else", KeyWordType.ELSE.ordinal());
	}
	
	public static synchronized KeyWord getKeyWord(){
		if(keyWord == null){
			keyWord = new KeyWord();
		}
		return keyWord;
	}
}
