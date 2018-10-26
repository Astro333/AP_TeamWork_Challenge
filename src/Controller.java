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
    private Player frontPlayer;
    private boolean isProcessing = true;

    public boolean isProcessing() {
        return isProcessing;
    }

    public void startProcessing(){

        isProcessing = true;

        p1 = new Player();
        p2 = new Player();

        currentPlayer = p1;
        frontPlayer = p2;

        StringBuilder log = new StringBuilder();

        String input;
        input = scanner.next();
        input = input.toLowerCase();

        while(!input.equals("yield")){

            if(input.matches(ADD_BLOCK_COMMAND_REGEX)){
                if(!currentPlayer.addBlock())
                    log.append("not possible\n");
            }

            else if(input.matches(REMOVE_BLOCK_COMMAND_REGEX)){

                int blockId = Integer.parseInt(input.split(" ")[1]);
                currentPlayer.removeBlock(blockId);
            }
            else if(input.matches(UPGRADE_BLOCK_COMMAND_REGEX)){
                int blockId = Integer.parseInt(input.split(" ")[1]);
                if(!currentPlayer.upgradeBlock(blockId))
                    log.append("not possible\n");
            }

            else if(input.matches(ADD_HOME_COMMAND_REGEX)){

                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[2]);
                int floor = Integer.parseInt(s[3]);
                int unit = Integer.parseInt(s[4]);
                if(!currentPlayer.addHome(blockId, floor, unit))
                    log.append("not possible\n");
            }
            else if(input.matches(UPGRADE_HOME_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int homeId = Integer.parseInt(s[2]);
                //means command is unit or floor
                if(s.length == 4){
                    if(!currentPlayer.upgradeHome(blockId, homeId, s[3]))
                        log.append("not possible\n");
                }
                else if(s.length == 5){
                    if(!currentPlayer.upgradeHome(blockId, homeId, s[3]+" "+s[4]))
                        log.append("not possible\n");
                }
            }


            else if(input.matches(ADD_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[2]);
                if(!currentPlayer.addBuilding(s[1], blockId))
                    log.append("not possible\n");
            }
            else if(input.matches(REMOVE_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int buildingId = Integer.parseInt(s[2]);
                if(!currentPlayer.removeBuilding(buildingId, blockId))
                    log.append("not possible\n");
            }
            else if(input.matches(UPGRADE_BUILDING_COMMAND_REGEX)){
                String[] s = input.split(" ");
                int blockId = Integer.parseInt(s[1]);
                int buildingId = Integer.parseInt(s[2]);
                if(!currentPlayer.upgradeBuilding(buildingId, blockId))
                    log.append("not possible\n");
            }

            else if(input.equals("done")){
                currentPlayer.passDay();

                if(currentPlayer.equals(p2)){
                    currentPlayer = p1;
                    frontPlayer = p2;
                }
                else{
                    currentPlayer = p2;
                    frontPlayer = p1;
                }
            }

            else if(input.equals("see gills")){
                log.append(currentPlayer.getCredit()).append("\n");
            }

            else if(input.equals("see score")){
                log.append(currentPlayer.getScore()).append("\n");
            }

            else if(input.matches("loot \\d+")){
                String s = input.split(" ")[1];
                int lootedAmount = currentPlayer.loot(frontPlayer, Integer.parseInt(s));
                if(lootedAmount < 0){
                    log.append("not possible\n");
                }

                else{
                    currentPlayer.addCredit(lootedAmount);
                }
            }

            else if(input.matches("attack \\d+")){
                String s = input.split(" ")[1];
                int blockId = Integer.parseInt(s);


            }

            input = scanner.next();
            input = input.toLowerCase();
        }

        isProcessing = false;
    }
}
