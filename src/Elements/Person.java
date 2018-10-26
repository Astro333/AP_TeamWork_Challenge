package Elements;

public class Person {

    public Person(boolean isWorking){
        this.isWorking = isWorking;
        this.id = lastPersonId;
        ++lastPersonId;
    }
    private int id;
    public static int lastPersonId = 1;
    private int daysWorked = 0;
    public int getIncome(){
        return daysWorked * 100;
    }

    public int getDaysWorked() {
        return daysWorked;
    }
    public void incremenetDaysWorked(){
        ++this.daysWorked;
    }

    private boolean isWorking = false;

    public void setWorking(){
        isWorking = true;
    }

    public void setIdle(){
        isWorking = false;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public int getId() {
        return id;
    }
}
