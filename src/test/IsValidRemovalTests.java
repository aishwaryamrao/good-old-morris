package test;
import junit.framework.TestCase;
import production.logic.*;

public class IsValidRemovalTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidRemoval() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 9);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 20);
            myBoard.placePawn(0, 2);
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidRemoval(0, 9));
    }


    public void testInvalidTurn() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 9);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 20);
            myBoard.placePawn(0, 2);
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidRemoval(1, 9));
    }


    public void testEmptyLocation() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 9);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 20);
            myBoard.placePawn(0, 2);
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidRemoval(0, 10));
    }


    public void testOwnPiece() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 9);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 20);
            myBoard.placePawn(0, 2);
        } catch (Exception e) {
            fail();
        }
        assertFalse(myBoard.isValidRemoval(0, 0));
    }

    public void testEveryPieceAMill() {
        try {
            myBoard.placePawn(0, 0);
            myBoard.placePawn(1, 3);
            myBoard.placePawn(0, 1);
            myBoard.placePawn(1, 10);
            myBoard.placePawn(0, 2);



        } catch (Exception e) {

            fail();
        }
        assertTrue(myBoard.everyPieceAMill(0));
        assertFalse(myBoard.everyPieceAMill(1));
    }
    public void testValidRemovalNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
        } catch (Exception e) {
            fail();
        }
        Movement currentRemoval=new Movement(0, 9, true);
        assertTrue(myBoard.isValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
    }


    public void testInvalidTurnNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
        } catch (Exception e) {
            fail();
        }
        Movement currentRemoval=new Movement(1, 9, true);
        assertFalse(myBoard.isValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
    }


    public void testEmptyLocationNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
        } catch (Exception e) {
            fail();
        }
        Movement currentRemoval=new Movement(0, 10, true);
        assertFalse(myBoard.isValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
    }


    public void testOwnPieceNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 9));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 20));
            myBoard.makeMove(new Movement(0, 2));
        } catch (Exception e) {
            fail();
        }
        Movement currentRemoval=new Movement(0, 0, true);
        assertFalse(myBoard.isValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
    }

    public void testEveryPieceAMillNewMove() {
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 3));
            myBoard.makeMove(new Movement(0, 1));
            myBoard.makeMove(new Movement(1, 10));
            myBoard.makeMove(new Movement(0, 2));



        } catch (Exception e) {

            fail();
        }
        assertTrue(myBoard.everyPieceAMill(0));
        assertFalse(myBoard.everyPieceAMill(1));
    }
}



