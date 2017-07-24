package exercise4_4;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])
                dfs(G, i);
        }
        
     // check that digraph has a cycle
        assert check();
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (this.hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);     // 此时f的起点是w
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    private boolean check() {

        // edge-weighted digraph is cyclic
        if (hasCycle()) {
            // verify cycle
            DirectedEdge first = null, last = null;
            for (DirectedEdge e : cycle()) {    // 保证环中每一节相连
                if (first == null)
                    first = e;    // first 为stack中第一个出来的
                if (last != null) {
                    if (last.to() != e.from()) {    // 此时last 为上一次循环的e，所以...
                        System.err.printf("cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.to() != first.from()) {    // make sure the cycle has the same head and tail.
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }

        return true;
    }

}
