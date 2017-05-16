package exercise1_2;

import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class Exercise1_2_2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid args.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]);
        Scanner input = new Scanner(System.in);
        Interval1D[] list = new Interval1D[n];
        //初始化interval数组
        StdOut.print("Enter " + n + " intervals : ");
        for (int i = 0; i < n; i++) {
            double a = input.nextDouble();
            double b = input.nextDouble();
            list[i] = new Interval1D(a, b);
        }
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list[i].intersects(list[j]))
                    System.out.println(list[i].toString() + list[j].toString() + " are intersected.");
            }
        }
    }

}
