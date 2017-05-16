package exercise3_4;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class ModSequentialSearchST<Key, Value> {
    private Node first;    // 链表首结点
    private int num;

    private class Node {
        Key key;
        Value val;
        Node next;
        int totalNum;

        public Node(Key key, Value val, Node next, int totalNum) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.totalNum = totalNum;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }
    
    public int getTotalNum(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.totalNum;
        }
        return -1;
    }

    public void put(Key key, Value val, int totalNum) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                x.totalNum = totalNum;
                return;
            }
        }
        first = new Node(key, val, first, totalNum);
        num++;
    }

    public int size() {
        return num;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException();
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next.key = null;
                x.next.val = null;
                x.next = x.next.next;
                return;
            }
        }
        return;
    }

    public Iterable<Key> keys() {
        Queue<Key> qe = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            qe.enqueue(x.key);
        }
        return qe;
    }
    
    public static void main(String[] args) {
        ModSequentialSearchST<Integer, String> ssSt = new ModSequentialSearchST<>();

        
        StdOut.println(ssSt.get(1) + " " + ssSt.get(2) + " " + ssSt.get(3));
    }
}

