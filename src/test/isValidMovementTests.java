package test;
import junit.framework.TestCase;
import production.logic.*;

public class isValidMovementTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }
    public void testValidPlacement() {
        assertTrue(myBoard.isValidMovement(new Movement(0, 0)));
    }


    public void testInValidMovement()
    {
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

    public void testValidFlying()
    {
        try {
            myBoard.placePawn(0, 3);
            myBoard.placePawn(1, 6);

            myBoard.placePawn(0, 4);
            myBoard.placePawn(1, 16);

            myBoard.placePawn(0, 5); // formed mill
            myBoard.isValidRemoval(0, 16); // remove piece
            myBoard.placePawn(1, 11);

            myBoard.placePawn(0, 10);
            myBoard.placePawn(1, 7);

            myBoard.placePawn(0, 18); // formed mill
            myBoard.isValidRemoval(0, 6); // remove piece
            myBoard.placePawn(1, 15);

            myBoard.placePawn(0, 6);
            myBoard.placePawn(1, 16);

            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 17); // formed mill
            myBoard.isValidRemoval(1, 6); // remove piece

            myBoard.placePawn(0, 9);
            myBoard.placePawn(1, 6); // formed mill
            myBoard.isValidRemoval(1, 9); // remove piece

            myBoard.placePawn(0, 21);
            myBoard.placePawn(1, 12);

            myBoard.movePawn(0, 9, 10); // formed mill
            myBoard.isValidRemoval(0, 7); // remove piece
            myBoard.movePawn(1, 7, 6);

            myBoard.movePawn(0, 10, 9); // formed mill
            myBoard.isValidRemoval(0, 11); // remove piece
            myBoard.movePawn(1, 11, 15);

            myBoard.movePawn(0, 9, 10); // formed mill
            myBoard.isValidRemoval(0, 11); // remove piece
            myBoard.movePawn(1, 6, 7);

            myBoard.movePawn(0, 10, 9); // formed mill
            myBoard.isValidRemoval(0, 6); // remove piece

            // Player 2 only has 3 pieces left, attempt to fly
            myBoard.movePawn(1, 9, 16);
        } catch (Exception e) {
            fail();
        }

    }




    public void testInvalidPlacementNotEmpty()
    {
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
