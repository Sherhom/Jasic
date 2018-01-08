package team.interpreter.jasic.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.domain.WordType;
import team.interpreter.jasic.exception.CommandTypeException;
import team.interpreter.jasic.exception.FileNotTxtException;
import team.interpreter.jasic.exception.ParameterNumberNotEqualException;
import team.interpreter.jasic.exception.SeparatorNotCommaException;
import team.interpreter.jasic.main.ClientUI;

public class InputHelper {

    private static int listSize = 0;
    private static ArrayList<String> varList = new ArrayList<>();
    
    public static void execute(ProgramHelper phelper, GetNextWordHelper ghelper) throws SeparatorNotCommaException,
            CommandTypeException, ParameterNumberNotEqualException, InvocationTargetException, InterruptedException {
        if (phelper.currentType != WordType.COMMAND.ordinal()) {
            // throw Exception
            throw new CommandTypeException();
        }
        ghelper.execute(phelper);
        // 保存变量名，此处假设变量均为定义
        while (!phelper.currentWord.equals("\n\n")) {
            if (phelper.currentType == WordType.VARIABLE.ordinal()) {
                varList.add(phelper.currentWord);
                ghelper.execute(phelper);
                if (phelper.currentWord.equals("\n\n") || phelper.currentWord.equals("\0")) {
                    break;
                } else if (!phelper.currentWord.equals(",")) {
                    throw new SeparatorNotCommaException();
                } else {
                    ghelper.execute(phelper);
                }
            }
        }
        listSize = varList.size();
        String tip = "请输入" + listSize + "个变量:";
        Store store = new Store().getStore();
        store.tip = tip;
        store.programOutput = store.programOutput + tip + "\r\n";
        store.isInput = true;
        ClientUI cui = new ClientUI();
        cui.output_area.setText(store.programOutput);
        /*
         * JTextArea output_area = cui.getOutput_area(); String uiOutput =
         * output_area.getText(); String afterUIOutput = uiOutput + "\r\n" + tip
         * + "\r\n"; output_area.append(afterUIOutput);
         * System.out.println(output_area.getBounds());
         * output_area.paintImmediately(output_area.getBounds());
         */

        /*
         * Scanner input = new Scanner(System.in); String str =
         * input.nextLine(); String[] strArray = str.split(","); if
         * (strArray.length != listSize) { throw new
         * ParameterNumberNotEqualException(); } else { for (int i = 0; i <
         * listSize; i++) { String key = varList.get(i); //double value =
         * Double.parseDouble(strArray[i]); Object value = strArray[i];
         * varMap.put(key, value); } } input.close();
         */

    }

    public void setInput(String programInput) throws ParameterNumberNotEqualException {
        ProgramHelper phelper = ProgramHelper.getHelper();
        HashMap<String, Double> numberMap = phelper.numberMap;
        String[] strArray = programInput.split(",");
        if (strArray.length != listSize) {
            throw new ParameterNumberNotEqualException();
        } else {
            for (int i = 0; i < listSize; i++) {
                String key = varList.get(i);
                double value = Double.parseDouble(strArray[i]);
                numberMap.put(key, value);
            }
        }
    }

}
