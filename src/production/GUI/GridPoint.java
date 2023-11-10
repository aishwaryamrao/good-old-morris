package production.GUI;

import production.logic.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GridPoint extends JPanel
{
    private int id; //0-23
    private int x, y;
    private static final int SIZE = 20;
    private static final int PIECE_SIZE = 45;

    public GridPoint(int id, int x, int y)
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.setVisible(false);
    }

    public int retX()
    {//getter for x
        return x;
    }

    public int retY()
    {//getter for y
        return y;
    }

    public int getID()
    { //getter for id
        return id;
    }

    public void drawPiece(Graphics graphic, Board currentGame)
    {
        /* get called by game board object to draw the piece
         * in the graphics context in the correct location */

        if(!currentGame.isEmpty(id))
        {
            Image image;
            try
            {
                // reads in image
                if (currentGame.isPlayersPawn(0, id))
                {
                    graphic.setColor(Color.white);
                    //gameWindow.setIconImage(ImageIO.read(getClass().getResource("/resources/images/whitePiece.png")));
                    image = ImageIO.read(getClass().getResource("/resources/images/whitePawn.png"));
                }
                else
                {
                    graphic.setColor(Color.black);
                    image = ImageIO.read(getClass().getResource("/resources/images/blackPawn.png"));
                }
                Image scaledImage = image.getScaledInstance(PIECE_SIZE, PIECE_SIZE, Image.SCALE_DEFAULT);
                graphic.drawImage(scaledImage, (x - SIZE), (y - SIZE), null);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
