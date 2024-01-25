package horseracing;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Player {
    Scanner console=new Scanner(System.in);
    private int money = 1000;
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
                boolean vaildHorseEntered = false;
                Horse horse=null;
                int horseNum=0;
                //Prompts user for the horse they want to win
                while(!vaildHorseEntered){
                    System.out.println("Which horse do u choose? (enter name or number)");
                    String horseName=console.nextLine();
                    //Allows user to enter a number instead of a name for horse
                    try{
                        horseNum=Integer.parseInt(horseName)-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse=horses.get(horseNum);
                            vaildHorseEntered = true;
                        }
                    }
                    //Makes sure user enters a valid horse name
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseName)){
                                horse = h;
                                vaildHorseEntered = true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse not found. Please try again: ");
                    }
                }
                //Adds your potential winnings to potentialEarnings and takes money away from the wallet.
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse, "win")));
                money -= amount;
                betT = "win";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first place");
            }else if (betType.toLowerCase().equals("place")){
                boolean vaildHorseEntered = false;
                Horse horse=null;
                int horseNum=0;
                //Prompts user for the horse they want to come first or second
                while(!vaildHorseEntered){
                    System.out.println("Which horse do u choose? (enter name or number)");
                    String horseName=console.nextLine();
                    //Allows user to enter a number instead of a name for horse
                    try{
                        horseNum=Integer.parseInt(horseName)-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse=horses.get(horseNum);
                            vaildHorseEntered = true;
                        }
                    }
                    //Makes sure user enters a valid horse name
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseName)){
                                horse = h;
                                vaildHorseEntered = true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse not found. Please try again: ");
                    }
                }
                //Adds your potential winnings to potentialEarnings and takes money away from the wallet.
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse, "place")));
                money -= amount;
                betT = "place";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first or second place");
            }else if (betType.toLowerCase().equals("show")){
                boolean vaildHorseEntered = false;
                Horse horse=null;
                int horseNum=0;
                //Prompts user for the horse they want to come first or second or third
                while(!vaildHorseEntered){
                    System.out.println("Which horse do u choose? (enter name or number)");
                    String horseName=console.nextLine();
                    //Allows user to enter a number instead of a name for horse
                    try{
                        horseNum=Integer.parseInt(horseName)-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse=horses.get(horseNum);
                            vaildHorseEntered = true;
                        }
                    }
                    //Makes sure user enters a valid horse name
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseName)){
                                horse = h;
                                vaildHorseEntered = true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse not found. Please try again: ");
                    }
                }
                //Adds your potential winnings to potentialEarnings and takes money away from the wallet.
                potentialEarnings.put(horse.getName(), (int)(amount*odds.getOdds(horse, "show")));
                money -= amount;
                betT = "show";
                System.out.println("Bet of " + amount + " placed on " + horse.getName() + " to come first, second or third place");
            }else if (betType.toLowerCase().equals("box")){
                boolean vaildHorseEntered=false;
                Horse horse1 = null;
                Horse horse2 = null;
                int horseNum=0;
                while(!vaildHorseEntered){
                    System.out.println("Enter the 2 horses you want to box (say name or number) seperated by a COMMA NO SPACES.");
                    String[] horseNames = console.nextLine().split(",");
                    try{
                        horseNum=Integer.parseInt(horseNames[0])-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse "+horseNames[0]+" not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse1 = horses.get(Integer.parseInt(horseNames[0])-1);
                            vaildHorseEntered = true;
                        }
                    }
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseNames[1])){
                                horse1 = h;
                                vaildHorseEntered=true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse "+horseNames[0]+" not found. Please try again: ");
                    }
                    try{
                        horseNum=Integer.parseInt(horseNames[1])-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse "+horseNames[1]+" not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse2 = horses.get(Integer.parseInt(horseNames[1])-1);
                            vaildHorseEntered=true;
                        }
                    }
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseNames[1])){
                                horse2 = h;
                                vaildHorseEntered=true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse "+horseNames[1]+" not found. Please try again: ");
                    }
                if (horse1 != null && horse2 != null) {
                    double combinedOdds = (odds.getOdds(horse1, "win") + odds.getOdds(horse2, "win")); 
                    potentialEarnings.put(horse1.getName() + " & " + horse2.getName(), (int)(amount * combinedOdds));
                    money -= amount;
                    betT = "box";
                    System.out.println("Box bet of " + amount + " placed on " + horse1.getName() + " & " + horse2.getName());
                } else {
                    System.out.println("not valid input");
                }
            }
            }else if (betType.toLowerCase().equals("exacta")){
                boolean vaildHorseEntered=false;
                Horse horse1 = null;
                Horse horse2 = null;
                int horseNum=0;
                while(!vaildHorseEntered){
                    System.out.println("Enter the 2 horses you want to exacta box (say name or number) seperated by a COMMA NO SPACES. IN THE ORDER YOU WANT THE EXACTA TO BE.");
                    String[] horseNames = console.nextLine().split(",");    
                    try{
                        horseNum=Integer.parseInt(horseNames[0])-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse "+horseNames[0]+" not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse1 = horses.get(Integer.parseInt(horseNames[0])-1);
                            vaildHorseEntered=true;
                        }
                        
                    }
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseNames[0])){
                                horse1 = h;
                                vaildHorseEntered=true;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse not found. Please try again: ");
                    }    
                    try{
                        horseNum=Integer.parseInt(horseNames[1])-1;
                        if(horseNum+1>horses.size()||horseNum<0){
                            System.out.println("Horse "+horseNames[1]+" not found");
                            vaildHorseEntered=false;
                        }
                        else{
                            horse2 = horses.get(Integer.parseInt(horseNames[1])-1);
                            vaildHorseEntered=true;
                        }
                        
                    }
                    catch(NumberFormatException e){
                        for (Horse h:horses){
                            if(h.getName().equals(horseNames[1])){
                                horse2 = h;
                            }
                        }
                        if(!vaildHorseEntered)
                            System.out.println("Horse not found. Please try again: ");
                    }

                if (horse1 != null && horse2 != null) {
                    double exactaOdds = odds.getOdds(horse1, "win") * odds.getOdds(horse2, "place") + 1;
                    potentialEarnings.put(horse1.getName() + " -> " + horse2.getName(), (int)(amount * exactaOdds));
                    money -= amount;
                    betT = "exacta";
                    System.out.println("Exacta bet of " + amount + " placed on " + horse1.getName() + " -> " + horse2.getName());
                } else {
                    System.out.println("Invalid input");
                }
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

    public void resetBet(){
        betT = "";
        potentialEarnings.clear();
    }
}
