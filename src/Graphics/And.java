package Graphics;

import java.awt.*;

public class And extends Node {
    Node A, B;
    private final int RECTANGLE_LENGTH = 30;
    private final int CIRCLE_RADIUS = RECTANGLE_LENGTH/2;

    public And(Node A, Node B) {
        this.A = A;
        this.B= B;
    }

    @Override
    public int getHeight() {
        return A.getHeight() + B.getHeight() + RECTANGLE_LENGTH;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);

        // Semi-Circle: Basically drawing the full circle and only showing half of it
        int circleDiameter = 2*CIRCLE_RADIUS;
        g.fillArc(x - circleDiameter, y - CIRCLE_RADIUS, circleDiameter, circleDiameter, 90, -180);

        // Rectangle
        g.fillRect(x - CIRCLE_RADIUS - RECTANGLE_LENGTH, y - CIRCLE_RADIUS, RECTANGLE_LENGTH, RECTANGLE_LENGTH);

        // Draw input lines
        int gateLength = CIRCLE_RADIUS + RECTANGLE_LENGTH;
        int shortLineLength = 10;
        g.drawLine(x - gateLength, y + RECTANGLE_LENGTH/4, x - gateLength - shortLineLength, y + RECTANGLE_LENGTH/4);
        g.drawLine(x - gateLength, y - RECTANGLE_LENGTH/4, x - gateLength - shortLineLength, y - RECTANGLE_LENGTH/4);

        // Draw offset line for A
        int offsetB = A.getHeight();
        if (offsetB == 0) {
            A.setLayout(x - gateLength - shortLineLength, y + RECTANGLE_LENGTH/4);
            A.draw(g);
        } else {
            g.drawLine(
                    x - gateLength - shortLineLength, y + RECTANGLE_LENGTH/4,
                    x - gateLength - shortLineLength, y + RECTANGLE_LENGTH/4 + offsetB
            );
            g.drawLine(
                    x - gateLength - shortLineLength, y + RECTANGLE_LENGTH/4 + offsetB,
                    x - gateLength - shortLineLength - shortLineLength,y + RECTANGLE_LENGTH/4 + offsetB
            );
            A.setLayout(x - gateLength - shortLineLength - shortLineLength,y + RECTANGLE_LENGTH/4 + offsetB);
            A.draw(g);
        }

        // Draw offset line for B
        int offsetA = B.getHeight();
        if (offsetA == 0) {
            B.setLayout(x - gateLength - shortLineLength, y - RECTANGLE_LENGTH/4);
            B.draw(g);
        } else {
            g.drawLine(
                    x - gateLength - shortLineLength, y - RECTANGLE_LENGTH/4,
                    x - gateLength - shortLineLength, y - RECTANGLE_LENGTH/4 - offsetA
            );
            g.drawLine(
                    x - gateLength - shortLineLength, y - RECTANGLE_LENGTH/4 - offsetA,
                    x - gateLength - shortLineLength - shortLineLength,y - RECTANGLE_LENGTH/4 - offsetA
            );
            B.setLayout(x - gateLength - shortLineLength - shortLineLength,y - RECTANGLE_LENGTH/4 - offsetA);
            B.draw(g);
        }


    }

}
