package exercise1_2;

import edu.princeton.cs.algs4.*;

public class Exercise1_2_1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid args.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]);
        Point2D[] list = new Point2D[n];

        // 创建随机点
        for (int i = 0; i < n; i++) {
            list[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            list[i].draw();
        }

        double minDistance = list[0].distanceTo(list[1]);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double temp = list[i].distanceTo(list[j]);
                if (temp < minDistance)
                    minDistance = temp;
            }
        }
        
        StdOut.println("Min distence is : " + minDistance);

    }

}
