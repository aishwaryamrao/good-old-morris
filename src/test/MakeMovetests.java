package test;
import junit.framework.TestCase;
import production.logic.*;

public class MakeMovetests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidMove() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 1);
        } catch (Exception e) {
            fail();
        }
    }


    public void testInvalidMove() {
        try {
            myBoard.placePawn(0, 24);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    public void testValidMoveNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 1));
        } catch (Exception e) {
            fail();
        }
    }


    public void testInvalidMoveNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 24));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}


