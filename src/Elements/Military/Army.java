package Elements.Military;

import java.util.Arrays;

public class Army extends GilgArmy {
    public static final int ARMY_MAX_LEVEL = 5;
    public static final int PEOPLE_NEEDED_TO_BUILD = 100;
    public static final int PEOPLE_NEEDED_TO_UPGRADE = 10;
    public static final int COST_TO_BUILD = 15000;
    public static final int COST_TO_UPGRADE = 20000;
    public static final int ARMY_BASE_SCORE = 10;

    private int attackLevel = 1;

    public Army(Integer[] peopleToAddIds){
        peopleInsideIds.addAll(Arrays.asList(peopleToAddIds));
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public boolean upgrade(Integer[] peopleToAddIds) {
        if(attackLevel < ARMY_MAX_LEVEL) {
            ++attackLevel;
            peopleInsideIds.addAll(Arrays.asList(peopleToAddIds));
            return true;
        }
        return false;
    }

    @Override
    public void incrementDaysAlive() {
        ++daysAlive;
    }

    @Override
    public double getScore(double scoreMultiplier) {
        int i;
        double score = 1;
        for (i = 0; i < daysAlive; i++) {
            score *= ARMY_BASE_SCORE;
        }
        return score;
    }
}
