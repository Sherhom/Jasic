package team.interpreter.jasic.draw;
/**
 * 
 * @author 游凯佳
 *
 */
public class Coordinate {
    int x, y;
    Window win = new Window().getWindow();
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRealX() {
        return win.right + x;
    }

    public int getRealY() {
        return win.top - y;
    }
}