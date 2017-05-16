package exercise1_5;

import java.util.Scanner;
import java.util.Random;

public class ErdosRenyi {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("N = ");
        int N = input.nextInt();
        input.close();
        System.out.println("Couont : " + count(N));
        
    }
    
    public static int count(int N) {
        CompressedWeightedQuickUnionUF uf = new CompressedWeightedQuickUnionUF(N);
        Random rd = new Random();
        int connect = 0;
        
        while(uf.count() != 1) {
            int p = rd.nextInt(N);
            int q = rd.nextInt(N);
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connect++;
                System.out.println(p + " + " + q);
            }
                
        }
        
        //return uf.count();
        return connect;
    }

}
