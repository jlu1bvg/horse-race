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
        boolean validPlayerCount=false;
        int playerCount=1;
        while(!validPlayerCount){
            System.out.print("Number of Players: ");
            try{
                playerCount=Integer.parseInt(in.nextLine());
                if(playerCount<=0){
                    System.out.println("Number too low");
                }
                else{
                    validPlayerCount=true;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid number");
            }
        }
        PlayerContainer players=new PlayerContainer();
        for(int i=0;i<playerCount;i++){
            boolean nameEntered=false;
            String enteredName="";
            Player player=new Player();
            while(!nameEntered){
                System.out.print("Player " + (i+1) + " name: ");
                enteredName=in.nextLine();
                if(enteredName.equals("")){
                    System.out.println("Name cannot be empty");
                    nameEntered=false;
                }
                else{
                    player.setName(enteredName);
                    nameEntered=true;
                }
            }
            players.addPlayer(player);
        }

        while(!gameOver){
            boolean validLength=false;
            int length=0;
            while(!validLength){
                System.out.print("Choose race length (leave empty for random): \nSHORT\nMIDDLE\nLONG\n");
                String input=in.nextLine();
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
                else{
                    System.out.println("Invalid input");
                    validLength=false;
                }
            }
            
            boolean validTerrain=false;
            int terrain=0;
            while(!validTerrain){
                System.out.print("Choose race terrain (leave empty for random): \nGRASS\nDIRT\nMUD\nAIR\nPARADISUS\nKITCHEN\n");
                String input=in.nextLine();
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
                else{
                    System.out.println("Invalid input");
                    validTerrain=false;
                }
            }

            int numHorsesInRace = (int)(Math.random()*7)+5;
            List<Horse> horseList=HorseRacingHelper.shuffleHorses();
            int addedHorses=0;
            boolean addingHorses=true;
            while(addingHorses){
                boolean validHorse=false;
                while(!validHorse){
                    System.out.print("Add horse to race (leave empty to continue): ");
                    String input=in.nextLine();
                    if(input.equals("")){
                        addingHorses=false;
                        break;
                    }
                    for(int i=0;i<horseList.size();i++){
                        if(input.equals(horseList.get(i).getName())){
                            Collections.swap(horseList,addedHorses,i);
                            System.out.println(input+" added to race");
                            addedHorses++;
                            validHorse=true;
                        }
                    }
                    if(!validHorse){
                        System.out.println("Horse not found");
                    }
                }

            }

            boolean validNum=false;
            while(!validNum){
                System.out.print("Horses in race (leave empty for random): ");
                String input=in.nextLine();
                if(input.equals("")){
                    validNum=true;
                    break;
                }
                try{
                    numHorsesInRace=Integer.parseInt(input);
                    validNum=true;
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid number");
                    validNum=false;
                }
            }
            horseList=horseList.subList(0,numHorsesInRace);

            HorseRacingHelper.clearConsole();

            
            
            Race race = null;

            race = HorseRacingHelper.createRace(numHorsesInRace, length, terrain, players,horseList);
            BettingOdds odds = new BettingOdds(race);
            race.displayRaceInfo(odds,terrain);
            race.startRace();
            
            System.out.println("Race is Over");
            race.displayBetResults();
            for(int i=0;i<players.getPlayers().size();i++){
                players.getPlayers().get(i).resetBet();
            }
            gameOver = playAgain(in).equals("n");

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
        while(!validInput){
            System.out.print("Play Again: (y/n): ");
            String result = in.nextLine();
            if (result.equals("y")){
                temp = "y";
                validInput = true;
            }else if (result.equals("n")){
                temp = "n";
                validInput = true;
            }else
                System.out.println("Please enter y/n:");
        }
        return temp;
    }
}
