package exercise5_3;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM;
    
    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM= (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }
    
    // Monte Carlo version
   /* public boolean check(int i) {
        return true;
    }*/
    
    // Las Vegas version
    public boolean check(int i, String txt) {
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        }
        
        return true;
    }
    
    
    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j) % Q);
        }
        return h;
    }
    
    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0, txt))
            return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i - M + 1, txt))
                    return i - M + 1;
        }
        return N;
    }
    
    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(63, new Random());
        return prime.longValue();
    }
    
    public int checkSub(String txt) {
        int i = search(txt);
        if (i <= txt.length() - M)
            System.out.println("MATCHED");
        else 
            System.out.println("Not matched.");
        return i;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("pat: ");
        String pat = input.nextLine();
        System.out.print("txt: ");
        String txt = input.nextLine();
        
        RabinKarp rk = new RabinKarp(pat);
        int offset = rk.checkSub(txt);
        System.out.println("Index " + offset);
        
        System.out.println(txt);
        for (int i = 0; i < offset; i++)
            System.out.print(" ");
        System.out.println(pat);
        
    }
}
