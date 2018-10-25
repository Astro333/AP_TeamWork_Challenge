package Elements.Residency;

import Elements.Building;

public class Gilgdoony extends Building {
    private int floors = 1;
    private int units = 1;
    private int score;
    public Gilgdoony(int floors, int units) {

        this.floors = floors;
        this.units  = units;
    }
    public int getPopulation(){

        return (floors *units) * 5;
    }

    public int costToAddHome(int floor, int unit){
        return unit*100 + floor*300 +700 ;
    }
    public int getUpgradeCost(String command) {
        switch (command) {
            case "floor":
                return costToUpgradeHomeFloors( floors);
            case "unit":
                return costToUpgradeHomeUnit( units);
            case "floor unit":
                return costToUpgradeHomeFloorUnit( floors, units);
        }
        return 0 ;
    }
    private int costToUpgradeHomeFloors(int floors) {
        int a ;
        a  = floors + (1 * units );
        a -= (floors *units);
        return (a * 500 + floors*300) ;
    }
    private int  costToUpgradeHomeUnit(int units){
    int a ;
    a = floors * (units + 1);
    a -= floors * units;
    return ((a * 500));
    }
    private int  costToUpgradeHomeFloorUnit(int floors, int units){
        int a ;
        a = (floors + 1) * (units + 1);
        a -= (floors * units);
        return (a * 500 + floors*300);
    }
}
