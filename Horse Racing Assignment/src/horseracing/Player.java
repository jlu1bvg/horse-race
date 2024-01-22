package horseracing;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Player {
    Scanner console=new Scanner(System.in);
    private int money = 100;
    private Map<String, Integer>potentialEarnings;
    private String name;

    public Player(){
        this.potentialEarnings = new HashMap<String,Integer>();
    }

    public int getMoney(){
        return money;
    }

    public void betWon(String horseName){
        money += potentialEarnings.get(horseName);
    }

    public Map<String, Integer> getEarnings(){
        return potentialEarnings;
    }

    public void placeBet(String betType, int amount, BettingOdds odds, List<Horse> horses){
        if(money >= amount){
            if (betType.equals("win")){
                System.out.println("Which horse do u choose? (enter name or number)");
                String horseName=console.nextLine();
                Horse horse=null;
                try{
                    horse=horses.get(Integer.parseInt(horseName)-1);
                }
                catch(NumberFormatException e){
                    //jeff did this
                    for (Horse h:horses){
                        if(h.getName().equals(horseName)){
                            horse = h;
                        }
                    }
                }
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first place");
            }else if (betType.equals("place")){
                System.out.println("Which horse do u choose? (say name)");
                String horseName = console.nextLine();
                Horse horse=null;
                try{
                    horse=horses.get(Integer.parseInt(horseName)-1);
                }
                catch(NumberFormatException e){
                    //jeff did this
                    for (Horse h:horses){
                        if(h.getName().equals(horseName)){
                            horse = h;
                        }
                    }
                }
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first or second place");
            }else if (betType.equals("show")){
                System.out.println("Which horse do u choose? (say name)");
                String horseName = console.nextLine();
                Horse horse=null;
                try{
                    horse=horses.get(Integer.parseInt(horseName)-1);
                }
                catch(NumberFormatException e){
                    //jeff did this
                    for (Horse h:horses){
                        if(h.getName().equals(horseName)){
                            horse = h;
                        }
                    }
                }
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse)));
                money -= amount;
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first, second or third place");
            }else if (betType.equals("box")){
                
            }else if (betType.equals("exacta")){
                
            }
        }
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
