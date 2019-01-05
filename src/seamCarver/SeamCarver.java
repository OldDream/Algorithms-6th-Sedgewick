package seamCarver;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture pic;
    private int width, height;
    private double[][] energyMatrix;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();
        pic = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }

    // current picture
    public Picture picture() {
        if (width == pic.width() && height == pic.height())
            return new Picture(pic);
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
        double energy = CalcualteEnergy.getEnergy(x, y, pic, width, height);
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
        checkVerticalSeam(seam);
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
        checkHorizontalSeam(seam);
        height--;
        for (int x = 0; x < width(); x++) {
            for (int y = seam[x]; y < height(); y++) {
                pic.setRGB(x, y, pic.getRGB(x, y + 1));
            }
        }
    }

    private void checkVerticalSeam(int[] seam) {
        if (seam == null || seam.length != height) {
            throw new IllegalArgumentException();
        }
        for (int i = 0, j = 1; j < seam.length; i++, j++) {
            int first = seam[i];
            int second = seam[j];
            if (Math.abs(first - second) > 1 || first < 0 || second < 0 || first >= width
                    || second >= width) {
                throw new IllegalArgumentException();
            }
        }
    }
    
    private void checkHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width) {
            throw new IllegalArgumentException();
        }
        for (int i = 0, j = 1; j < seam.length; i++, j++) {
            int first = seam[i];
            int second = seam[j];
            if (Math.abs(first - second) > 1 || first < 0 || second < 0 || first >= height
                    || second >= height) {
                throw new IllegalArgumentException();
            }
        }
    }

}
