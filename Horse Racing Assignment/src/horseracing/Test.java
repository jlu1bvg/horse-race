package horseracing;

public class Test {
    public static void main(String[] args) {
        for (int i = 13; i > 0; i--) {
                System.out.println(i + " " + ((-i + 16)/2.0));
        }
        for (int i = 13; i > 0; i--) {
            System.out.println(i + " " + Math.round(20 * Math.pow(0.86, i)));
    }
    }
}
