package exercise3_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestBST {
    public static void main(String[] args) {
        Exercise3_2_13<String, Integer> bst = new Exercise3_2_13<>();
        
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            bst.put(key, i);
        }
        
        StdOut.println("height = " + bst.height());
        StdOut.println("min = " + bst.min());
        StdOut.println("max = " + bst.max());
        StdOut.println("FLOOR \"D\" = " + bst.floor("D"));
        StdOut.println("size = " + bst.size());
    }
}
