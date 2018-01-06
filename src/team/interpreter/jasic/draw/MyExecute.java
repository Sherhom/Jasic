package team.interpreter.jasic.draw;
import javax.swing.*;

import team.interpreter.jasic.domain.Store;
/**
 * 
 * @author 游凯佳
 *
 */
public class MyExecute extends JFrame {

    public final static MyExecute myexecute = new MyExecute();
    
    public MyExecute getMyExecute() {
        return myexecute;
    }
    
    private void init() {
        myexecute.setTitle("MyExcute");
        Window window = new Window().getWindow();
        int left = window.left;
        int right = window.right;
        int buttom = window.buttom;
        int top = window.top;
        myexecute.setSize((right - left) * 100, (top - buttom) * 100);
        myexecute.setLocationRelativeTo(null);
        myexecute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myexecute.setVisible(true);
        myexecute.setResizable(false);
    }
    
    public void add() {
        init();
        this.add(new DrawArea());
    }

    /*public static void main(String[] args) {
        MyExecute frame = new MyExecute();
        frame.setTitle("MyExcute");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }*/
}
