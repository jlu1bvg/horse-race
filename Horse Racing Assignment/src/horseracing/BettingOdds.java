package horseracing;

public class BettingOdds {
    private Race raceObj;
    public BettingOdds(Race race){
        raceObj = race;
    }
    //Uses the increment system to calculate odds (the higher the increment the higher chances of winning, but lower odds)
    public double getOdds(Horse horse, String betType){
        //Uses expotential function to calcuate the odds for win, place and show. 
        //Using desmos, I created this formula: f(x) = 20(0.86)^x
        //To calculate the odds I just divided by 2.0
        double odds = 0;
        if(betType.equals("win")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i))/2.0;
                            break;
                        }
                    }
        }
        //To calculate the odds for place I just subtracted 0.5 from the odds for win
        if(betType.equals("place")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i) - 1)/2.0;
                            break;
                        }
                    }
        }
        //To calculate the odds for show, I subtracted 0.5 from the odds for place
        if(betType.equals("show")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i) - 2)/2.0;
                            if(odds < 1)
                                odds += 0.5;
                            break;
                        }
                    }
        }
        return odds;
    }
}
