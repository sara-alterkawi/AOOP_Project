import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Levels implements ChangeListener {

    private int currentLevel;
    private final Model model;       
  // Define 3D array of strings. It contains data for several levels of a game,
    // where each level is represented as a 2D array of strings.
    private final static String[][][] gameLevels = new String[][][]{
            {
                    {"f", "w", "w", "w", "w", "w"},
                    {"f", "w", ".", ".", "f", "w"},
                    {"w", "w", "w", "f", "f", "w"},
                    {"w", "f", "x", "f", "f", "w"},
                    {"w", "f", "x", "f", "w", "w"},
                    {"w", "s", "f", "f", "w", "f"},
                    {"w", "w", "w", "w", "w", "f"},
            },
            {
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "f", "f", "w", ".", "f", "f", "w", "f", "f", "f", "w", "w"},
                    {"w", "w", "w", "x", "f", "f", "f", "f", "s", "f", "f", "x", "f", "w", "w"},
                    {"w", "w", "w", ".", "f", "f", "f", "f", "f", "f", "f", "f", "w", "w", "w"},
                    {"w", "w", "w", "w", "f", "f", "f", "f", "f", "f", "f", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "f", "f", ".", "f", "x", "f", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w"}
            },
            {
                    {"f", "f", "w", "w", "w", "w", "w", "f"},
                    {"w", "w", "w", "f", "f", "f", "w", "f"},
                    {"w", ".", "s", "x", "f", "f", "w", "f"},
                    {"w", "w", "w", "f", "x", ".", "w", "f"},
                    {"w", ".", "w", "w", "x", "f", "w", "f"},
                    {"w", "f", "w", "f", ".", "f", "w", "w"},
                    {"w", "x", "f", "*", "x", "x", ".", "w"},
                    {"w", "f", "f", "f", ".", "f", "f", "w"},
                    {"w", "w", "w", "w", "w", "w", "w", "w"}
            }
    };

}
