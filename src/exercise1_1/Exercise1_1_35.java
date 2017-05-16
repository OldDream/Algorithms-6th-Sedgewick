package exercise1_1;
import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class Exercise1_1_35 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] logical = logical_craps();
        System.out.print("Enter the amount of random test : ");
        int n = input.nextInt();
        double[] ran = random_craps(n);
        
        //show the delta between logical and random
        for (int i = 2; i < logical.length; i++) {
            double delta =  Math.abs(logical[i] - ran[i]);
            if (delta >= 0.01)
                System.out.println(i + ": delta is bigger than 0.00X ; delta = " + delta);
            else {
                System.out.println(i + ": pass");
            }
        }
        
    }
    
    public static double[] logical_craps() {
        double[] logical = new double[2 * 6 + 1];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                logical[i + j] += 1.0;
            }
        }
        
        for (int i = 2; i < logical.length; i++) {
            logical[i] /= 36;
        }
        return logical;
    }
    
    public static double[] random_craps(int n) {
        double[] ran = new double[2 * 6 + 1];
        for (int i = 0; i < n; i++) {
            int temp = StdRandom.uniform(1, 7) + StdRandom.uniform(1, 7);
            ran[temp] += 1;
        }
        
        for (int i = 0; i < ran.length; i++) {
            ran[i] /= n;
        }
        
        return ran;
    
    }
}
