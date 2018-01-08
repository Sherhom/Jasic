package team.interpreter.jasic.main;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.EbonyHighContrastSkin;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.title.Glass3DTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import org.jvnet.substance.watermark.SubstanceMosaicWatermark;

import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.exception.FileNotTxtException;
import team.interpreter.jasic.exception.ParameterNumberNotEqualException;
import team.interpreter.jasic.main.Execute;
import team.interpreter.jasic.main.FileInput;
import team.interpreter.jasic.utils.InputHelper;
import team.interpreter.jasic.utils.ProgramHelper;

public class ClientUI extends JFrame implements ActionListener {
	/*
	 * 
	 * update 2018.1.8-16.20
	 * 
	 */
	public int index = 0;
	private final static JFrame cui = new JFrame("客户端");

	// public Jcui cui = new Jcui("客户端");

	private JLabel path_label = new JLabel("文件路径:");
	private JTextField path_field = new JTextField();
	private JButton btn_confirm = new JButton();

	private JButton btn_upload = new JButton();
	/*
	 * private JComboBox<Object> comboBox = new JComboBox<>(); private final
	 * String[] function = { "功能1", "功能2", "功能3", "功能4", "功能5" };
	 */
	private JButton btn_continue = new JButton();
	private JButton btn_start = new JButton();

	private JLabel input_label = new JLabel("输入窗口");
	private JTextArea input_area = new JTextArea();
	private JScrollPane input_jsp = new JScrollPane(input_area);

	private JLabel output_label = new JLabel("输出窗口");
	public JTextArea output_area = new JTextArea();
	private JScrollPane output_jsp = new JScrollPane(output_area);

	private FileInput fileInput = new FileInput().getFileInput();

	private JLabel logo_label = new JLabel();
	// private ImageIcon image_background = new
	// ImageIcon(getClass().getResource("/image/background.png"));
	// private ImageIcon image_confirm = new
	// ImageIcon(getClass().getResource("/image/Icon.jpg"));
	//private ImageIcon image_upload = new ImageIcon(getClass().getResource(""));
	//private ImageIcon image_start = new ImageIcon(getClass().getResource(""));

	public ClientUI() {
		set();
		setImage();
		addTocui();
		draw();
	}

	public JFrame getCUI() {
		return cui;
	}

	private void set() {

		this.btn_upload.setSize(22, 22);
		this.btn_upload.setLocation(5, 50);
		this.btn_upload.addActionListener(this);
		this.btn_upload.setIcon(new ImageIcon(getClass().getResource("/image/file1.png")));

		this.path_label.setBounds(5, 10, 60, 20);

		this.path_field.setBounds(65, 10, 130, 20);

		this.btn_confirm.setSize(22, 22);
		this.btn_confirm.setLocation(205, 10);
		this.btn_confirm.addActionListener(this);
		this.btn_confirm.setIcon(new ImageIcon(getClass().getResource("/image/confirm.png")));

		this.btn_continue.setSize(22, 22);
		this.btn_continue.setLocation(105, 50);
		this.btn_continue.addActionListener(this);
		this.btn_continue.setIcon(new ImageIcon(getClass().getResource("/image/continue.png")));

		this.btn_start.setSize(22, 22);
		this.btn_start.setLocation(205, 50);
		this.btn_start.addActionListener(this);
		this.btn_start.setIcon(new ImageIcon(getClass().getResource("/image/execute.png")));

		// this.comboBox.setSize(100, 20);
		// this.comboBox.setLocation(105, 50);

		this.input_label.setBounds(5, 90, 60, 20);

		this.output_label.setBounds(300, 10, 60, 181);

		this.input_jsp.setBounds(5, 110, 290, 350);

		this.output_area.setEditable(false);

		this.output_jsp.setBounds(300, 110, 290, 350);
		
		this.logo_label.setBounds(450, 5, 120, 98);
		this.logo_label.setIcon(new ImageIcon(getClass().getResource("/image/Jasic2.png")));

		cui.setSize(600, 500);
		cui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cui.setLayout(null);
		cui.setResizable(false);
	}

	private void setImage() {
		// this.btn_confirm.setIcon(image_confirm);
		//this.btn_upload.setIcon(image_upload);
		//this.btn_start.setIcon(image_start);
	}

