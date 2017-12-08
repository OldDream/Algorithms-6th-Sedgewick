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
        Picture newPic = new Picture(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                newPic.setRGB(x, y, pic.getRGB(x, y));
            }
        }
        pic = newPic;
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

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        renewEnergyMatrix();
        int[] seam = SeamFinder.findSeam(transposeMatrix(energyMatrix));
        return seam;
    }
    
    private double[][] transposeMatrix(double[][] eM) {
        double[][] result = new double[eM[0].length][eM.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = eM[j][i];
            }
        }
        return result;
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        width--;
        for (int y = 0; y < height(); y++) {
            for (int x = seam[y]; x < width(); x++) {
                // when x >= seam[y], copy pixel from the right one
                pic.setRGB(x, y, pic.getRGB(x + 1, y));
            }
        }
        
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        height--;
        for (int x = 0; x < width(); x++) {
            for (int y = seam[x]; y < height(); y++) {
                pic.setRGB(x, y, pic.getRGB(x, y + 1));
            }
        }
    }
    
    
/*    // low efficiency version
    public void removeVerticalSeam(int[] seam) {
        Picture newPic = new Picture(width - 1, height);
        for (int y = 0; y < newPic.height(); y++) {
            for (int x = 0; x < newPic.width(); x++) {
                if (x < seam[y]) {
                    newPic.setRGB(x, y, pic.getRGB(x, y));
                } else {  // x >= seam[y]
                    newPic.setRGB(x, y, pic.getRGB(x + 1, y));
                } 
            }
        }
        pic = newPic;
        width--;
    }
    */
    
   
}
