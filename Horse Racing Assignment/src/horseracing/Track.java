package horseracing;

public class Track{

    public static void main(String[] args) {
        int lanes=5;
        for(int i=lanes;i>0;i--){
            for(int j=i*3;j>0;j--){
                System.out.print(" ");
            }
            System.out.print("/");
            for(int j=Math.abs(i-5);j>0;j--){
                System.out.print("  /");
            }
            for(int j=0;j<50;j++){
                System.out.print("-");
            }
            for(int j=Math.abs(i-5);j>0;j--){
                System.out.print("\\  ");
            }
            System.out.println("\\");
        }
        System.out.print("");
        for(int i=lanes;i>0;i--){
            System.out.print("  |");
        }
        for(int j=0;j<50;j++){
            System.out.print(" ");
        }
        for(int i=lanes;i>0;i--){
            System.out.print("  |");
        }
        System.out.println("");
    for(int i=1;i<lanes;i++){
        for(int j=i*3;j>0;j--){
            System.out.print(" ");
        }
        System.out.print("\\");
        for(int j=Math.abs(i-5);j>0;j--){
            System.out.print("  \\");
        }
        for(int j=0;j<50;j++){
            System.out.print("-");
        }
        for(int j=Math.abs(i-5);j>0;j--){
            System.out.print("/  ");
        }
        System.out.println("/");
    }
    }
}