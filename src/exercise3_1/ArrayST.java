package exercise3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Base on Unsorted array!
public class ArrayST<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public ArrayST(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException();
        if (isEmpty())
            return null;
        int i = rank(key);
        
        if (i != -1 && i < N && keys[i].equals(key))
            return vals[i];
        else 
            return null;
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException();
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key))
                return i;
        }
        return -1;
    }

    public void put(Key key, Value val) {
        if (key == null || val == null)
            throw new NullPointerException();
        int i = rank(key);
        if (i != -1 && i < N && keys[i].equals(key))
            vals[i] = val;
        else {
            keys[N] = key;
            vals[N] = val;
            N++;
        }
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException();
        int i = rank(key);
        if (i != -1 && i < N && keys[i].equals(key)) {
            for (int j = i; j + 1 < N; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            N--;
        }
    }
    
    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException();
        return get(key) != null;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> qu = new Queue<>();
        for (int i = 0; i < keys.length; i++)
            qu.enqueue(keys[i]);
        return qu;
    }
    
    public static void main(String[] args) {
        ArrayST<String, Double> grades = new ArrayST<String, Double>(11);
        
        grades.put("A",  4.00);
        grades.put("B",  3.00);
        grades.put("C",  2.00);
        grades.put("D",  1.00);
        grades.put("F",  0.00);
        grades.put("A+", 4.33);
        grades.put("B+", 3.33);
        grades.put("C+", 2.33);
        grades.put("A-", 3.67);
        grades.put("B-", 2.67);
        
        int n = 0;
        double total = 0.0;
        for (n = 0; !StdIn.isEmpty(); n++) {
            String grade = StdIn.readString();
            double value = grades.get(grade);
            total += value;
        } 
        double gpa = total / n;
        StdOut.println("GPA = " + gpa);
    }

}
