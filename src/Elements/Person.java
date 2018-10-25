package Elements;

public class Person {

    private int daysWorked = 0;
    public int getIncome(){
        return daysWorked * 100;
    }

    public int getDaysWorked() {
        return daysWorked;
    }
    public int incremenetDaysWorked(int daysWorked){
        this.daysWorked++;
        return this.daysWorked;
    }
    return 0 ;
}
