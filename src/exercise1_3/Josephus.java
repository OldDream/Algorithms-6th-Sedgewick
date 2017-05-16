package exercise1_3;

import edu.princeton.cs.algs4.*;

public class Josephus {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);    //总人数
        int m = Integer.parseInt(args[1]);    //死者序号

        // initialize the queue
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty()) {
            for (int i = 0; i < m-1; i++)
                queue.enqueue(queue.dequeue());
            StdOut.print(queue.dequeue() + " ");
        } 
        StdOut.println();
    }
}
