package exercise1_1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise1_1_20 {

    public static void main(String[] args) {
        StdOut.print("Enter a number  N = ");
        int n = StdIn.readInt();
        StdOut.print("ln(N!) = " + ln(n));
    }
    
    public static double ln(int n) {
        if (n == 1)
            return Math.log(n);
        else {
            return Math.log(n) + ln(--n);
        }
    }

}
