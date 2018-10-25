package Elements.Residency;

import Elements.Building;

public class Home extends Building {

    public static final int unitBaseScore = 2;
    public static final int floorBaseScore = 3;
    public static final int homeBaseScore = 10;
    private int floors;
    private int unitPerFloor;

    public Home(int floors, int units) {
        this.floors = floors;
        this.unitPerFloor = units;
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
        if(floors < 6 && unitPerFloor < 4) {
            ++floors;
            ++unitPerFloor;
            return true;
        }
        return false;
    }
    @Override
    public int getScore(double scoreMultiplier){
        int score = 0;

        //e+sum(floor)+sum(unit)*2+sum(person)*3
    }
}
