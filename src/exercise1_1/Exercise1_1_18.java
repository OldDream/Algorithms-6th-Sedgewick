package exercise1_1;

import edu.princeton.cs.algs4.*;

public class Exercise1_1_18 {

    public static void main(String[] args) {
        StdOut.println(mystery(3, 11));
        StdOut.println(3 / 2 + "");
    }

    public static int mystery(int a, int b) {
        if (b == 0)
            return 1;
        if (b % 2 == 0)
            return mystery(a * a, b / 2);
        return mystery(a * a, b / 2) + a;
    }

}