	private void addTocui() {
		cui.add(path_label);
		cui.add(path_field);
		cui.add(btn_upload);
		btn_upload.setFocusPainted(false);
		btn_upload.setContentAreaFilled(false);
		btn_upload.setBorderPainted(false);
		cui.add(btn_confirm);
		btn_confirm.setFocusPainted(false);
		btn_confirm.setContentAreaFilled(false);
		btn_confirm.setBorderPainted(false);
		// this.cui.add(comboBox);
		cui.add(btn_continue);
		btn_continue.setFocusPainted(false);
		btn_continue.setContentAreaFilled(false);
		btn_continue.setBorderPainted(false);
		cui.add(btn_start);
		btn_start.setFocusPainted(false);
		btn_start.setContentAreaFilled(false);
		btn_start.setBorderPainted(false);
		cui.add(input_label);
		cui.add(output_label);
		cui.add(input_jsp);
		cui.add(output_jsp);
		cui.add(logo_label);
		
		
	}

	private void draw() {
		cui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btn_upload.equals(e.getSource())) {
			new UpLoadThread().start();
		} else if (btn_confirm.equals(e.getSource())) {
			new ConfirmThread().start();
		} else if (btn_start.equals(e.getSource())) {
			new StartThread().start();
		} else if (btn_continue.equals(e.getSource())) {
			new ContinueThread().start();
		}
	}

	 class UpLoadThread extends Thread {
	        @Override
	        public void run() {
	            input_area.setText("");
	            output_area.setText("");
	            fileInput.setFile(null);
	            ProgramHelper phelper = ProgramHelper.getHelper();
	            phelper.setNull();
	            JFileChooser jfc = new JFileChooser();
	            if (jfc.showOpenDialog(cui) == JFileChooser.APPROVE_OPTION) {
	                File file = jfc.getSelectedFile();
	                fileInput.setFile(file);
	            }
	        }
	    }

	    class ConfirmThread extends Thread {
	        @Override
	        public void run() {
	            fileInput.setFile_path(null);
	            ProgramHelper phelper = ProgramHelper.getHelper();
	            phelper.setNull();
	            String file_path = path_field.getText();
	            fileInput.setFile_path(file_path);
	            input_area.setText("");
	            output_area.setText("");
	        }
	    }

	    class StartThread extends Thread {
	        @Override
	        public void run() {
	            String programInput = input_area.getText();
	            if (programInput.equals("")) {
	            } else {
	                String str = programInput.replaceAll("\n", "\r\n");
	                fileInput.setGuiInput(str);
	            }
	            input_area.setText("");
	            output_area.setText("");
	            Store store = new Store().getStore();
	            store.init();
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
	            fileInput.setFile(null);
	            ProgramHelper phelper = ProgramHelper.getHelper();
	            phelper.setNull();
	        }
	    }
	    
	    private String getInput() {
	        return input_area.getText();
	    }
	    
	    class ContinueThread extends Thread {
	        @Override
	        public void run() {
	            Store store = new Store().getStore();
	            String programInput = getInput();
	            InputHelper inputHelper = new InputHelper();
	            try {
	                inputHelper.setInput(programInput);
	            } catch (ParameterNumberNotEqualException e) {
	                //output_area.setText(store.programOutput);
	                e.printStackTrace();
	            }
	            store.programInput = programInput;
	            store.isInput = false;
	            input_area.setText("");
	        }
	    }

	public static void initGlobalFontSetting(Font fnt) {
		FontUIResource fontRes = new FontUIResource(fnt);
		for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}
	}
	
	public static void setSubstance() {
		initGlobalFontSetting(new Font("微软雅黑", Font.PLAIN, 13));
		// 设置皮肤
		SubstanceSaharaLookAndFeel.setSkin(new CremeSkin());
		// 设置按钮
		SubstanceSaharaLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
		// 设置水印
		SubstanceLookAndFeel.setCurrentWatermark(new SubstanceMosaicWatermark());
		//SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());;
		// 设置主题
		SubstanceLookAndFeel.setCurrentTheme(new SubstanceCremeTheme());
		// 设置题头
		SubstanceLookAndFeel.setCurrentTitlePainter(new Glass3DTitlePainter());
	}
	/*
	public static void main(String[] args) {
		setSubstance();
		ClientUI cui = new ClientUI();
	}
	*/
	public JTextArea getOutput_area() {
		return output_area;
	}

}
