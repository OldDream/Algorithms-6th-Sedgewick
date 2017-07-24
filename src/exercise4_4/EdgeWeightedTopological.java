package exercise4_4;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class EdgeWeightedTopological {
    private Iterable<Integer> order;
    
    public EdgeWeightedTopological(EdgeWeightedDigraph G) {
        EdgeWeightedCycleFinder cyclefinder = new EdgeWeightedCycleFinder(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    
    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
