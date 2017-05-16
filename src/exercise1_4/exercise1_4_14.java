package exercise1_4;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class exercise1_4_14 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            list.add(input.nextInt());
        }
        int size = list.size();
        Integer[] array = (Integer[])list.toArray(new Integer[size]);
        int[] array1 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array1[i] = array[i];
        }
        Arrays.sort(array1);
        System.out.println(finder(array1));
        
    }
    
    public static int finder(int[] a) {
        int counter = 0, n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (BinarySearch.indexOf(a, - a[i] - a[j] - a[k]) > k)
                        counter++;
                }
            }
        }
        return counter;
    }
}
