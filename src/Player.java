import Elements.*;
import Elements.Military.Army;
import Elements.Military.Defence;
import Elements.Residency.Home;

import java.util.HashMap;

public class Player {

    HashMap<Integer, Block> playerBlocks;

    public int lastBlockId = 1;
    private int credit = 30_000;


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
                if(credit >= Army.costToBuild && !hasArmy) {
                    playerBlocks.get(blockId).addArmy();
                    hasArmy = true;
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

    public boolean addHome(int blockId, int floor, int unit) {
        if(floor <= 6 && floor >= 3 && unit >= 1 && unit <= 4)
            return playerBlocks.get(blockId).addHome(floor, unit);
        return false;
    }

    public boolean upgradeHome(int blockId, int homeId, String command){
        if(credit >= ((Home)playerBlocks.get(blockId).getBuildings().get(homeId)).getUpgradeCost(command))
            return ((Home)playerBlocks.get(blockId).getBuildings().get(homeId)).upgrade();
        return false;
    }

    public void removeBlock(int blockId){

        playerBlocks.remove(blockId);

        int maximumIdBlock = Integer.MIN_VALUE;

        for(Integer i : playerBlocks.keySet()){
            if (maximumIdBlock < i)
                maximumIdBlock = i;
        }
        lastBlockId = maximumIdBlock;
    }

    public boolean upgradeBlock(int blockId) {
        return playerBlocks.get(blockId).upgrade();
    }

    public void passDay() {
        for(Block block : playerBlocks.values()) {
            block.getPopulation();
        }
    }

    public int getScore(){
        int score = 0;
        for(Block block : playerBlocks.values()){
            score += block.getScore();
        }
        return score;
    }

    public int loot(int blockId) {
        if(playerBlocks.get(blockId).hasDefence)
            return -1;
        return 1;
    }
}

