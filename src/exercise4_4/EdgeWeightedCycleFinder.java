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
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge w : G.adj(v)) {
            if (this.hasCycle())
                return;
            else if (!marked[w.to()]) {
                edgeTo[w.to()] = w;
                dfs(G, w.to());
            }
            else if (onStack[w.to()]) {
                cycle = new Stack<DirectedEdge>();
                for (int i = v; i != w.to(); i = edgeTo[w.from()].from()) {
                    cycle.push(edgeTo[i]);
                }
                cycle.push(w);
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
}
