package exercise4_1;

import edu.princeton.cs.algs4.Graph;

public class GraphProperties {
    private boolean[] marked;
    private int count;
    private Graph g;
    private int[] diameter;    // the eccentricity of each vector

    public GraphProperties(Graph g) throws Exception {
        this.g = g;
        // check if the graph is connected.
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }

        if (count < g.V())
            throw (new Exception("The Graph is not connected."));
    }
    
    public GraphProperties(Graph g, int s) {
        // mark one of the connected components of the graph.
        this.g = g;
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                count++;
            }
        }
    }

    public int eccentricity(int v) {
        int[] depthCounter = new int[g.V()];     // save the distance of the farthest Vector
        for (int i = 0; i < depthCounter.length; i++) {
            depthCounter[i] = -1;
        }
        
        depthCounter[v] = 0;
        for (int w : g.adj(v)) {
            depthCounter[w] = depthCounter[v] + 1;
            eccentricity(w, depthCounter);
        }

        int max = 0;
        for (int i = 0; i < depthCounter.length; i++) {
            if (depthCounter[i] > max)
                max = depthCounter[i];
        }

        return max;
    }

    private void eccentricity(int v, int[] depthCounter) {
        for (int w : g.adj(v)) {
            if (depthCounter[w] != -1)
                continue;
            depthCounter[w] = depthCounter[v] + 1;
            eccentricity(w, depthCounter);
        }
    }

    public int diameter() {
        if (diameter == null) {
            diameter = new int[g.V()];
            for (int i = 0; i < g.V(); i++) {
                diameter[i] = eccentricity(i);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < diameter.length; i++) {
            if (diameter[i] > max && marked[i])
                max = diameter[i];
        }

        return max;
    }

    public int radius() {
        if (diameter == null) {
            diameter = new int[g.V()];
            for (int i = 0; i < g.V(); i++) {
                diameter[i] = eccentricity(i);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < diameter.length; i++) {
            if (diameter[i] < min && marked[i])
                min = diameter[i];
        }

        return min;
    }
    
    public int center() {
        int radius = radius();
        for (int i = 0; i < g.V(); i++) {
            int eccI = eccentricity(i);
            if (eccI == radius && marked[i])
                return i;
        }
        
        return -1;    // there is no center;
    }

}
