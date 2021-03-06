package exercise2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.*;

//width of square 16，half = 8.
//hight = num * 16

public class Exercise2_1_17 {
    static int size = 20;
    static int painter = 16;
    static double max = 30;
    static Double[] b = null;
    public Exercise2_1_17() {
        
    }

    public static void main(String[] args) {
        b = new Double[size];
        for (int i = 0; i < b.length; i++) {
            b[i] = StdRandom.uniform(0, max);
        }
        sort(b);
        for (double a : b) {
            System.out.println(a);
        }
        draw(b);
        
        
    }

    public static void draw(Double[] a) {
        StdDraw.clear();
        int canvasWidth = painter * size;
        int canvasHeight = painter * (1 + (int)max);
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setScale(canvasWidth, canvasHeight);
        for (int i = 0; i < a.length; i++) {
            double x = (i * painter + painter / 2);
            double y = a[i] * painter / 2;
            double halfWidth = painter / 2 / canvasWidth;
            double halfHeight = a[i] * painter / 2 / canvasHeight;
            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        }
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
    
    public static void sort(Double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
            
        }
        assert isSorted(a);
    }
    

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(comparator, a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
    }

    /***************************************************************************
     * Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     * Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    // is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i - 1]))
                return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}
