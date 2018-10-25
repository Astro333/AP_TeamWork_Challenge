package Elements.Residency;

import Elements.Building;

public class Gilgdoony extends Building {
    private int floors = 1;
    private int units = 1;
    private int score;
    public Gilgdoony() {
    }
    public int getPopulation(int floor , int unit){

        return unitsOfHome(floor ,unit) * 5;
    }
    public int unitsOfHome(int floor , int unit){
        floors=floor;
        units = unit;
        return units*floors;
    }


    public int costToAddHome(int floor, int unit){
        return unit*100 + floor*300 +700 ;
    }
    public void addHome(int floors , int units){
        this.floors = floors;
        this.units = units;
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
    public int costToUpgradeHomeFloors(int floors) {
        int a ;
        a  = unitsOfHome(floors + 1 , units );
        a -= unitsOfHome(floors,units);
        return (a * 500 + 300) ;
    }
    public int  costToUpgradeHomeUnit(int units){
    int a ;
    a = unitsOfHome(floors , units + 1);
    a -= unitsOfHome(floors , units);
    return ((a * 500) + 300);
    }
    public int  costToUpgradeHomeFloorUnit(int floors,int units){
        int a ;
        a = unitsOfHome(floors + 1 , units + 1);
        a -= unitsOfHome(floors , units);
        return (a * 500 + 300);
    }

    @Override
    protected void upgrade() {

    }
}
