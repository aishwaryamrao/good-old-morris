package production.logic;

import java.util.Arrays;
import java.util.HashMap;

public class Board
{
    private Integer[] GridLoc = new Integer[24];
    private Integer playerTurn = 0;
    private String playerOneName="";
    private String playerTwoName="";
    private String dispStatus="";

    private HashMap<Integer, Integer[]> adj = new HashMap<Integer, Integer[]>();
    private int prevClick=0;
    private boolean moveInProgress;
    private GamePhase gamePhase;

    private Integer[] unplacedPawns = new Integer[] {9, 9};
    private Integer[] livePawns = new Integer[] {9, 9};

    // all possible mills in an immutable array
    private static final Integer[][] allMills = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {9, 10, 11},
            {12, 13, 14},
            {15, 16, 17},
            {18, 19, 20},
            {21, 22, 23},
            {0, 9, 21},
            {3, 10, 18},
            {6, 11, 15},
            {1, 4, 7},
            {16, 19, 22},
            {8, 12, 17},
            {5, 13, 20},
            {2, 14, 23}
    };

    private void createAdjList() {
        // mark all clickable spots on the board as 0
        Arrays.fill(GridLoc, 0, 24, 0);

        // adjacent points for each spot from 0 to 23 on board
        adj.put(0, new Integer[] {1, 9});
        adj.put(1, new Integer[] {0, 2, 4});
        adj.put(2, new Integer[] {1, 14});
        adj.put(3, new Integer[] {4, 10});
        adj.put(4, new Integer[] {1, 3, 5, 7});
        adj.put(5, new Integer[] {4, 13});
        adj.put(6, new Integer[] {7, 11});
        adj.put(7, new Integer[] {4, 6, 8});
        adj.put(8, new Integer[] {7, 12});
        adj.put(9, new Integer[] {0, 10, 21});
        adj.put(10, new Integer[] {3, 9, 11, 18});
        adj.put(11, new Integer[] {6, 10, 15});
        adj.put(12, new Integer[] {8, 13, 17});
        adj.put(13, new Integer[] {5, 12, 14, 20});
        adj.put(14, new Integer[] {2, 13, 23});
        adj.put(15, new Integer[] {11, 16});
        adj.put(16, new Integer[] {15, 17, 19});
        adj.put(17, new Integer[] {12, 16});
        adj.put(18, new Integer[] {10, 19});
        adj.put(19, new Integer[] {16, 18, 20, 22});
        adj.put(20, new Integer[] {13, 19});
        adj.put(21, new Integer[] {9, 22});
        adj.put(22, new Integer[] {19, 21, 23});
        adj.put(23, new Integer[] {14, 22});
    }

    public Board()
    {
        initBoard();
        gamePhase = GamePhase.move;
    }

    private void initBoard()
    {
        // mark all clickable spots on the board as 0
        //Arrays.fill(GridLoc, 0, 24, 0);
        createAdjList();
    }


    public void takeInput(int location)
    {
        // Method invoked when user clicks on a valid point on the board
        //currently method checking placement phase for Sprint 1
        if(gamePhase==GamePhase.remove) {
            processRemove(location);
        }
        else if(moveInProgress) {
            finishMovement(location);
        }
        else if (isPlacementPhase()) {
            placementProcess(location);
        }
        else if(validFirstClick(location,playerTurn)) {
                prevClick=location;
                moveInProgress=true;
            }
            dispStatus+="";

    }

    public boolean isPlacementPhase() {
        // Method to check if game is in placing phase
        // Checks if both player has unplacedpawns or not
        return (hasUnplacedPawns(0) || hasUnplacedPawns(1));
    }

    private boolean validFirstClick(int loc, int player) {

        return isPlayersPawn(player,loc);

    }

    private void placementProcess(int location) {
        //Placing phase logic
        Movement currentMovement =new Movement(playerTurn,location);
        if(isValidMovement(currentMovement)) {
            takeAction(currentMovement);
        }

    }

    private void processRemove(int location) {

        if(isValidRemoval(playerTurn,location)) {
            Movement currentMovement=new Movement(playerTurn,location,true);
            takeAction(currentMovement);
            prevClick=0;
            moveInProgress=false;
        }else {
            dispStatus+="";
        }
    }

    public boolean IsMill(Integer location) {
        /* checks if the location is part of a mill
         * A mill is formed if a player has three pieces in a straight line
         * either vertical or horizontal */

        // get the value of the location
        Integer playerNum = GridLoc[location];
        // trivial case - is empty
        if (playerNum == 0) {
            return false;
        }
        Integer[] first, second;
        first = new Integer[3];
        second = new Integer[3];
        // search the array of possMills search for the two possiblities
        // of this lcoation
        boolean isFirst = true;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 3; j++) {
                if (allMills[i][j] == location) {
                    // not this is just a reference
                    if (isFirst) {
                        first = allMills[i];
                        isFirst = false;
                    }
                    else {
                        second = allMills[i];
                    }
                }
            }
        }
        if (GridLoc[first[0]] == playerNum && GridLoc[first[1]] == playerNum && GridLoc[first[2]] == playerNum) {
            return true;
        }
        if (GridLoc[second[0]] == playerNum && GridLoc[second[1]] == playerNum && GridLoc[second[2]] == playerNum) {
            return true;
        }
        return false;
    }

    public GamePhase getGamePhase() {
        // gets the game state on board
        return gamePhase;
    }

    public boolean isValidMovement( Movement currMovement) {
        /* Method to check if a particular movement is valid
         * movement. Works with placement or movement */
        // check that both locations exist

        boolean placement=currMovement.isPlacement();
        int playerNum=currMovement.getPlayerTurn();
        int locationTo=currMovement.getLocationTo();

        if (!isValidLoc(locationTo)) {
            return false;
        }
        if (!isPlayersTurn(playerNum)) {
            return false;
        }
        // check that the location to is empty
        if (!isEmpty(locationTo)) {
            return false;
        }

        if(!placement) {

            int locationFrom=currMovement.getLocationFrom();

            if (!isValidLoc(locationFrom)) {
                return false;
            }

            // check that the player has no piece's left
            if (hasUnplacedPawns(playerNum)) {
                return false;
            }
            // check that the locationFrom is the correct player
            if (!isPlayersPawn(playerNum, locationFrom)) {
                return false;
            }
            // check if the two locations are adjacent or if flying is valid
            // !Adj && !CanFly <==> !(Adj || CanFly)
            if (!(AreAdjacent(locationTo, locationFrom) || CanFly(playerNum))) {
                return false;
            }

        }
         else {
            if (!hasUnplacedPawns(playerNum)) {
                return false;
            }
        }
        // check game state
        if (getGamePhase() != GamePhase.move) {
            return false;
        }
        return true;
    }

    public boolean isValidLoc(Integer location) {
        // checks if the location is valid for our data structure
        return !(location < 0 || location > 23);
    }

    public boolean hasUnplacedPawns(Integer playerNum) {
        // checks if a player has unplaced pieces
        return (unplacedPawns[playerNum] > 0);
    }

    public boolean AreAdjacent(Integer loc1, Integer loc2) {
        // checks if the location are adjacent
        return (Arrays.asList(adj.get(loc1)).contains(loc2));
    }

    private void finishMovement(int location) {
        Movement currentMovement=new Movement(playerTurn,location,prevClick);

        if(isValidMovement(currentMovement)) {

            takeAction(currentMovement);
            prevClick=0;
            moveInProgress=false;

        }else {
            dispStatus+="Invalid Movement\n";
            prevClick=0;
            moveInProgress=false;
        }



    }

    public void takeAction(Movement currMovement){

        if(currMovement.isRemove()) {
            try {
                removePawn(currMovement);
            } catch (Exception e) {

                dispStatus += "Invalid Removal\n";
            }
        }
        if (currMovement.isPlacement()) {
            try {
                    makeMove(currMovement);

                } catch (Exception e) {

                    dispStatus += "Invalid Placement\n";
                }

            } else {
                try {
                    makeMove(currMovement);
                } catch (Exception e) {

                    dispStatus += "Invalid Movement\n";
                }
            }

        }

    public boolean isPlayersTurn(Integer playerNum) {
        // checks if it is a player's turn
        return (playerTurn == playerNum);
    }
    public boolean isEmpty(Integer location) {
        // checks if location is empty
        return (GridLoc[location] == 0);
    }

    public boolean makeMove(Movement currMovement) throws Exception {
        //place the piece in selected location

        int locationTo= currMovement.getLocationTo();
        int playerNum= currMovement.getPlayerTurn();
        boolean placement= currMovement.isPlacement();

        if(placement) { //placement
            if (!isValidMovement(currMovement)) {
                throw new Exception("Invalid placement");
            }
            placePawn(playerNum, locationTo);
            Boolean formedMill = IsMill(locationTo);
            if (formedMill) {
                gamePhase = GamePhase.remove;
            }
            else {
                playerTurn = (playerTurn + 1) % 2;
            }
            return (formedMill);
        }else { //Movement
            int locationFrom=currMovement.getLocationFrom();

            if (!isValidMovement(currMovement)) {
                throw new Exception("Invalid movement");
            }
            // otherwise is valid move
            movePawn(playerNum, locationTo, locationFrom);
            Boolean formedMill = IsMill(locationTo);
            if (formedMill) {
                gamePhase = GamePhase.remove;
            }
            else {
                playerTurn = (playerTurn + 1) % 2;
            }
            return (formedMill);
        }
    }


    public void removePawn(Movement currMovement) throws Exception {
        /* Remove a pawn
         * If the move is not valid, this will throw an error */

        int playerNum=currMovement.getPlayerTurn();
        int location=currMovement.getLocationTo();
        if (!isValidRemoval(playerNum, location)) {
            throw new Exception("Invalid movement");
        }
        // otherwise is valid move
        removePawnFrom(location);
        gamePhase = GamePhase.move;
        playerTurn = (playerTurn + 1) % 2;
    }

    private void movePawn(Integer playerNum, Integer locationTo, Integer locationFrom) {
        // moves a pieces from one location to another
        GridLoc[locationTo] = playerNum + 1;
        GridLoc[locationFrom] = 0;
    }

    public boolean isValidRemoval(Integer playerNum, Integer location) {
        // Checks if a removal is valid
        // check that the location exists
        if (!isValidLoc(location)) {
            return false;
        }
        // check that it is the player's turn
        if (!isPlayersTurn(playerNum)) {
            return false;
        }
        // check that the location is nonempty
        if (isEmpty(location)) {
            return false;
        }
        // check that the location correct player
        if (!isPlayersPawn(((playerNum + 1) % 2), location)) {
            return false;
        }
        // check game state
        if (getGamePhase() != GamePhase.remove) {
            return false;
        }
        // check if the location is a part of a mill
        if (IsMill(location)) {

            boolean allItemsInMill=true;

            for(int x=0;x<24;x++) {
                if(isPlayersPawn(((playerNum + 1) % 2), x) && !IsMill(x)) {
                    allItemsInMill=false;
                }
            }
            return allItemsInMill;
        }
        return true;
    }

    private void removePawnFrom(Integer location) {
        // removes a piece at location
        Integer player = GridLoc[location] - 1;
        livePawns[player]--;
        GridLoc[location] = 0;
    }

    public boolean CanFly(Integer playerNum) {
        /* Checks if a player can fly, this means the player has 3 or less pieces */
        return livePawns[playerNum] <= 3;
    }

    public int isWinner() {

        if(livePawns[0] <=2) {
            return 2;
        }
        if(livePawns[1] <=2) {
            return 1;
        }
        if(!HasLegalMoves(0)) {
            return 2;
        }
        if(!HasLegalMoves(1)) {
            return 1;
        }

        return -1;
    }

    public boolean HasLegalMoves(Integer playerNum) {
        // checks if a player has a possible move
        // if during placement
        if (hasUnplacedPawns(playerNum) || hasUnplacedPawns((playerNum+1)%2)) {
            return true;
        }
        // if looking for movement
        if (gamePhase == GamePhase.move) {
            // if they can fly, there is a guaranteed move
            if (CanFly(playerNum)) {
                return true;
            }
            // otherwise start with location 0
            // see if the player is in that position
            // and there are open adjacent spot
            int i = 0;
            while (i < 24) {
                if (GridLoc[i] == (playerNum + 1)) {
                    Integer[] possibleAdj = adj.get(i);
                    for (int j = 0; j < possibleAdj.length; j++) {
                        if (isEmpty(possibleAdj[j])) {
                            return true;
                        }
                    }
                }
                i++;
            }
            // if we iterated through the full board and they cannot move
            return false;
        }
        // want to find a piece of the opponent that is not in a mill
        if (gamePhase == GamePhase.remove) {
            int i = 0;
            while (i < 24) {
                if (GridLoc[i] == ((playerNum + 1) % 2)) {
                    if (!IsMill(i) || everyPieceAMill((playerNum))) {
                        return true;
                    }
                }
                i++;
            }
            return false;
        }
        // if neither of those game states (impossible since enum)
        return false;
    }

    public boolean everyPieceAMill(int playerNum) {

        for (int x=0;x<24;x++) {
            if (isPlayersPawn(playerNum,x) && !IsMill(x)) {
                return false;
            }
        }

        return true;
    }

    public String getPlayerName(int playerNum) {
        if(playerNum==0) {
            return getPlayerOneName();
        }
        if(playerNum==1) {
            return getPlayerTwoName();
        }

        else return "invalid player";
    }

    public String getPlayerOneName() {
        return playerOneName;
    }
    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }
    public Integer numUnplacedPawns(Integer playerNum) {
        // getter for number of unplaced pieces
        return unplacedPawns[playerNum];
    }
    private void placePawn(Integer playerNum, Integer location) {
        // places a piece at a location and decrements unplaced count
        unplacedPawns[playerNum]--;
        GridLoc[location] = playerNum + 1;
    }
    public boolean isPlayersPawn(Integer playerNum, Integer location) {
        // checks if the location's pawn is the player's
        return (GridLoc[location] == (playerNum + 1));
    }


}
