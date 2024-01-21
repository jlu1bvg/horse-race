package horseracing;

import java.util.Map;
import java.util.HashMap;

public class Player {
    private int money = 100;
    private Map<String, Integer>potentialEarnings;

    public Player(){
        this.potentialEarnings = new HashMap<String,Integer>();
    }

    public int getMoney(){
        return money;
    }

    public void betWon(String horseName){
        
    }

    public void placeBet(String betType, int amount, String horseName, BettingOdds odds, Horse horse){
        if(money >= amount){
            if (betType.equals("win")){
                potentialEarnings.put(horseName, (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " on " + horseName + " to come first place");
            }else if (betType.equals("place")){
                potentialEarnings.put(horseName, (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " on " + horseName + " to come first or second place");
            }else if (betType.equals("show")){
                potentialEarnings.put(horseName, (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " on " + horseName + " to come first, second or third place");
            }else if (betType.equals("box")){
                
            }else if (betType.equals("Exacta")){
                
            }
        }
    }
}
