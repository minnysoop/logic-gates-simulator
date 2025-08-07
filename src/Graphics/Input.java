package Graphics;

import java.awt.*;

public class Input extends Node {
    private int width;
    private int height;
    private String value;

    public Input(String value) {
        this.value = value;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void draw(Graphics2D g) {
        Font arial = new Font("Arial", Font.BOLD, 14);
        g.setFont(arial);
        g.setColor(Color.BLACK);

        // I require some information about the font
        FontMetrics m = g.getFontMetrics(arial);
        this.width = m.stringWidth(value);
        // getAscent retrieves the height above the baseline
        this.height = m.getAscent();

        g.drawString(this.value, x - this.width, y + this.height/2);
    }
}
