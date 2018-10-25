package Elements.Residency;

import Elements.Building;

public class Gilgdoony extends Building {
    private int floors = 1;
    private int unit = 1;
    private int score;
    public Gilgdoony(int floors, int units) {

        this.floors = floors;
        this.unit = units;
    }
    public int getPopulation(){

        return (floors * unit) * 5;
    }

    public int costToAddHome(int floor, int unit){
        return unit*100 + floor*300 +700 ;
    }
    public int getUpgradeCost(String command) {
        switch (command) {
            case "floor":
                return costToUpgradeHomeFloors();
            case "unit":
                return costToUpgradeHomeUnit();
            case "floor unit":
                return costToUpgradeHomeFloorUnit();
        }
        return -1;
    }

    private void addFloor(){
        ++floors;
    }
    private void addUnit() {
        ++unit;
    }
    private int costToUpgradeHomeFloors() {
        int a ;
        a  = (floors +1) * (unit);
        a -= (floors * unit);
        return (a * 500 + 300) ;
    }
    private int  costToUpgradeHomeUnit(){
    int a ;
    a = floors * (unit + 1);
    a -= floors * unit;
    return ((a * 500));
    }

    private int  costToUpgradeHomeFloorUnit(){
        int a ;
        a = (floors + 1) * (unit + 1);
        a -= (floors * unit);
        return (a * 500 + 300);
    }

    public boolean upgrade(){
        addFloor();
        addUnit();
        return true;
    }
}
