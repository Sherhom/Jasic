package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.KeyWordType;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.SwitchCaseToolException;
import team.interpreter.jasic.main.Execute;

//case"switch"
public class SelectHelper {
    private String var;
    private boolean isNum = true;
    private boolean caseFound = false;
    private CalculateHelper calhelper = new CalculateHelper();
    private static JumpBackWordHelper jumpback = new JumpBackWordHelper();

    //case:"select" : readSelect()
    public void readSelect(ProgramHelper ph, GetNextWordHelper getNext, JumpBackWordHelper jumpback) throws SwitchCaseToolException {
        getNext.execute(ph);
        if (ph.currentKey != KeyWordType.CASE.ordinal())
            throw new SwitchCaseToolException("Select-case语法错误");//throwError("缺少case");
        
        getNext.execute(ph);
        if (ph.currentType != WordType.VARIABLE.ordinal())
            //helper.currentKey == KeyWordType.UNCERTAIN.ordinal()
            throw new SwitchCaseToolException("变量不符合语法");
        this.var = ph.currentWord;

        // 是字符串
        if (ph.stringMap.get(ph.currentWord) != null) {
            this.isNum = false;
        }

        getNext.execute(ph);//换行
        getNext.execute(ph);
        if (ph.currentKey != KeyWordType.CASE.ordinal())
            throw new SwitchCaseToolException("Select-case语法错误");//throwError("缺少case");


    }
    
    // Case已读
    public void readCase(ProgramHelper ph, GetNextWordHelper getNext, JumpBackWordHelper jumpback) throws SwitchCaseToolException, CalculateSyntaxException{
        
        
            getNext.execute(ph);
            
            //不是default
            if (ph.currentKey == KeyWordType.ELSE.ordinal()){
                this.caseFound = true;
                //System.out.println("else");
                return;
            }else{
                jumpback.execute(ph);
            }
            
            //继续判断后面的case
            if (isNum) {
                double caseNum = calhelper.execute(ph, getNext);
                // 找到
                if (ph.numberMap.get(this.var) == caseNum) {
                    this.caseFound = true;
                } else {
                    toNextCase(ph, getNext);
                }
            } else {
                // 是字符串
                getNext.execute(ph);
                String s = ph.currentWord;
                if (ph.stringMap.get(this.var).equals(s)) {
                    this.caseFound = true;
                } else {
                    toNextCase(ph, getNext);
                }
            }
        
    }

    // case不符合 跳到下一个Case之前
    public void toNextCase(ProgramHelper ph, GetNextWordHelper getNext) throws SwitchCaseToolException {
        while (ph.currentKey != KeyWordType.CASE.ordinal()) {
            
            //case1，case2没有else且未找到caseFound
            if(ph.currentType == WordType.EOP.ordinal()){
                throw new SwitchCaseToolException("Select-case语法错误");
            }
            getNext.execute(ph);
        }
        // Case读完
    }

    // 找到case 成功直接跳转到结尾
    public void toSwitchEnd(ProgramHelper ph, GetNextWordHelper getNext) throws SwitchCaseToolException{
        while (ph.currentKey != KeyWordType.END.ordinal()) {

            // 如果读到程序结束都没有NEXT 报错
            if (ph.currentType == WordType.EOP.ordinal())
                throw new SwitchCaseToolException("Select-case语法错误");//throwError("缺少end select");

            getNext.execute(ph);
        }
        
        // 读SELECT
        getNext.execute(ph);
        if (ph.currentKey != KeyWordType.SELECT.ordinal())
            throw new SwitchCaseToolException("Select-case语法错误");//throwError("end缺少 select");

    }
    
//  //读到"END" 
//  case"END" : getNext.execute(ph);
//      //end select
//      if (ph.currentKey == KeyWordType.SELECT.ordinal()){
//                  
//              }else{
//                  //程序结束
//                  break;
//              }
    
    public void execute(ProgramHelper ph,GetNextWordHelper getNext) throws Exception{
        ph.selectBegin = true;
        Execute exec = new Execute();
        readSelect(ph, getNext,jumpback);
        while(!caseFound){
             readCase(ph,getNext,jumpback);
        }
        //System.out.println(ph.currentKey);//NONE
        assert caseFound == true:"未找到符合的case";
        do{
            exec.executeonce(ph,getNext);
            if (ph.currentType == WordType.EOP.ordinal())
                throw new SwitchCaseToolException("Select-case语法错误");
        }while(ph.currentKey != KeyWordType.CASE.ordinal() && ph.currentKey != KeyWordType.END.ordinal());
        //System.out.println(ph.currentKey);//NONE
        toSwitchEnd(ph,getNext);
        ph.selectBegin = false;
    }

}
