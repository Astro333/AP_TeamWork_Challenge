package Elements;

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

    public int maxBuildingsId = 1;

    private int workingPeople;

    public HashMap<Integer, Building> getBulildings(){
        return buildings;
    }

    public int getPopulation(){
        return 0;
    }

    public void removeElement(int id){
        //must handle
    }

    public Block(){

    }
}
