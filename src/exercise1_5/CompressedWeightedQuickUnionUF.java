package exercise1_5;

import java.util.Scanner;

public class CompressedWeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    
    public CompressedWeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
        
    public int count() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    /*public int find(int p) {
        int root = p;
        while (id[root] != root) {
            root = id[root];
        }
        //Route compression!这个是完全循环，每个都链接到root上，但是，有个更笨的方法。。。
        while (id[p] != p) {
            p = id[p];
            id[p] = root;
            sz[p] = 2;
        }
        
        return root;
    }*/
    
    public int find(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];    //指向其祖辈节点。。。省去了一次loop，效果也很不错。
            p = id[p];
        }
        return p;
    }
    
    public void union(int p, int q) {
        int pRoot = find(p), qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[pRoot] = qRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        CompressedWeightedQuickUnionUF uf = new CompressedWeightedQuickUnionUF(N);
        while (input.hasNext()) {
            int p = input.nextInt(), q = input.nextInt();
            if(uf.connected(p , q))
                ;
            else {
                uf.union(p, q);
                System.out.println(p + " + " + q);
            }
        }
        
        input.close();
        
        System.out.println(uf.count() + " components.");
    }
}
