package team.interpreter.jasic.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Store {

    private final static Store store = new Store();

    public Map<String, String> funcMap = new HashMap<>();

    public Vector<Integer> sizeParameter = new Vector<Integer>();

    public String var = "";
    public int start = 0;// 循环开始
    public int end = 0;// 循环结束条件

    public int max = 0;
    public String expString = "";
    public String paraString = "";

    public String programInput = "";
    public String programOutput = "";

    public String tip = "";

    public boolean isInput = false;

    public Store getStore() {
        return store;
    }

    public void init() {
        funcMap = new HashMap<>();
        sizeParameter = new Vector<Integer>();
        sizeParameter = new Vector<Integer>();
        var = "";
        start = 0;
        end = 0;// 循环结束条件
        max = 0;
        expString = "";
        paraString = "";
        programInput = "";
        programOutput = "";
        tip = "";
        isInput = false;
    }

}
