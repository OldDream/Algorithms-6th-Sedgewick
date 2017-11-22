import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || w < 0 || v >= G.V() || w >= G.V())
            throw new java.lang.IllegalArgumentException();

        int ancestorV = ancestor(v, w);
        int length = -1;
        if (ancestorV != -1) {
            length = getAncestorDist(v, ancestorV) + getAncestorDist(w, ancestorV);
        }

        return length;
    }
    
    private int getAncestorDist (int v, int ancestor) {
        if (v == ancestor)
            return 0;
        
        int[] edgeTo = new int[G.V()];
        Queue<Integer> q = new Queue<>();
        q.enqueue(v);
        
        while (!q.isEmpty()) {
            int tempV = q.dequeue();
            for (int i : G.adj(tempV)) {
                edgeTo[i] = tempV;
                if (i == ancestor)
                    return pathLength(edgeTo, ancestor, v);
                q.enqueue(i);
            }
        }
        return -1;
    }
    
    private int pathLength (int[] edgeTo, int from, int to) {
        int dist = 0;
        for (int x = from; x != to; x = edgeTo[x]) {
            dist++;
        }
        return dist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || w < 0 || v >= G.V() || w >= G.V())
            throw new java.lang.IllegalArgumentException();

        int ancestorNum = -1;
        boolean[] marked = new boolean[G.V()];
        marked[v] = true;
        marked[w] = true;
        Queue<Integer> q = new Queue<>();
        q.enqueue(v);
        q.enqueue(w);

        while (!q.isEmpty()) {
            int temp = q.dequeue();    // pull v or w out in turn.
            for (int i : G.adj(temp)) {
                if (marked[i]) {
                    ancestorNum = i;
                    return ancestorNum;
                }
                else {
                    marked[i] = true;
                    q.enqueue(i);
                }
            }
        }

        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int ancestorV = ancestor(v, w);
        if (ancestorV == -1)
            return -1;
        
        int minFromV = Integer.MAX_VALUE;
        int minFromW = Integer.MAX_VALUE;
        
        for (int i : v) {
            int tempL = getAncestorDist(i , ancestorV);
            if (tempL < minFromV)
                minFromV = tempL;
        }
        for (int i : w) {
            int tempL = getAncestorDist(i , ancestorV);
            if (tempL < minFromW)
                minFromW = tempL;
        }
        
        return minFromV + minFromW;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        boolean[] marked = new boolean[G.V()];
        Queue<Integer> q = new Queue<>();
        for (int i : v) {
            if (i < 0 || i >= G.V())
                throw new java.lang.IllegalArgumentException();
            q.enqueue(i);
        }
        for (int i : w) {
            if (i < 0 || i >= G.V())
                throw new java.lang.IllegalArgumentException();
            q.enqueue(i);
        }
        
        while(!q.isEmpty()) {
            int tempV = q.dequeue();
            if (!marked[tempV]) {
                marked[tempV] = true;
                for (int i : G.adj(tempV))
                    q.enqueue(i);
            }
            return tempV;
        }
        
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}