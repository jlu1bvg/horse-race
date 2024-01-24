package horseracing;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private Player player;
    private Player player2;
    private Player player3;
    private List<Horse> results;
    private int playerCount;


    public Race(List<Horse> horses, double raceLength, String raceSurface, Player player) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
        this.player = player;
        playerCount=1;
    }

    public Race(List<Horse> horses, double raceLength, String raceSurface, Player player,Player player2) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
        this.player = player;
        this.player2=player2;
        playerCount=2;
    }

    public Race(List<Horse> horses, double raceLength, String raceSurface, Player player,Player player2,Player player3) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
        this.player = player;
        this.player2=player2;
        this.player3=player3;
        playerCount=3;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public int numHorses(){
        return horses.size();
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public Horse getCurrentHorse(){
        return horses.get(currentHorse);
    }

    public String getRaceSurface() {
        return raceSurface;
    }

    public void displayRaceInfo(BettingOdds odds,int terrain) { 
        System.out.println("Race Information");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("List of Horses:");
        /*for (Horse horse : horses) {
            System.out.println("- " + horse.getName());
        }
        */
        System.out.printf("%-29s| %-18s| %-15s| %-15s| %-15s| %-15s\n", "Horse Name", "Preferred Length", "Terrain Rating", "Win Odds", "Place Odds", "Show Odds");

        for (int i = 0; i < 115; i++){
            System.out.print("-");
        }
        System.out.println();
        for (Horse horse : horses) {
            int temp = (int)(odds.getOdds(horse, "win")*2);
            int temp2 = (int)(odds.getOdds(horse, "place")*2);
            int temp3 = (int)(odds.getOdds(horse, "show")*2);
            int terrainRating=0;
            if (terrain == HorseRacingHelper.GRASS)
                terrainRating = horse.getGrassRating();
            else if (terrain == HorseRacingHelper.DIRT)
                terrainRating = horse.getDirtRating();
            else if(terrain==HorseRacingHelper.MUD)
                terrainRating = horse.getMudRating();
            else if(terrain==HorseRacingHelper.AIR)
                terrainRating=horse.getAirRating();
            else if(terrain==HorseRacingHelper.PARADISUS)
                terrainRating=horse.getParadisusRating();
            else
                terrain=horse.getKitchenRating();
            System.out.printf("%-3s %-25s| %-18s| %-15s| %-15s| %-15s| %-15s\n",
                horse.getNumber()+":",
                horse.getName(), 
                horse.getPreferredLength(), 
                terrainRating,
                temp % 2 == 0 ? temp/2 + "-1" : temp + "-2",
                temp2 % 2 == 0 ? temp2/2 + "-1" : temp2 + "-2",
                temp3 % 2 == 0 ? temp3/2 + "-1" : temp3 + "-2"
            );
        }
        BetDialogue betDialogue = new BetDialogue(player);
        betDialogue.CreateBetDialogue(player, odds, horses);
        if(playerCount>=2){
            BetDialogue betDialogue2 = new BetDialogue(player);
            betDialogue2.CreateBetDialogue(player2, odds, horses);
        }
        if(playerCount==3){
            BetDialogue betDialogue3 = new BetDialogue(player);
            betDialogue3.CreateBetDialogue(player3, odds, horses);
        }
    }

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
    }

    public int getIncrement(Horse horse){
        int increment = 1;
        increment += (int)(7 - Math.abs(horse.getPreferredLength()-this.raceLength));
        if(raceSurface.equals("Grass"))
            increment += horse.getGrassRating()/2;
        else if(raceSurface.equals("Mud"))
            increment += horse.getMudRating()/2;
        else if(raceSurface.equals("Dirt"))
            increment += horse.getDirtRating()/2;
        else if(raceSurface.equals("Air"))
            increment += horse.getAirRating()/2;
        else if(raceSurface.equals("Dirt"))
            increment += horse.getParadisusRating()/2;
        else if(raceSurface.equals("Kitchen"))
            increment += horse.getKitchenRating()/2;
        return increment;
    }

    public void startRace(){
        System.out.println("Race is starting");
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(1000);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        while(!done){
            HorseRacingHelper.pauseForMilliseconds(100);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();
           

            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition((int)(Math.random() * getIncrement(horse))+1);
            }

            displayResults();
            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    /*
       we only focus on the first 3 places.
       exacta is built on to a box.
       box is no order specified.
    */

    public void displayBetResults(){
        System.out.println("\n\nBet Results");
        System.out.println("------------");
        System.out.print("Type of bet: ");
        System.out.println(player.getBetType());
        System.out.print("Bet Log (Potential Earnings): ");
        System.out.println(player.getEarnings());
        if(player.getEarnings() == null){
            System.out.println("no bet placed, therefore no reward.");
        }else{
            if(player.getBetType().equals("win")){
                if(player.getEarnings().get(results.get(0).getName()) == null){
                    System.out.println("you lost the bet.");
                }else{
                    System.out.println("You won " + player.getEarnings().get(results.get(0).getName()) + " dollars");
                    player.betWon(results.get(0).getName());
                }
            }else if(player.getBetType().equals("place")){
                if(player.getEarnings().get(results.get(0).getName()) == null && player.getEarnings().get(results.get(1).getName()) == null){
                    System.out.println("you lost the bet.");
                }else{
                    if(player.getEarnings().get(results.get(0).getName()) != null){
                        System.out.println("You won " + player.getEarnings().get(results.get(0).getName()) + " dollars");
                        player.betWon(results.get(0).getName());
                    }else if(player.getEarnings().get(results.get(1).getName()) != null){
                        System.out.println("You won " + player.getEarnings().get(results.get(1).getName()) + " dollars");
                        player.betWon(results.get(1).getName());
                    }
                }
            }else if(player.getBetType().equals("show")){
                if(player.getEarnings().get(results.get(0).getName()) == null && player.getEarnings().get(results.get(1).getName()) == null && player.getEarnings().get(results.get(2).getName()) == null){
                    System.out.println("you lost the bet.");
                }else{
                    if(player.getEarnings().get(results.get(0).getName()) != null){
                        System.out.println("You won " + player.getEarnings().get(results.get(0).getName()) + " dollars");
                        player.betWon(results.get(0).getName());
                    }else if(player.getEarnings().get(results.get(1).getName()) != null){
                        System.out.println("You won " + player.getEarnings().get(results.get(1).getName()) + " dollars");
                        player.betWon(results.get(1).getName());
                    }else{
                        System.out.println("You won " + player.getEarnings().get(results.get(2).getName()) + " dollars");
                        player.betWon(results.get(2).getName());
                    }
                }
            } else if (player.getBetType().equals("box")) {
                String[] horses = player.getEarnings().keySet().toArray(new String[0])[0].split(" & ");
                boolean firstHorseWon = results.get(0).getName().equals(horses[0]) || results.get(0).getName().equals(horses[1]);
                boolean secondHorseWon = results.get(1).getName().equals(horses[0]) || results.get(1).getName().equals(horses[1]);
            
                if (firstHorseWon && secondHorseWon) {
                    player.betWon(horses[0] + " & " + horses[1]);
                    System.out.println("You won " + player.getEarnings().get(horses[0] + " & " + horses[1]) + " dollars on box bet");
                } else {
                    System.out.println("You lost the box bet.");
                }
            } else if (player.getBetType().equals("exacta")) {
                String[] horses = player.getEarnings().keySet().toArray(new String[0])[0].split(" -> ");
                if (results.get(0).getName().equals(horses[0]) && results.get(1).getName().equals(horses[1])) {
                    player.betWon(horses[0] + " -> " + horses[1]);
                    System.out.println("You won " + player.getEarnings().get(horses[0] + " -> " + horses[1]) + " dollars on exacta bet");
                } else {
                    System.out.println("You lost the exacta bet.");
                }
            }
            
        }
    }

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
