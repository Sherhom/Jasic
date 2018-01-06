package team.interpreter.jasic.utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTool extends JFrame{
	MyPanel mp = null ;
	
	public DrawTool(int x, int y, int width,int height)
	 {  
	      mp = new MyPanel(x,y,width,height);     
	      this.add(mp);  
	      this.setSize(400,300);  
	      this.setVisible(true);  
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	 }  
}

class MyPanel extends JPanel{
	static int W = 800, H = 600;
	int x, y,width,height;
    //覆盖JPanel的paint方法  
    //Graphics是绘图的重要类，可以理解成一支画笔 
	public MyPanel(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
    public void paint(Graphics g)  
    {   
        super.paint(g);     //这句话不能少  
        //画圆  
        g.drawOval(this.x,this.y,this.width,this.height);   
        g.setColor(Color.BLUE);     //设置颜色  
          
    }  
}