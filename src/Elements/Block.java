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

    public HashMap<Integer, Building> getBulildings(){
        return buildings;
    }
    public void addHome(int floor,int unit){
        buildings.put(Integer.valueOf(maxBuildingsId+1), new Gilgdoony(floor, unit));
    }
    public int getPopulation(){

        for(Integer id : buildings.keySet()){
            buildings.get(id);
        }
        return 0;
    }

    public void removeElement(int id){
        //must handle
    }

    public Block(){

    }
}
