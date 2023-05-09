import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsolePrinter implements ChangeListener {
    private final Model model;

public ConsolePrinter(Model model) {
        this.model = model;
        model.addNotifyListener(this);
    }

}
