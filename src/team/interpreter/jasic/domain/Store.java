package team.interpreter.jasic.domain;
/**
 * 
 * @author 叶飞
 *
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Store {

    public final static Store store = new Store();
    
    public Map<String, String> funcMap = new HashMap<>();
    
    public Vector<Integer> sizeParameter = new Vector<Integer>();
    
    public String var = "";
    public int start = 0;// 循环开始
    public int end = 0;// 循环结束条件
    
    public int max = 0;
    public String expString = "";
    public String paraString = "";
    
    public String programOutput = "";
    
    public Store getStore() {
        return store;
    }
    
}
