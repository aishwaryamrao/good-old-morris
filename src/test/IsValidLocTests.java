package test;

import junit.framework.TestCase;
import production.logic.*;

public class IsValidLocTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception {
        super.setUp();
        myBoard = new Board();
    }

    public void testValidLoc() {
        assertTrue(myBoard.isValidLoc(0));
        assertTrue(myBoard.isValidLoc(23));
    }

    public void testLargerLoc() {
        assertFalse(myBoard.isValidLoc(24));
    }

    public void testNegativeLoc() {
        assertFalse(myBoard.isValidLoc(-1));
    }
}
