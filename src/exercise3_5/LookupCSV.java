package exercise3_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Programming assignment 3_5_12;

public class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
/*        for (String string : args) {
            StdOut.println(string);
        }*/
        ST<String, Queue<String>> st = new ST<>();    // The implement of ST is a treeMap, so, it couldn't link one Key with multiple Values
                                                             // So I have to use <String, Queue<String>>, in order t o
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
//            StdOut.println("Key = " + key + "; Value = " + val);
            if (!st.contains(key)) {
                Queue<String> tempQ = new Queue<>();
                tempQ.enqueue(val);
                st.put(key, tempQ); 
            }
            else {
                Queue<String> tempQ = st.get(key);
                tempQ.enqueue(val);
            }
            
        }
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query))
                StdOut.println(st.get(query));
            else {
                StdOut.println("Doesn't contain query string.");
            }
        }
    }
}