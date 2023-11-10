package production.GUI;

import production.logic.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameGUI
{
    public JFrame gameWindow = new JFrame("Good Old Morris");
    // private JFrame welcomeWindow = new JFrame("Welcome to Good Old Morris");
    private JPanel gameMenu;
    private BoardPanel gameBoard;
    List<Pawn> blackPawns = new ArrayList<Pawn>();
    List<Pawn> whitePawns = new ArrayList<Pawn>();
    private GridPoint[] gridPoints = new GridPoint[24];
    private Board currentBoard;
    private JTextArea statusText;
    JPanel playerPanel = new JPanel(new BorderLayout());
    public static final int PLAYER_PAWN = 9;
    public static final int BOARD_SIZE = 450;
    public static final int PLACE_SIZE = 20;
    public static final int PAWN_SIZE = 35;
    public static final int OFFSET = 25;
    JPanel Player1_Panel = new JPanel();
    JPanel Player2_Panel = new JPanel();
    JPanel Gridpanel = new JPanel(new GridLayout(1,3));
    public GameGUI(Board currentBoard)
    {
        this.currentBoard = currentBoard;
    }

    //Initiates the game by creating the menu and displaying the game window.
    public void beginGame()
    {
        //Creates and configures the menu panel at the top of the game window, including buttons for various game options.
        createMenu();
    }

    //Creates and configures the menu panel at the top of the game window, including buttons for various game options.
    private void createMenu()
    {
        gameMenu = new JPanel(new BorderLayout());
        // Application icon
        gameWindow.getContentPane().add(gameMenu, BorderLayout.NORTH);
        //This code attempts to set the icon image of a Swing window (gameWindow) by reading an image file named "whitepeice1.png" from a specified resource path.
        // If an IOException occurs during the image reading, it prints the exception's stack trace for debugging.
        try
        {
            gameWindow.setIconImage(ImageIO.read(getClass().getResource("/resources/images/logo.jpg")));
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
        }
        // Create a panel to hold the buttons horizontally
        // Adjust FlowLayout to LEFT
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 200, 20));

        JButton newGameButton = new JButton("New Game");
        JButton recordGameButton = new JButton("Record Game");
        JButton replayButton = new JButton("Replay");
        JButton quitButton = new JButton("Quit");

        // Add buttons to the buttonPanel
        buttonPanel.add(newGameButton);
        buttonPanel.add(recordGameButton);
        buttonPanel.add(replayButton);
        buttonPanel.add(quitButton);

        // Add the buttonPanel to the gameMenu
        gameMenu.add(buttonPanel, BorderLayout.NORTH);
        JPanel PlayerTurn_Panel = new JPanel();
        gameMenu.add(PlayerTurn_Panel);
        //This code adds an action listener to the "newGameButton" that triggers the "newGameClick()" method when the button is clicked,
        // allowing for the initiation of a new game in a Swing application.
        newGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newGameClick();
            }
        });
        recordGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                recordGameClick();
            }
        });
        replayButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    replayClick();
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        quitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                quitClick();
            }
        });
    }

    //Handles the action when the "New Game" button is clicked, prompting the user for confirmation to start a new game.
    private void newGameClick()
    {
        int confirmed = JOptionPane.showConfirmDialog(gameWindow, "Are you sure you want to start a new game?",
                "Start New Game", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION)
        {
            gameWindow.setVisible(false);
            gameWindow.dispose();
        }
    }

    //Displays information about the game and its creators when the "Record Game" button is clicked.
    private void recordGameClick()
    {
        String aboutMessage = "Created by:\nAishwarya\nHarshitha\nroja\nUma";
        JOptionPane.showMessageDialog(gameWindow, aboutMessage);

    }

    // Prompts the user to enter the filename of the game they want to replay. If the file exists, the game is replayed
    private void replayClick() throws Exception
    {

    }

    //Prompts the user to confirm that they want to quit the game. If the user confirms, the game window is closed.
    private void quitClick()
    {
        int confirmed = JOptionPane.showConfirmDialog(gameWindow, "Are you sure you want to exit the Game?",
                "Exit Program Message Box", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION)
        {
            gameWindow.dispose();
        }
    }

    public void createNewBoard()
    {
        createClickablePoints();
        BoardPanel gameBoard = new BoardPanel();
        gameBoard.setPreferredSize(new Dimension(450, 450));
        //Gridpanel.add(Player1_Panel);
        Gridpanel.add(gameBoard);
        //Gridpanel.add(Player2_Panel);
        gameWindow.add(Gridpanel);
        gameWindow.getContentPane();
        gameWindow.setVisible(true);
        createPlayersPanel();
    }

    private void clearBoard()
    {
        gameBoard.setVisible(false);
        gameWindow.getContentPane().remove(gameBoard);
    }

    private void createClickablePoints()
    {
        final int BORDER = (int) (BOARD_SIZE / 13.6); // distance from border
        final int STEP = (int) (BOARD_SIZE / 6.98); // distance between each point on 7x7 grid
        /*
         * ^ These are the ratios between the distance between dots and the board size
         */
        gridPoints[0] = new GridPoint(0, BORDER, BORDER);
        gridPoints[1] = new GridPoint(1, BORDER + 3 * STEP, BORDER);
        gridPoints[2] = new GridPoint(2, BORDER + 6 * STEP, BORDER);

        gridPoints[3] = new GridPoint(3, BORDER + STEP, BORDER + STEP);
        gridPoints[4] = new GridPoint(4, BORDER + 3 * STEP, BORDER + STEP);
        gridPoints[5] = new GridPoint(5, BORDER + 5 * STEP, BORDER + STEP);

        gridPoints[6] = new GridPoint(6, BORDER + 2 * STEP, BORDER + 2 * STEP);
        gridPoints[7] = new GridPoint(7, BORDER + 3 * STEP, BORDER + 2 * STEP);
        gridPoints[8] = new GridPoint(8, BORDER + 4 * STEP, BORDER + 2 * STEP);

        gridPoints[9] = new GridPoint(9, BORDER, BOARD_SIZE - (BORDER + 3 * STEP));
        gridPoints[10] = new GridPoint(10, BORDER + STEP, BOARD_SIZE - (BORDER + 3 * STEP));
        gridPoints[11] = new GridPoint(11, BORDER + 2 * STEP, BOARD_SIZE - (BORDER + 3 * STEP));
        gridPoints[12] = new GridPoint(12, BORDER + 4 * STEP, BOARD_SIZE - (BORDER + 3 * STEP));
        gridPoints[13] = new GridPoint(13, BORDER + 5 * STEP, BOARD_SIZE - (BORDER + 3 * STEP));
        gridPoints[14] = new GridPoint(14, BORDER + 6 * STEP, BOARD_SIZE - (BORDER + 3 * STEP));

        gridPoints[15] = new GridPoint(15, BORDER + 2 * STEP, BOARD_SIZE - (BORDER + 2 * STEP));
        gridPoints[16] = new GridPoint(16, BORDER + 3 * STEP, BOARD_SIZE - (BORDER + 2 * STEP));
        gridPoints[17] = new GridPoint(17, BORDER + 4 * STEP, BOARD_SIZE - (BORDER + 2 * STEP));

        gridPoints[18] = new GridPoint(18, BORDER + STEP, BOARD_SIZE - (BORDER + STEP));
        gridPoints[19] = new GridPoint(19, BORDER + 3 * STEP, BOARD_SIZE - (BORDER + STEP));
        gridPoints[20] = new GridPoint(20, BORDER + 5 * STEP, BOARD_SIZE - (BORDER + STEP));

        gridPoints[21] = new GridPoint(21, BORDER, BOARD_SIZE - BORDER);
        gridPoints[22] = new GridPoint(22, BORDER + 3 * STEP, BOARD_SIZE - BORDER);
        gridPoints[23] = new GridPoint(23, BORDER + 6 * STEP, BOARD_SIZE - BORDER);

    }

    //Initializes the player panel at the bottom of the game window, containing white and black pawns.
    public class PlayersPanel extends JComponent
    {
        private static final long serialVersionUID = 1L;
        private static final int SIZE = 20;
        private static final int PAWN_SIZE = 15;
        List<Pawn> playerOnePawns;
        List<Pawn> playerTwoPawns;
        //Custom component for painting player pawns on the player panel.
        PlayersPanel(List<Pawn> playerOnePawns, List<Pawn> playerTwoPawns)
        {
            setPreferredSize(new Dimension(500, 100));
            this.playerOnePawns = playerOnePawns;
            this.playerTwoPawns = playerTwoPawns;
        }

        @Override
        //Overrides the painting behavior to render player pawns and other elements on the player panel.
        public void paintComponent(Graphics graphic)
        {
            super.paintComponent(graphic);
            for (Pawn pawns : playerOnePawns)
            {
                pawns.drawPawn(graphic);
            }
            for (Pawn pawns : playerTwoPawns)
            {
                pawns.drawPawn(graphic);
            }
            Font font = graphic.getFont().deriveFont(30.0f);

            graphic.drawString(currentBoard.getPlayerOneName(), 100, 80);
            graphic.drawString(currentBoard.getPlayerTwoName(), 600, 80);
            graphic.setColor(Color.white);
            graphic.setFont(font);
            graphic.drawString(currentBoard.getPlayersNameTurn() + "'s Turn", 300, 80);

        }
    }

    //Creates a panel that displays the black and white pawns.
    private void createPlayersPanel()
    {
        // Player panel with white and black pawns
        blackPawns.clear();
        whitePawns.clear();

        int y1 = 0;
        for (int i = 1; i <= PLAYER_PAWN; i++)
        {
            Pawn pawns = new Pawn(40 + y1, 50);
            pawns.setPawnColor(Color.WHITE);
            whitePawns.add(pawns);
            pawns.setUsed(false);
            y1 += 35;
        }
        int y2 = 350;
        for (int i = 1; i <= PLAYER_PAWN; i++)
        {
            Pawn pawns = new Pawn(40 + y2, 50);
            pawns.setPawnColor(Color.BLACK);
            blackPawns.add(pawns);
            pawns.setUsed(false);
            y2 += 35;

        }
        playerPanel.setBackground(Color.PINK);
        playerPanel.add(new PlayersPanel(blackPawns, whitePawns));
        gameWindow.getContentPane().add(playerPanel, BorderLayout.SOUTH);
    }

    private void endGame(int winningPlayer, String winningPlayerName)
    {

        String winMessage = "Player " + winningPlayer + "(" + winningPlayerName + ") has won the game!!!";
        int confirmed = JOptionPane.showConfirmDialog(gameWindow, winMessage, "New Game?", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION)
        {
            statusText.setText(null);
            clearBoard();
            createNewBoard();
        }
        else
        {
            gameWindow.dispose();
        }
    }

    private class BoardPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        private Image boardImage;
        private boolean firstSetup = true;
        public static final int OFFSET = 25;
        public static final int BOARD_SIZE = 450;
        public BoardPanel()
        {
            addBoardBackground();
            addClickable();
        }
        private void addClickable()
        { // Adds mouse listener
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    GridPoint clickedPoint = getClickedPoint(e.getX(), e.getY());
                    System.out.println(clickedPoint);
                    makeMove(clickedPoint);
                    repaint();
                }
            });
        }
        private void makeMove(GridPoint clickedPoint)
        {
            int pointID = clickedPoint.getID();
            currentBoard.takeInput(pointID);
            updatePieceList();
            playerPanel.repaint();
            int end = currentBoard.isWinner();
            if (end>-1)
            {
                endGame(end, currentBoard.getPlayerName(end-1));
                statusText.append("The game is over");
            }

        }
        private GridPoint getClickedPoint(int clickX, int clickY)
        {
            //checks if clicked point in grid or null
            for (int x = 0; x < gridPoints.length; x++)
            {
                GridPoint currPoint = gridPoints[x];
                if (Math.abs(currPoint.retX() - clickX) <= OFFSET && Math.abs(currPoint.retY() - clickY) <= OFFSET)
                {
                    //OFFSET is used to allow some room for error on the click
                    return currPoint;
                }
            }
            return null; // If they did not click on the square
        }

        private void setBackgroundImage(Image boardImage)
        {
            this.boardImage = boardImage;
        }
        private void addBoardBackground()
        { // adds the background image of the board
            try
            {
                Image image = ImageIO.read(getClass().getResource("/resources/images/BlankBoardNew1.png")); // reads in image

                Image scaledImage = image.getScaledInstance(BOARD_SIZE, BOARD_SIZE, Image.SCALE_DEFAULT); // Resizes the image
                setBackgroundImage(scaledImage);
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                System.out.println("Invalid background selected");
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            // Draws the background image of the board
            super.paintComponent(g);

            g.drawImage(boardImage, 0, 0, null); // Draws image at location 0,0

            for (int x = 0; x < gridPoints.length; x++)
            {
                // Draws the current occupying piece for each of the grid points
                gridPoints[x].drawPiece(g, currentBoard);
            }

            if (firstSetup)
            {
                setGridPoints();
                firstSetup = false;
            }
        }

        private void setGridPoints()
        { // Places the gridpoints on the board
            for (int x = 0; x < gridPoints.length; x++)
            {
                GridPoint currPoint = gridPoints[x];
                this.add(currPoint);
                // Makes sure the gridpoints cover the space entirely
                currPoint.setLocation(currPoint.retX() - OFFSET, currPoint.retY() - OFFSET);
            }
        }

        private void updatePieceList()
        {
            int blackUnplayed=currentBoard.numUnplacedPawns(1);
            int whiteUnplayed=currentBoard.numUnplacedPawns(0);

            for(int x=0;x<9;x++)
            {
                Pawn currBlack=blackPawns.get(x);
                Pawn currWhite=whitePawns.get(x);

                if(!currBlack.isUsed()&&(x+1)>blackUnplayed)
                {
                    currBlack.setUsed(true);
                }
                if(!currWhite.isUsed()&&(x+1)>whiteUnplayed)
                {
                    currWhite.setUsed(true);
                }
            }
        }
    }
}













