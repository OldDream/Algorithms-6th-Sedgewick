package exercise4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise4_1_23 {
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new Graph(in);
        
        // 计算联通分量的数量
        CCFinder ccf = new CCFinder(g);
        int ccNum = ccf.ccNum();
        StdOut.println("The number of CC is : " + ccNum);
        
        int less = 0;
        for (int i = 0; i < ccNum; i++) {
            if (ccf.vectorNumOfCC(i) < 10)
                less++;
        }
        StdOut.println("The number of CC which contains less than 10 vectors is : " + less);
        
        GraphProperties gp = new GraphProperties(g, ccf.maxCCSource());
        StdOut.println("The diameter of the largest CC is : " + gp.diameter());
        StdOut.println("The radius of the largest CC is : " + gp.radius());
        if (gp.center() != -1)
            StdOut.println("The center of the largest CC is : " + gp.center());
        else
            StdOut.println("The center of the largest CC does not exist.");
    }
}
