import Elements.*;
import Elements.Military.Army;
import Elements.Military.Defence;
import Elements.Residency.Home;

import java.util.HashMap;

public class Player {

    private HashMap<Integer, Block> ownedBlocks;

    public int lastBlockId = 0;
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
       // try {

            switch (buildingName) {
                case "bazaar":
                    if (credit >= Bazaar.COST_TO_BUILD) {
                        boolean wasAdded = ownedBlocks.get(blockId).addBazaar();
                        System.out.println(wasAdded);
                        if (wasAdded) {
                            credit -= Bazaar.COST_TO_BUILD;
                        }
                        System.out.println("bazaar was added "+wasAdded);
                        return wasAdded;
                    }
                    else{
                        System.out.println("Why?");
                    }
                    break;
                case "army":
                    if (credit >= Army.COST_TO_BUILD && !hasArmy) {
                        boolean wasAdded = ownedBlocks.get(blockId).addArmy();
                        if (wasAdded) {
                            hasArmy = true;
                            credit -= Army.COST_TO_BUILD;
                        }
                        return wasAdded;
                    }
                    break;
                case "defence":
                    if (credit >= Defence.COST_TO_BUILD) {
                        boolean wasAdded = ownedBlocks.get(blockId).addDefence();
                        if (wasAdded) {
                            credit -= Defence.COST_TO_BUILD;
                        }
                        return wasAdded;
                    }
                    break;
            }
            return false;
        //}
        /*catch (Exception e){
            return false;
        }*/
    }

    public boolean upgradeBuilding(int buildingId, int blockId){
        try {
            Building temp = ownedBlocks.get(blockId).getBuildings().get(buildingId);
            int costToUpgrade = 0;
            if (temp instanceof Bazaar) {
                costToUpgrade = ((Bazaar) temp).getUpgradeCost();
            } else if (temp instanceof Army) {
                costToUpgrade = Army.COST_TO_UPGRADE;
            } else if (temp instanceof Defence) {
                costToUpgrade = Defence.COST_TO_UPGRADE;
            }
            if (credit >= costToUpgrade) {
                boolean wasUpgraded = ownedBlocks.get(blockId).upgradeBuilding(buildingId);
                if (wasUpgraded) {
                    credit -= costToUpgrade;
                }
                return wasUpgraded;
            }
            return false;
        }
        catch (Exception e){
            System.err.println("Exception Occurred");
            return false;
        }
    }

    public boolean removeBuilding(int buildingId, int blockId){
        try {
            Building temp = ownedBlocks.get(blockId).getBuildings().get(buildingId);
            int costToRemove = 0;
            if(temp instanceof Bazaar){
                costToRemove = Bazaar.COST_TO_REMOVE;
            }

            else if(temp instanceof Army){
                costToRemove = Army.COST_TO_REMOVE;
            }

            else if(temp instanceof Defence){
                costToRemove = Defence.COST_TO_REMOVE;
            }

            credit -= costToRemove;
            ownedBlocks.get(blockId).removeBuilding(buildingId);
            return true;
        }
        catch (Exception e){
            System.err.println("Exception Occurred");
            return false;
        }
    }

    public boolean addHome(int blockId, int floor, int unit) {
        try {
            int costToAdd = Home.getAdditionCost(floor, unit);
            if (credit >= costToAdd && floor <= Home.MAX_FLOOR && floor >= Home.MIN_FLOOR &&
                    unit >= Home.MIN_UNIT_PER_FLOOR && unit <= Home.MAX_UNIT_PER_FLOOR) {
                boolean wasAdded = ownedBlocks.get(blockId).addHome(floor, unit);
                if (wasAdded) {
                    credit -= costToAdd;
                }
                return wasAdded;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean upgradeHome(int blockId, int homeId, String command){
        int costToUpgrade = ((Home) ownedBlocks.get(blockId).getBuildings().get(homeId)).getUpgradeCost(command);
        if(credit >= costToUpgrade) {
            boolean isUpgraded = ((Home) ownedBlocks.get(blockId).getBuildings().get(homeId)).upgrade();
            if(isUpgraded){
                credit -= costToUpgrade;
            }
            return isUpgraded;
        }
        return false;
    }

    public boolean addBlock(){
        if(credit >= Block.COST_TO_ADD) {
            ownedBlocks.put(++lastBlockId, new Block());
            credit -= Block.COST_TO_ADD;
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
        int costToUpgrade = ownedBlocks.get(blockId).getUpgradeCost();
        if(credit >= costToUpgrade) {
            boolean wasUpgraded = ownedBlocks.get(blockId).upgrade();
            if (wasUpgraded) {
                credit -= costToUpgrade;
            }
            return wasUpgraded;
        }

        return false;
    }

    public void passDay() {
        for(Block block : ownedBlocks.values()) {
            credit += block.getBlockIncome();
        }
    }

    public double getScore(){
        double score = 0;
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

