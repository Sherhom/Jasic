package team.interpreter.jasic.domain;

import java.awt.AlphaComposite;  
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.image.BufferedImage;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;  
 
 
/**  
 * @author zakisoft.com  
 *  
 */ 
public class ShowSlow extends JComponent implements ActionListener {  
 
	//设置帧数
	private static final int FRAME_NUMBER = 300;  
	//播放每帧的时间间隔
    private static final int FRAME_INTERVAL = 10;
    //数帧数
    private int frameCounter;
    
    //private static final long serialVersionUID = 1L;  
    private Image image = new ImageIcon(getClass().getResource("/image/Jasic.png")).getImage();  
    //private BufferedImage image;    
       
    
    // 时钟  
    private Timer timer;  
 /*
    public BufferedImage getImage() {  
        return image;  
    }  
 
    public void setImage(BufferedImage image) {  
        this.image = image;  
    }  
 */
    private int imgWidth;  
    private int imgHeight;  
 
    public int getImgWidth() {  
        return imgWidth;  
    }  
 
    public void setImgWidth(int imgWidth) {  
        this.imgWidth = imgWidth;  
    }  
 
    public int getImgHeight() {  
        return imgHeight;  
    }  
 
    public void setImgHeight(int imgHeight) {  
        this.imgHeight = imgHeight;  
    }  
 
    public ShowSlow() { 
    }  
    
    /*
    public void setImagePath(String imgPath) {    
        try {  
            // 该方法会将图像加载到内存，从而拿到图像的详细信息。  
            image = ImageIO.read(new FileInputStream(imgPath));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        setImgWidth(image.getWidth(this));  
        setImgHeight(image.getHeight(this));  
    }  
     */
    public void paintComponent(Graphics g) {  
        int x = 0;  
        int y = 0;  
        if (null == image) {  
            return;  
        }  
        g.drawImage(image, x, y, 600, 450,  
                null);
    }  
 
    public void paint(Graphics g) {  
        if (isAnimating()) {  
            // 根据当前帧显示当前透明度的内容组件  
            float alpha = (float) frameCounter / (float) FRAME_NUMBER;  
            Graphics2D g2d = (Graphics2D) g;  
            g2d.setComposite(AlphaComposite.getInstance(  
                    AlphaComposite.SRC_OVER, alpha));  
            // Renderer渲染机制  
            super.paint(g2d);  
        } else {  
            // 如果是第一次，启动动画时钟  
            frameCounter = 0;  
            timer = new Timer(FRAME_INTERVAL, this);  
            timer.start();  
        }  
    }  
 
    // 判断当前是否正在进行动画  
    private boolean isAnimating() {  
        return timer != null && timer.isRunning();  
    }  
 
    // 关闭时钟，重新初始化  
    private void closeTimer() {  
        if (isAnimating()) {  
            timer.stop();  
            frameCounter = 0;  
            timer = null;  
        }  
    }  
 
    // 动画时钟处理事件  
    public void actionPerformed(ActionEvent e) {  
        // 前进一帧  
    	frameCounter++;  
        if (frameCounter >= FRAME_NUMBER) {
        	// 最后一帧，关闭动画
            closeTimer();
        } 
        else 
            // 更新当前一帧  
            repaint();  
    }  
} 

