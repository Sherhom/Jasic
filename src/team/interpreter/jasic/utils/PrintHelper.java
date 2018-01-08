package team.interpreter.jasic.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.exception.CommandTypeException;
import team.interpreter.jasic.main.ClientUI;

public class PrintHelper {
    
    /*
     * update 2018.1.8-2:15
     * */
    
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws CommandTypeException, AssignSyntaxException, CalculateSyntaxException {
        if (phelper.currentType != WordType.COMMAND.ordinal()) {
            throw new CommandTypeException();
        }
        ghelper.execute(phelper);
        Store store = new Store().getStore();
        String tempOutput = "";
        //单独print
        if (phelper.currentWord.equals("\n\n")) {
            tempOutput += "\r\n";
            store.programOutput += tempOutput;
            return;
        }
        int length = 0;
        int spaceCount = 0;
        while (!phelper.currentWord.equals("\n\n")) {
            if (phelper.currentType == WordType.STRING.ordinal()) {
                length += phelper.currentWord.length();
                tempOutput += phelper.currentWord;
                //ghelper.execute(phelper);
            } else if (phelper.currentType == WordType.NUMBER.ordinal()) {
                JumpBackWordHelper jhelper = new JumpBackWordHelper();
                jhelper.execute(phelper);
                CalculateHelper ch = new CalculateHelper();
                tempOutput += "" + ch.execute(phelper, ghelper);
                length += phelper.currentWord.length();
                //ghelper.execute(phelper);
            } else if (phelper.currentType == WordType.VARIABLE.ordinal()) {
                length += phelper.currentWord.length();
                if(phelper.numberMap.get(phelper.currentWord)!=null){
                    //System.out.print(phelper.numberMap.get(phelper.currentWord));
                    java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
                    double number = phelper.numberMap.get(phelper.currentWord);
                    if (number != 0) {
                        tempOutput += df.format(number);
                    } else {
                        tempOutput += "0.00";
                    }                    
                }else{
                    //System.out.print(phelper.stringMap.get(phelper.currentWord));
                    tempOutput += phelper.stringMap.get(phelper.currentWord);
                }
                //ghelper.execute(phelper);
            } else if (phelper.currentType == WordType.FUNC.ordinal()) {
                
            }
            if (phelper.currentWord.equals(",")) {
                spaceCount = 8 - (length % 8);
                length += spaceCount;
                while (spaceCount > 0) {
                    //System.out.print(" ");
                    tempOutput += " ";
                    spaceCount--;
                }
            } else if (phelper.currentWord.equals(";")){
                //System.out.print(" ");
                tempOutput += " ";
                length++;
            } else if (phelper.currentWord.equals("\n\n")) {
                //System.out.println();
                tempOutput += "\r\n";
            } else if (phelper.currentWord.equals("\0")) {
                break;
            }
            ghelper.execute(phelper);
        }
        ClientUI cui = new ClientUI();
        store.programOutput = store.programOutput + tempOutput + "\r\n";
        cui.output_area.setText(store.programOutput);
    }

}
