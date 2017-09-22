package exercise5_3;

import java.math.BigInteger;
import java.util.Random;

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
}
