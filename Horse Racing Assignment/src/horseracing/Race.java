package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private Player player;
    private List<Horse> results;


    public Race(List<Horse> horses, double raceLength, String raceSurface, Player player) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
        this.player = player;
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

    public String getRaceSurface() {
        return raceSurface;
    }

    public void displayRaceInfo() { //yields btw
        Scanner console = new Scanner(System.in);
        String result = "y"; 
    
        while (result.equals("y")) {
            System.out.println("Race Information:");
            System.out.println("Race Surface: " + raceSurface);
            System.out.println("Race Length: " + raceLength + " furlongs");
            System.out.println("List of Horses:");
            for (Horse horse : horses) {
                System.out.println("- " + horse.getName());
            }
            System.out.println("Remaining money: " + player.getMoney());
            System.out.println("do u want to bet (y/n)");
            result = console.nextLine();
    
            if (result.equals("y")) {
                int amount = 0;
                boolean validBetEntered = false;
                while (!validBetEntered) {
                    try {
                        System.out.println("how much do u bet");
                        amount = Integer.parseInt(console.nextLine());
                        validBetEntered = true;
                    } catch (NumberFormatException e) {
                        System.out.println("etan stop messing with me");
                    }
                }
    
                System.out.println("which horse do u choose? (say name)");
                String horseName = console.nextLine();
    
                System.out.println("do you want to bet the position? (y/n)");
                String betOnPosition = console.nextLine();
    
                if(!betOnPosition.equals("y") && !betOnPosition.equals("n")){
                    System.out.println("not valid so i ma take it as a no. ill take away all ur money though, congrats.");
                    //get some setter to remove player money rq
                }

                if (betOnPosition.equals("n")) {
                    player.placeBet(amount, horseName);
                } else if (betOnPosition.equals("y")) {
                    int position = 0;
                    boolean validPosEntered = false;

                    while (!validPosEntered) {
                        try {
                            System.out.println("Which position do you bet on? (say number)");
                            position = Integer.parseInt(console.nextLine());
                            validPosEntered = true;
                        } catch (NumberFormatException e) {
                            System.out.println("eric tan i know it was you");
                        }
                    }
                    player.placeBet(amount, horseName, position);
                }
    
                System.out.println("Do you want to continue betting? (y/n)");
                
                String temp = console.nextLine();

                if(!temp.equals("y") && !temp.equals("n")){
                    result = "n";
                    System.out.println("Invalid again so I ma ban you from the game goodbye");
                    //crash the client or something 
                }

                result = temp;
            } else {
                break; 
            }
        }
    }

    

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
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
                horse.incrementPosition((int)(Math.random() * 4));
            }

            displayResults();

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
