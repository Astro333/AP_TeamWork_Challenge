package Elements;

public class Bazaar extends Building {
    public static final int BAZAAR_MAX_LEVEL = 3;
    public static final int peopleNeededToBuild = 50;
    public static final int peopleNeededToUpgrade = 20;
    public static final int costToBuild = 6000;
    public static final int costToUpgrade = 5000;
    public static final int bazaarBaseScore = 5;
    private int peopleWorking = 0;

    private double scoreMultiplier = 1.2;

    public Bazaar(){
        peopleWorking += peopleNeededToBuild;
    }

    public int getPeopleWorking() {
        return peopleWorking;
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

    public boolean upgrade() {
        if(level < BAZAAR_MAX_LEVEL) {
            ++level;
            scoreMultiplier += 0.2;
            peopleWorking += peopleNeededToUpgrade;
            return true;
        }
        return false;
    }

    public int incrementDaysActive() {
        return daysActive++;
    }

    public int getUpgradeCost() {
        return (level + 1) * costToUpgrade;
    }

    @Override
    public int getScore(double scoreMultiplier) {
        int i, score = 1;
        for (i = 0; i < daysActive; i++) {
            score *= bazaarBaseScore;
        }
        return score;
    }
}
