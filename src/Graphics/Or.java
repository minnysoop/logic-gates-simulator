package Graphics;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.QuadCurve2D;

public class Or extends Node {
    Node A, B;
    private final int CRESCENT_HEIGHT = 30;
    private final int CRESCENT_LENGTH = 60;

    public Or(Node A, Node B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public int getHeight() {
        return A.getHeight() + B.getHeight() + CRESCENT_HEIGHT;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);

        // Big Oval
        g.fillArc(
                x - CRESCENT_LENGTH, y - CRESCENT_HEIGHT/2,
                CRESCENT_LENGTH, CRESCENT_HEIGHT,
                0, 360);
        // Semi-Circle
        g.setColor(Color.WHITE);
        g.fillArc(
                x - CRESCENT_LENGTH - CRESCENT_LENGTH/2, y - CRESCENT_HEIGHT/2,
                CRESCENT_LENGTH, CRESCENT_HEIGHT, 90, -180);

        // Input Wires
        int shortLineLength = 30;
        g.setColor(Color.BLACK);
        g.drawLine(
                x - CRESCENT_LENGTH/2, y + CRESCENT_HEIGHT/4,
                x - CRESCENT_LENGTH/2 - shortLineLength, y + CRESCENT_HEIGHT/4);
        g.drawLine(
                x - CRESCENT_LENGTH/2, y - CRESCENT_HEIGHT/4,
                x - CRESCENT_LENGTH/2 - shortLineLength, y - CRESCENT_HEIGHT/4);


        // Draw offset line for A
        int offsetB = A.getHeight();
        if (offsetB == 0) {
            A.setLayout(x - CRESCENT_LENGTH/2 - shortLineLength, y + CRESCENT_HEIGHT/4);
            A.draw(g);
        } else {
            g.drawLine(
                    x - CRESCENT_LENGTH/2 - shortLineLength, y + CRESCENT_HEIGHT/4,
                    x - CRESCENT_LENGTH/2 - shortLineLength, y + CRESCENT_HEIGHT/4 + offsetB
            );
            g.drawLine(
                    x - CRESCENT_LENGTH/2 - shortLineLength, y + CRESCENT_HEIGHT/4 + offsetB,
                    x - CRESCENT_LENGTH/2 - shortLineLength - shortLineLength,y + CRESCENT_HEIGHT/4 + offsetB
            );
            A.setLayout(x - CRESCENT_LENGTH/2 - shortLineLength - shortLineLength,y + CRESCENT_HEIGHT/4 + offsetB);
            A.draw(g);
        }

        // Draw offset line for B
        int offsetA = B.getHeight();
        if (offsetA == 0) {
            B.setLayout(x - CRESCENT_LENGTH/2 - shortLineLength, y - CRESCENT_HEIGHT/4);
            B.draw(g);
        } else {
            g.drawLine(
                    x - CRESCENT_LENGTH/2 - shortLineLength, y - CRESCENT_HEIGHT/4,
                    x - CRESCENT_LENGTH/2 - shortLineLength, y - CRESCENT_HEIGHT/4 - offsetA
            );
            g.drawLine(
                    x - CRESCENT_LENGTH/2 - shortLineLength, y - CRESCENT_HEIGHT/4 - offsetA,
                    x - CRESCENT_LENGTH/2 - shortLineLength - shortLineLength,y - CRESCENT_HEIGHT/4 - offsetA
            );
            B.setLayout(x - CRESCENT_LENGTH/2 - shortLineLength - shortLineLength,y - CRESCENT_HEIGHT/4 - offsetA);
            B.draw(g);
        }


    }
}
