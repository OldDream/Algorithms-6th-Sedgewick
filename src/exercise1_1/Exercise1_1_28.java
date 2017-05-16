package exercise1_1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise1_1_28 {
    public static int rank(int key, Integer[] a) { // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        if (args.length != 1)
            System.exit(0);
        
        int[] whitelist1 = In.readInts(args[0]);
        Integer[] whitelist = new Integer[whitelist1.length];
        for (int i = 0; i < whitelist.length; i++) {
            whitelist[i] = whitelist1[i];
        }
        Arrays.sort(whitelist);
        //删除白名单中重复的
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(whitelist));
        for (int i = 0; i < temp.size(); i++) {
            int a = temp.get(i);
            if (temp.indexOf(a) != temp.lastIndexOf(a)) {
                temp.remove(temp.lastIndexOf(a));
            }
        }
        
        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0)
                StdOut.println(key);
        }
    }
}
