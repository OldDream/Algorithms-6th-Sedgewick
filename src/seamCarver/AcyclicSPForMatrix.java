package seamCarver;

import java.awt.Point;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class AcyclicSPForMatrix {
    private double[][] distTo;
    private static int[][] vertexTo;
    private int width, height, edge;
    private double[][] eMatrix;
    private double minDist;

    public AcyclicSPForMatrix(double[][] eMatrix, int s) {
        this.eMatrix = eMatrix;
        width = eMatrix.length;
        height = eMatrix[0].length;
        edge = height - 1;
        vertexTo = new int[width][height];
        // save the distance from source point to this point.
        distTo = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                distTo[i][j] = Double.POSITIVE_INFINITY;
                vertexTo[i][j] = -1;
            }
        }

        distTo[s][0] = 0.0;
        for (Point i : getToplogicalOrder(s)) {
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
        for (int i = 1; i < width; i++) {
            if (distTo[i][edge] < minDist)
                bottomXOfPath = i;
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

    private void relaxVertex(Point i) {
        int x = i.x;
        int y = i.y;
        if (y == edge)
            return;
        int nextY = y + 1;
        int leftX = x - 1;
        int rightX = x + 1;
        int middleX = x;
        
        if (leftX >= 0) {
            if (distTo[leftX][nextY] > distTo[x][y] + eMatrix[x][y]) {
                distTo[leftX][nextY] = distTo[x][y] + eMatrix[x][y];
                vertexTo[leftX][nextY] = x;
            }
        }

        if (rightX < width) {
            if (distTo[rightX][nextY] > distTo[x][y] + eMatrix[x][y]) {
                distTo[rightX][nextY] = distTo[x][y] + eMatrix[x][y];
                vertexTo[rightX][nextY] = x;
            }
        }

        if (middleX >= 0 && middleX < width) {
            if (distTo[middleX][nextY] > distTo[x][y] + eMatrix[x][y]) {
                distTo[middleX][nextY] = distTo[x][y] + eMatrix[x][y];
                vertexTo[middleX][nextY] = x;
            }
        }
    }

    private Queue<Point> getToplogicalOrder(int s) {
        Queue<Point> q = new Queue<>();
        Stack<Point> stack = new Stack<>();

        stack.push(new Point(s, 0));
        q.enqueue(new Point(s, 0));
        while (!stack.isEmpty()) {
            Point temp = stack.pop();
            int x = temp.x;
            int y = temp.y;

            if (y == edge) { // detect the bottom edge
                continue;
            }

            int nextY = y + 1;
            int leftX = x - 1;
            int rightX = x + 1;
            int middleX = x;

            if (leftX >= 0) {
                Point temp1 = new Point(leftX, nextY);
                stack.push(temp1);
                q.enqueue(temp1);
            }

            if (middleX >= 0 && middleX < width) {
                Point temp1 = new Point(middleX, nextY);
                stack.push(temp1);
                q.enqueue(temp1);
            }

            if (rightX < width) {
                Point temp1 = new Point(rightX, nextY);
                stack.push(temp1);
                q.enqueue(temp1);
            }
        }
        return q;
    }
}
