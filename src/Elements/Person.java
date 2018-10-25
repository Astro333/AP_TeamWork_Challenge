package Elements;

public class Person {

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
}
