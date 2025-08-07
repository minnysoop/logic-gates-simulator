package Views;

import javax.swing.*;
import java.awt.*;

import Controller.Parser;

public class LogicGatesSimulatorInput extends JPanel {
    private JTextArea input;
    private final LogicGatesSimulatorOutput output;

    public LogicGatesSimulatorInput(LogicGatesSimulatorOutput output) {
        super(new BorderLayout());
        this.output = output;
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane textAreaContainer = generateTextArea();
        JPanel submitButtonContainer = generateSubmitButton();

        add(textAreaContainer, BorderLayout.CENTER);
        add(submitButtonContainer, BorderLayout.SOUTH);
    }

    JPanel generateSubmitButton() {
        JPanel submitButtonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Generate Logic Circuit");

        // When you click on the submit button, it is processed by the controller
        submitButton.addActionListener(e -> {
            Parser parser = new Parser(getBooleanExpression(), output);
        });

        submitButtonContainer.add(submitButton);
        return submitButtonContainer;
    }

    JScrollPane generateTextArea() {
        input = new JTextArea("");
        input.setLineWrap(true);
        input.setWrapStyleWord(true);

        // Adds padding
        input.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return new JScrollPane(input,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public String getBooleanExpression() {
        return input.getText();
    }
}
