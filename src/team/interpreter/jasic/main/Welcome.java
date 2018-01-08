package team.interpreter.jasic.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import team.interpreter.jasic.domain.ShowSlow;

public class Welcome {
	JFrame jframe = new JFrame();
	JButton btn = new JButton("go");
	public static ShowSlow LOGO = null;

	public Welcome() {
		initFrame();
	}

	// 初始化窗口
	public void initFrame() {
		jframe.setLayout(null);
		btn.setSize(78, 28);
		btn.setLocation(500, 400);
		btn.setIcon(new ImageIcon(getClass().getResource("/image/button.png")));
		btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
		jframe.add(btn);
		btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jframe.setVisible(false);
                ClientUI.setSubstance();
        		ClientUI cui = new ClientUI();
            }           
        });
		
		
		LOGO = new ShowSlow();
		LOGO.setSize(600, 500);
		LOGO.setLocation(0, 0);
		//LOGO.setImagePath("/image/Jasic.png");
		jframe.setSize(650,500);
		jframe.setTitle("欢迎使用Jasic");
		jframe.add(LOGO);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}

	public static void main(String[] args) {
		new Welcome();
	}
}
