package production.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;



public class Pawn extends JPanel
{

    private int x, y;
    private boolean used;
    private static final int SIZE = 20;
    private static final int PAWN_SIZE = 35;
    private Color PawnColor;

    public Pawn(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Color getPawnColor() {
        return PawnColor;
    }

    public void setPawnColor(Color PawnColor) {
        this.PawnColor = PawnColor;
    }

    public void drawPawn(Graphics g)
    {

        if (!used)
        {

            g.setColor(PawnColor);
            Image image;
            try
            {
                if (PawnColor == Color.white)
                {
                    image = ImageIO.read(getClass().getResource("/resources/images/whitePawn.png"));
                }
                else
                {
                    image = ImageIO.read(getClass().getResource("/resources/images/blackPawn.png"));
                }
                Image scaledImage = image.getScaledInstance(PAWN_SIZE, PAWN_SIZE, Image.SCALE_DEFAULT);
                g.drawImage(scaledImage, (x - PAWN_SIZE), (y - PAWN_SIZE), null);
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // reads in image
        }
    }
}



