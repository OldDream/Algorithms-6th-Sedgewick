package exercise1_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise1_1_21 {

    public static void main(String[] args) {
        StdOut.println("Read from file ...");
        while(StdIn.hasNextLine()) {
            String name = StdIn.readString();
            int a = StdIn.readInt();
            int b = StdIn.readInt();
            StdIn.readLine();    //clean this line.
            System.out.printf("%s\t\t%d   %d   %.3f\n", name, a, b, 1.0 * a / b);
        }
        
    }

}
