import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        loadImg();
        levelDataSetting(levelData,levelNum);
    }

    private void levelDataSetting (String[][] levelData, int levelNum) {
        private void levelDataSetting (String[][] levelData, int levelNum) {
        currentLevel = levelNum; // Assign the current level number.
        numRows = levelData.length;
        // It is assumed that all rows in the s array have the same number of columns,
        // so we can use the length of the first row to determine the number of columns in the entire s array.
        numCols =levelData[0].length;
        gameBoard = new String[numRows][numCols]; // Initialize the game board.
        // Iterate over the array rows and columns
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                gameBoard[row][col] = levelData[row][col]; // Fill in the game board with the contents of the provided level data.
            }
        }
        playerPosition = sokobanPosition(); // Find the player's position.
        update();
    }

    private void loadImg() {
    }


}
