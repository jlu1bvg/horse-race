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

    public void displayRaceInfo(BettingOdds odds) { 
        Track t = new Track();
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("List of Horses:");
        /*for (Horse horse : horses) {
            System.out.println("- " + horse.getName());
        }
        */
        System.out.printf("%-20s| %-20s| %-15s |%-15s| %-15s |%-15s\n", "Horse Name", "Preferred Length", "Dirt Rating", "Mud Rating", "Grass Rating", "Odds");

        for (int i = 0; i < 100; i++){
            System.out.print("-");
        }
        System.out.println();
        for (Horse horse : horses) {
            System.out.printf("%-20s| %-20s| %-15s| %-15s |%-15s| %-15s\n",
                horse.getName(), 
                horse.getPreferredLength(), 
                horse.getDirtRating(),
                horse.getMudRating(), 
                horse.getGrassRating(),
                (int)(odds.getOdds(horse)*2) + ":" + 2
            );
        }
        BetDialogue betDialogue = new BetDialogue(player);
        betDialogue.CreateBetDialogue(player, odds, horses);
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
        return increment;
    }

    public void startRace(){
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
                horse.incrementPosition((int)(Math.random() * getIncrement(horse)));
            }

            displayResults();

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    public void displayBetResults(){
        System.out.println("\n\nBet Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            player.betWon(results.get(i).getName());
        }
    }

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
