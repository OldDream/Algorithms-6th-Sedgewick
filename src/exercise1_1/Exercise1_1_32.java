package exercise1_1;

import edu.princeton.cs.algs4.*;
import java.util.*;


public class Exercise1_1_32 {
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Invalid args.");
            System.exit(0);
        }
        
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]), r = Double.parseDouble(args[2]);
        
        printer(N, l, r);
    }
    
    public static void printer(int N, double l, double r) {
        double delta = (r - l) / N;
        int[] counter = new int[N];
        
        ArrayList<Double> numberIn = new ArrayList<>();
        StdOut.println("-------");
        Scanner input = new Scanner(System.in);
        numberIn.add(input.nextDouble());
        while (input.hasNext()) {
            numberIn.add(input.nextDouble());
        }

        for (int i = 0; i < numberIn.size(); i++) {
            if (numberIn.get(i) < l || numberIn.get(i) > r)
                continue;
            else {
                int temp = (int) ((numberIn.get(i) - l) / delta - 0.00000001);
                counter[temp] += 1;
            }
        }
        
        for (int a : counter) {
            StdOut.print(a + " ");
        }
        
        int maxCount = StdStats.max(counter);
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, maxCount);
        
        for (int i = 0; i < counter.length; i++) {
            double x = l + i * delta + delta / 2.0;
            double y = counter[i] / 2.0;
            double rw = delta / 4.0;
            double rh = counter[i] / 2.0;
            
            StdDraw.filledRectangle(x, y, rw, rh);
                    
        }
    }

}
