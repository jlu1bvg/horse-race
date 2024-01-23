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
    private String betT = "";

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
            if (betType.toLowerCase().equals("win")){
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
                betT = "win";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first place");
            }else if (betType.toLowerCase().equals("place")){
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
                betT = "place";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first or second place");
            }else if (betType.toLowerCase().equals("show")){
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
                betT = "show";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first, second or third place");
            }else if (betType.toLowerCase().equals("box")){
                System.out.println("Enter the 2 horses you want to box (say name or number) seperated by a COMMA NO SPACES.");
                String[] horseNames = console.nextLine().split(",");
                Horse horse1 = null;
                Horse horse2 = null;
                try{
                    horse1 = horses.get(Integer.parseInt(horseNames[0])-1);
                }
                catch(NumberFormatException e){
                    for (Horse h:horses){
                        if(h.getName().equals(horseNames[1])){
                            horse1 = h;
                        }
                    }
                }
                try{
                    horse2 = horses.get(Integer.parseInt(horseNames[1])-1);
                }
                catch(NumberFormatException e){
                    for (Horse h:horses){
                        if(h.getName().equals(horseNames[1])){
                            horse2 = h;
                        }
                    }
                }
                if (horse1 != null && horse2 != null) {
                    double combinedOdds = (odds.getOdds(horse1) + odds.getOdds(horse2)) / 2; 
                    potentialEarnings.put(horse1.getName() + " & " + horse2.getName(), (int)(amount * combinedOdds));
                    money -= amount;
                    betT = "box";
                    System.out.println("Box bet of " + amount + " placed on " + horse1.getName() + " & " + horse2.getName());
                } else {
                    System.out.println("not valid input");
                }
            }else if (betType.toLowerCase().equals("exacta")){
                System.out.println("Enter the 2 horses you want to exacta box (say name or number) seperated by a COMMA NO SPACES. IN THE ORDER YOU WANT THE EXACTA TO BE.");
                String[] horseNames = console.nextLine().split(",");
                Horse horse1 = null;
                Horse horse2 = null;
                try{
                    horse1 = horses.get(Integer.parseInt(horseNames[0])-1);
                }
                catch(NumberFormatException e){
                    for (Horse h:horses){
                        if(h.getName().equals(horseNames[0])){
                            horse1 = h;
                        }
                    }
                }

                try{
                    horse2 = horses.get(Integer.parseInt(horseNames[1])-1);
                }
                catch(NumberFormatException e){
                    for (Horse h:horses){
                        if(h.getName().equals(horseNames[1])){
                            horse2 = h;
                        }
                    }
                }

                if (horse1 != null && horse2 != null) {
                    double exactaOdds = odds.getOdds(horse1) * odds.getOdds(horse2);
                    potentialEarnings.put(horse1.getName() + " -> " + horse2.getName(), (int)(amount * exactaOdds));
                    money -= amount;
                    betT = "exacta";
                    System.out.println("Exacta bet of " + amount + " placed on " + horse1.getName() + " -> " + horse2.getName());
                } else {
                    System.out.println("invalid input");
                }
            }
        }
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getBetType(){
        return betT;
    }
}
