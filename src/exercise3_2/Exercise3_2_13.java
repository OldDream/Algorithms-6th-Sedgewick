package exercise3_2;

import edu.princeton.cs.algs4.Queue;

public class Exercise3_2_13<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;    // 该结点为根的子树中的结点数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public Value get(Key key) {
        Node x = root;
        
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        Node previous = null;
        Node now = root;
        if (root == null) {
            root = new Node(key, val, 1);
            return;
        }
        
        while (now != null) {
            previous = now;
            int cmp = key.compareTo(now.key);
            if (cmp < 0)
                now = now.left;
            else if (cmp > 0)
                now = now.right;
            else {
                now.val = val;
                return;
            }
        }
        int cmpo = key.compareTo(previous.key);
        if (cmpo > 0)
            previous.right = new Node(key, val, 1);
        else
            previous.left = new Node(key, val, 1);
        // 重置路径上各个结点的N
        previous = null;
        now = root;
        
        while (now != null) {
            now.N++;
            previous = now;
            int cmp = key.compareTo(now.key);
            if (cmp < 0)
                now = now.left;
            else if (cmp > 0)
                now = now.right;
            else {
                now.N--;    // 最后一个结点会多加
                return;
            }
        }
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);    // cmp > 0
        if (t != null)
            return t;
        else
            return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);    // cmp < 0
        if (t != null)
            return t;
        else
            return x;
    }

    public Key select(int k) {    // 返回排名为k的结点
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null)
            return null;
        int t = size(x.left);
        if (k < t)
            return select(x.left, k);
        if (k > t)
            return select(x.right, k - t - 1);
        return x;
    }

    public int rank(Key key) {    // 返回小于key的键的数量
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(x.left, key);
        if (cmp > 0)
            return size(x.left) + 1 + rank(x.right, key);
        return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(x.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            queue.enqueue(x.key);
        if (cmphi > 0)
            keys(x.right, queue,lo, hi);
    }
    
    public int height() {
        return height(root);
    }
    
    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
}
