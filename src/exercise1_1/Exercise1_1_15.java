package exercise1_1;

import edu.princeton.cs.algs4.*;

public class Exercise1_1_15 {

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 2, 4, 4, 4};
        StdOut.print("Enter M : ");
        int M = StdIn.readInt();
        int[] b = histogram(a, M);
        for (int i : b) {
            StdOut.print(i + " ");
        }
        
    }

    public static int[] histogram(int[] a, int M) {
        int[] b = new int[M];
        for (int i : a) {
            if (i < M)
                b[i]++;
        }

        return b;
    }

}
