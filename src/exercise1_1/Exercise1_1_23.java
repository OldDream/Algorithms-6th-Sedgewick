package exercise1_1;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class Exercise1_1_23 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("+ ? - ? : ");
        char cc = input.nextLine().charAt(0);
        
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("Enter some number : ");
        list.add(StdIn.readInt());
        while (!StdIn.isEmpty()) {
            list.add(StdIn.readInt());
        }
        StdIn.readAll();
        
        
        int[] whitelist = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        
        for (int key : list) {
            System.out.println("Index of " + key + " is " + rank(key, whitelist, cc));
        }
            
    }

    public static int rank(int key, int[] a, char cc) {
        return rank(key, a, 0, a.length - 1, 0, cc);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth, char cc) {
        if (lo > hi) {
            if (cc == '+')
                System.out.println(key +" was not in the white list.");
            return -1;
        }
        int mid = (lo + hi) / 2;
        if (key < a[mid]) {
            return rank(key, a, lo, mid - 1, ++depth, cc);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, hi, ++depth, cc);
        } else {
            if (cc == '-')
                System.out.println(key + " was in the white list.");
            return mid;
        }
    }

}
