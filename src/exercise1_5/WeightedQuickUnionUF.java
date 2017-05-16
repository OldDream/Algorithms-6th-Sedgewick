package exercise1_5;

import java.util.Scanner;

public class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz;
    private int count;
    
    public WeightedQuickUnionUF(int N) {
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
    
    public int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        
        return p;
    }
    
    public void union(int p, int q) {
        int pRoot = find(p), qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (sz[pRoot] > sz[qRoot]) {    //小树接到大树上，
            id[qRoot] = pRoot;      //p大q小
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[pRoot] = qRoot;    //q大p小
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
