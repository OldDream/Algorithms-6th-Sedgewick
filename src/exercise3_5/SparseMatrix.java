package exercise3_5;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

// Type is the Object in Matrix.
public class SparseMatrix<Type> {

    private LinearProbingHashST<Integer, Type>[] rowAl;

    @SuppressWarnings("unchecked")
    public SparseMatrix(boolean rowOrCol, int size, int indexOfV, LinearProbingHashST<Integer, Type> vector) {
        // rowOrCol == true, vector is row, otherwise,it would be column.
        // indexOfV [0, size - 1]
        rowAl = (LinearProbingHashST<Integer, Type>[]) new Object[size];
        if (indexOfV < 0 || indexOfV >= size) {
            StdOut.println("Wrong index.");
            System.exit(0);
        }

        if (rowOrCol) {    // vector is a row,the index of it is indexOfV.
            rowAl[indexOfV] = vector;
        }
        else {    // vector is column.
            for (Integer index : vector.keys()) {
                rowAl[index].put(indexOfV,  vector.get(index));
            }
        }
    }
    
    
}
