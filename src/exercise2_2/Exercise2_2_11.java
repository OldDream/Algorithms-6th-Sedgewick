package exercise2_2;

import edu.princeton.cs.algs4.*;

public class Exercise2_2_11 {
    private static int cutoff = 7;
    
 // This class should not be instantiated.
    private Exercise2_2_11() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);
        
        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(dst[j], dst[i])) dst[k] = src[j++];
            else                           dst[k] = src[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(dst, lo, hi);
    }
    
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j >= lo; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1); 
            }
        }
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        //if the array was too short ,use InsertionSort instead.
        if (lo + cutoff >= hi) {
            insertionSort(dst, lo, hi);    // 也就是上一个sort调用中的src被排序了
            return;
        }
        
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);    // merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi)
        sort(dst, src, mid + 1, hi);    
        
        //保证dst和src同步变化
        if (less(src[mid], src[mid + 1])) {    // 如果恰好合适，就不需要merge了
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
        }
        else {
            merge(src, dst, lo, mid, hi);
        }
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param src the array to be sorted
     */
    public static void sort(Comparable[] src) {
        Comparable[] aux = src.clone();
        sort(aux, src, 0, src.length-1);    // sort(Comparable[] src, Comparable[] dst
        assert isSorted(src);
    }


   /***************************************************************************
    *  Helper sorting function.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


   /***************************************************************************
    *  Index mergesort.
    ***************************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }

    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[N-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;

        int[] aux = new int[n];
        sort(a, index, aux, 0, n-1);
        return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        merge(a, index, aux, lo, mid, hi);
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        show(a);
    }
}
