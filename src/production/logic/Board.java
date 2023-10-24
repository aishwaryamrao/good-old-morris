package production.logic;

import java.util.Arrays;

public class Board
{
    private Integer[] GridLoc = new Integer[24];
    private Integer playerTurn = 0;
    private String playerOneName="";
    private String playerTwoName="";
    private String dispStatus="";

    private Integer[] unplacedPawns = new Integer[] {9, 9};
    private Integer[] livePawns = new Integer[] {9, 9};

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        // mark all clickable spots on the board as 0
        Arrays.fill(GridLoc, 0, 24, 0);
    }


    public void takeInput(int location)
    {
        // Method invoked when user clicks on a valid point on the board
        //currently method checking placement phase for Sprint 1
        // checking for other phases will be written in this method
        if (isPlacementPhase()) {
            placementProcess(location);
        }
    }

    public boolean isPlacementPhase() {
        // Method to check if game is in placing phase
        // Checks if both player has unplacedpawns or not
        return (hasUnplacedPawns(0) || hasUnplacedPawns(1));
    }

    private void placementProcess(int location) {
        //Placing phase logic
        Movement currentMovement =new Movement(playerTurn,location);
        if(isValidMovement(currentMovement)) {
            takeAction(currentMovement);
        }

    }

    public boolean isValidMovement(Movement currMovement) {
        //method to check valid click and valid move

        boolean placement= currMovement.isPlacement();
        int playerNum= currMovement.getPlayerTurn();
        int locationTo= currMovement.getLocationTo();

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

        else {
            if (!hasUnplacedPawns(playerNum)) {
                return false;
            }
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

    public void takeAction(Movement currMovement){

        if(currMovement.isPlacement()){

            try {
                makeMove(currMovement);
            } catch (Exception e) {

                dispStatus+="Invalid Placement\n";
            }

        }else{
            try {
                makeMove(currMovement);
            } catch (Exception e) {

                dispStatus+="Invalid Movement\n";
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
            playerTurn = (playerTurn + 1) % 2;
        }
        return false;
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
