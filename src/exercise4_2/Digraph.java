package exercise4_2;

import edu.princeton.cs.algs4.Bag;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    // make a copy of S
    public Digraph(Digraph S) {
        this.V = S.V();
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<Integer>();
            for (int j : S.adj(i))
                addEdge(i, j);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // add an edge form v -> w.
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int j : R.adj(i)) {
                R.addEdge(j, i);
            }
        }
        return R;
    }
}
