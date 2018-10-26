package Elements.Military;

import java.util.ArrayList;
import java.util.Arrays;

public class Defence extends GilgArmy {
    public static final int DEFENCE_MAX_LEVEL = 5;
    public static final int PEOPLE_NEEDED_TO_BUILD = 30;
    public static final int PEOPLE_NEEDED_TO_UPGRADE = 0;
    public static final int COST_TO_BUILD = 10000;
    public static final int COST_TO_UPGRADE = 5000;
    public static final int COST_TO_REMOVE = -10000;
    public static final int DEFENCE_BASE_SCORE = 15;

    private int defenceLevel = 1;

    public Defence(Integer[] peopleToAddIds){
        peopleInsideIds = new ArrayList<>();
        peopleInsideIds.addAll(Arrays.asList(peopleToAddIds));
    }

    public int getDefenceLevel() {
        return defenceLevel;
    }

    public boolean upgrade() {
        if(defenceLevel < DEFENCE_MAX_LEVEL) {
            ++defenceLevel;
            return true;
        }
        return false;
    }

    @Override
    public void incrementDaysAlive(){
        ++daysAlive;
    }

    @Override
    public double getScore(double scoreMultiplier) {
        int i;
        double score = 1;
        for (i = 0; i < daysAlive; i++) {
            score *= DEFENCE_BASE_SCORE;
        }
        return score;
    }
}
