package exercise1_4;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise1_4_20 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("N = ");
        int n = input.nextInt();
        int[] array = new int[n];
        
        System.out.print("Enter bitonic array : ");
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        
        System.out.print("key = ");
        int key = input.nextInt();
        
        System.out.println(contains(array, key));
        
    }
    
    /* 1. 找到bitonic数列中max的index。
     * 2.以max为分界线，左边使用正常binarysearch，右边使用倒置的binarysearch。
     * 3.分别在左右两侧查找key。*/
    
    public static boolean contains(int[] array, int key) {
        int maxIndex = maxFinder(array, 0, array.length - 1);    //log n;
        if (key == array[maxIndex])
            return true;
        else if (key < array[maxIndex] && BinarySearch.indexOf(array, key) != -1)    //log n;
            return true;
        else if (key < array[maxIndex] && reverseIndexOf(array, key) != -1)    //log n,  sum = 3lg n.
            return true;
        else
            return false;
    }
    
    public static int maxFinder(int[] array,int lo, int hi){
        if (lo == hi)
            return hi;
        int mid = (lo + hi) / 2;
        if (array[mid] < array[mid + 1])
            return maxFinder(array, mid + 1, hi);
        else if (array[mid] > array[mid + 1])
            return maxFinder(array, lo, mid);
        else
            return -1;      
    }
    
    public static int reverseIndexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) lo = mid + 1;
            else if (key > a[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
}
