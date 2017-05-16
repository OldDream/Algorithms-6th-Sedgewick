package exercise3_4;

import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        M = 16;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2)
            resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    private boolean contains(Key key) {
        for (Key keyIn : keys) {
            if (keyIn != null)
                if (keyIn.equals(key))
                    return true;
        }
        return false;
    }

    public void delete(Key key) {
        if (!contains(key))
            return;
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N <= M / 8)
            resize(M / 2);
    }
    
    public Iterable<Value> vals() {
        Queue<Value> qe = new Queue<>();
        for (Value value : vals) {
            if (value != null)
                qe.enqueue(value);
        }
        return qe;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> qe = new Queue<>();
        for (Key key : keys) {
            if (key != null)
                qe.enqueue(key);
        }
        return qe;
    }
    
    
    public static void main(String[] args) {
        LinearProbingHashST<Character, Character> st = new LinearProbingHashST<>();
        System.out.println("Enter a string : ");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), str.charAt(i));
        }
        st.delete('C');
        
    }
}
