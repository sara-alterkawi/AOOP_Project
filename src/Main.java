import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        // Passing in the level data for level 1 obtained from the Levels.getLevelData().
        Model model = new Model(Levels.getLevelData(1), 1);
        // "Sokoban" as the title of the game window.
        View frame = new View(model, "Sokoban");
        // Add a new KeyAdapter
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                model.play(frame);
            }
        });
        ConsolePrinter sysOutListener = new ConsolePrinter(model);
        Levels levelController = new Levels(model);
    }
}
