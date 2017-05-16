package exercise1_1;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Scanner;

public class Exercise1_1_36 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("entet M and N : ");
        int M = input.nextInt();    //thr size of array
        int N = input.nextInt();    //the repeat times
        int[] a = new int[M];
        double[][] b = new double[M][M];
        
        for (int i = 0; i < N; i++) {
            initialize(a);
            shuffle(a);
            testResult(a, b);
           
        }
        for (int i = 0; i < b.length; i++) {    
            for (int j = 0; j < b[i].length; j++) {
                System.out.printf("%.2f ", b[i][j]);
            }
            System.out.println();
        }

    }
    
    public static void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + StdRandom.uniform(N - i);
            int temp = a[i];
            a[i] = a [r];
            a[r] = temp;
        }
    }
    
    public static void initialize(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
    }
    
    public static void testResult(int[] a, double[][] b) {
        for (int i = 0; i < a.length; i++) {
            b[a[i]][i] += 1.0 ;
        }
    }

}
