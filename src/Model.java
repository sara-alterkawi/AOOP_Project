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
/**
     * Provide a way for other objects in the program to be notified of changes to the game's state,
     * which can be useful for coordinating different parts of the program or updating the user interface.
     */
    private void update() {
        for (ChangeListener c : notifyListner) // Iterates over each ChangeListener.
            c.stateChanged(new ChangeEvent(this)); // Calls the stateChanged method of each ChangeListener.
    }

    public Point sokobanPosition() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String content = (String) getContent(row, col);
                if (content.equals(sokoban) || content.equals(sokobanOnGoal)) {
                    return new Point(row, col);
                }
            }
        }
        return null;
    }

public String getContent(int row, int col) {
        return gameBoard[row][col];// Return the content at the specified row and column on the game board.
    }

    public String getContent(Point point){
        return gameBoard[point.x][point.y]; // Return the content at the specified `point` on the game board.
    }

    public void setContent (Point point, String s){
        gameBoard[point.x][point.y] = s;// Set the content at the specified `point` on the game board to the given `object`.
    }
    
    /**
     *  Initializes images used in a game
     */
    private void loadImg() {
        try {
            wallImg = ImageIO.read(new File("sokoban_icons/wall.png"));
            freeSpaceImg = ImageIO.read(new File("sokoban_icons/freeSpace.png"));
            boxImg = ImageIO.read(new File("sokoban_icons/box.png"));
            goalPlaceImg = ImageIO.read(new File("sokoban_icons/goalPlace.png"));
            boxOnGoalImg = ImageIO.read(new File("sokoban_icons/boxOnGoal.png"));
            sokobanImg = ImageIO.read(new File("sokoban_icons/sokoban.png"));
            } catch (IOException e) {
            System.out.println("Image is not existed !!");
        }
    }
    public int getNumCols (){
        return numCols;
    }

    public int getNumRows (){
        return numRows;
    }

    public boolean winCase() {
        // Use Java 8 Streams API to flatten the 2D game board array into a 1D stream of cells.
        // Check if all cells are marked with something other than box.
        return Arrays.stream(gameBoard)
                .flatMap(Arrays::stream)
                .allMatch(cell -> !cell.equals(box));
    }
    
    public boolean loseCase() {
        if (winCase()) {
            return false;
        }
        for (int row = 1; row < numRows - 1; row++) {
            for (int col = 0; col < numCols; col++) {
                if (isBoxAt(row, col) && canPushCrateVertically(row, col)) {
                    return false;
                }
            }
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 1; col < numCols - 1; col++) {
                if (isBoxAt(row, col) && canPushCrateHorizontally(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBoxAt(int row, int col) {
        String content = getContent(row, col);
        return content.equals(box) || content.equals(boxOnGoal);
    }

    private boolean canPushCrateVertically(int row, int col) {
        String contentAbove = getContent(row - 1, col);
        String contentBelow = getContent(row + 1, col);
        return (contentAbove.equals(freeSpace)
                || contentAbove.equals(goalPlace)
                || contentAbove.equals(sokoban)
                || contentAbove.equals(sokobanOnGoal))
                && (contentBelow.equals(freeSpace)
                || contentBelow.equals(goalPlace)
                || contentBelow.equals(sokoban)
                || contentBelow.equals(sokobanOnGoal));
    }

    private boolean canPushCrateHorizontally(int row, int col) {
        String contentLeft = getContent(row, col - 1);
        String contentRight = getContent(row, col + 1);
        return (contentLeft.equals(freeSpace)
                || contentLeft.equals(goalPlace)
                || contentLeft.equals(sokoban)
                || contentLeft.equals(sokobanOnGoal))
                && (contentRight.equals(freeSpace)
                || contentRight.equals(goalPlace)
                || contentRight.equals(sokoban)
                || contentRight.equals(sokobanOnGoal));
    }


}
