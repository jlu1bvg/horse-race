package horseracing;

import java.util.Map;

public class Player {
    private int money = 100;
    private Map<String, Integer>potentialEarnings;

    public int getMoney(){
        return money;
    }

    public void betWon(String horseName){
       if(potentialEarnings.containsKey(horseName)){
            money += potentialEarnings.get(horseName);
            potentialEarnings.remove(horseName);
       }
    }

    public void placeBet(int amount, String horseName, String position){
        if(amount <= money){
           potentialEarnings.put(horseName, 2 * amount);
        }
    }

    public void placeBet(int amount, String horseName){
        if(amount <= money){
           potentialEarnings.put(horseName, amount);
        }
    }
}
