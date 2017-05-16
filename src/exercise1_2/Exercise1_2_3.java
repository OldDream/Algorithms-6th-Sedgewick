package exercise1_2;

import edu.princeton.cs.algs4.*;

public class Exercise1_2_3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid args.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        
        Interval2D[] list = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            double min1 = StdRandom.uniform(min, max);
            double max1 = StdRandom.uniform(min, max);
            if (min1 > max1) {
                double temp = min1;
                min1 = max1;
                max1 = temp;
            }
            Interval1D a = new Interval1D(min1, max1);
            
            double min2 = StdRandom.uniform(min, max);
            double max2 = StdRandom.uniform(min, max);
            if (min2 > max2) {
                double temp = min2;
                min2 = max2;
                max2 = temp;
            }
            Interval1D b = new Interval1D(min1, max1);
            
            
            list[i] = new Interval2D(a, b);
            list[i].draw();
        }
        
        int intersected = 0;
//        int contained = 0;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list[i].intersects(list[j]))
                    intersected++;
            }
        }
        
        StdOut.println("Intersected : " + intersected);
//      StdOut.println("Contained : " + contained);
        
    }

}
