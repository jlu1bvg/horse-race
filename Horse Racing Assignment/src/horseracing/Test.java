package horseracing;

public class Test {
    public static void main(String[] args) {
        for (int i = 13; i > 0; i--) {
                System.out.println(i + " " + ((-i + 16)/2.0));
        }
        for (int i = 13; i > 0; i--) {
            System.out.println(i + " " + Math.round(20 * Math.pow(0.86, i)));
        }
        /* 
        for (Horse h:horses){
            if(h.getName().equals(horseName)){
                horse = h;
            }
        }
        */
        /* 
        if (raceObj.getIncrement(horse) > 14){
                odds = 3/2; //num = 3
        }else if (raceObj.getIncrement(horse) > 10){
                odds = 4/2; //num = 4
        }else if (raceObj.getIncrement(horse) > 8){
                odds = 6/2; //num = 6
        }else if (raceObj.getIncrement(horse) > 6){
                odds = 9/2; //num = 9
        }else if (raceObj.getIncrement(horse) > 4){
                odds = 13/2; //num = 13
        }else if (raceObj.getIncrement(horse) > 1){
                odds = 18/2; //num = 18
        }
        */
    }
}
