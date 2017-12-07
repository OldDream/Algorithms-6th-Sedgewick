package seamCarver;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture pic;
    private int width, height;
    private double[][] energyMatrix;
    
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        pic = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }

    // current picture
    public Picture picture() {
        return pic;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            throw new IllegalArgumentException();
        double energy = CalcualteEnergy.getEnergy(x, y, pic);
        return energy;
    }
    
    // renew the Matrix of energy
    private void renewEnergyMatrix() {
        energyMatrix = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // row i and column j
                energyMatrix[i][j] = energy(i, j);
            }
        }
    }
    
 // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        renewEnergyMatrix();
        int[] seam = SeamFinder.findSeam(energyMatrix);
        return seam;
    }

   /* // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
    }

    

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
    }*/
    
   
}
