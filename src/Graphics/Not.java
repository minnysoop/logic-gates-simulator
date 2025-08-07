package Graphics;

import java.awt.*;

public class Not extends Node {
    private final int CIRCLE_RADIUS = 6;
    private final int TRIANGLE_HEIGHT = 30;
    private final int INPUT_LINE_LENGTH = 20;
    private final int TOTAL_LENGTH = CIRCLE_RADIUS*2 + TRIANGLE_HEIGHT + INPUT_LINE_LENGTH;
    Node A;

    public Not(Node A) {
        this.A = A;
    }

    @Override
    public int getHeight() {
        return this.TRIANGLE_HEIGHT;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        // Little circle at the top
        int circleDiameter = 2*this.CIRCLE_RADIUS;
        g.fillOval(x - circleDiameter, y - this.CIRCLE_RADIUS,
                circleDiameter, circleDiameter);

        // Facing sideways triangle
        int[] xPoints = {
                x - circleDiameter,
                x - this.TRIANGLE_HEIGHT - circleDiameter,
                x - this.TRIANGLE_HEIGHT - circleDiameter
                };
        int[] yPoints = {
                y,
                y - this.TRIANGLE_HEIGHT /2,
                y + this.TRIANGLE_HEIGHT /2
                };
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw input line from A to the triangle
        A.setLayout(x - this.TOTAL_LENGTH, y);
        g.drawLine(A.x, A.y,
                A.x + this.INPUT_LINE_LENGTH, A.y);
        A.draw(g);
    }
}
