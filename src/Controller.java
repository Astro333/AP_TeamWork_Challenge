import java.util.Scanner;

public class Controller {

    private Scanner scanner = new Scanner(System.in);
    private Player p1, p2;
    private Player currentPlayer;

    private boolean isProcessing = true;

    public boolean isProcessing() {
        return isProcessing;
    }

    private void addCreditToPlayer(Player p){
        int amount = 0;
        //ToDo: calculate amount based on player
        p.addCredit(amount);
    }

    private void showScore(Player p){}

    private void addBlock(Player currentPlayer){
    }

    public void startProcessing(){

        isProcessing = true;
        String input;
        input = scanner.next();

        while(!input.equals("yield")){
            /*
            * write code here
            **/
            input = scanner.next();
        }

        isProcessing = false;
    }
}
