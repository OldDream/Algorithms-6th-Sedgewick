package exercise1_1;

import edu.princeton.cs.algs4.*;

public class Exercise1_1_14 {
    public static void main(String[] args) {
        StdOut.print("Enter a Integer : ");
        int num = StdIn.readInt();
        int result = lg(num);
        StdOut.println("log 2 num = " + result);
    }
    
    public static int lg(int num) {
        int count = 0;
        int result = 1;
        
        if (num < 0 || num == 0) {
            System.out.println("Invalid number.");
            System.exit(0);
        }
        else if (num == 1) {
            return 0;
        }
        else {
            while (result < num) {
                result *= 2;
                count++;
            }
        }

        if (result == num)
            return count;
        else
            return --count;
    }
}
