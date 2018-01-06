package team.interpreter.jasic.draw;
import java.awt.Graphics;
/**
 * 
 * @author 游凯佳
 *
 */
public class Window {

    public int left;
    public int right;
    public int buttom;
    public int top;
    
    public Graphics G;

    private final static Window win = new Window();

    public Window getWindow() {
        return win;
    }

    public void setWindow() {
        int leftPortion = left * 100;
        int rightPortion = right * 100;
        int buttomPortion = buttom * 100;
        int topPortion = top * 100;

        this.left = -leftPortion;
        this.top = topPortion;
        this.right = rightPortion;
        this.buttom = buttomPortion;

        // 画轴和xy标注
        drawShaft(leftPortion, 0, rightPortion, 0);
        drawShaft(0, buttomPortion, 0, topPortion);
        /*
         * drawLine(leftPortion, 0, rightPortion, 0); drawShaft(0,
         * buttomPortion, 0, topPortion);
         */

        // 画X和Y标记
        drawTag("X", rightPortion - 30, -20);
        drawTag("Y", -20, topPortion - 20);
        /*
        drawString("X", rightPortion - 30, -20);
        drawString("Y", -20, topPortion - 20);
        */
        
        // 画箭头
        drawArrow1(rightPortion);
        drawArrow2(topPortion);
        /*
        for (int i = 1; i <= 10; i++) {
            draw(rightPortion - i - 6, i);
            draw(rightPortion - i - 6, -i);
        }
        for (int i = 1; i <= 10; i++) {
            draw(-i, topPortion - i);
            draw(i, topPortion - i);
        }
        */
    }

    private void drawShaft(int leftPortion, int buttomPortion, int rightPortion, int topPortion) {
        drawLine(leftPortion, buttomPortion, rightPortion, topPortion);
    }

    private void drawTag(String str, int num1, int num2) {
        drawString(str, num1, num2);
    }
    
    private void drawArrow1(int rightPortion) {
        for (int i = 1; i <= 10; i++) {
            draw(rightPortion - i - 6, i);
            draw(rightPortion - i - 6, -i);
        }
    }
    
    private void drawArrow2(int topPortion) {
        for (int i = 1; i <= 10; i++) {
            draw(-i, topPortion - i);
            draw(i, topPortion - i);
        }
    }
    
    // 画坐标系
    private void draw(int x, int y) {
        int X = new Coordinate(x, y).getRealX();
        int Y = new Coordinate(x, y).getRealY();
        G.drawLine(X, Y, X, Y);
    }

    private void drawRec(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 100 : -100;
        int dy = y1 < y2 ? 100 : -100;
        for (int i = x1; i != x2 + dx; i += dx) {
            for (int j = y1; j != y2 + dy; j += dy) {
                draw(i, j);
                // 画纵坐标标记
                if (j != 0)
                    drawString(String.valueOf(j / 100), i - 16, j);
            }
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 100 : -100;
        if (x1 == x2)
            drawRec(x1, y1, x2, y2);
        else {
            double d = (double) (y2 - y1) / (x2 - x1);
            for (int i = x1; i != x2 + dx; i += dx) {
                draw(i, (int) (y1 + (i - x1) * d));
                // 画横坐标标记
                drawString(String.valueOf(i / 100), i, (int) (y1 + (i - x1) * d) - 16);

                // drawString(String.valueOf(i - x1), i, (int) (y1 + (i - x1) *
                // d));
            }
        }
    }

    private void drawString(String s, int x, int y) {
        int X = new Coordinate(x, y).getRealX();
        int Y = new Coordinate(x, y).getRealY();
        G.drawString(s, X, Y);
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getButtom() {
        return buttom;
    }

}
