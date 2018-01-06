package team.interpreter.jasic.utils;

import java.lang.reflect.Method;

import team.interpreter.jasic.domain.KeyWord;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.*;

public class FunctionHelper {
	
	/*
	TAN,
	ATN,
	LOG,
	EXP,
	SQR,
	CIRCLE
	*/
	
	//计算数学函数值
	public static double MathMethod(ProgramHelper phelper, GetNextWordHelper ghelper) throws Exception {
		String methodName = "";
		double para = 0;
		String currentMethod = phelper.currentWord;
		ghelper.execute(phelper);//(
		ghelper.execute(phelper);//参数
		if(phelper.currentType != WordType.NUMBER.ordinal()){
			throw new ParameterTypeException();
		}
		para = 0;
		para = Double.valueOf(phelper.currentWord);
		if(currentMethod.equals("sqr")) {
			if(para < 0){
				throw new ParameterMinusException();
			}
		}
		ghelper.execute(phelper);//)
		if(!")".equals(phelper.currentWord)){
			throw new ParenthesesLackException();
		}
		methodName = currentMethod + "Func";
		
		String helperName = "FunctionHelper";
        Class<?> cl = Class.forName("team.interpreter.jasic.utils." + helperName);
        Method method = cl.getMethod(methodName, double.class);
        double result = (double) method.invoke(null, para);
        return result;
		
	}
	
	//画圆
	public static void drawCircle(ProgramHelper phelper, GetNextWordHelper ghelper) throws ParameterTypeException, CommaLackException, ParenthesesLackException {
		System.out.println("进入画圆");	
		//用一位数组存放画圆函数的4个参数
			double[] para = {0,0,0,0};
			for(int i = 0;i < 4;i++) {
				ghelper.execute(phelper);//(
				if(i != 0) {
					notComma(phelper.currentWord);
				}
				ghelper.execute(phelper);//x
				if(phelper.currentType != WordType.NUMBER.ordinal()){
					throw new ParameterTypeException();
				}else{
					para[i] =  Double.valueOf(phelper.currentWord.toString());
				}
			}
			ghelper.execute(phelper);//)
			if(!")".equals(phelper.currentWord)){
				throw new ParenthesesLackException();
			}
			circleFunc(para[0],para[1],para[2],para[3]);
		}
	
	public static double execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws Exception{
		double result = 0;
		if(phelper.currentWord.equals("circle")){
			//画圆
			drawCircle(phelper,ghelper);
		}
		else{
			//计算函数值
			result = MathMethod(phelper, ghelper);
		}
		ghelper.execute(phelper);
		//System.out.println(phelper.currentWord);
		System.out.println(result);
		return result;
	}
	
	//不是逗号
	public static void notComma(String str) throws CommaLackException{
		if(!",".equals(str)){
			throw new CommaLackException();
		}
	}
	public static void circleFunc(double x, double y , double width, double height){
		int x1 = new Double(x).intValue();
		int y1 = new Double(y).intValue();
		int width1 = new Double(width).intValue();
		int height1 = new Double(height).intValue();
		DrawTool draw = new DrawTool(x1,y1,width1,height1);
	}
	public static double tanFunc(double x){
		return Math.tan(x);
	}
	public static double atanFunc(double x){
		return Math.atan(x);
	}
	public static double logFunc(double x){
		return Math.log(x);
	}
	public static double expFunc(double x){
		return Math.exp(x);
	}
	public static double sqrFunc(double x){
		return Math.sqrt(x);
	}
	
}
