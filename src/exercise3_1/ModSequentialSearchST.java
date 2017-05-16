package exercise3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class ModSequentialSearchST<Key, Value> {
    private Node first;    // 链表首结点
    private int num;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
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
        ssSt.put(1, "Jiang");
        ssSt.put(2, "Ze");
        ssSt.put(3, "ha");
        ssSt.put(3, "Min");
        
        StdOut.println(ssSt.get(1) + " " + ssSt.get(2) + " " + ssSt.get(3));
    }
}
