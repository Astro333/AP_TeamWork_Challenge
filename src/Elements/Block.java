package Elements;

import java.util.ArrayList;

public class Block{
    //Each Block Maximum level is 3
    private int MAX_BLOCK_CAPACITY = 15;

    private int numberOfBuildings = 0;

    private int level = 1;

    private GilgArmy army;
    private GilgArmy defence;
    private ArrayList<Building> buildings;

    private int workingPeople;

    public ArrayList<Building> getBuildings(){
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
