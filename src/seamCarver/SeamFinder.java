package seamCarver;

public class SeamFinder {
    // find seam form top to bottom

    public static int[] findSeam(double[][] eMatrix) {
        int[][] savePaths = new int[eMatrix.length][];
        double[] distOfPaths = new double[eMatrix.length];

        // start from top edge.
        for (int i = 0; i < eMatrix.length; i++) {
            AcyclicSPForMatrix pathFinder = new AcyclicSPForMatrix(eMatrix, i);
            savePaths[i] = pathFinder.getShortestPath();
            distOfPaths[i] = pathFinder.getShortestDist();
        }

        // find the shortest one
        int indexOfMin = 0;
        double minDist = distOfPaths[0];
        for (int i = 0; i < distOfPaths.length; i++) {
            if (distOfPaths[i] < minDist) {
                minDist = distOfPaths[i];
                indexOfMin = i;
            }
        }

        return savePaths[indexOfMin];
    }
}
