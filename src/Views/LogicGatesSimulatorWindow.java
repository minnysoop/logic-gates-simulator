package Views;

import javax.swing.*;
import java.awt.*;

public class LogicGatesSimulatorWindow extends JFrame{

    public LogicGatesSimulatorWindow(int width, int height) {
        setTitle("Logic Gates Simulator");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit the program when you click that x button
        setLocationRelativeTo(null); // Places the window at the center of the screen
        setResizable(false);

        LogicGatesSimulatorOutput displayPanel = new LogicGatesSimulatorOutput();
        LogicGatesSimulatorUserPanel userPanel = new LogicGatesSimulatorUserPanel(displayPanel);


        // Layout direction is from left-to-right then top-to-bottom
        JPanel container = new JPanel(new GridLayout(1, 2));
        container.add(userPanel);
        container.add(displayPanel);
        add(container);

    }

}
