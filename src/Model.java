import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Model extends JComponent {
    // Instance variables.

    private String[][] board; // Represent the game board.
    private int currentLevel;
    // Represent the number of rows and columns in the game board.
    private int numRows;
    private int numCols;
    private final LinkedList<ChangeListener> changeListeners;
    private Point playerPosition; // Represent the current position of the player.

    private final static String wall = "w"; // “w” is a wall
    private final static String blank = "b"; // “b” is a blank

    private final static String crate = "c"; // “c” is a crate
    private final static String blankMarked = "."; // “.” is a blank marked
    private final static String crateMarked = "*"; // “*” is a crate placed on a goal
    private final static String player = "p"; // “p” is for Sokoban
    private final static String playerMarked = "+"; // “+” is for Sokoban on a goal
    
    // To store images used in the game.
    private BufferedImage blankImg;
    private BufferedImage blankMarkedImg;
    private BufferedImage crateImg;
    private BufferedImage crateMarkedImg;
    private BufferedImage wallImg;
    private BufferedImage playerImg;

    private final static int blockWidth = 32; // Represent the width of a game block.

    private final LinkedList<SoundObserver> soundObservers; // Notified when a sound is played.

    public Model(String[][] levelData, int levelNum) {
        changeListeners = new LinkedList<>();
        imgInit(); // Load the images
        setLevelData(levelData, levelNum); // Initial game board.
        soundObservers = new LinkedList<>();
    }

    public void setLevelData(String[][] levelData, int levelNum) {
        currentLevel = levelNum; // Assign the current level number.
        numRows = levelData.length;
        numCols = levelData[0].length;
        board = new String[numRows][numCols]; // Initialize the game board.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = levelData[i][j]; // Fill in the game board with the contents of the provided level data.
            }
        }
        playerPosition = findPlayerPosition(); // Find the player's position.
        update(); // Notify the change listeners.
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public String getObjectAt(Point point) {
        return board[point.x][point.y]; // Return the content at the specified `point` on the game board.
    }

    public String getObjectAt(int row, int col) {
        return board[row][col]; // Return the content at the specified row and column on the game board.
    }

    public void setObjectAt(Point point, String object) {
        board[point.x][point.y] = object; // Set the content at the specified `point` on the game board to the given `object`.
    }

    public Point findPlayerPosition() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (getObjectAt(i, j).equals(player) || getObjectAt(i, j).equals(playerMarked))
                    return new Point(i, j); // Return the position of the player on the game board.
            }
        }
        return null;
    }

    public boolean winState() {
        // Iterate over every row and column of the game board.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (getObjectAt(i, j).equals(crate))
                    return false; // If there is a crate on the game board, the game is not won yet.
            }
        }
        return true; // Indicate that the game has been won.
    }

    public boolean loseState() {
        if (winState())
            return false; // Indicate that the game has not been lost.
        // Iterate over every row and column of the game board
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = 0; j < numCols; j++) {
                // Checking for a lose state.
                // Check whether a crate (or marked crate) is in the current position,
                // and whether it can be pushed off the game board vertically by checking that there
                // is an adjacent blank space or player in both the up and down directions.
                if ((getObjectAt(i, j).equals(crate) || getObjectAt(i, j).equals(crateMarked))
                        &&
                        (getObjectAt(i - 1, j).equals(blank)
                                        || getObjectAt(i - 1, j).equals(blankMarked)
                                        || getObjectAt(i - 1, j).equals(playerMarked)
                                        || getObjectAt(i - 1, j).equals(player))
                                && (
                                getObjectAt(i + 1, j).equals(blank)
                                        || getObjectAt(i + 1, j).equals(blankMarked)
                                        || getObjectAt(i + 1, j).equals(playerMarked)
                                        || getObjectAt(i + 1, j).equals(player))
                ) {
                    return false; // Indicate that the game has not been lost.
                }
            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 1; j < numCols - 1; j++) {
                // Excluding the edges, since a crate cannot be pushed off the edge of the board.
                // Checking for a lose state horizontally.
                if (
                        (getObjectAt(i, j).equals(crate) || getObjectAt(i, j).equals(crateMarked))
                                && (
                                getObjectAt(i, j - 1).equals(blank)
                                        || getObjectAt(i, j - 1).equals(blankMarked)
                                        || getObjectAt(i, j - 1).equals(playerMarked)
                                        || getObjectAt(i, j - 1).equals(player))
                                && (
                                getObjectAt(i, j + 1).equals(blank)
                                        || getObjectAt(i, j + 1).equals(blankMarked)
                                        || getObjectAt(i, j + 1).equals(playerMarked)
                                        || getObjectAt(i, j + 1).equals(player))
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addChangeListener(ChangeListener l) {
        changeListeners.add(l);
    }

    private void update() {
        for (ChangeListener c : changeListeners) // Iterates over each ChangeListener.
            c.stateChanged(new ChangeEvent(this)); // Calls the stateChanged method of each ChangeListener.
    }

    public void play(Controller controller){
        String instruction = controller.getInstruction(); //calls the getInstruction.
        switch (instruction){ //Evaluate the value of instruction and performs different actions depending on its value.
            case "RIGHT":
                movePlayer(0,1);
                break;
            case "LEFT":
                movePlayer(0,-1);
                break;
            case "UP":
                movePlayer(-1,0);
                break;
            case "DOWN":
                movePlayer(1,0);
                break;
        }
    };

    private void movePlayer(int row, int col) {
        // Represent the position of the player's after moving to the new location specified by the row and col.
        Point position1 = new Point(playerPosition.x + row, playerPosition.y + col); // Represents the location after the player moves one step.
        Point position2 = new Point(playerPosition.x + 2 * row, playerPosition.y + 2 * col); // Represents the location after the player moves two steps.
        //  Checks if position1 is out of bounds or if it contains a wall object.
        if (position1.x < 0 || position1.y < 0 || position1.x >= numRows || position1.y >= numCols
                || getObjectAt(position1).equals(wall))
            return;
        switch (getObjectAt(position1)) { // Check what type of object is at position1 and handles the movement accordingly.
            case crate: // If position1 contains a crate.
                // Check if position2 is out of bounds or contains a wall object.
                if (position2.x < 0 || position2.y < 0 || position2.x >= numRows || position2.y >= numCols)
                    return;
                // If position2 is a blank object, the method moves the player and crate to position2.
                else if (getObjectAt(position2).equals(blank)) {
                    setObjectAt(position1, player);
                    setObjectAt(position2, crate);
                } else if (getObjectAt(position2).equals(blankMarked)) {
                    // If position2 is a blankMarked object,
                    setObjectAt(position1, player); // Move the player and crate to position2
                    setObjectAt(position2, crateMarked); // Mark the crate as crateMarked.
                } else return; //If position2 contains anything else, the method exits early.
                break;
            case crateMarked: //If position1 contains a crateMarked.
                // Check if position2 is out of bounds or contains a wall object.
                if (position2.x < 0 || position2.y < 0 || position2.x >= numRows || position2.y >= numCols)
                    return;
                else if (getObjectAt(position2).equals(blank)) {
                    // If position2 is a blank object.
                    setObjectAt(position1, playerMarked); // Move the player and crate to position2.
                    setObjectAt(position2, crate); // Mark the player's new location as crate.
                } else if (getObjectAt(position2).equals(blankMarked)) {
                    // If position2 is a blankMarked object.
                    setObjectAt(position1, playerMarked); // Move the player and crate to `position2
                    setObjectAt(position2, crateMarked); // Mark the player's new location as crateMarked.
                } else return;
                break;
            case blank:
                setObjectAt(position1, player);
                break;
            case blankMarked:
                setObjectAt(position1, playerMarked);
                break;
        }
        if (getObjectAt(playerPosition).equals(playerMarked)) {
            setObjectAt(playerPosition, blankMarked);
        } else {
            setObjectAt(playerPosition, blank);
        }
        playerPosition = position1;
        update();
        playMoveSound();
    }

    /**
     *  Initializes images used in a game
     */
    private void imgInit() {
        try {
            blankImg = ImageIO.read(new File("sokoban_src/blank.png"));
            blankMarkedImg = ImageIO.read(new File("sokoban_src/blankmarked.png"));
            crateImg = ImageIO.read(new File("sokoban_src/crate.png"));
            crateMarkedImg = ImageIO.read(new File("sokoban_src/cratemarked.png"));
            wallImg = ImageIO.read(new File("sokoban_src/wall.png"));
            playerImg = ImageIO.read(new File("sokoban_src/player.png"));
        } catch (IOException e) {
            System.out.println("No Files !!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // Iterate over every row and column of the game board
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Draw an image on the canvas accordingly.
                switch (getObjectAt(i, j)) {
                    case blank:
                        g2.drawImage(blankImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case blankMarked:
                        g2.drawImage(blankMarkedImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case player:
                        g2.drawImage(playerImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case playerMarked:
                        g2.drawImage(playerImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case crate:
                        g2.drawImage(crateImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case crateMarked:
                        g2.drawImage(crateMarkedImg, null, j * blockWidth, i * blockWidth);
                        break;
                    case wall:
                        g2.drawImage(wallImg, null, j * blockWidth, i * blockWidth);
                        break;
                }
            }
        }
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 25)); // Set the font of the graphics context style, and size 25.
        g2.drawString("Level:" + currentLevel, 10, 25 + numRows * blockWidth); // 25 + numRows * blockWidth is used to position the string at the bottom of the grid.
    }

    @Override
    public int getWidth() {
        return blockWidth * numCols + 20; //Calculate the width of the game board adding 20 pixels of padding
    }

    @Override
    public int getHeight() {
        return blockWidth * numRows + 70; // Calculate the height of the game board adding 70 pixels of padding
    }

    public void addSoundObserver(SoundObserver observer) {
        soundObservers.add(observer); // Add the SoundObserver object to a list of sound observers.
    }

    public void notifySoundObservers(String soundType) {
        for (SoundObserver observer : soundObservers) { // Iterates over the list of sound observers.
            observer.playSound(soundType); // Call the playSound() method of each SoundObserver object.
        }
    }

    private void playMoveSound() {
        notifySoundObservers("sokoban_src/move.wav");
    }
}