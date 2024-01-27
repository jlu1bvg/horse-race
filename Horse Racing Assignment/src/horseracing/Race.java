package horseracing;

import java.util.ArrayList;
import java.util.List;

public class Race {
    // instance variables
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud", "air", "paradisus", "kitchen" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private PlayerContainer players;
    private List<Horse> results;

    //constructor 
    public Race(List<Horse> horses, double raceLength, String raceSurface, PlayerContainer players) {
        this.horses = horses; //set the horses
        this.raceLength = raceLength; //set race length
        this.raceSurface = raceSurface; //set surface
        this.currentHorse = 0; //set current horse to 0 so we can iterate in the future
        this.results = new ArrayList<Horse>(); //create the array for results, which we can populate overtime 
        this.players = players; //gets the list of players participating in the game.
    }

    //getters  and setters 
    
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

    //display race info 
    public void displayRaceInfo(BettingOdds odds,int terrain) { 
        
        for(int i=0;i<players.getPlayers().size();i++){ //for each player, create a bet dialogue
            HorseRacingHelper.clearConsole();
            System.out.println("Race Information\n");
            System.out.printf("%-15s %-30s\n","Race Surface: " , raceSurface);
            System.out.printf("%-15s %-30s\n","Race Length: " , raceLength + " furlongs");
            System.out.println("\nList of Horses:\n");
            /*for (Horse horse : horses) {
                System.out.println("- " + horse.getName());
            }
            */
            //prints out the horses in a table, formatted by shifting the columns to the left
            System.out.printf("%-29s| %-18s| %-15s| %-15s| %-15s| %-15s\n", "    Horse Name", "Preferred Length", "Terrain Rating", "Win Odds", "Place Odds", "Show Odds");

            //creates the line of dashes as table body
            for (int j = 0; j < 115; j++){
                System.out.print("-");
            }
            System.out.println();
            //printing out table
            for (Horse horse : horses) {
                //getting odds for win place show
                int temp = (int)(odds.getOdds(horse, "win")*2);
                int temp2 = (int)(odds.getOdds(horse, "place")*2); 
                int temp3 = (int)(odds.getOdds(horse, "show")*2);
                int terrainRating=0;
                //getting the terrain rating
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
                    terrainRating=horse.getKitchenRating();
                //printing more table stuff
                System.out.printf("%-3s %-25s| %-18s| %-15s| %-15s| %-15s| %-15s\n",
                    horse.getNumber()+":",
                    horse.getName(), 
                    horse.getPreferredLength(), 
                    terrainRating,
                    temp % 2 == 0 ? temp/2 + "-1" : temp + "-2", //this is a ternary operator that simplifies an if statement that checks if the number is even or odd
                    temp2 % 2 == 0 ? temp2/2 + "-1" : temp2 + "-2",
                    temp3 % 2 == 0 ? temp3/2 + "-1" : temp3 + "-2"
                );
            }
            BetDialogue betDialogue = new BetDialogue(players.getPlayers().get(i));
            betDialogue.CreateBetDialogue(players.getPlayers().get(i), odds, horses);
        }
    }

    public void displayResults(){ //display bet results 
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            //this prints out i (the place) and the name of the horse and its number
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
    }

