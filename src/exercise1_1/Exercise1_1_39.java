package exercise1_1;
import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise1_1_39 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the T (repeated times)s: ");
        int T = input.nextInt();
        
        for (int i = 1000; i <= 1000000; i *= 10) {
            int avg = 0;
            for (int j = 0; j < T; j++) {
                avg += randomPair(i);
            }
            double b = 1.0 * avg / T;
            StdOut.println("i = " + i + "  avg = " + b);
        }
    }
    //Create two pairs ,compare and  return the number...
    public static int randomPair(int n) {
        int count = 0;
        int[] a = new int[n];
        int[] b = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(100000, 999999);
        }
        for (int i = 0; i < n; i++) {
            b[i] = StdRandom.uniform(100000, 999999);
        }
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        for (int key : a) {
            if (Arrays.binarySearch(b, key) >= 0)
                count++;
        }
            
        return count;
        
    }

}
