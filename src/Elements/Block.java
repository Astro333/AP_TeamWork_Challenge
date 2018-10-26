package Elements;

import Elements.Military.Army;
import Elements.Military.Defence;
import Elements.Residency.Home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Block{

    public static final int BLOCK_MAX_LEVEL = 3;
    public static final int COST_TO_ADD = 1000;
    public static final int COST_TO_REMOVE = -500;

    public boolean hasDefence = false;


    private int currentBlockCapacity = 15;
    private int numberOfBuildings = 0;
    private int level = 1;
    private int maxBuildingsId = 0;

    private HashMap<Integer, Person> idlePeople;
    private HashMap<Integer, Person> workingPeople;
    private double peopleScoreMultiplier = 1;

    private HashMap<Integer, Building> buildings;

    public Block(){
        buildings = new HashMap<>();
        idlePeople = new HashMap<>();
        workingPeople = new HashMap<>();
    }

    public HashMap<Integer, Building> getBuildings(){
        return buildings;
    }

    public int getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public boolean addHome(int floor, int unit){
        if(numberOfBuildings < currentBlockCapacity) {
            Integer[] peopleToAddIds = new Integer[floor*unit];

            for(int i = 0 ; i < floor*unit; ++i){
                peopleToAddIds[i] = Person.lastPersonId+i;
                idlePeople.put(peopleToAddIds[i], new Person(false));
            }
            buildings.put(++maxBuildingsId , new Home(floor, unit, peopleToAddIds));
            ++numberOfBuildings;
            return true;
        }
        return false;
    }

    public boolean addBazaar() {
        if(numberOfBuildings < currentBlockCapacity && (idlePeople.size()) >= 50) {
            Integer[] peopleToAddIds = new Integer[50];
            int i = 0;
            for(int id : idlePeople.keySet()){
                idlePeople.remove(id);
                workingPeople.put(id, new Person(true));
                peopleToAddIds[i] = id;
                ++i;
                if(i >= 50) {
                    break;
                }
            }
            buildings.put(++maxBuildingsId, new Bazaar(peopleToAddIds));
            ++numberOfBuildings;

            peopleScoreMultiplier *= 1.2;
            return true;
        }
        return false;
    }

    public boolean addArmy() {
        if(numberOfBuildings < currentBlockCapacity && (idlePeople.size()) >= 100) {
            Integer[] peopleToAddIds = new Integer[100];
            int i = 0;
            for(int id : idlePeople.keySet()){
                idlePeople.remove(id);
                workingPeople.put(id, new Person(true));
                peopleToAddIds[i] = id;
                ++i;
                if(i >= 100) {
                    break;
                }
            }
            buildings.put(++maxBuildingsId, new Army(peopleToAddIds));
            return true;
        }
        return false;
    }

    public boolean addDefence(){
        if(!hasDefence && numberOfBuildings < currentBlockCapacity && (idlePeople.size()) >= 30) {
            Integer[] peopleToAddIds = new Integer[30];
            int i = 0;
            for(int id : idlePeople.keySet()){
                idlePeople.remove(id);
                workingPeople.put(id, new Person(true));
                peopleToAddIds[i] = id;
                ++i;
                if(i >= 30) {
                    break;
                }
            }
            buildings.put(++maxBuildingsId, new Defence(peopleToAddIds));
            hasDefence = true;
            return true;
        }
        return false;
    }

    public boolean upgradeBuilding(int buildingId){

        if(buildings.get(buildingId) instanceof Bazaar){
            if(idlePeople.size() >= Bazaar.PEOPLE_NEEDED_TO_UPGRADE) {
                Integer[] peopleToAdd = new Integer[Bazaar.PEOPLE_NEEDED_TO_UPGRADE];
                int i = 0;
                for(int id : idlePeople.keySet()){
                    peopleToAdd[i] = (id);
                    ++i;
                    if(i >= Bazaar.PEOPLE_NEEDED_TO_UPGRADE)
                        break;
                }


                boolean isUpgraded = ((Bazaar) buildings.get(buildingId)).upgrade(peopleToAdd);
                if (isUpgraded) {
                    double temp = ((Bazaar) buildings.get(buildingId)).getScoreMultiplier();
                    peopleScoreMultiplier = peopleScoreMultiplier*(temp-0.2)/temp;

                    for(i = 0 ; i < peopleToAdd.length ; ++i){
                        workingPeople.put(peopleToAdd[i], idlePeople.get(peopleToAdd[i]));
                        idlePeople.remove(peopleToAdd[i]);
                    }
                }
                return isUpgraded;
            }
            return false;
        }

        else if(buildings.get(buildingId) instanceof Army){
            if(idlePeople.size() >= Army.PEOPLE_NEEDED_TO_UPGRADE) {
                Integer[] peopleToAdd = new Integer[Army.PEOPLE_NEEDED_TO_UPGRADE];
                int i = 0;
                for(int id : idlePeople.keySet()){
                    peopleToAdd[i] = (id);
                    ++i;
                    if(i >= Army.PEOPLE_NEEDED_TO_UPGRADE)
                        break;
                }

                boolean isUpgraded = ((Army) buildings.get(buildingId)).upgrade(peopleToAdd);
                if (isUpgraded) {
                    for(i = 0 ; i < peopleToAdd.length ; ++i){
                        workingPeople.put(peopleToAdd[i], idlePeople.get(peopleToAdd[i]));
                        idlePeople.remove(peopleToAdd[i]);
                    }
                }
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
            peopleScoreMultiplier /= ((Bazaar) temp).getScoreMultiplier();
            for(int i : temp.getPeopleInsideIds()){
                idlePeople.put(i, workingPeople.get(i));
                workingPeople.remove(i);
            }
        }
        else if(temp instanceof Army){
            for(int i : temp.getPeopleInsideIds()){
                idlePeople.put(i, workingPeople.get(i));
                workingPeople.remove(i);
            }
        }

        else if(temp instanceof Defence)
            hasDefence = false;

        buildings.remove(id);
    }
    public int getPopulation(){
        return idlePeople.size()+workingPeople.size();
    }

    public int getAmountOfWorkingPeople(){
        return workingPeople.size();
    }

    public int getUpgradeCost(){
        int temp = 1;
        for(int i = 0 ; i < level ; ++i)
            temp *= 500;
        return temp;
    }

    public int getLevel(){
        return level;
    }

    public int getCurrentBlockCapacity() {
        return currentBlockCapacity;
    }

    public int getBlockIncome(){

        int sum = 0;

        for(Person p : workingPeople.values()){
            sum += p.getIncome();
        }

        sum += idlePeople.size() * 100;

        return sum;
    }

    public boolean upgrade(){
        if(level < BLOCK_MAX_LEVEL) {
            currentBlockCapacity += 5;
            ++level;
            return true;
        }
        return false;
    }

    public double getScore() {
        double score = 0;
        for(Building building : buildings.values()){
            score += building.getScore(peopleScoreMultiplier);
        }
        return score;
    }
}
