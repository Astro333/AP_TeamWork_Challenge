package Elements;

import Elements.Residency.Home;

import java.util.HashMap;

public class Block{
    //Each Block Maximum level is 3
    private int CURRENT_BLOCK_CAPACITY = 15;

    private int numberOfBuildings = 0;

    private int level = 1;

    private GilgArmy army;
    private GilgArmy defence;
    private HashMap<Integer, Building> buildings;
    //not to be touched
    private int maxBuildingsId = 1;

    private int workingPeople;

    public HashMap<Integer, Building> getbuildings(){
        return buildings;
    }
    public void addHome(int floor,int unit){
        buildings.put(maxBuildingsId + 1, new Home(floor, unit));
    }
    public void addBazaar(){
        buildings.put(maxBuildingsId+1,new Bazaar());
    }
    public void addArmy(){
        buildings.put(maxBuildingsId+1,new Army());
    }
    public void addDefence(){
        buildings.put(maxBuildingsId+1,new Defence());
    }

    public boolean upgradeBuilding(int buildingId){

        if(buildings.get(buildingId) instanceof Bazaar){
            ((Bazaar)buildings.get(buildingId)).upgrade();
            return true;
        }

        else if(buildings.get(buildingId) instanceof Army){
            ((Bazaar)buildings.get(buildingId)).upgrade();
            return true;
        }

        else if(buildings.get(buildingId) instanceof Defence){
            ((Bazaar)buildings.get(buildingId)).upgrade();
            return true;
        }
        return false;
    }
    public int getPopulation(){
    int sum = 0;
        for(Integer id : buildings.keySet()){
           if(buildings.get(id) instanceof Home){
               sum += ((Home)buildings.get(id)).getPopulation();
           }
        }
        return sum;
    }

    public void removeBuilding(int id){
        buildings.remove(id);
    }
    public int getLevel(){
        return level ;
    }

    public int getCURRENT_BLOCK_CAPACITY() {
        return CURRENT_BLOCK_CAPACITY;
    }

    public Block(){

    }

    public boolean upgrade(){
        //ToDo:must change current Block Capacity for Each Upgrade
        if(level < 3) {
            CURRENT_BLOCK_CAPACITY += 5;
            return true;
        }
        return false;
    }
}
