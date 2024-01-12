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

    public void placeBet(int amount, String horseName, int position){
        if(money >= amount){
           potentialEarnings.put(horseName, amount+10);
           money -= amount;
           System.out.println("Bet of " + amount + " on " + horseName + " positioned " + position + " placed");
        }
    }

    public void placeBet(int amount, String horseName){
        if(money >= amount){
           money -= amount;
           potentialEarnings.put(horseName, amount+1);
            System.out.println("bet of " + amount + " on " + horseName + " to win placed");
        }
    }
}
