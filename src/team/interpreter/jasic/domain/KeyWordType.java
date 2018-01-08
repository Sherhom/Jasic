package team.interpreter.jasic.domain;
/**
 * 
 * @author 叶飞
 *
 */
public enum KeyWordType {
	//打印和输入
	PRINT,
	INPUT,
	IF,//结构
	ELSE,
	THEN,
	FOR,
	TO,
	NEXT,
	GOTO,
	SELECT,
	CASE,
	END,
	DO,
	WHILE,
	LOOP,
	
	//变量类型
	UNCERTAIN,//未知变量，可能是变量名
	NUMBER,
	STRING,
	LET,
	
	//画图
	SET,
	WINDOW,
	PLOT,
	LINES,
	DRAW,
	DEF,
	
	//函数名
	SIN,
	COS,
	TAN,
	ATN,
	LOG,
	EXP,
	SQR,
	CIRCLE, //画图函
	ASIN,
	ACOS,
	FLOOR,
	CEIL,
	RINT,
	ROUND,
	TODEGREES,
	TORADIANS,
	RANDOM,
	NONE
	
}
