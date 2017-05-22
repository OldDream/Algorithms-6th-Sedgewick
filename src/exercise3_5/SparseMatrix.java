package exercise3_5;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

// Type is the Object in Matrix.
public class SparseMatrix {

    private LinearProbingHashST<Integer, Double>[] rowAl;
    private int size;

    @SuppressWarnings("unchecked")
    public SparseMatrix(boolean rowOrCol, int size, int indexOfV, LinearProbingHashST<Integer, Double> vector) {
        // rowOrCol == true, vector is row, otherwise,it would be column.
        // indexOfV [0, size - 1]
        this.size = size;
        rowAl = (LinearProbingHashST<Integer, Double>[]) new Object[size];
        if (indexOfV < 0 || indexOfV >= size) {
            StdOut.println("Wrong index.");
            System.exit(0);
        }

        if (rowOrCol) {    // vector is a row,the index of it is indexOfV.
            rowAl[indexOfV] = vector;
        }
        else {    // vector is column.
            for (Integer index : vector.keys()) {
                rowAl[index].put(indexOfV, vector.get(index));
            }
        }
    }

    public void add(SparseMatrix that) {
        if (that.size() != size) {
            StdOut.println("Wrong size.");
            System.exit(0);
        }
        for (int i = 0; i < size; i++) {
            // rowAl[i] is the row we are going to deal with.
            for (Integer index : that.getRow(i).keys()) {
                if (this.getRow(i).contains(index)) {
                    Double val = this.getRow(i).get(index);
                    val += that.getRow(i).get(index);
                    this.getRow(i).put(index, val);
                }
                else {
                    Double val = that.getRow(i).get(index);
                    this.getRow(i).put(index, val);
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public LinearProbingHashST<Integer, Double> getRow(int index) {
        return rowAl[index];
    }

}
