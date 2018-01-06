package team.interpreter.jasic.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.exception.FileNotTxtException;
import team.interpreter.jasic.main.Execute;
import team.interpreter.jasic.main.FileInput;

public class ClientUI implements ActionListener{

    public final static ClientUI cui = new ClientUI();
    
    private JFrame frame = new JFrame("客户端");
    
    private JLabel path_label = new JLabel("文件路径:");
    private JTextField path_field = new JTextField();
    private JButton btn_confirm = new JButton("确定");
    
    private JButton btn_upload = new JButton("上传");
    /*private JComboBox<Object> comboBox = new JComboBox<>();
    private final String[] function = { "功能1", "功能2", "功能3", "功能4", "功能5" };*/
    private JButton btn_start = new JButton("执行");
    
    private JLabel input_label = new JLabel("输入窗口:");
    private JTextArea input_area = new JTextArea();
    private JScrollPane input_jsp = new JScrollPane(input_area);
    
    private JLabel output_label = new JLabel("输出窗口:");
    public JTextArea output_area = new JTextArea();
    private JScrollPane output_jsp = new JScrollPane(output_area);
    
    private FileInput fileInput = new FileInput().getFileInput();
    
    public ClientUI() {
        /*for (int i = 0; i < function.length; i++) {
            this.comboBox.addItem(function[i]);
        }*/
    }
    
    public ClientUI getCUI() {
        return cui;
    }
    
    private void set() {
        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        
        this.btn_upload.setSize(100,20);
        this.btn_upload.setLocation(5, 50);
        this.btn_upload.addActionListener(this);
        
        this.path_label.setBounds(5, 10, 60, 20);
        
        this.path_field.setBounds(65, 10, 130, 20);
        
        this.btn_confirm.setSize(100, 20);
        this.btn_confirm.setLocation(205, 10);
        this.btn_confirm.addActionListener(this);
        
        this.btn_start.setSize(100,20);
        this.btn_start.setLocation(205, 50); 
        this.btn_start.addActionListener(this);
        
        //this.comboBox.setSize(100, 20);
        //this.comboBox.setLocation(105, 50);
        
        this.input_label.setBounds(5, 90, 60, 20);
        
        this.output_label.setBounds(310, 10, 60, 20);
        
        this.input_jsp.setBounds(5, 110, 300, 260);
        
        this.output_area.setEditable(false);
        
        this.output_jsp.setBounds(310, 30, 280, 340);
    }
    
    public void draw() {
        set();
        this.frame.add(path_label);
        this.frame.add(path_field);
        this.frame.add(btn_upload);
        this.frame.add(btn_confirm);
        //this.frame.add(comboBox);
        this.frame.add(btn_start);
        this.frame.add(input_label);
        this.frame.add(output_label);
        this.frame.add(input_jsp);
        this.frame.add(output_jsp);
    }
    
    public void setOuput(String str) {
        this.output_area.setText(str);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn_upload.equals(e.getSource())) {
            new UpLoadThread().start();
        } else if (btn_confirm.equals(e.getSource())) {
            new ConfirmThread().start();
        } else if (btn_start.equals(e.getSource())){
            new StartThread().start();
        } else if (output_area.equals(e.getSource())) {
            new newThread().start();
        }
    }
    
    class newThread extends Thread {
        @Override
        public void run() {
            System.out.println("******************************************");
        }
    }
    
    class UpLoadThread extends Thread {
        @Override
        public void run() {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                fileInput.setFile(file);;
            }
        }
    }
    
    class ConfirmThread extends Thread {
        @Override
        public void run() {
            String file_path = path_field.getText();
            fileInput.setFile_path(file_path);
        }
    }
    
    class StartThread extends Thread {
        @Override
        public void run() {
            Store store = new Store().getStore();
            try {
                fileInput.execute();
            } catch (FileNotTxtException e) {
                output_area.setText(store.programOutput);
                e.printStackTrace();
            }
            Execute execute = new Execute();
            try {
                execute.execute();
            } catch (Exception e) {
                output_area.setText(store.programOutput);
                e.printStackTrace();
            }
                output_area.setText("33");
        }
    }
    
    public static void main(String[] args) {
        ClientUI cui = new ClientUI();
        cui.draw();
    }

    public JTextArea getOutput_area() {
        return output_area;
    }
    
}
