import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class View {
  private final Model model;
  private String dataSaver = null;
  
  public View(Model model, String name) {
        this.model = model;
        model.addNotifyListener(this);
        add(model);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    dataSaver = "RIGHT";
                } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    dataSaver = "LEFT";
                } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                    dataSaver = "UP";
                } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    dataSaver = "DOWN";
                }
            }
        });

        setTitle(name); // Title of the View.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to EXIT_ON_CLOSE.
        setSize(model.getWidth(),model.getHeight()); // Set the size of the View to the width and height of gameModel.
        setVisible(true); // Sets the View to be visible.
    }
}
