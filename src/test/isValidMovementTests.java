package test;
import junit.framework.TestCase;
import production.logic.*;

public class isValidMovementTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception {
        super.setUp();
        myBoard = new Board();
    }
    public void testValidPlacement() {
        assertTrue(myBoard.isValidMovement(new Movement(0, 0)));
    }


    public void testInValidMovement() {
        try {
            myBoard.makeMove(new Movement(0,0));
            myBoard.makeMove(new Movement(1,1));

            myBoard.makeMove(new Movement(0, 2));
            myBoard.makeMove(new Movement(1, 9));

            myBoard.makeMove(new Movement(0, 3));
            myBoard.makeMove(new Movement(1, 5));

            myBoard.makeMove(new Movement(0, 4));
            myBoard.makeMove(new Movement(1, 10));

            myBoard.makeMove(new Movement(0, 21));
            myBoard.makeMove(new Movement(1, 22));

            myBoard.makeMove(new Movement(0, 11));
            myBoard.makeMove(new Movement(1, 6));

            myBoard.makeMove(new Movement(0, 7));
            myBoard.makeMove(new Movement(1, 8));

            myBoard.makeMove(new Movement(0, 13));
            myBoard.makeMove(new Movement(1, 14));

            myBoard.makeMove(new Movement(0, 18));
            myBoard.makeMove(new Movement(1, 15));
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidMovement(new Movement(0, 23)));
    }

    public void testInvalidPlacementNotEmpty() {
        try {
            myBoard.makeMove(new Movement(0, 0));
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidMovement(new Movement(1, 0)));
    }

    public void testInvalidTurn() {
        assertFalse(myBoard.isValidMovement(new Movement(1, 0)));
    }

    public void testInvalidLoc() {
        assertFalse(myBoard.isValidMovement(new Movement(0, 24)));
    }
}
