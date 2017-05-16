package exercise1_1;

public class Exercise1_1_22 {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        int key = 3;
        System.out.println("Index of " + key + " is " + rank(key, a));

    }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth) {
        if (lo > hi)
            return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");
            System.out.println("lo = " + lo + "   hi = " + hi);
            return rank(key, a, lo, mid - 1, ++depth);
        } else if (key > a[mid]) {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");
            System.out.println("lo = " + lo + "   hi = " + hi);
            return rank(key, a, mid + 1, hi, ++depth);
        } else
            return mid;
    }

}
