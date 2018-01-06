package team.interpreter.jasic.main;

import java.lang.reflect.Method;
import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;

import team.interpreter.jasic.utils.GetNextWordHelper;
import team.interpreter.jasic.utils.GotoHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class Execute{

    
    public void execute() throws Exception{
        ProgramHelper phelper = ProgramHelper.getHelper();
        GetNextWordHelper ghelper = new GetNextWordHelper();
        GotoHelper.searchAllMarks(phelper, ghelper);
        //System.out.println(phelper.markLoc.toString());
        while (phelper.currentType != WordType.EOP.ordinal()) {
        	executeonce(phelper,ghelper);
        }
    }
    
    //调用helper方法
    public void executeHelper(ProgramHelper phelper,GetNextWordHelper ghelper, String helperName) throws Exception {
    	Class<?> cl = Class.forName("team.interpreter.jasic.utils." + helperName);
        Method method = cl.getMethod("execute", phelper.getClass(), ghelper.getClass());
        String[] helperArr = {"SelectHelper","ForHelper","DoWhileHelper","IfHelper"};
        boolean isInclude = false;
        for(int i = 0;i < helperArr.length;i++) {
        	if(helperName.equals(helperArr[i])) {
        		isInclude = true;
        		break;
        	}
        }
        if (isInclude){
        	//System.out.println(helperName);
        	method.invoke(cl.newInstance(), phelper, ghelper);
        }else{
        	//System.out.println(helperName);
        	method.invoke(null, phelper, ghelper);
        }
    }
    
    //判断除了画图外的操作
    public String excludeCircle(ProgramHelper phelper,GetNextWordHelper ghelper, String helperName) {
    	if(phelper.currentType == WordType.MARK.ordinal()){
    		ghelper.jumpToNextLine(phelper);
    	}else if(phelper.currentType == WordType.FUNC.ordinal()){
    		helperName = "FunctionHelper";
    	}else if(phelper.currentWord.equals("let") || phelper.currentType == WordType.VARIABLE.ordinal()){
    		helperName = "AssignmentHelper";
    	}else if(phelper.currentWord.equals("do")){
    		helperName = "DoWhileHelper";
    	}else if(phelper.currentKey == KeyWordType.CASE.ordinal() ){
    		helperName = "";
    	}
    	return helperName;
    }
    
    public void executeonce( ProgramHelper phelper,GetNextWordHelper ghelper) throws Exception{
    	ghelper.execute(phelper);
        int[] typeArr = new int[]{WordType.COMMAND.ordinal(),WordType.MARK.ordinal(),
        				 WordType.FUNC.ordinal(),WordType.VARIABLE.ordinal()};
        boolean isInclude = false;
        for(int i = 0;i < typeArr.length;i++) {
        	if(phelper.currentType == typeArr[i]) {
        		isInclude = true;
        		break;
        	}
        }
        if (isInclude) {
        	//System.out.println(phelper.index);
            String currentWord = phelper.currentWord;
            
            //画图中的for
            if(currentWord.equals("def")){
            	phelper.forDraw = true;
            }
            
            String tempString = currentWord.toLowerCase();
            int num = tempString.charAt(0);
            char c = (char)(num - 32);
            String helperName = c + tempString.substring(1)+ "Helper";
            //System.out.println(helperName);
            //画图中的for
            if(phelper.forDraw && phelper.currentWord.equals("for")){
            	helperName = "ForDrawHelper";
            }
            
            //判断除了画图外的操作
        	String tempHelperName = excludeCircle(phelper,ghelper,helperName);
        	
        	if(phelper.selectBegin&&phelper.currentKey == KeyWordType.END.ordinal()){
        		return;
        	}
        	//System.out.println(helperName);
        	if (!tempHelperName.equals("")) {
        		executeHelper(phelper,ghelper,tempHelperName);
        	}
            
        }
    }

    
    
}
