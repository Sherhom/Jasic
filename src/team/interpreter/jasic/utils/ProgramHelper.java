package team.interpreter.jasic.utils;

import java.util.HashMap;
import java.util.Stack;

import team.interpreter.jasic.domain.KeyWord;
import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.Operator;
import team.interpreter.jasic.domain.WordType;

public class ProgramHelper {
	
	private static ProgramHelper helper;

	public final int maxLength = 1000;
	public char[] program;
	public int index;
	public String currentWord;
	public int currentKey;
	public int currentType;
	public HashMap<String,Integer> markLoc;
	public Operator op;
	public KeyWord keyWord;
	//存储数值型变量的值
	public HashMap<String,Double> numberMap=new HashMap<String,Double>();
	//存储字符串型变量的值
	public HashMap<String,String> stringMap=new HashMap<String,String>();
	//存储do-while循环对象
	public Stack<DoWhileHelper> whileStack = new Stack<DoWhileHelper>();
	//存储for-next循环对象
	public Stack<ForHelper> forStack = new Stack<ForHelper>();
	public boolean forDraw;
	public boolean selectBegin = false;
	
	public ProgramHelper(){
		currentWord = "";
		currentKey = KeyWordType.NONE.ordinal();
		currentType = WordType.NONE.ordinal();
		program = new char[maxLength];
		index = 0;
		markLoc = new HashMap<String,Integer>();
		op = Operator.getOperator();
		keyWord = KeyWord.getKeyWord();
		forDraw = false;
	}
	
	public static synchronized ProgramHelper getHelper(){
		if(helper == null){
			helper = new ProgramHelper();
		}
		return helper;
	}
	
	public void getNextWordHelper(){
		currentWord = "";
		currentKey = KeyWordType.NONE.ordinal();
		currentType = WordType.NONE.ordinal();
	}
	
	public int getKeyWordType(String word){
		//System.out.println(KeyWord.getKeyWord().keyWordMap.get(word));
		if(KeyWord.keyWordMap.get(word) != null){
			return KeyWord.keyWordMap.get(word);
		}
		return KeyWordType.UNCERTAIN.ordinal();
	}
	
	
}
