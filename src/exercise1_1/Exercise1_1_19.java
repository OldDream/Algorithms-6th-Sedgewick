package exercise1_1;

import java.util.ArrayList;

public class Exercise1_1_19 {
    private static ArrayList<Long> al = new ArrayList<>();
    
    public static void main(String[] args) {
        al.add(0l);
        al.add(1l);
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + F(i));
        }

    }

    public static long F(int N) {
        if (N == 0)
            return al.get(0);
        if (N == 1)
            return al.get(1);
        if (N <= (al.size() - 1 ))
            return al.get(N);
        else {
            al.add(F(N - 1) + F(N - 2));
            return al.get(al.size() - 1);
        }
    }

}
