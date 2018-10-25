import Elements.*;

import java.util.HashMap;

public class Player {

    HashMap<Integer, Block> playerBlocks;
    private int credit = 30_000;

    public int lastBlockId = 1;

    private boolean hasArmy = false;

    public int getCredit() {
        return credit;
    }

    public void addCredit(int amount) {
        this.credit += amount;
    }

    public Player(){
        playerBlocks = new HashMap<>();
    }

    public boolean addBuilding(String buildingName){
    }

    /*public boolean addBlock(){

    }*/
}

