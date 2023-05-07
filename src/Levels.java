import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Levels implements ChangeListener {
    // Define 3D array of strings. It contains data for several levels of a game,
    // where each level is represented as a 2D array of strings.
    private final static String[][][] levelsData = new String[][][]{
            {
                    {"b", "w", "w", "w", "w", "w"},
                    {"b", "w", ".", ".", "b", "w"},
                    {"w", "w", "w", "b", "b", "w"},
                    {"w", "b", "c", "b", "b", "w"},
                    {"w", "b", "c", "b", "w", "w"},
                    {"w", "p", "b", "b", "w", "b"},
                    {"w", "w", "w", "w", "w", "b"}
            },
            {
                    {"b", "b", "w", "w", "w", "w", "w", "b"},
                    {"w", "w", "w", "b", "b", "b", "w", "b"},
                    {"w", ".", "p", "c", "b", "b", "w", "b"},
                    {"w", "w", "w", "b", "c", ".", "w", "b"},
                    {"w", ".", "w", "w", "c", "b", "w", "b"},
                    {"w", "b", "w", "b", ".", "b", "w", "w"},
                    {"w", "c", "b", "*", "c", "c", ".", "w"},
                    {"w", "b", "b", "b", ".", "b", "b", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w"}
            },
            {
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b"},
                    {"w", ".", ".", "b", "b", "w", "b", "b", "b", "b", "b", "w", "w", "w"},
                    {"w", ".", ".", "b", "b", "w", "b", "c", "b", "b", "c", "b", "b", "w"},
                    {"w", ".", ".", "b", "b", "w", "c", "w", "w", "w", "w", "b", "b", "w"},
                    {"w", ".", ".", "b", "b", "b", "b", "p", "b", "w", "w", "b", "b", "w"},
                    {"w", ".", ".", "b", "b", "w", "b", "w", "b", "b", "c", "b", "w", "w"},
                    {"w", ".", ".", "b", "b", "w", "b", "b", "b", "b", "b", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "b", "w", "w", "c", "b", "c", "b", "w"},
                    {"b", "b", "w", "b", "c", "b", "b", "c", "b", "c", "b", "c", "b", "w"},
                    {"b", "b", "w", "b", "b", "b", "b", "w", "b", "b", "b", "b", "b", "w"},
                    {"b", "b", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"}
            },
            {
                    {"b", "b", "b", "b", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w", "b"},
                    {"b", "b", "b", "b", "b", "b", "b", "b", "w", "b", "b", "b", "b", "b", "p", "w", "b"},
                    {"b", "b", "b", "b", "b", "b", "b", "b", "w", "b", "c", "w", "c", "b", "w", "w", "b"},
                    {"b", "b", "b", "b", "b", "b", "b", "b", "w", "b", "c", "b", "b", "c", "w", "b", "b"},
                    {"b", "b", "b", "b", "b", "b", "b", "b", "w", "w", "c", "b", "c", "b", "w", "b", "b"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "c", "b", "w", "b", "w", "w", "w"},
                    {"w", ".", ".", ".", ".", "b", "b", "w", "w", "b", "c", "b", "b", "c", "b", "b", "w"},
                    {"w", "w", ".", ".", ".", "b", "b", "b", "b", "c", "b", "b", "c", "b", "b", "b", "w"},
                    {"w", ".", ".", ".", ".", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b"}
            },
            {
                    {"b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "w", "b", "b", ".", ".", ".", ".", "w"},
                    {"b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", ".", ".", ".", ".", "w"},
                    {"b", "b", "b", "w", "b", "b", "b", "b", "w", "b", "b", "c", "b", "c", "b", "b", "b", ".", ".", ".", ".", "w"},
                    {"b", "b", "b", "w", "b", "c", "c", "c", "w", "c", "b", "b", "c", "b", "w", "b", "b", ".", ".", ".", ".", "w"},
                    {"b", "b", "b", "w", "b", "b", "c", "b", "b", "b", "b", "b", "c", "b", "w", "b", "b", ".", ".", ".", ".", "w"},
                    {"b", "b", "b", "w", "b", "c", "c", "b", "w", "c", "b", "c", "b", "c", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "b", "b", "c", "b", "w", "b", "b", "b", "b", "b", "w", "b", "b", "b", "b", "b", "b", "b"},
                    {"w", "b", "b", "b", "w", "b", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b"},
                    {"w", "b", "b", "b", "b", "c", "b", "b", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"},
                    {"w", "b", "c", "c", "w", "c", "c", "b", "p", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"},
                    {"w", "b", "b", "b", "w", "b", "b", "b", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"}
            }
    };

    // Declare private instance variables.
    private int currentLevelIndex;
    private final Model model;

    private final SoundPlayer soundPlayer;

    public Levels(Model model) {
        currentLevelIndex = 1;
        this.model = model;
        this.model.addChangeListener(this);
        this.soundPlayer = new SoundPlayer();
        this.model.addSoundObserver(soundPlayer);
    }

    /**
     * Defines what should happen when the state of the "Model" object changes.
     * */
    @Override
    public void stateChanged(ChangeEvent event) {
        // Checks whether the player has won or lost the game.
        if (model.winState()&&currentLevelIndex<=levelsData.length) {
            // If the player has won the game and there are more levels to play
            currentLevelIndex++;
            model.setLevelData(getLevelData(currentLevelIndex), currentLevelIndex);
        } else if (model.loseState()) {
            // If the player has lost the game, the "Model" object is updated with the data for the current level.
            model.setLevelData(getLevelData(currentLevelIndex), currentLevelIndex);
        }
    }

    public static String[][] getLevelData(int i){
        // Retrieve the data for each level in the game.
        return levelsData[i-1];
    }
}
