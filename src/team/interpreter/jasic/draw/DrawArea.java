package team.interpreter.jasic.draw;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import team.interpreter.jasic.domain.Store;
import team.interpreter.jasic.exception.AssignSyntaxException;
import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.utils.CalculateHelper;
import team.interpreter.jasic.utils.StringUtil;
/**
 * 
 * @author 游凯佳
 *
 */
public class DrawArea extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Window win = new Window().getWindow();
        win.G = g;
        win.setWindow();
        int left = win.left;
        int right = win.right;
        int top = win.top;
        int buttom = win.buttom;
        
        // 画函数图像
        Polygon p = new Polygon();
        Store store = new Store().getStore();
        int max = store.max;
        //System.out.println("start:"+ store.start);
        //System.out.println("end:"+ store.end);
        double scaleFactor = Math.pow(100, -(max - 1));
        //System.out.println("-----scale:"+ scaleFactor);
        CalculateHelper ch = new CalculateHelper();
        for (int x = store.start; x <= store.end; x++) {
            // 二次函数加点
            String paraString = store.paraString;
            //System.out.println("paraString:" + paraString);
            String tempParaString = paraString.replaceAll("x", ""+x);
            String paraCalString = StringUtil.addComma(tempParaString);
            double para = 0.00;
            
                try {
					para = ch.excuteString(paraCalString);
				} catch (CalculateSyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
          
                // TODO Auto-generated catch block
            
            String expString = store.expString;
            //System.out.println("ExpString: " + expString);
            String tempExpString = expString.replaceAll("x", ""+(int)para);
            //System.out.println("tempExpString: " + tempExpString);
            String expCalString = StringUtil.addComma(tempExpString);
            //System.out.println("----------------------expcalString:" + expCalString);
            double result = 0.00;
            
                try {
					result = ch.excuteString(expCalString);
				} catch (CalculateSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
           
            //System.out.println("afterresult:" + scaleFactor*result);
            p.addPoint(x + left, top - (int) (scaleFactor * result));
            //p.addPoint(x + left, top - (int) (scaleFactor * (work(x))));
            /*double d = Math.sin(x * Math.PI / 180);
            d = (left + 100 * d - 100);
            // g.drawString(".", left + x, (int) d);
            // 正弦
            g.drawLine(left + x, (int) d, left + x, (int) d);*/
        }
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        /*
        // 二次函数
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);


        // 画圆
        g.drawOval(left - 50, top - 50, 100, 100);

        // 五角星画着玩的
        int[] x = { 60, 35, 110, 10, 95 };
        int[] y = { 10, 90, 40, 40, 90 };
        g.drawPolygon(x, y, 5);*/
        
    }
    /*
    double work(int x) {
        return (x * x - 10000);
    }*/
}
