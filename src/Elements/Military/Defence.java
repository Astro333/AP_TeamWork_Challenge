package Elements.Military;

import java.util.Arrays;

public class Defence extends GilgArmy {
    public static final int DEFENCE_MAX_LEVEL = 5;
    public static final int peopleNeededToBuild = 30;
    public static final int peopleNeededToUpgrade = 0;
    public static final int costToBuild = 10000;
    public static final int costToUpgrade = 5000;
    public static final int defenceBaseScore = 15;

    private int defenceLevel = 1;

    public Defence(Integer[] peopleToAddIds){
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
            score *= defenceBaseScore;
        }
        return score;
    }
}
