package Elements.Residency;

import Elements.Building;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends Building {

    public static final int UNIT_BASE_SCORE = 2;
    public static final int FLOOR_BASE_SCORE = 3;
    public static final int HOME_BASE_SCORE = 10;

    public static final int MAX_FLOOR = 6;
    public static final int MAX_UNIT_PER_FLOOR = 4;

    public static final int MIN_FLOOR = 3;
    public static final int MIN_UNIT_PER_FLOOR = 1;

    public static final int PEOPLE_IN_EACH_UNIT = 5;

    private int floors;
    private int unitPerFloor;

    public Home(int floors, int units, Integer[] peopleToAdd) {
        this.floors = floors;
        this.unitPerFloor = units;
        peopleInsideIds.addAll(Arrays.asList(peopleToAdd));
    }

    public int getPopulation(){
        return (floors * unitPerFloor) * 5;
    }

    public static int getAdditionCost(int floor, int unit){
        return unit*100 + floor*300 +700 ;
    }
    public int getUpgradeCost(String command) {
        switch (command) {
            case "floor":
                return 300;
            case "unit":
                return floors*50;
            case "floor unit":
                return (unitPerFloor + floors + 1)*500+300;
        }
        return -1;
    }

    public boolean upgrade(){
        if(floors < MAX_FLOOR && unitPerFloor < MAX_UNIT_PER_FLOOR) {
            ++floors;
            ++unitPerFloor;
            return true;
        }
        return false;
    }
    @Override
    public double getScore(double scoreMultiplier){

        double peopleScore = scoreMultiplier * PEOPLE_IN_EACH_UNIT;

        double unitsScore = (PEOPLE_IN_EACH_UNIT*scoreMultiplier + UNIT_BASE_SCORE)*unitPerFloor;

        double floorsScore = ((PEOPLE_IN_EACH_UNIT*scoreMultiplier + UNIT_BASE_SCORE)*unitPerFloor +
                2*scoreMultiplier*unitPerFloor*PEOPLE_IN_EACH_UNIT + FLOOR_BASE_SCORE)*floors;

        double homeScore = ((PEOPLE_IN_EACH_UNIT*scoreMultiplier + UNIT_BASE_SCORE)*unitPerFloor +
                2*scoreMultiplier*unitPerFloor*PEOPLE_IN_EACH_UNIT + FLOOR_BASE_SCORE) * floors +
                2*(PEOPLE_IN_EACH_UNIT*scoreMultiplier + UNIT_BASE_SCORE)*unitPerFloor*floors +
                           3*scoreMultiplier*PEOPLE_IN_EACH_UNIT * unitPerFloor * floors +
                            HOME_BASE_SCORE;

        return (homeScore + unitsScore + floorsScore + peopleScore);
    }
}
