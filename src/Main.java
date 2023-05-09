import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        // Passing in the level data for level 1 obtained from the Levels.getLevelData().
        Model gameModel = new Model(Levels.getCurrentLevel(1),1);
        // "SOKOBAN" as the title of the game window.
        View gameFrame = new View(gameModel, "SOKOBAN");
        // Add a new KeyAdapter
        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                gameModel.gameInstruction(gameFrame);
            }
        });
        ConsolePrinter consolePrint = new ConsolePrinter(gameModel);
        Levels leve = new Levels(gameModel);
    }

}
