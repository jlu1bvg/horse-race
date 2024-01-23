package horseracing;

import java.util.Scanner;
import java.util.List;

public class BetDialogue {
    Scanner console;
    private BettingOdds odds;
    public BetDialogue(Player player) {
        System.out.print("\u001B[?25l");
        console = new Scanner(System.in);
    }
    public void CreateBetDialogue(Player player, BettingOdds odd, List<Horse> horses){
        odds = odd;
        System.out.println();
        System.out.println(player.getName()+" is betting");
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
                boolean validBetTypeEntered=false;
                while(!validBetTypeEntered){
                    System.out.println("What type of bet do you want to place? (win, place, show, box, exacta)");
                    String betType = console.nextLine();
                    if(!(betType.equals("win")||betType.equals("place")||betType.equals("show")||betType.equals("box")||betType.equals("exacta"))){
                        System.out.println("Invalid bet type");
                        validBetTypeEntered=false;
                    }
                    else{
                        validBetTypeEntered=true;
                        player.placeBet(betType, amount, odds, horses);
                    }
                }                
                /* 
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
                    player.placeBet(betType, infos)
                }
                */
                result = "n";
            } 
    }

    public void CloseDialogue(){
        console.close();
    }
}
