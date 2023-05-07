import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsolePrinter implements ChangeListener {
    // Declare a private instance variable.

    private final Model model;

/**
 *  This is the constructor for the ConsolePrinter class.
 */
    public ConsolePrinter(Model model) {
        this.model = model;
        this.model.addChangeListener(this);
    }

    /**
     * This line is an annotation that indicates that the following method is meant
     * to override a method from the parent class or interface.
     * In this case, the method "stateChanged" from the "ChangeListener" interface is being overridden.
     * The method takes a "ChangeEvent" object as a parameter.
     * */
    @Override
    public void stateChanged(ChangeEvent event) {
        // Iterate through the rows of the "model" object.
        for (int i = 0; i < model.getNumRows(); i++) {
            for (int j = 0; j < model.getNumCols(); j++) {
                System.out.print(model.getObjectAt(i, j)); // Print the value object to the console.
            }
            System.out.println();
        }
    }
}
