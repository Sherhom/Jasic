package team.interpreter.jasic.domain;
/**
 * 
 * @author 叶飞
 *
 */
public enum WordType {
	NONE,//无
	FUNC,//函数
	COMMAND,//命令
	VARIABLE,//变量
	OPERATOR,//操作符
	MARK,//标记
	NUMBER,//数值
	STRING,//字符串
	//结束行和程序
	EOL,
	EOP
}
