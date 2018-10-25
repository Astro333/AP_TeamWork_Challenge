import Elements.Army;
import Elements.Block;
import Elements.GilgArmy;

import java.util.ArrayList;

public class Player {

    ArrayList<Block> playerBlocks;
    private int credit = 30_000;
    private boolean hasArmy = false;

    public int getCredit() {
        return credit;
    }

    public void addCredit(int amount){
        this.credit += amount;
    }
}
