package exercise4_1;

import edu.princeton.cs.algs4.Bag;

// find all connected components from a graph
public class CCFinder {
    private int[] ccState;
    private int ccCounter;      // the number of connected components
    private Bag<Integer>[] cc;      // a chain list of connected components
    
    public CCFinder(Graph g) {
        ccCounter = 0;
        ccState = new int[g.V()];
        for (int i = 0; i < ccState.length; i++)
            ccState[i] = -1;

        for (int i = 0; i < ccState.length; i++) {
            if (ccState[i] == -1) {
                ccState[i] = ccCounter;
                dfs(g, i);
                ccCounter++;
            }
        }
        
        cc = (Bag<Integer>[]) new Bag[ccCounter];
        for (int i = 0; i < ccState.length; i++) {
            cc[ccState[i]].add(i);
        }
    }

    private void dfs(Graph g, int v) {
        for (int w : g.adj(v)) {
            ccState[w] = ccCounter;
            dfs(g, w);
        }
    }
    
    public int ccNum() {
        return ccCounter;
    }
    
    public int vectorNumOfCC(int ccIndex) {
        return cc[ccIndex].size();
    }
    
    public int maxCCSource() {
        int indexOfCC = 0;    // the index of maxCC in cc.
        for (int i = 1; i < cc.length; i++) {
            if (cc[indexOfCC].size() < cc[i].size()) 
                indexOfCC = i;
        }
        
        int source = 0;
        for (int i : cc[indexOfCC]) {
            source = i;
            break;
        }
        
        return source;
    }
}
