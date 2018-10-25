import Elements.Block;

import java.util.Scanner;

public class Controller {

    private Scanner scanner = new Scanner(System.in);

    private final String ADD_BLOCK_COMMAND_REGEX;
    private final String REMOVE_BLOCK_COMMAND_REGEX;
    private final String UPGRADE_BLOCK_COMMAND_REGEX;

    private final String ADD_HOME_COMMAND_REGEX;
    private final String UPGRADE_HOME_COMMAND_REGEX;

    private final String ADD_BUILDING_COMMAND_REGEX;
    private final String REMOVE_BUILDING_COMMAND_REGEX;
    private final String UPGRADE_BUILDING_COMMAND_REGEX;


    {
        ADD_BLOCK_COMMAND_REGEX = "add block";
        REMOVE_BLOCK_COMMAND_REGEX = "remove \\d+";
        UPGRADE_BLOCK_COMMAND_REGEX = "upgrade \\d+";

        ADD_HOME_COMMAND_REGEX = "add home \\d+ \\d+ \\d+";
        UPGRADE_HOME_COMMAND_REGEX = "upgrade \\d+ \\d+( floor)|( unit)";

        ADD_BUILDING_COMMAND_REGEX = "add [A-Za-z]+ \\d+";
        REMOVE_BUILDING_COMMAND_REGEX = "remove \\d+ \\d+";
        UPGRADE_BUILDING_COMMAND_REGEX = "upgrade \\d+ \\d+";
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
        input = input.toLowerCase();

        while(!input.equals("yield")){

            if(input.matches(ADD_BLOCK_COMMAND_REGEX)){
                Integer blockId = currentPlayer.lastBlockId;
                currentPlayer.playerBlocks.put(blockId, new Block());
                ++currentPlayer.lastBlockId;
            }
            else if(input.matches(REMOVE_BLOCK_COMMAND_REGEX)){
                Integer blockId = Integer.valueOf(input.split(" ")[1]);
                if(currentPlayer.lastBlockId == blockId)
                    --currentPlayer.lastBlockId;
                currentPlayer.playerBlocks.remove(blockId);
            }
            else if(input.matches(UPGRADE_BLOCK_COMMAND_REGEX)){
                Integer blockId = Integer.valueOf(input.split(" ")[1]);
                currentPlayer.playerBlocks.get(blockId).upgrade();
            }


            else if(input.matches(ADD_HOME_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[2]);
                int floor = Integer.parseInt(s[3]);
                int unit = Integer.parseInt(s[4]);

                currentPlayer.playerBlocks.get(blockId).addHome(floor, unit);
            }
            else if(input.matches(UPGRADE_HOME_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int homeId = Integer.parseInt(s[2]);
                if(s.length == 4){
                    currentPlayer.upgradeHome(blockId, homeId, s[3]);
                }
                else if(s.length == 5){
                    currentPlayer.upgradeHome(blockId, homeId, s[3]+" "+s[4]);
                }
            }


            else if(input.matches(ADD_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[2]);
                currentPlayer.addBuilding(s[1], blockId);
            }
            else if(input.matches(REMOVE_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int buildingId = Integer.parseInt(s[2]);

                currentPlayer.removeBuilding(buildingId, blockId);
            }
            else if(input.matches(UPGRADE_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int buildingId = Integer.parseInt(s[2]);
                currentPlayer.upgradeBuilding(buildingId, blockId);
            }

            input = scanner.next();
            input = input.toLowerCase();
        }

        isProcessing = false;
    }
}
