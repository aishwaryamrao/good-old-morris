package test;

import junit.framework.TestCase;
import production.logic.*;


public class PlayersTurnTest extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception {
        super.setUp();
        myBoard = new Board();
    }

    public void testInitialState() {
        // want to have "move" game state and is player 1's turn
        assertTrue(myBoard.isPlayersTurn(0));
    }

    public void testOneMoveState() {
        // make a move
        try {
            myBoard.makeMove(new Movement(0, 0));
        } catch (Exception e) {
            fail();
        }
        //  is player 2's turn
        assertTrue(myBoard.isPlayersTurn(1));
    }

    public void testOneRoundState() {
        // make a move
        try {
            myBoard.makeMove(new Movement(0, 0));
            myBoard.makeMove(new Movement(1, 1));
        } catch (Exception e) {
            fail();
        }
        // is player 1's turn
        assertTrue(myBoard.isPlayersTurn(0));
    }
}
