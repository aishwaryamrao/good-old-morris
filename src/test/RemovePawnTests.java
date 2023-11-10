package test;
import junit.framework.TestCase;
import production.logic.*;

public class RemovePawnTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidRemoval() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
            myBoard.removePawn(new Movement(0, 9, true));
        } catch (Exception e) {
            fail();
        }
    }


    public void testInvalidRemoval() throws Exception
    {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.removePawn(new Movement(0, 9, true));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}