    //Increment system
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
        else if(raceSurface.equals("Paradisus Varadero"))
            increment += horse.getParadisusRating()/2;
        else if(raceSurface.equals("Mr. Deslauriers Malevolent Kitchen"))
            increment += horse.getKitchenRating()/2;
        return increment;
    }

    public void startRace(int terrainType){
        System.out.print("\u001B[?25l");
        HorseRacingHelper.clearConsole();
        System.out.println("Race is starting");
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(1000);

        //honor check
        boolean honored = false;
        for(int i=0;i<players.getPlayers().size();i++){ 
            if(players.getPlayers().get(i).isHonoredOne()){
                honored = true;
            }
        }

        //custom intro music

        if(terrainType==HorseRacingHelper.AIR&&honored==false){
            HorseRacingHelper.playBackgroundMusicAndWait("Word of Honor OST Opening Theme Song.wav");
        }
        else if(terrainType==HorseRacingHelper.PARADISUS&&honored==false){
            HorseRacingHelper.playBackgroundMusicAndWait("Crab Rave.wav");
        }
        else if(terrainType==HorseRacingHelper.KITCHEN&&honored==false){
            HorseRacingHelper.playBackgroundMusicAndWait("C418 Mutation.wav");
        }
        else{
            HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        }

        //custom race music

        if(honored == true){
            HorseRacingHelper.playBackgroundMusic("Gojo Satoru - The Honored One.wav", true);
        }
        else if(terrainType==HorseRacingHelper.AIR){
            HorseRacingHelper.playBackgroundMusic("Word of Honor OST Opening Theme Song2.wav", true);
        }
        else if(terrainType==HorseRacingHelper.PARADISUS){
            HorseRacingHelper.playBackgroundMusic("Crab Rave2.wav", true);
        }
        else if(terrainType==HorseRacingHelper.KITCHEN){
            HorseRacingHelper.playBackgroundMusic("C418 Mutation2.wav", true);
            HorseRacingHelper.playBackgroundMusic("minecraft-eating-sound.wav", true);
            HorseRacingHelper.playBackgroundMusic("minecraft-drinking-sound-effect.wav", true);
        }
        else{
            HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);
        }        

        while(!done){
            if(honored){
                HorseRacingHelper.pauseForMilliseconds(500);
            }else{
                HorseRacingHelper.pauseForMilliseconds(100);
            }
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

    public void displayBetResults(){ //this displays the bet results
        System.out.println("\n\nBet Results");
        System.out.println("------------");
        for(int i=0;i<players.getPlayers().size();i++){ //iterate through all players
            System.out.println(players.getPlayers().get(i).getName()+"'s bet"); //gets current player's name and does some concatenation to print with 's bet'
            System.out.print("Type of bet: ");
            System.out.println(players.getPlayers().get(i).getBetType()); //shows the bet type
            System.out.print("Bet Log (Potential Earnings): ");
            System.out.println(players.getPlayers().get(i).getEarnings()); //shows their earnings (potential)
            if(players.getPlayers().get(i).getEarnings() == null){
                System.out.println("No bet placed, therefore no reward.");
            }else{
                if(players.getPlayers().get(i).getBetType().equals("win")){ //if they placed a win bet
                    if(players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) == null){ //if theu don't have a bet on the winner then 
                        System.out.println("You lost the bet." + "\n"); //u lose the bet
                    }else{ //they won, so they get money. bet won gets called which adds the money to their account
                        System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) + " dollars" + "\n");
                        players.getPlayers().get(i).betWon(results.get(0).getName());
                    }
                }else if(players.getPlayers().get(i).getBetType().equals("place")){ //if they placed a place bet
                    if(players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) == null && players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) == null){ //if they don't have a bet on the first or second place horse
                        System.out.println("You lost the bet." + "\n");
                    }else{
                        if(players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) != null){ //if they have a bet on the first place horse
                            System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) + " dollars" + "\n"); 
                            players.getPlayers().get(i).betWon(results.get(0).getName());
                        }else if(players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) != null){ //if they have a bet on the second place horse
                            System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) + " dollars" + "\n"); 
                            players.getPlayers().get(i).betWon(results.get(1).getName());
                        }
                    }
                }else if(players.getPlayers().get(i).getBetType().equals("show")){ //show bet
                    //if they don't have a bet on the first, second, or third place horse
                    if(players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) == null && players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) == null && players.getPlayers().get(i).getEarnings().get(results.get(2).getName()) == null){
                        System.out.println("You lost the bet." + "\n");
                    }else{
                        //if they have a bet on the first place horse
                        if(players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) != null){
                            System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(0).getName()) + " dollars" + "\n");
                            players.getPlayers().get(i).betWon(results.get(0).getName());
                        //if they have a bet on the second place horse
                        }else if(players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) != null){
                            System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(1).getName()) + " dollars" + "\n");
                            players.getPlayers().get(i).betWon(results.get(1).getName());
                        }else{
                            //if they have a bet on the third place horse
                            System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(results.get(2).getName()) + " dollars" + "\n");
                            players.getPlayers().get(i).betWon(results.get(2).getName());
                        }
                    }
                } else if (players.getPlayers().get(i).getBetType().equals("box")) { //if they placed a box bet
                    String[] horses = players.getPlayers().get(i).getEarnings().keySet().toArray(new String[0])[0].split(" & "); //gets the horses they bet on
                    boolean firstHorseWon = results.get(0).getName().equals(horses[0]) || results.get(0).getName().equals(horses[1]); //checks if the first horse did well
                    boolean secondHorseWon = results.get(1).getName().equals(horses[0]) || results.get(1).getName().equals(horses[1]); //checks if the second horse did well
                
                    if (firstHorseWon && secondHorseWon) { //if both horses did well (top 2)
                        players.getPlayers().get(i).betWon(horses[0] + " & " + horses[1]); //add the money to their account
                        System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(horses[0] + " & " + horses[1]) + " dollars on box bet" + "\n");
                    } else {
                        System.out.println("You lost the box bet." + "\n");
                    }
                } else if (players.getPlayers().get(i).getBetType().equals("exacta")) { //if they placed an exacta bet
                    String[] horses = players.getPlayers().get(i).getEarnings().keySet().toArray(new String[0])[0].split(" -> "); //splits the input into the horses they bet on
                    if (results.get(0).getName().equals(horses[0]) && results.get(1).getName().equals(horses[1])) { //if the horses they bet on are the top 2 in order
                        players.getPlayers().get(i).betWon(horses[0] + " -> " + horses[1]);
                        System.out.println("You won " + players.getPlayers().get(i).getEarnings().get(horses[0] + " -> " + horses[1]) + " dollars on exacta bet" + "\n");
                    } else {
                        System.out.println("You lost the exacta bet." + "\n");
                    }
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
