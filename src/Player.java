import Elements.*;
import Elements.Military.Army;
import Elements.Military.Defence;
import Elements.Residency.Home;

import java.util.HashMap;

public class Player {

    private HashMap<Integer, Block> ownedBlocks;

    public int lastBlockId = 1;
    private int credit = 30_000;


    private boolean hasArmy = false;

    public HashMap<Integer, Block> getOwnedBlocks() {
        return ownedBlocks;
    }

    public int getCredit() {
        return credit;
    }

    public void addCredit(int amount) {
        this.credit += amount;
    }

    public Player(){
        ownedBlocks = new HashMap<>();
    }

    public boolean addBuilding(String buildingName, int blockId){

        switch (buildingName){
            case "bazaar":
                if(credit >= Bazaar.COST_TO_BUILD) {
                    ownedBlocks.get(blockId).addBazaar();
                    return true;
                }
                break;
            case "army":
                if(credit >= Army.COST_TO_BUILD && !hasArmy) {
                    ownedBlocks.get(blockId).addArmy();
                    hasArmy = true;
                    return true;
                }
                break;
            case "defence":
                if(credit >= Defence.costToBuild) {
                    ownedBlocks.get(blockId).addDefence();
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean upgradeBuilding(int buildingId, int blockId){
        return ownedBlocks.get(blockId).upgradeBuilding(buildingId);
    }

    public boolean removeBuilding(int buildingId, int blockId){
        ownedBlocks.get(blockId).removeBuilding(buildingId);
        return true;
    }

    public boolean addHome(int blockId, int floor, int unit) {
        if(floor <= Home.MAX_FLOOR && floor >= Home.MIN_FLOOR &&
           unit >= Home.MIN_UNIT_PER_FLOOR && unit <= Home.MAX_UNIT_PER_FLOOR)
            return ownedBlocks.get(blockId).addHome(floor, unit);
        return false;
    }
    public boolean upgradeHome(int blockId, int homeId, String command){
        if(credit >= ((Home) ownedBlocks.get(blockId).getBuildings().get(homeId)).getUpgradeCost(command))
            return ((Home) ownedBlocks.get(blockId).getBuildings().get(homeId)).upgrade();
        return false;
    }

    public boolean addBlock(){
        if(credit >= Block.COST_TO_ADD) {
            ownedBlocks.put(++lastBlockId, new Block());
            return true;
        }
        return false;
    }
    public void removeBlock(int blockId){

        ownedBlocks.remove(blockId);

        int maximumIdBlock = Integer.MIN_VALUE;

        for(Integer i : ownedBlocks.keySet()){
            if (maximumIdBlock < i)
                maximumIdBlock = i;
        }
        lastBlockId = maximumIdBlock;
    }
    public boolean upgradeBlock(int blockId) {
        return ownedBlocks.get(blockId).upgrade();
    }

    public void passDay() {
        for(Block block : ownedBlocks.values()) {
            credit += block.getBlockIncome();
        }
    }

    public int getScore(){
        int score = 0;
        for(Block block : ownedBlocks.values()){
            score += block.getScore();
        }
        return score;
    }

    public int loot(Player opponent, int blockId) {
        if(opponent.ownedBlocks.get(blockId).hasDefence)
            return -1;
        return opponent.ownedBlocks.get(blockId).getNumberOfBuildings() * 500;
    }
}

