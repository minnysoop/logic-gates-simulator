package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LogicGatesSimulatorUserPanel extends JPanel{

    public LogicGatesSimulatorUserPanel(LogicGatesSimulatorOutput output) {
        super(new BorderLayout());

        LogicGatesSimulatorInput inputField = new LogicGatesSimulatorInput(output);
        JLabel prompt = generatePrompt();
        JLabel instructions = generateInstructions();

        add(prompt, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
        add(instructions, BorderLayout.SOUTH);
    }

    private JLabel generatePrompt() {
        JLabel prompt = new JLabel("Enter a boolean expression in the field below.", SwingConstants.CENTER);
        prompt.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        return prompt;
    }

    private JLabel generateInstructions() {
        JLabel instruction = new JLabel(
                "Currently supports: *, +, ~"
        );
        instruction.setBorder(BorderFactory.createEmptyBorder(10, 20, 50, 20));
        return instruction;
    }

}
