package test;
import junit.framework.TestCase;
import production.logic.*;

public class HasMillFormedTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidMill()
    {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 9);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 20);
            myBoard.placePawn(0, 2);
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasMillFormed(0));
        assertTrue(myBoard.hasMillFormed(1));
        assertTrue(myBoard.hasMillFormed(2));
    }
    public void testFormerMill()
    {
        try {
            myBoard.placePawn(0, 3);
            myBoard.placePawn(1, 6);

            myBoard.placePawn(0, 4);
            myBoard.placePawn(1, 16);

            myBoard.placePawn(0, 5); // formed mill
            myBoard.isValidRemoval(0,  16); // remove piece
            myBoard.placePawn(1, 11);

            myBoard.placePawn(0, 10);
            myBoard.placePawn(1, 7);

            myBoard.placePawn(0, 18); // formed mill
            myBoard.isValidRemoval(0,  6); // remove piece
            myBoard.placePawn(1, 15);

            myBoard.placePawn(0, 6);
            myBoard.placePawn(1, 16);

            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 17); // formed mill
            myBoard.isValidRemoval(1,  6); // remove piece

            myBoard.placePawn(0, 9);
            myBoard.placePawn(1, 6); // formed mill
            myBoard.isValidRemoval(1,  9); // remove piece

            myBoard.placePawn(0, 21);
            myBoard.placePawn(1, 12);

            myBoard.movePawn(0, 9, 10); // formed mill
            myBoard.isValidRemoval(0,  7); // remove piece
        } catch (Exception e) {
            fail();
        }

        // test former mill for player 1: {3, 10, 18} (not a mill)
        // NOTE: 3 is part of a mill {3, 4, 5}
        assertTrue(myBoard.hasMillFormed(3));
        assertFalse(myBoard.hasMillFormed(10));
        assertFalse(myBoard.hasMillFormed(18));

        // test new mill for player 1: {0, 9, 21} (mill)
        assertTrue(myBoard.hasMillFormed(0));
        assertTrue(myBoard.hasMillFormed(9));
        assertTrue(myBoard.hasMillFormed(21));
    }

    public void testValidMillNewMove()
    {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
        } catch (Exception e) {
            fail();
        }
        assertTrue(myBoard.hasMillFormed(0));
        assertTrue(myBoard.hasMillFormed(1));
        assertTrue(myBoard.hasMillFormed(2));
    }


    public void testInvalidMill() {
        assertFalse(myBoard.hasMillFormed(0));
    }

    public void testFormerMillNewMove()
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

        // test former mill for player 1: {3, 10, 18} (not a mill)
        // NOTE: 3 is part of a mill {3, 4, 5}
        assertTrue(myBoard.hasMillFormed(3));
        assertFalse(myBoard.hasMillFormed(10));
        assertFalse(myBoard.hasMillFormed(18));

        // test new mill for player 1: {0, 9, 21} (mill)
        assertTrue(myBoard.hasMillFormed(0));
        assertTrue(myBoard.hasMillFormed(9));
        assertTrue(myBoard.hasMillFormed(21));
    }

}

