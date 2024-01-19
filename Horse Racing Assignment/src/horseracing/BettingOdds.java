package horseracing;

public class BettingOdds {
    private Race raceObj;
    public BettingOdds(Race race){
        raceObj = race;
    }

    public int getOdds(){
        return 0;
    }

    public void displayHorsenames(){
        for (Horse horse : raceObj.getHorses()) {
            System.out.println("- " + horse.getName());
        }
    }
}
