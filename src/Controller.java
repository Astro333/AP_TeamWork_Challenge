import Elements.Block;

import java.util.Scanner;

public class Controller {

    private Scanner scanner = new Scanner(System.in);

    private final String ADD_BLOCK_COMMAND_REGEX;
    private final String REMOVE_BLOCK_COMMAND_REGEX;
    private final String UPGRADE_BLOCK_COMMAND_REGEX;

    private final String ADD_HOME_COMMAND_REGEX;
    private final String UPGRADE_HOME_COMMAND_REGEX;

    private final String ADD_BAZAAR_COMMAND_REGEX;
    private final String REMOVE_BAZAAR_COMMAND_REGEX;
    private final String UPGRADE_BAZAAR_COMMAND_REGEX;

    private final String ADD_ATTACKARMY_COMMAND_REGEX;
    private final String ADD_DEFENCEARMY_COMMAND_REGEX;
    private final String REMOVE_ARMY_COMMAND_REGEX;
    private final String UPGRADE_ARMY_COMMAND_REGEX;

    {
        ADD_BLOCK_COMMAND_REGEX = "add block";
        REMOVE_BLOCK_COMMAND_REGEX = "remove \\d+";
        UPGRADE_BLOCK_COMMAND_REGEX = "upgrade \\d+";

        ADD_HOME_COMMAND_REGEX = "add home \\d+ \\d+ \\d+";
        UPGRADE_HOME_COMMAND_REGEX = "upgrade \\d+ \\d+( floor)|( unit)";

        ADD_BAZAAR_COMMAND_REGEX = "add bazaar \\d+";
        REMOVE_BAZAAR_COMMAND_REGEX = "remove \\d+ \\d+";
        UPGRADE_BAZAAR_COMMAND_REGEX = "upgrade \\d+ \\d+";

        ADD_ATTACKARMY_COMMAND_REGEX = "add army \\d+";
        ADD_DEFENCEARMY_COMMAND_REGEX = "add defense \\d+";


        REMOVE_ARMY_COMMAND_REGEX = "upgrade \\d+ \\d+";
        UPGRADE_ARMY_COMMAND_REGEX = "remove \\d+ \\d+";
    }

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

            if(input.matches(ADD_BLOCK_COMMAND_REGEX)){
                Integer blockId = currentPlayer.lastBlockId;
                currentPlayer.playerBlocks.put(blockId, new Block());
                ++currentPlayer.lastBlockId;
            }
            else if(input.matches(REMOVE_BLOCK_COMMAND_REGEX)){
                Integer blockId = Integer.valueOf(input.split(" ")[1]);
                currentPlayer.playerBlocks.remove(blockId);
            }
            else if(input.matches(UPGRADE_BLOCK_COMMAND_REGEX)){
                Integer blockId = Integer.valueOf(input.split(" ")[1]);
                currentPlayer.playerBlocks.get(blockId)
            }


            else if(input.matches(ADD_HOME_COMMAND_REGEX)){

            }
            else if(input.matches(UPGRADE_HOME_COMMAND_REGEX)){

            }


            else if(input.matches(ADD_BAZAAR_COMMAND_REGEX)){

            }
            else if(input.matches(REMOVE_BAZAAR_COMMAND_REGEX)){

            }
            else if(input.matches(UPGRADE_BAZAAR_COMMAND_REGEX)){

            }



            else if(input.matches(ADD_ATTACKARMY_COMMAND_REGEX)){

            }
            else if(input.matches(ADD_DEFENCEARMY_COMMAND_REGEX)){

            }


            else if(input.matches(REMOVE_ARMY_COMMAND_REGEX)){

            }
            else if(input.matches(UPGRADE_ARMY_COMMAND_REGEX)){

            }

            input = scanner.next();
        }

        isProcessing = false;
    }
}
