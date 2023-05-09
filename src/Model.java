import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Model extends JComponent {
    private String [][] gameBoard;
    private int currentLevel;

    private final LinkedList<ChangeListener> notifyListner;

    private Point playerPosition;
    private int numCols;
    private int numRows;

    private final static int boardWidth = 32;

    private final static String wall = "w";
    private final static String freeSpace = "f";
    private final static String box = "x";
    private final static String goalPlace = ".";
    private final static String boxOnGoal = "*";
    private final static String sokoban = "s";
    private final static String sokobanOnGoal = "$";

    private BufferedImage wallImg;
    private BufferedImage freeSpaceImg;
    private BufferedImage boxImg;
    private BufferedImage goalPlaceImg;
    private BufferedImage boxOnGoalImg;
    private BufferedImage sokobanImg;
    
public Model (String[][] levelData, int levelNum){
        notifyListner = new LinkedList<>();
        loadImg(); // Load the images
        levelDataSetting(levelData,levelNum); // Initial game board.
    }
    
    public void levelDataSetting(String[][] s, int r) {
        currentLevel = r;
        numRows = s.length;
        numCols = s[0].length;
        gameBoard = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                gameBoard[i][j] = s[i][j];
            }
        }
        playerPosition = sokobanPosition();
        update();
    }


}
