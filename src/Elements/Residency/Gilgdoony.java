package Elements.Residency;

import Elements.Building;

public class Gilgdoony extends Building {

    private int floors;
    private int unit;

    public Gilgdoony() {
    }

    public int getPopulation(){
        return unit*floors*5;
    }

    @Override
    void upgrade() {

    }
}
