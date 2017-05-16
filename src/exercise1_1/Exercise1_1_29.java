package exercise1_1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise1_1_29 {
    //返回小于key的元素数量
    public static int rank(int key, Integer[] a) { // Array must be sorted.
        int index = rankOriginal(key, a);
        while (a[--index] == key)
            ;
        return ++index;
    }
    
    //正常的二分法搜索
    public static int rankOriginal(int key, Integer[] a) { // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;  //返回小于key的元素数量
        }
        return -1;
    }
    
    //返回等于key的元素的数量。
    public static int count(int key, Integer[] a) { // Array must be sorted.
        int count = 0;
        Integer[] b = new Integer[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        while (rankOriginal(key, b) != -1) {
            b[rankOriginal(key, b)] = key - 1;
            Arrays.sort(b);
            count++;
            StdOut.println("+1");
        }
        return count;
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
        /*int key = 3;
        StdOut.println(rank(key, whitelist));*/
        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            StdOut.println(count(key, whitelist));
        }
    }
}
