package exercise4_3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;    // the closest edge form [v] to MST,like v -> w
    private double[] distTo;    // distTo[w] = edgeTo[w].weight()
    private boolean[] marked;    // marked[v] = true if v is in MST
    private IndexMinPQ<Double> pq;    // available cuts
    private double weight;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(G, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w])
                continue;
            if (e.weight() < distTo[w]) {
                double oldWeight = distTo[w];
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                    weight -= oldWeight;
                    weight += distTo[w];
                }
                else {
                    pq.insert(w, distTo[w]);
                    weight += distTo[w];
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Bag<Edge> mst = new Bag<Edge>();
        for (int i = 1; i < edgeTo.length; i++)    // i = 0,there is a self-cycle.
            mst.add(edgeTo[i]);
        return mst;
    }

    public double weight() {
        return weight;
    }

}
