import edu.princeton.cs.algs4.SET;

import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
    private SET<Point2D> pointSet;

    public PointSET() {
        // construct an empty set of points
        pointSet = new SET<>();
    }

    public boolean isEmpty() {
        // is the set empty?
        return pointSet.isEmpty();
    }

    public int size() {
        // number of points in the set
        return pointSet.size();
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new NullPointerException();
        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        if (p == null)
            throw new NullPointerException();
        return pointSet.contains(p);
    }

    public void draw() {
        // draw all points to standard draw
        Iterator<Point2D> iterPoints = pointSet.iterator();

        while (iterPoints.hasNext()) {
            Point2D temp = iterPoints.next();
            temp.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        if (rect == null)
            throw new NullPointerException();
        Iterator<Point2D> iterPoints = pointSet.iterator();
        SET<Point2D> tempSet = new SET<>();

        while (iterPoints.hasNext()) {
            Point2D temp = iterPoints.next();
            if (rect.contains(temp))
                tempSet.add(temp);
        }
        
        return tempSet;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new NullPointerException();
        
        Iterator<Point2D> iterPoints = pointSet.iterator();
        double distance = Double.MAX_VALUE;
        Point2D tempPoint = null;
        
        while (iterPoints.hasNext()) {
            Point2D temp = iterPoints.next();
            double tempDis = p.distanceSquaredTo(temp);
            if (tempDis < distance) {
                distance = tempDis;
                tempPoint = temp;
            }
        }
        
        return tempPoint;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}