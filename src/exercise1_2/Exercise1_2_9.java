package exercise1_2;

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Exercise1_2_9 {

    public static void main(String[] args) {
        Counter count = new Counter(" .");
        int[] a = In.readInts();
        Arrays.sort(a);
        int key = 6;

        indexOf(a, key, count);
        System.out.println(
                "the index of " + key + " is " + indexOf(a, key, count) + " compaired times : " + count.toString());
    }

    public static int indexOf(int[] a, int key, Counter count) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            count.increment();
            // Key is in a[lo..hi] or not present.
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

}
