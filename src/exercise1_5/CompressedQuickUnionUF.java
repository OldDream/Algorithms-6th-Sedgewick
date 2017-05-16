package exercise1_5;

import java.util.Scanner;

public class CompressedQuickUnionUF {
    private int[] id;
    private int count;
    
    public CompressedQuickUnionUF(int N) {
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
    
    //在此添加压缩操作。
    public int find(int p) {
        int root = p;
        while (id[root] != root) {
            root = id[root];
        }
        //Route compression!
        while (id[p] != p) {
            p = id[p];
            id[p] = root;
        }
        
        return root;
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
        CompressedQuickUnionUF uf = new CompressedQuickUnionUF(N);
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
