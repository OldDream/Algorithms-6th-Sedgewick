package exercise1_5;
import java.util.Scanner;

public class QuickFindUF {
    private int[] id;
    private int count;
    
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    
    public int count() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
    
    public int find(int p) {
        return id[p];
    }
    
    public void union(int p, int q) {
        int idP = id[p], idQ = id[q];
        if (idP == idQ)
            return;
        else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == idP)
                    id[i] = idQ;
            }
        }
        count--;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        QuickFindUF uf = new QuickFindUF(N);
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
