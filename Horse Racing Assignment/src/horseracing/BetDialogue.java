package horseracing;

import java.util.Scanner;

public class BetDialogue {
    Scanner console;
    public BetDialogue(Player player) {
        System.out.print("\u001B[?25l");
        console = new Scanner(System.in);
    }
    public void CreateBetDialogue(Player player){
        String result = "y"; 
        System.out.println("Remaining money: " + player.getMoney());
        System.out.println("Do u want to place a bet (y/n)");
        result = console.nextLine();
        while (result.equals("y")) {
                int amount = 0;
                boolean validBetEntered = false;
                while (!validBetEntered) {
                    try {
                        System.out.println("How much do u bet");
                        amount = Integer.parseInt(console.nextLine());
                        if (amount <= player.getMoney()){
                            validBetEntered = true;
                        }else{
                            System.out.println("You're too poor to place this bet. Please try again: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter an integer please: ");
                    }
                }
    
                System.out.println("Which horse do u choose? (say name)");
                String horseName = console.nextLine();
    
                System.out.println("Do you want to do an exactor? (y/n)");
                String betOnPosition = console.nextLine();
    
                if(!betOnPosition.equals("y") && !betOnPosition.equals("n")){
                    System.out.println("not valid so i ma take it as a no. ill take away all ur money though, congrats.");
                    //get some setter to remove player money rq
                }

                if (betOnPosition.equals("n")) {
                    player.placeBet(amount, horseName);
                } else if (betOnPosition.equals("y")) {
                    int position = 0;
                    boolean validPosEntered = false;

                    while (!validPosEntered) {
                        try {
                            System.out.println("Which position do you bet on? (say number)");
                            position = Integer.parseInt(console.nextLine());
                            validPosEntered = true;
                        } catch (NumberFormatException e) {
                            System.out.println("eric tan i know it was you");
                        }
                    }
                    player.placeBet(amount, horseName, position);
                }
    
                System.out.println("Do you want to continue betting? (y/n)");
                
                String temp = console.nextLine();

                if(!temp.equals("y") && !temp.equals("n")){
                    result = "n";
                    System.out.println("Invalid again so I ma ban you from the game goodbye");
                    //crash the client or something 
                }

                result = temp;
            } 
    }

    public void CloseDialogue(){
        console.close();
    }
}
