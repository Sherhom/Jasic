package team.interpreter.jasic.utils;

import java.util.ArrayList;

public class StringUtil {

    public static String getNumber(int i, char[] charArray) {
        String str = "";
        while (Character.isDigit(charArray[i])) {
            str += charArray[i];
            if (i < charArray.length - 1) {
                i++;
            } else {
                break;
            }
        }
        return str;
    }
    
    public static int getMax(char[] charArray) {
        int max = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'x' && i < charArray.length - 1){
                i++;
                if (charArray[i] == '^') {
                    i++;
                    String numString = getNumber(i, charArray);
                    int temp = Integer.parseInt(numString);
                    if (temp > max) {
                        max = temp;
                    }
                    int length = numString.length();
                    i += length;
                } else {
                    if (max == 0) {
                        max = 1;
                    }
                }
            } else if (charArray[i] == 'x' && charArray.length == 1) {
                max = 1;
            }
        }
        return max;
    }
    
    public static String getAfterString(char[] charArray, int max) {
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'x' && i < charArray.length - 1) {
                stringList.add("" + charArray[i]);
                i++;
                if (charArray[i] == '^') {
                    stringList.add("" + charArray[i]);
                    i++;
                    String numString = getNumber(i, charArray);
                    int t = Integer.parseInt(numString);
                    if (t < max) {
                        stringList.add(numString + "*1");
                        int dif = max - t;
                        for (int j = 0; j < dif * 2; j++) {
                            stringList.add("0");
                        }
                    } else {
                        stringList.add("" + t);
                    }
                    i += numString.length() - 1;
                } else {
                    stringList.add("*1");
                    int dif = max - 1;
                    for (int j = 0; j < dif * 2; j++) {
                        stringList.add("0");
                    }
                    stringList.add("" + charArray[i]);
                }
            } else if (Character.isDigit(charArray[i])) {
                String numString = getNumber(i, charArray);
                stringList.add(numString);
                i += numString.length() - 1;
                if (i < charArray.length-1 && (charArray[i+1] == '+' || charArray[i+1] == '/')) {
                    //stringList.add("*1");
                    for (int j = 0; j < max * 2; j++) {
                        stringList.add("0");
                    }
                } else if (i == charArray.length - 1) {
                    for (int j = 0; j < max * 2; j++) {
                        stringList.add("0");
                    }
                } else {
                	;
                }
            } else if (charArray[i] == 'x' && i == charArray.length - 1) {
            	stringList.add(""+charArray[i]);
            	stringList.add("*1");
            	int dif = max - 1;
                for (int j = 0; j < dif * 2; j++) {
                    stringList.add("0");
                }
            } else {
                stringList.add("" + charArray[i]);
            }
        }
        String afterString = "";
        for (String str: stringList) {
            if (!str.equals(" ")) {
                afterString += str;
            }            
        }
        return afterString;
    }
    
    public static String addComma(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '-') {
                if (i != 0) {
                    if (Character.isDigit(str.charAt(i-1))) {
                        result += ",";
                    }
                }
                result += "-";
            } else if (ch == 'x') {
                result += "x";
            } else if (Character.isDigit(ch)) {
                result += ch;
            } else {
                result += ",";
                result += ch;
            }
        }
        result += ",";
        return result;
    }
    
    public static void main(String[] args) {
        String str = "x^2-6*x+3+x";
        String str2 = str.replaceAll(" ", "");
        char[] charArray = str2.toCharArray();
        int max = getMax(charArray);
        System.out.println(max);
        System.out.println(getAfterString(charArray, max));
        System.out.println(addComma(getAfterString(charArray, max)));
    }
    
}
