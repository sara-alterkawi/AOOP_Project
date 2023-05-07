import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame implements ChangeListener,Controller {
    // Instance variables
    private final Model gameModel;
    private String gameInstruction = null;

    public View(Model model, String gameName){
        /**
         * The constructor creates a new KeyAdapter and overrides the keyPressed method to
         * set the gameInstruction variable based on the key pressed.
         * If the right arrow key is pressed, gameInstruction is set to "RIGHT", and so on.
         */
        this.gameModel = model;
        this.gameModel.addChangeListener(this);
        add(gameModel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.VK_RIGHT: // If the right arrow key is pressed, gameInstruction is set to "RIGHT"
                        gameInstruction = "RIGHT";
                        break;
                    case KeyEvent.VK_LEFT: // If the left arrow key is pressed, gameInstruction is set to "LEFT"
                        gameInstruction = "LEFT";
                        break;
                    case KeyEvent.VK_UP:  // If the up arrow key is pressed, gameInstruction is set to "UP"
                        gameInstruction = "UP";
                        break;
                    case KeyEvent.VK_DOWN: // If the down arrow key is pressed, gameInstruction is set to "DOWN"
                        gameInstruction = "DOWN";
                        break;
                }
            }
        });

        setTitle(gameName); // Title of the View.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to EXIT_ON_CLOSE.
        setSize(gameModel.getWidth(),gameModel.getHeight()); // Set the size of the View to the width and height of gameModel.
        setVisible(true); // Sets the View to be visible.

    }

    @Override
    public void stateChanged(ChangeEvent event) {
        setSize(gameModel.getWidth(),gameModel.getHeight()); // Set the size of the View to the width and height of gameModel
        gameModel.repaint();
    }

    @Override
    public String getInstruction() {
        String currentInstruction = gameInstruction;
        gameInstruction = null;
        return currentInstruction; // Current value of gameInstruction
    }
}
