package horseracing;

import java.util.Scanner;

public class BetDialogue {
    Scanner console;
    public BetDialogue(Player player) {
        console = new Scanner(System.in);
    }
    public void CreateBetDialogue(Player player){
        String result = "y"; 
        System.out.println("Remaining money: " + player.getMoney());
        System.out.println("do u want to bet (y/n)");
        result = console.nextLine();
        while (result.equals("y")) {
                int amount = 0;
                boolean validBetEntered = false;
                while (!validBetEntered) {
                    try {
                        System.out.println("how much do u bet");
                        amount = Integer.parseInt(console.nextLine());
                        validBetEntered = true;
                    } catch (NumberFormatException e) {
                        System.out.println("etan stop messing with me");
                    }
                }
    
                System.out.println("which horse do u choose? (say name)");
                String horseName = console.nextLine();
    
                System.out.println("do you want to do an exactor? (y/n)");
                String betOnPosition = console.nextLine();
    
                if(!betOnPosition.equals("y") && !betOnPosition.equals("n")){
                    System.out.println("not valid so i ma take it as a no. ill take away all ur money though, congrats.");
                    //get some setter to remove player money rq
                }

                if (betOnPosition.equals("n")) {
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
