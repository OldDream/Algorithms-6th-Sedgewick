package exercise2_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Exercise2_5_8 {
    private static class StringNode implements Comparable<StringNode>{
        int frequency;
        String str = null;
        
        public StringNode(String i) {
            str = i;
            frequency = 1;
        }
        
        public String toString() {
            return str;
        }

        @Override
        public int compareTo(StringNode o) {
            if (frequency > o.frequency)
                return 1;
            else if (frequency == o.frequency)
                return 0;
            else 
                return -1;
            
        }
    }
    
    public static void main(String[] args) {
        String[] strArray = StdIn.readAllStrings();
        Arrays.sort(strArray);
        int n = strArray.length;
        StringNode[] strN = new StringNode[n];
        
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (j != -1 && strN[j] != null && strN[j].str.equals(strArray[i])) {
                    strN[j].frequency++;
            }
            else {
                j++;
                StringNode temp = new StringNode(strArray[i]);
                strN[j] = temp;
            }
        }
        
        Arrays.sort(strN, 0, j + 1);
        for (int i = j; i >= 0; i--) {
            StdOut.println(strN[i].toString());
        }
    }
}
