package exercise4_3;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;
    
    public KruskalMST(EdgeWeightedGraph G) {
        weight = 0.0;
        mst = new Queue<Edge>();
        Edge[] tempE = new Edge[G.E()];
        int tempIndex = 0;
        for(Edge e : G.edges()) {
            tempE[tempIndex++] = e;
        }
        MinPQ<Edge> pq = new MinPQ<Edge>(tempE);
        UF uf = new UF(G.V());
        
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w))
                continue;
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }
    
    public Iterable<Edge> edges() {
        return mst;
    }
    
    public double weight() {
        return weight;
    }
}
