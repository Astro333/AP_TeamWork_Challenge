package Elements;

import java.util.ArrayList;

public class Bazaar extends Building {

    public static final int peopleNeededToBuild = 50;
    public static final int peopleNeededToUpgrade = 20;
    public static final int costToBuild = 6000;
    public static final int costToUpgrade = 5000;
    private double multiplier = 1;

    public double getMultiplier() {
        return multiplier;
    }

    private int level;
    private ArrayList<Person> people = new ArrayList<>();
    private int bazaarWorking = 1;

    public int getBazaarWorking() {
        return bazaarWorking;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void upgrade() {
        level++;
    }

    public void addWorker(Person p) {
        people.add(p);
    }

    public int additionToLife() {
        return bazaarWorking++;
    }

    public int calculateScore() {
        int i, score = 1;
        for (i = 0; i < bazaarWorking; i++) {
            score = score * 5;
        }
        return score;
    }

    public int getCostToUpgrade() {
        return (level + 1) * costToUpgrade;
    }

    public void updateMultiplier() {
        multiplier = multiplier + (double) level * (0.2);
    }
}
