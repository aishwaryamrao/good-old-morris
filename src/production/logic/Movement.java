package production.logic;

public class Movement
{
    int playerTurn;
    int locationFrom = 0;
    int locationTo;
    boolean isRemove;
    boolean isPlacement;
    public Movement(int playerTurn, int location)
    { //placement
        this.playerTurn = playerTurn;
        this.locationTo = location;
        this.isRemove = false;
        this.isPlacement = true;
    }
    public Movement(int playerTurn, int location, boolean isRemoval)
    { //removal
        this.playerTurn=playerTurn;
        this.locationTo=location;
        this.isRemove = isRemoval;
        this.isPlacement = !(isRemoval);
    }

    public Movement(int playerTurn, int locationTo, int locationFrom)
    { //movement
        this.playerTurn=playerTurn;
        this.locationFrom=locationFrom;
        this.locationTo=locationTo;
        this.isRemove = false;
        this.isPlacement = false;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public int getLocationTo(){
        return locationTo;
    }

    public int getLocationFrom(){
        return locationFrom;
    }

    public boolean isRemove(){
        return isRemove;
    }

    public boolean isPlacement(){
        return isPlacement;
    }

}
