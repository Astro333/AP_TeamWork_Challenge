package Elements;

import java.util.ArrayList;

public abstract class Building {
    public abstract double getScore(double scoreMultiplier);
    protected ArrayList<Integer> peopleInsideIds;
    public ArrayList<Integer> getPeopleInsideIds(){
        return peopleInsideIds;
    }
}
