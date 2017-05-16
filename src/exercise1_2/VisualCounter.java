package exercise1_2;

import edu.princeton.cs.algs4.*;

public class VisualCounter {
    private int N, max;
    private int counter = 0, times = 0;
    // N为最大操作次数；
    // max 为counter的最大绝对值。
    public VisualCounter(int N, int max) {
        this.N = N;
        this.max = max;
    }
    
    public void increase() {
        counter++;
        times++;
        if (Math.abs(counter) >= max) {
            StdOut.println("Counter equals to the max absolute number.");
            System.exit(0);
        }
        if (times >= N) {
            StdOut.println("Reach the max operation times.");
            System.exit(0);
        }
        StdDraw.clear();
        StdDraw.text(0.5, 0.5, counter + "");
    }
    
    public void decrease() {
        counter--;
        times++;
        if (Math.abs(counter) >= max) {
            StdOut.println("Counter equals to the max absolute number.");
            System.exit(0);
        }
        if (times >= N) {
            StdOut.println("Reach the max operation times.");
            System.exit(0);
        }
        
        StdDraw.clear();
        StdDraw.text(0.5, 0.5, counter + "");
    }
    

    public static void main(String[] args) {
        VisualCounter vc = new VisualCounter(10, 9);
            vc.increase();
            vc.increase();
        
    }

}
