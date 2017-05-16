package exercise1_1;

public class Exercise1_1_6 {

    public static void main(String[] args) {
        int f = 0, g = 1;
        for (int i = 0; i <=15; i++) {
            System.out.print(f + ", ");
            f = f + g;
            g = f - g;
        }

    }

}
