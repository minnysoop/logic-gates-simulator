package Graphics;

import java.awt.*;

public abstract class Node {
    protected int x, y;

    public void setLayout(int x, int y) {
        this.x = x;
        this.y = y;
    };

    public abstract int getHeight();
    public abstract void draw(Graphics2D g);
}
