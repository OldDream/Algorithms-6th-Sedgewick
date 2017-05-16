package exercise1_1;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Exercise1_1_30 {

    public static void main(String[] args) {
        System.out.print("Enter a num : ");
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        boolean[][] xxoo = new boolean[N][N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (euclid(i, j) == 1)
                    xxoo[i - 1][j - 1] = true;
                else
                    xxoo[i - 1][j - 1] = false;
            }
        }
        
        //print the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(xxoo[i][j] +" ");
            }
            System.out.println();
        }
        
    }
    
    public static int euclid(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return euclid(q, r);
    }

}
