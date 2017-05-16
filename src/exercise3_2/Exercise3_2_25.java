package exercise3_2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise3_2_25 {
    public static void perfect(BST bst, String[] a) {
        Arrays.sort(a);
        perfect(bst, a, 0, a.length - 1);
    }
    
    public static void perfect(BST bst, String[] a, int lo, int hi) {
        if (hi < lo)
            return;
        
        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);
        StdOut.print(a[mid] + " ");
        perfect(bst, a, lo, mid - 1);
        perfect(bst, a, mid + 1, hi);
    }
    
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        BST<String, Integer> bst = new BST<>();
        perfect(bst, a);
        
    }
}
