package horseracing;

import java.util.Scanner;

public class HorseRacing {

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        System.out.print("Players: ");
        boolean validPlayerCount=false;
        int playerCount=1;
        while(!validPlayerCount){
            try{
                playerCount=Integer.parseInt(in.nextLine());
                if(playerCount<=0){
                    System.out.println("Number too low");
                }
                else if(playerCount>3){
                    System.out.println("Number too high");
                }
                else{
                    validPlayerCount=true;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid number");
            }
        }
        Player player=new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        if(playerCount==1){
            System.out.print("Player1 name: ");
            player.setName(in.nextLine());
            System.out.println();
        }
        if(playerCount==2){
            System.out.print("Player1 name: ");
            player.setName(in.nextLine());
            System.out.println();
            
            System.out.print("Player2 name: ");
            player2.setName(in.nextLine());
            System.out.println();
        }
        if(playerCount==3){
            System.out.print("Player1 name: ");
            player.setName(in.nextLine());
            System.out.println();

            System.out.print("Player2 name: ");
            player2.setName(in.nextLine());
            System.out.println();

            System.out.print("Player3 name: ");
            player3.setName(in.nextLine());
            System.out.println();
        }

        while(!gameOver){
            HorseRacingHelper.clearConsole();

            int numHorsesInRace = (int)(Math.random()*7)+5;
            
            if(playerCount==1){
                Race race = HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT, player);
                BettingOdds odds = new BettingOdds(race);
                race.displayRaceInfo(odds);
                race.startRace();
            }
            if(playerCount==2){
                Race race=HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT, player,player2);
                BettingOdds odds=new BettingOdds(race);
                race.displayRaceInfo(odds);
                race.startRace();
            }
            if(playerCount==3){
                Race race=HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT, player,player2,player3);
                BettingOdds odds=new BettingOdds(race);
                race.displayRaceInfo(odds);
                race.startRace();
            }
            
            System.out.println("Race is Over");
            gameOver = playAgain(in);
            
        }

        
    }

    private static boolean playAgain(Scanner in) {
        System.out.print("\u001B[?25l");  // Hide the cursor
        System.out.print("Play Again: (y/n): ");
        String result = in.nextLine();

        if (result.equals("n"))
            return true;

        return false;

    }
}
