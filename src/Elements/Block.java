package Elements;

import Elements.Military.Army;
import Elements.Military.Defence;
import Elements.Residency.Home;

import java.util.HashMap;

public class Block{

    public static final int BLOCK_MAX_LEVEL = 3;
    public boolean hasDefence = false;


    private int currentBlockCapacity = 15;
    private int numberOfBuildings = 0;
    private int level = 1;
    private int maxBuildingsId = 0;

    private int amountOfPeople = 0;
    private int amountOfWorkingPeople = 0;

    private double peopleScoreMultiplier = 1;

    private HashMap<Integer, Building> buildings;

    public Block(){
        buildings = new HashMap<>();
    }

    public HashMap<Integer, Building> getBuildings(){
        return buildings;
    }

    public boolean addHome(int floor,int unit){
        if(numberOfBuildings < currentBlockCapacity) {
            buildings.put(++maxBuildingsId , new Home(floor, unit));
            ++numberOfBuildings;
            amountOfPeople += (floor*unit*5);
            return true;
        }
        return false;
    }

    public boolean addBazaar() {
        if(numberOfBuildings < currentBlockCapacity && (amountOfPeople - amountOfWorkingPeople) >= 50) {
            buildings.put(++maxBuildingsId, new Bazaar());
            ++numberOfBuildings;
            amountOfWorkingPeople += 50;
            peopleScoreMultiplier *= 1.2;
            return true;
        }
        return false;
    }

    public boolean addArmy() {
        if(numberOfBuildings < currentBlockCapacity && (amountOfPeople - amountOfWorkingPeople) >= 100) {
            buildings.put(++maxBuildingsId, new Army());
            ++numberOfBuildings;
            amountOfWorkingPeople += 100;
            return true;
        }
        return false;
    }

    public boolean addDefence(){
        if(!hasDefence && numberOfBuildings < currentBlockCapacity && (amountOfPeople - amountOfWorkingPeople) >= 30) {
            buildings.put(++maxBuildingsId, new Defence());
            ++numberOfBuildings;
            amountOfWorkingPeople += 30;
            hasDefence = true;
            return true;
        }
        return false;
    }

    public boolean upgradeBuilding(int buildingId){

        if(buildings.get(buildingId) instanceof Bazaar){
            if(amountOfPeople-amountOfWorkingPeople >= 20) {
                boolean isUpgraded = ((Bazaar) buildings.get(buildingId)).upgrade();
                if (isUpgraded) {
                    double temp = ((Bazaar) buildings.get(buildingId)).getScoreMultiplier();
                    peopleScoreMultiplier = peopleScoreMultiplier*(temp-0.2)/temp;
                    amountOfWorkingPeople += 20;
                }
                return isUpgraded;
            }
            return false;
        }

        else if(buildings.get(buildingId) instanceof Army){
            if(amountOfPeople - amountOfWorkingPeople >= 10) {
                boolean isUpgraded = ((Army) buildings.get(buildingId)).upgrade();
                if(isUpgraded)
                    amountOfWorkingPeople += 10;
                return isUpgraded;
            }
            return false;
        }

        else if(buildings.get(buildingId) instanceof Defence){
           return ((Defence)buildings.get(buildingId)).upgrade();
        }
        return false;
    }

    public void removeBuilding(int id){
        Building temp = buildings.get(id);
        if(temp instanceof Bazaar) {
            amountOfWorkingPeople -= ((Bazaar)temp).getPeopleWorking();
            peopleScoreMultiplier /= ((Bazaar) temp).getScoreMultiplier();
        }
        else if(temp instanceof Army){
            amountOfWorkingPeople -= ((Army) temp).getPeopleWorking();
        }

        else if(temp instanceof Defence)
            hasDefence = false;

        buildings.remove(id);
    }
    public int getPopulation(){
        return amountOfPeople;
    }

    public int getWorkingPeople(){
        return amountOfWorkingPeople;
    }

    public int getUpgradeCost(){
        int temp = 1;
        for(int i = 0 ; i < level ; ++i)
            temp *= 500;
        return temp;
    }

    public int getLevel(){
        return level ;
    }

    public int getCurrentBlockCapacity() {
        return currentBlockCapacity;
    }

    public boolean upgrade(){
        if(level < BLOCK_MAX_LEVEL) {
            currentBlockCapacity += 5;
            ++level;
            return true;
        }
        return false;
    }

    public int getScore() {
        int score = 0;
        for(Building building : buildings.values()){
            score += building.getScore(peopleScoreMultiplier);
        }
        return score;
    }
}
