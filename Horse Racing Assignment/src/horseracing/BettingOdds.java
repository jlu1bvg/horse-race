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
        /* 
        if (raceObj.getIncrement(horse) > 14){
                odds = 3/2; //num = 3
        }else if (raceObj.getIncrement(horse) > 10){
                odds = 4/2; //num = 4
        }else if (raceObj.getIncrement(horse) > 8){
                odds = 6/2; //num = 6
        }else if (raceObj.getIncrement(horse) > 6){
                odds = 9/2; //num = 9
        }else if (raceObj.getIncrement(horse) > 4){
                odds = 13/2; //num = 13
        }else if (raceObj.getIncrement(horse) > 1){
                odds = 18/2; //num = 18
        }
        */
        return odds;
    }
}
