import javax.swing.SwingUtilities;

public class LogicGatesSimulator {

    public static void main(String[] args) {
        // The invokeLater method schedules the GUI to run as soon as possible on the Event Dispatch Thread,
        // which is a special thread dedicated to Swing to do handle user inputs, fire event listeners,
        // and update components
        SwingUtilities.invokeLater(
                // () -> { } is known as an anonymous function and is used for
                // defining behaviour that won't be reused.
                () -> {
                    new Views.LogicGatesSimulatorWindow(1200, 700).setVisible(true);
                }
        );
    }
}
