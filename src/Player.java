import Elements.*;
import Elements.Residency.Home;

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

    public boolean removeBuilding(int buildingId, int blockId){
        playerBlocks.get(blockId).removeBuilding(buildingId);
        return true;
    }

    public boolean upgradeBuilding(int buildingId, int blockId){
        return playerBlocks.get(blockId).upgradeBuilding(buildingId);
    }

    public boolean addBuilding(String buildingName, int blockId){

        switch (buildingName){
            case "bazaar":
                if(credit >= Bazaar.costToBuild) {
                    playerBlocks.get(blockId).addBazaar();
                    return true;
                }
                break;
            case "army":
                if(credit >= Army.costToBuild) {
                    playerBlocks.get(blockId).addArmy();
                    return true;
                }
                break;
            case "defence":
                if(credit >= Defence.costToBuild) {
                    playerBlocks.get(blockId).addDefence();
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean upgradeHome(int blockId, int homeId, String command){
        if(credit >= ((Home)playerBlocks.get(blockId).getbuildings().get(homeId)).getUpgradeCost(command))
            return ((Home)playerBlocks.get(blockId).getbuildings().get(homeId)).upgrade();
        return false;
    }

    /*public boolean addBlock(){

    }*/
}

