package Elements;

public class Gilgdoony extends Building{

    private int floors;
    private int unit;

    public int getPopulation(){
        return unit*floors*5;
    }

    @Override
    void upgrade() {

    }
}
