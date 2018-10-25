package Elements.Military;

public class Army extends GilgArmy {
    public static final int ARMY_MAX_LEVEL = 5;
    public static final int peopleNeededToBuild = 100;
    public static final int peopleNeededToUpgrade = 10;
    public static final int costToBuild = 15000;
    public static final int costToUpgrade = 20000;
    public static final int armyBaseScore = 10;

    private int peopleWorking = 0;
    private int attackLevel = 1;

    public int getPeopleWorking() {
        return peopleWorking;
    }

    public Army(){
        peopleWorking += peopleNeededToBuild;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public boolean upgrade() {
        if(attackLevel < ARMY_MAX_LEVEL) {
            ++attackLevel;
            peopleWorking += peopleNeededToUpgrade;
            return true;
        }
        return false;
    }

    @Override
    public void incrementDaysAlive() {
        ++daysAlive;
    }

    @Override
    public int getScore(double scoreMultiplier) {
        int i, score = 1;
        for (i = 0; i < daysAlive; i++) {
            score *= armyBaseScore;
        }
        return score;
    }
}
