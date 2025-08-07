package Views;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

import Graphics.*;

public class LogicGatesSimulatorOutput extends JPanel {
    private Node root;
    private String errorMessage = "";

    public LogicGatesSimulatorOutput() {
        setBackground(Color.WHITE);
    }

    // Generates and renders the circuit diagram
    public void render(String expression, String error) {
        // NOTE: The BooleanExpressionParser will convert the text into postfix notation,
        // which will be used to generate the circuit diagram
        if (error != null) {
            root = null;
            this.errorMessage = error;
        } else {
            root = buildTreeFromPostfix(expression);
        }

        removeAll();
        revalidate();
        repaint();
    }

    private Node buildTreeFromPostfix(String expr) {
        Stack<Node>s = new Stack<>();
        int n = expr.length();
        for (int i = 0; i < n; i++) {
            char c = expr.charAt(i);

            Node t = null;
            if (Character.isLetter(c)) {
                t = new Input(Character.toString(c));
            } else if (c == '~') {
                Node in = s.pop();
                t = new Not(in);
            } else if (c == '*') {
                Node right = s.pop();
                Node left = s.pop();
                t = new And(right, left);
            } else if (c == '+') {
                Node right = s.pop();
                Node left = s.pop();
                t = new Or(right, left);
            }
            s.push(t);
        }

        if (s.isEmpty()) return null;
        return s.pop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Going to draw the tree, now hehe
            // Basically, I start drawing the root from this position
            // +------------------+
            // |                  |
            // |                  |
            // |               X  |
            // |                  |
            // |                  |
            // +------------------+
            root.setLayout(getWidth() - 80, getHeight() / 2);
            root.draw(g2d);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(errorMessage);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;
            g2d.drawString(errorMessage, x, y);
        }
    }
}
