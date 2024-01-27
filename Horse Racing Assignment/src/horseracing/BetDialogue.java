package horseracing;

import java.util.Scanner;
import java.util.List;

public class BetDialogue {
    Scanner console;
    private BettingOdds odds;
    public BetDialogue(Player player) {
        // System.out.print("\u001B[?25l");
        console = new Scanner(System.in);
    }
    public void CreateBetDialogue(Player player, BettingOdds odd, List<Horse> horses){
        odds = odd;
        System.out.println();
        System.out.println(player.getName() + " is betting");
        String result = "y"; 
        boolean decisionMade=false;
        System.out.println("Remaining money: " + player.getMoney());
        while(!decisionMade){
            System.out.println("\nDo u want to place a bet (y/n)");
            result = console.nextLine();
            if(!(result.equals("y")||result.equals("n"))){
                System.out.println("Invalid input");
                decisionMade=false;
            }
            else{
                decisionMade=true;
            }
        }
        while (result.equals("y")) {
                int amount = 0;
                boolean validBetEntered = false;
                while (!validBetEntered) {
                    try {
                        System.out.println("\nHow much do u bet");
                        amount = Integer.parseInt(console.nextLine());
                        if (amount <= player.getMoney()&&amount>0){
                            validBetEntered = true;
                        }else{
                            System.out.println("You're too poor to place this bet. Please try again: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter an integer please ");
                    }
                }
                boolean validBetTypeEntered=false;
                while(!validBetTypeEntered){
                    System.out.println("\nWhat type of bet do you want to place? (win, place, show, box, exacta)");
                    String betType = console.nextLine();
                    if(!(betType.toLowerCase().equals("win")||betType.toLowerCase().equals("place")||betType.toLowerCase().equals("show")||betType.toLowerCase().equals("box")||betType.toLowerCase().equals("exacta")||betType.equals("Throughout Heaven and Earth, I alone am the honored one"))){
                        System.out.println("Invalid bet type");
                        validBetTypeEntered=false;
                    }
                    else{
                        if(betType.equals("Throughout Heaven and Earth, I alone am the honored one") && player.getName().equals("Gojo Satoru")){
                            System.out.println("You have been blessed by the gods");
                            player.setMoney(player.getMoney()+1000000000);
                            validBetTypeEntered=true;
                            HorseRacingHelper.pauseForMilliseconds(2000);
                            HorseRacingHelper.clearConsole();
                        }else if(betType.equals("Throughout Heaven and Earth, I alone am the honored one") && !player.getName().equals("Gojo Satoru")){
                            System.out.println("Know your place, fool.");                            
                            validBetTypeEntered=false;
                            HorseRacingHelper.pauseForMilliseconds(2000);
                        }else{
                        
                        validBetTypeEntered=true;
                        player.placeBet(betType, amount, odds, horses);
                        }
                    }
                }                
                result = "n";
            } 
    }

    public void CloseDialogue(){
        console.close();
    }
}
