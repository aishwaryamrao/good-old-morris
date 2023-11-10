package test;
import junit.framework.TestCase;
import production.logic.*;

public class HasValidMovesTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidWithPlacements()
    {
        assertTrue(myBoard.hasValidMoves(0));
        assertTrue(myBoard.hasValidMoves(1));
    }

    public void testValidWithMovements()
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
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasValidMoves(0));
        assertTrue(myBoard.hasValidMoves(1));

    }

    public void testNoValidMoves()
    {
        try {
            myBoard.placePawn(0, 10);
            myBoard.placePawn(1, 6);

            myBoard.placePawn(0, 4);
            myBoard.placePawn(1, 7);

            myBoard.placePawn(0, 13);
            myBoard.placePawn(1, 11);

            myBoard.placePawn(0, 19);
            myBoard.placePawn(1, 16);

            myBoard.placePawn(0, 18);
            myBoard.placePawn(1, 17);

            myBoard.placePawn(0, 5);
            myBoard.placePawn(1, 12);

            myBoard.placePawn(0, 9);
            myBoard.placePawn(1, 15); // formed mill
            myBoard.isValidRemoval(1,  9); // remove piece

            myBoard.placePawn(0, 9);
            myBoard.placePawn(1, 8); // formed mill
            myBoard.isValidRemoval(1,  9); // remove piece

            myBoard.placePawn(0, 9);
            myBoard.placePawn(1, 0);

            myBoard.movePawn(0, 3, 10); // formed mill
            myBoard.isValidRemoval(0,  0); // remove piece
            myBoard.movePawn(1, 10, 11);

            myBoard.movePawn(0, 0, 9);
            myBoard.movePawn(1, 11, 10); // formed mill
            myBoard.isValidRemoval(1, 0); // remove piece

            myBoard.movePawn(0, 10, 18); // player 2 is now boxed in with no legal moves
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasValidMoves(0));
        assertFalse(myBoard.hasValidMoves(1));
    }

    public void testValidWithMovemenstsNewMove()
    {
        try {
            myBoard.makeMove(new Movement(0, 3));
            myBoard.makeMove(new Movement(1, 6));

            myBoard.makeMove(new Movement(0, 4));
            myBoard.makeMove(new Movement(1, 16));

            myBoard.makeMove(new Movement(0, 5)); // formed mill
            myBoard.removePawn(new Movement(0,  16, true)); // remove piece
            myBoard.makeMove(new Movement(1, 11));

            myBoard.makeMove(new Movement(0, 10));
            myBoard.makeMove(new Movement(1, 7));

            myBoard.makeMove(new Movement(0, 18)); // formed mill
            myBoard.removePawn(new Movement(0,  6, true)); // remove piece
            myBoard.makeMove(new Movement(1, 15));

            myBoard.makeMove(new Movement(0, 6));
            myBoard.makeMove(new Movement(1, 16));

            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 17)); // formed mill
            myBoard.removePawn(new Movement(1,  6, true)); // remove piece

            myBoard.makeMove(new Movement(0, 9));
            myBoard.makeMove(new Movement(1, 6)); // formed mill
            myBoard.removePawn(new Movement(1,  9, true)); // remove piece

            myBoard.makeMove(new Movement(0, 21));
            myBoard.makeMove(new Movement(1, 12));

            myBoard.makeMove(new Movement(0, 9, 10)); // formed mill
            myBoard.removePawn(new Movement(0,  7, true)); // remove piece
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasValidMoves(0));
        assertTrue(myBoard.hasValidMoves(1));
    }

    public void testNoValidMovesNewMove()
    {
        try {

            myBoard.makeMove(new Movement(0, 10));
            myBoard.makeMove(new Movement(1, 6));

            myBoard.makeMove(new Movement(0, 4));
            myBoard.makeMove(new Movement(1, 7));

            myBoard.makeMove(new Movement(0, 13));
            myBoard.makeMove(new Movement(1, 11));

            myBoard.makeMove(new Movement(0, 19));
            myBoard.makeMove(new Movement(1, 16));

            myBoard.makeMove(new Movement(0, 18));
            myBoard.makeMove(new Movement(1, 17));

            myBoard.makeMove(new Movement(0, 5));
            myBoard.makeMove(new Movement(1, 12));

            myBoard.makeMove(new Movement(0, 9));
            myBoard.makeMove(new Movement(1, 15)); // formed mill
            myBoard.removePawn(new Movement(1,  9,true)); // remove piece

            myBoard.makeMove(new Movement(0, 9));
            myBoard.makeMove(new Movement(1, 8)); // formed mill
            myBoard.removePawn(new Movement(1,  9, true)); // remove piece

            myBoard.makeMove(new Movement(0, 9));
            myBoard.makeMove(new Movement(1, 0));

            myBoard.makeMove(new Movement(0, 3, 10)); // formed mill
            myBoard.removePawn(new Movement(0,  0, true)); // remove piece
            myBoard.makeMove(new Movement(1, 10, 11));

            myBoard.makeMove(new Movement(0, 0, 9));
            myBoard.makeMove(new Movement(1, 11, 10)); // formed mill
            myBoard.removePawn(new Movement(1, 0)); // remove piece

            myBoard.makeMove(new Movement(0, 10, 18)); // player 2 is now boxed in with no legal moves
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasValidMoves(0));
        assertFalse(myBoard.hasValidMoves(1));
    }

}


