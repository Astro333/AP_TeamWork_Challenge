package Elements;


import java.util.ArrayList;

public class Army extends GilgArmy {

    public static final int peopleNeededToBuild = 100;
    public static final int peopleNeededToUpgrade = 10;
    public static final int costToBuild = 15000;
    public static final int costToUpgrade = 20000;
    private int attackLevel = 1;
    private ArrayList<Person> people = new ArrayList<>();

    public int getArmyWorking() {
        return armyWorking;
    }

    private int armyWorking = 1;

    public int getAttackLevel() {
        return attackLevel;
    }

    @Override
    public void upgrade() {
        attackLevel++;
    }

    public void addWorker(Person p) {
        people.add(p);
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public int additionToLife() {
        return armyWorking++;
    }

    public int calculateScore() {
        int i, score = 1;
        for (i = 0; i < armyWorking; i++) {
            score = score * 10;
        }
        return score;
    }
}
