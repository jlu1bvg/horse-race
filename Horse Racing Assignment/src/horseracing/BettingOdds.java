package horseracing;

public class BettingOdds {
    private Race raceObj;
    public BettingOdds(Race race){
        raceObj = race;
    }
    
    public double getOdds(Horse horse, String betType){
        double odds = 0;
        if(betType.equals("win")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i))/2.0;
                            break;
                        }
                    }
        }
        if(betType.equals("place")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i) - 1)/2.0;
                            break;
                        }
                    }
        }
        if(betType.equals("show")){
                for (int i = 14; i > 0; i--) {
                        if (raceObj.getIncrement(horse) >= i){
                            odds = Math.round(20 * Math.pow(0.86, i) - 2)/2.0;
                            break;
                        }
                    }
        }
        return odds;
    }
}
