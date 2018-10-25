package Elements;

import Elements.Residency.Gilgdoony;

import java.util.ArrayList;
import java.util.HashMap;

public class Block{
    //Each Block Maximum level is 3
    private int MAX_BLOCK_CAPACITY = 15;

    private int numberOfBuildings = 0;

    private int level = 1;

    private GilgArmy army;
    private GilgArmy defence;
    private HashMap<Integer, Building> buildings;
    //not to be touched
    public int maxBuildingsId = 1;

    private int workingPeople;

    public HashMap<Integer, Building> getbuildings(){
        return buildings;
    }
    public void addHome(int floor,int unit){
        buildings.put(maxBuildingsId + 1, new Gilgdoony(floor, unit));
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
    public int getPopulation(){
    int sum = 0;
        for(Integer id : buildings.keySet()){
           if(buildings.get(id) instanceof Gilgdoony){
               sum += ((Gilgdoony)buildings.get(id)).getPopulation();
           }
        }
        return sum;
    }

    public void removeElement(int id){

    }

    public Block(){

    }
}
