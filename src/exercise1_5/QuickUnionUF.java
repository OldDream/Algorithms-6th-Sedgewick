package exercise1_5;

import java.util.Scanner;

public class QuickUnionUF {
    private int[] id;
    private int count;
    
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
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
        id[pRoot] = qRoot;
        count--;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        QuickUnionUF uf = new QuickUnionUF(N);
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
