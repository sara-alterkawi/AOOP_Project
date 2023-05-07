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
    }

    private void loadImg() {
    }


}
