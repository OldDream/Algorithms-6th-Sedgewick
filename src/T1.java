import edu.princeton.cs.algs4.Queue;

public class T1 {
    public static void main(String[] args) {
        int i = 1, j = i + 1;
        System.out.println("i = " + i + " j = " + j);
        double k = 0.7;
        if (k > 0.7) 
            System.out.println("Ha");
        else
            System.out.println("Ma");
        
        Queue<Integer> q = new Queue<>();
        q.enqueue(3);
        q.enqueue(3);
        q.enqueue(3);
        
        while (!q.isEmpty())
            System.out.println(q.dequeue());
    }
}
