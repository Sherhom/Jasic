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
 
	//����֡��
	private static final int FRAME_NUMBER = 300;  
	//����ÿ֡��ʱ����
    private static final int FRAME_INTERVAL = 10;
    //��֡��
    private int frameCounter;
    
    //private static final long serialVersionUID = 1L;  
    private Image image = new ImageIcon(getClass().getResource("/image/Jasic.png")).getImage();  
    //private BufferedImage image;    
       
    
    // ʱ��  
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
            // �÷����Ὣͼ����ص��ڴ棬�Ӷ��õ�ͼ�����ϸ��Ϣ��  
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
            // ���ݵ�ǰ֡��ʾ��ǰ͸���ȵ��������  
            float alpha = (float) frameCounter / (float) FRAME_NUMBER;  
            Graphics2D g2d = (Graphics2D) g;  
            g2d.setComposite(AlphaComposite.getInstance(  
                    AlphaComposite.SRC_OVER, alpha));  
            // Renderer��Ⱦ����  
            super.paint(g2d);  
        } else {  
            // ����ǵ�һ�Σ���������ʱ��  
            frameCounter = 0;  
            timer = new Timer(FRAME_INTERVAL, this);  
            timer.start();  
        }  
    }  
 
    // �жϵ�ǰ�Ƿ����ڽ��ж���  
    private boolean isAnimating() {  
        return timer != null && timer.isRunning();  
    }  
 
    // �ر�ʱ�ӣ����³�ʼ��  
    private void closeTimer() {  
        if (isAnimating()) {  
            timer.stop();  
            frameCounter = 0;  
            timer = null;  
        }  
    }  
 
    // ����ʱ�Ӵ����¼�  
    public void actionPerformed(ActionEvent e) {  
        // ǰ��һ֡  
    	frameCounter++;  
        if (frameCounter >= FRAME_NUMBER) {
        	// ���һ֡���رն���
            closeTimer();
        } 
        else 
            // ���µ�ǰһ֡  
            repaint();  
    }  
} 

