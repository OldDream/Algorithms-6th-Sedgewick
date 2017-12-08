package seamCarver;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

public class AcyclicSPForMatrix {
    private double[][] distTo;
    private int[][] vertexTo;
    private final int width, height, edge;
    private final double[][] eMatrix;
    private double minDist;

    public AcyclicSPForMatrix(double[][] eMatrix, int s) {
        double[][] temp = new double[eMatrix.length][eMatrix[0].length];
        for (int i = 0; i < eMatrix.length; i++) {
            System.arraycopy(eMatrix[i], 0, temp[i], 0, eMatrix[i].length);
        }
        this.eMatrix = temp;
        width = eMatrix.length;
        height = eMatrix[0].length;
        edge = height - 1;
        vertexTo = new int[width][height];
        // save the distance from source point to this point.
        distTo = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                distTo[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        distTo[s][0] = 0.0;
        for (Point2D i : getToplogicalOrder(s)) {
            // the i was in topological order!
            // relax the vertex in order than we get the shortest path.
            relaxVertex(i);
        }
    }

    public int[] getShortestPath() {
        // find the shortest path.
        // the SP ends at bottomXOfPath
        int bottomXOfPath = 0;
        minDist = distTo[0][edge];
        for (int i = 0; i < width; i++) {
            if (distTo[i][edge] < minDist) {
                minDist = distTo[i][edge];
                bottomXOfPath = i;
            }
        }

        int[] path = new int[height];
        path[height - 1] = bottomXOfPath; // put bottomXOfPath in the end
        int temp = bottomXOfPath;
        for (int i = height - 1; i >= 0; i--) {
            if (i == height - 1)
                continue;
            path[i] = vertexTo[temp][i + 1];
            temp = vertexTo[temp][i + 1];
        }

        return path;
    }

    public double getShortestDist() {
        return minDist;
    }

    private void relaxVertex(Point2D i) {
        int x = (int) i.x();
        int y = (int) i.y();
        if (y == edge)
            return;
        int nextY = y + 1;
        int leftX = x - 1;
        int rightX = x + 1;
        int middleX = x;
        double terget = distTo[x][y] + eMatrix[x][y];

        if (leftX >= 0 && distTo[leftX][nextY] > terget) {
            distTo[leftX][nextY] = terget;
            vertexTo[leftX][nextY] = x;
        }

        if (rightX < width && distTo[rightX][nextY] > terget) {
            distTo[rightX][nextY] = terget;
            vertexTo[rightX][nextY] = x;
        }

        if (middleX >= 0 && middleX < width && distTo[middleX][nextY] > terget) {
            distTo[middleX][nextY] = terget;
            vertexTo[middleX][nextY] = x;
        }
    }

    private Queue<Point2D> getToplogicalOrder(int s) {
        Queue<Point2D> q = new Queue<>();
        Queue<Point2D> sq = new Queue<>();

        Point2D start = new Point2D(s, 0);
        Point2D previous = null;
        q.enqueue(start);

        // manually run the first point

        int firstNextY = 1;
        int firstLeftX = s - 1;
        int firstRightX = s + 1;
        int firstMiddleX = s;

        if (0 == edge)
            return q;

        if (firstLeftX >= 0) {
            Point2D temp1 = new Point2D(firstLeftX, firstNextY);
            sq.enqueue(temp1);
            q.enqueue(temp1);
        }

        if (firstMiddleX >= 0 && firstMiddleX < width) {
            Point2D temp1 = new Point2D(firstMiddleX, firstNextY);
            sq.enqueue(temp1);
            q.enqueue(temp1);
        }

        if (firstRightX < width) {
            Point2D temp1 = new Point2D(firstRightX, firstNextY);
            sq.enqueue(temp1);
            q.enqueue(temp1);
        }

        previous = start;

        while (!sq.isEmpty()) {
            Point2D temp = sq.dequeue();
            int x = (int) temp.x();
            int y = (int) temp.y();
            
            if (y >= edge) { // detect the bottom edge
                continue;
            }
            
            int previousY = (int) previous.y();
            int nextY = y + 1;
            int leftX = x - 1;
            int rightX = x + 1;
            int middleX = x;

            if (previousY < y) { // previous point and temp point are in DIFFERENT row. 
                                 // So try all points
                if (leftX >= 0) {
                    Point2D temp1 = new Point2D(leftX, nextY);
                    sq.enqueue(temp1);
                    q.enqueue(temp1);
                }

                if (middleX >= 0 && middleX < width) {
                    Point2D temp1 = new Point2D(middleX, nextY);
                    sq.enqueue(temp1);
                    q.enqueue(temp1);
                }

                if (rightX < width) {
                    Point2D temp1 = new Point2D(rightX, nextY);
                    sq.enqueue(temp1);
                    q.enqueue(temp1);
                }
            } else { // previous point and temp point are in THE SAME row. 
                     // So only try the right point, 
                     // middle and left are already there.
                if (rightX < width) {
                    Point2D temp1 = new Point2D(rightX, nextY);
                    sq.enqueue(temp1);
                    q.enqueue(temp1);
                }
            }
            previous = temp; // set temp as previous point
        }
        return q;
    }
}
