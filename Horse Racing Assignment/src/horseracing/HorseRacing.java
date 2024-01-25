package horseracing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HorseRacing {

     private static void writeScoresToCSV(Player player) {
        try (FileWriter csvWriter = new FileWriter("scores.csv", true)) {
            csvWriter.append(player.getName() + "," + player.getMoney() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }
     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;

        //input for players
        boolean validPlayerCount=false;
        int playerCount=1;
        while(!validPlayerCount){

            //text prompt
            System.out.print("Number of Players: ");
            try{

                //try to convert input to int
                playerCount=Integer.parseInt(in.nextLine());

                //cant have no players
                if(playerCount<=0){
                    System.out.println("Number too low");
                }
                else{
                    validPlayerCount=true;
                }
            }

            //input failsafe
            catch(NumberFormatException e){
                System.out.println("Invalid number");
            }
        }

        //create list of players
        PlayerContainer players=new PlayerContainer();
        for(int i=0;i<playerCount;i++){
            boolean nameEntered=false;
            String enteredName="";
            Player player=new Player();
            while(!nameEntered){

                //text prompt
                System.out.print("Player " + (i+1) + " name: ");
                enteredName=in.nextLine();

                //cant have empty name
                if(enteredName.equals("")){
                    System.out.println("Name cannot be empty");
                    nameEntered=false;
                }

                //sets player name
                else{
                    player.setName(enteredName);
                    nameEntered=true;
                }
            }

            //adds player to list
            players.addPlayer(player);
        }

        while(!gameOver){

            //input for race length
            boolean validLength=false;
            int length=0;
            while(!validLength){

                //text prompt
                System.out.print("Choose race length (leave empty for random): \nSHORT\nMIDDLE\nLONG\n");
                String input=in.nextLine();

                //sets race length based on input
                if(input.equals("")){
                    length=(int)(Math.random()*2);
                    validLength=true;
                }
                else if(input.toLowerCase().equals("short")){
                    length=0;
                    validLength=true;
                }
                else if(input.toLowerCase().equals("middle")){
                    length=1;
                    validLength=true;
                }
                else if(input.toLowerCase().equals("long")){
                    length=2;
                    validLength=true;
                }

                //failsafe
                else{
                    System.out.println("Invalid input");
                    validLength=false;
                }
            }
            
            //input for terrain type
            boolean validTerrain=false;
            int terrain=0;
            while(!validTerrain){

                //text prompt
                System.out.print("Choose race terrain (leave empty for random): \nGRASS\nDIRT\nMUD\nAIR\nPARADISUS\nKITCHEN\n");
                String input=in.nextLine();

                //sets terrain type based on input
                if(input.equals("")){
                    terrain=(int)(Math.random()*5);
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("grass")){
                    terrain=0;
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("dirt")){
                    terrain=1;
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("mud")){
                    terrain=2;
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("air")){
                    terrain=3;
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("paradisus")){
                    terrain=4;
                    validTerrain=true;
                }
                else if(input.toLowerCase().equals("kitchen")){
                    terrain=5;
                    validTerrain=true;
                }

                //failsafe
                else{
                    System.out.println("Invalid input");
                    validTerrain=false;
                }
            }

            //horse stuff for race
            int numHorsesInRace = (int)(Math.random()*7)+5;
            List<Horse> horseList=HorseRacingHelper.shuffleHorses();
            int addedHorses=0;
            boolean addingHorses=true;

            //adding specific horses from csv
            while(addingHorses){
                boolean validHorse=false;
                while(!validHorse){

                    //text prompt
                    System.out.print("Add horse to race (leave empty to continue): ");
                    String input=in.nextLine();

                    //exits loop
                    if(input.equals("")){
                        addingHorses=false;
                        break;
                    }
                    
                    //finds horse to move to front of list
                    for(int i=0;i<horseList.size();i++){
                        if(input.equals(horseList.get(i).getName())){
                            Collections.swap(horseList,addedHorses,i);
                            System.out.println(input+" added to race");
                            addedHorses++;
                            validHorse=true;
                        }
                    }

                    //failsafe
                    if(!validHorse){
                        System.out.println("Horse not found");
                    }
                }

            }

            //input for number of horses in race
            boolean validNum=false;
            while(!validNum){
                System.out.print("Number of horses in race (leave empty for random): ");
                String input=in.nextLine();

                //sets num horses to random
                if(input.equals("")){
                    validNum=true;
                    break;
                }

                //if something entered
                try{

                    //tries to convert to int
                    int enteredNum=Integer.parseInt(input);

                    //number failsafes
                    if(enteredNum<=0){
                        System.out.println("Number too low");
                        validNum=false;
                    }
                    else if(enteredNum>horseList.size()){
                        System.out.println("Number too high");
                        validNum=false;
                    }

                    //sets num horses
                    else{
                        numHorsesInRace=enteredNum;
                        validNum=true;
                    }
                }
                
                //failsafe
                catch(NumberFormatException e){
                    System.out.println("Invalid number");
                    validNum=false;
                }
            }

            //shortens horse list to ones in race
            horseList=horseList.subList(0,numHorsesInRace);

            HorseRacingHelper.clearConsole();

            
            
            Race race = null;

            //race prep
            race = HorseRacingHelper.createRace(numHorsesInRace, length, terrain, players,horseList);
            BettingOdds odds = new BettingOdds(race);
            race.displayRaceInfo(odds,terrain);
            race.startRace();
            
            //after race stuff
            System.out.println("Race is Over");
            race.displayBetResults();

            //resets player bets
            for(int i=0;i<players.getPlayers().size();i++){
                players.getPlayers().get(i).resetBet();
            }
            gameOver = playAgain(in).equals("n");

            //writes player data to csv
            if(gameOver){
                for(int i=0;i<players.getPlayers().size();i++){
                    writeScoresToCSV(players.getPlayers().get(i));
                }
            }
            
        }

        
    }

    private static String playAgain(Scanner in) {
        System.out.print("\u001B[?25l");  // Hide the cursor
        boolean validInput = false;
        String temp = "";
        
        //replay
        while(!validInput){

            //text prompt
            System.out.print("Play Again: (y/n): ");
            String result = in.nextLine();

            //decision
            if (result.equals("y")){
                temp = "y";
                validInput = true;
            }else if (result.equals("n")){
                temp = "n";
                validInput = true;

            //failsafe
            }else
                System.out.println("Please enter y/n:");
        }
        return temp;
    }
}
