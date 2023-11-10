package test;
import junit.framework.TestCase;
import production.logic.*;

public class AdjacentGridPointTests extends TestCase
{
    private Board myBoard;

    protected void setUp() throws Exception
    {
        super.setUp();
        myBoard = new Board();
    }

    public void testInitialState()
    {
        assertTrue(myBoard.areAdjacent(0, 1));
        assertFalse(myBoard.areAdjacent(0, 5));
    }

}
