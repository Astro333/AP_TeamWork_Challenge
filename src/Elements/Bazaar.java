package Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Bazaar extends Building {

    public static final int BAZAAR_MAX_LEVEL = 3;
    public static final int PEOPLE_NEEDED_TO_BUILD = 50;
    public static final int PEOPLE_NEEDED_TO_UPGRADE = 20;
    public static final int COST_TO_BUILD = 6000;
    public static final int COST_TO_UPGRADE = 5000;
    public static final int COST_TO_REMOVE = 500;
    public static final int BAZAAR_BASE_SCORE = 5;

    private double scoreMultiplier = 1.2;

    public Bazaar(Integer[] peopleToAdd){
        peopleInsideIds = new ArrayList<>();
        peopleInsideIds.addAll(Arrays.asList(peopleToAdd));
    }


    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    private int level;
    private int daysActive = 0;

    public int getDaysActive() {
        return daysActive;
    }

    public int getLevel() {
        return level;
    }

    public boolean upgrade(Integer[] peopleToAdd) {
        if(level < BAZAAR_MAX_LEVEL) {
            ++level;
            scoreMultiplier += 0.2;
            peopleInsideIds.addAll(Arrays.asList(peopleToAdd));
            return true;
        }
        return false;
    }

    public int incrementDaysActive() {
        return daysActive++;
    }

    public int getUpgradeCost() {
        return (level + 1) * COST_TO_UPGRADE;
    }

    @Override
    public double getScore(double scoreMultiplier) {
        int i;
        double score = 1;
        for (i = 0; i < daysActive; i++) {
            score *= BAZAAR_BASE_SCORE;
        }
        return score;
    }
}
