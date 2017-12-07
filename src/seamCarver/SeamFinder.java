package seamCarver;

public class SeamFinder {
    // find seam form top to bottom
    private static int[] seam;

    protected static int[] findSeam(double[][] eMatrix) {
        seam = new int[eMatrix.length];
        int[][] savePaths = new int[eMatrix[0].length][];
        double[] distOfPaths = new double[eMatrix[0].length];

        // start from top edge.
        for (int i = 0; i < eMatrix[0].length; i++) {
            AcyclicSPForMatrix pathFinder = new AcyclicSPForMatrix(eMatrix, i);
            savePaths[i] = pathFinder.getShortestPath();
            distOfPaths[i] = pathFinder.getShortestDist();
        }
        
        // find the shortest one 
        int indexOfMin = 0;
        double minDist = distOfPaths[0];
        for (int i = 1; i < distOfPaths.length; i++) {
            if (distOfPaths[i] < minDist) {
                minDist = distOfPaths[i];
                indexOfMin = i;
            }
        }
        
        
        return seam;
    }
    
    
}
