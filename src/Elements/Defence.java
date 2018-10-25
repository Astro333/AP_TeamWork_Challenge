package Elements;


import java.util.ArrayList;

public class Defence extends GilgArmy {

    public static final int peopleNeededToBuild = 30;
    public static final int peopleNeededToUpgrade = 0;
    public static final int costToBuild = 10000;
    public static final int costToUpgrade = 5000;
    private int defenceLevel = 1;
    private ArrayList<Person> people = new ArrayList<>();
    private int defenceWorking = 1;

    public int getDefenceLevel() {
        return defenceLevel;
    }

    public void upgrade() {
        defenceLevel++;
    }

    public void addWorker(Person p) {
        people.add(p);
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public int additionToLife() {
        return defenceLevel++;
    }

    public int calculateScore() {
        int i, score = 1;
        for (i = 0; i < defenceWorking; i++) {
            score = score * 15;
        }
        return score;
    }
}
