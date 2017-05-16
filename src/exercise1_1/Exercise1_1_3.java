package exercise1_1;

import edu.princeton.cs.algs4.*;

public class Exercise1_1_3 {
    public static void main(String[] args) {
        if (args.length != 3)
            StdOut.print("Invalid input.");
        else if (Integer.parseInt(args[0])  == Integer.parseInt(args[1]) && Integer.parseInt(args[1]) == Integer.parseInt(args[2]))
            StdOut.print("equals.");
        else
            StdOut.print("not equals.");
    }
}
