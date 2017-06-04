import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
    private Node root = null;
    private int count;

    private class Node {
        private Point2D pointHere = null;
        private Node left = null, right = null;
        private boolean compareX = true;    // the node split with X coordinate or Y coordinate? true == X
        private RectHV rect = null;    // the correspond rectangle

        public Node(Point2D p, Node father) {
            count++;
            pointHere = p;

            if (father == null)
                compareX = true;
            else
                compareX = !father.getCompare();

            if (father == null)
                rect = new RectHV(0, 0, 1, 1);
            else {
                RectHV faRect = father.getRect();
                Point2D fatherPoint = father.getPoint();
                if (father.getCompare() && p.x() < father.pointHere.x()) {
                    // true means separate with a VERTICAL line.
                    // Left half of father's rectangle.
                    rect = new RectHV(faRect.xmin(), faRect.ymin(), fatherPoint.x(), faRect.ymax());
                }
                else if (father.getCompare() && p.x() >= father.pointHere.x()) {
                    // right half.
                    rect = new RectHV(fatherPoint.x(), faRect.ymin(), faRect.xmax(), faRect.ymax());
                }
                else if (!father.getCompare() && p.y() < father.pointHere.y()) {
                    // true means separate with a Horizontal line.
                    // Lower half of father's rectangle.
                    rect = new RectHV(faRect.xmin(), faRect.ymin(), faRect.xmax(), fatherPoint.y());
                }
                else {
                    // Upper half of father's rectangle.
                    rect = new RectHV(faRect.xmin(), fatherPoint.y(), faRect.xmax(), faRect.ymax());
                }
            }
        }

        public boolean getCompare() {
            return compareX;
        }

        public RectHV getRect() {
            return rect;
        }

        public Point2D getPoint() {
            return pointHere;
        }

        public Node getLeftChild() {
            return left;
        }

        public Node getRightChild() {
            return right;
        }

        public void setLeftChild(Node n) {
            left = n;
        }

        public void setRightChild(Node n) {
            right = n;
        }
    }

    public KdTree() {
        // construct an empty set of points
    }

    public boolean isEmpty() {
        // is the set empty?
        return root == null;
    }

    public int size() {
        // number of points in the set
        return count;
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new NullPointerException();
        if (root == null) {
            root = new Node(p, null);
        }
        else {
            Node father = null;
            Node next = root;
            boolean left = true;    // left child or right child
            while (next != null) {
                if (next.getCompare() && p.x() < next.getPoint().x()) {
                    // go to left side
                    father = next;
                    next = next.getLeftChild();
                    left = true;
                }
                else if (next.getCompare() && p.x() >= next.getPoint().x()) {
                    if (p.y() == next.getPoint().y() && p.x() == next.getPoint().x())    // Check if father equals to p
                        return;
                    // go to right side
                    father = next;
                    next = next.getRightChild();
                    left = false;
                }
                else if (!next.getCompare() && p.y() < next.getPoint().y()) {
                    // go to lower side
                    father = next;
                    next = next.getLeftChild();
                    left = true;
                }
                else {
                    if (p.x() == next.getPoint().x() && p.y() == next.getPoint().y())    // Check if father equals to p
                        return;
                    // go to upper side
                    father = next;
                    next = next.getRightChild();
                    left = false;
                }
            }

            // Put Node to the right position
            if (left)
                father.setLeftChild(new Node(p, father));
            else
                father.setRightChild(new Node(p, father));
        }
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        if (p == null)
            throw new NullPointerException();
        if (root == null) {
            return false;
        }
        else {
            Node father = null;
            Node next = root;
            while (next != null) {
                if (next.getCompare() && p.x() < next.getPoint().x()) {
                    // go to left side
                    father = next;
                    next = next.getLeftChild();
                }
                else if (next.getCompare() && p.x() >= next.getPoint().x()) {
                    if (p.y() == next.getPoint().y() && p.x() == next.getPoint().x())    // Check if father equals to p
                        return true;
                    // go to right side
                    father = next;
                    next = next.getRightChild();
                }
                else if (!next.getCompare() && p.y() < next.getPoint().y()) {
                    // go to lower side
                    father = next;
                    next = next.getLeftChild();
                }
                else {
                    if (p.y() == next.getPoint().y() && p.x() == next.getPoint().x())    // Check if father equals to p
                        return true;
                    // go to upper side
                    father = next;
                    next = next.getRightChild();
                }
            }

            // Check if father equals to p
            return (father.getPoint().compareTo(p) == 0);
        }
    }

    public void draw() {
        // draw all points to standard draw
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        // Recursive
        SET<Point2D> store = new SET<>();
        putPointIn(root, rect, store);

        return store;
    }

    private void putPointIn(Node n, RectHV rect, SET<Point2D> store) {
        if (n == null)
            return;
        if (!n.getRect().intersects(rect))
            return;

        if (rect.contains(n.getPoint()))
            store.add(n.getPoint());
        putPointIn(n.getLeftChild(), rect, store);
        putPointIn(n.getRightChild(), rect, store);
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new NullPointerException();
        if (root == null)
            return null;

        return nearst(root, p, new Point2D(100, 100));
    }

    private Point2D nearst(Node n, Point2D p, Point2D nearstP) {
        double nGetPDisToP = n.getPoint().distanceSquaredTo(p);
        if (n.getLeftChild() == null && n.getRightChild() == null) {
            double distance = p.distanceSquaredTo(nearstP);
            if (nGetPDisToP < distance) {
                // distance = n.getPoint().distanceSquaredTo(p);
                nearstP = n.getPoint();
            }
            return nearstP;
        }

        // Use Rectangle's distance to choose which side to go first;
        // Left first or Right first
        boolean leftFirst = true;
        double nLeftRectDist = 0.0, nRightRectDist = 0.0;
        
        if (n.getLeftChild() == null)
            leftFirst = false;
        else if (n.getRightChild() == null)
            ;
        else if ((nLeftRectDist = n.getLeftChild().getRect().distanceSquaredTo(p)) > (nRightRectDist = n.getRightChild().getRect().distanceSquaredTo(p)))
            leftFirst = false;
        else
            ;
        // Use Node n to set new distance and set new nearstP
        double distance = p.distanceSquaredTo(nearstP);
        if (nGetPDisToP < distance) {
            distance = nGetPDisToP;
            nearstP = n.getPoint();
        }

        if (leftFirst) {
            if (nLeftRectDist <= distance) {
                nearstP = nearst(n.getLeftChild(), p, nearstP);
                double tempDistance = nearstP.distanceSquaredTo(p);
                if (distance > tempDistance)    // check if we can got a shortter distance.
                    distance = tempDistance;

                // search right subtree
                if (n.getRightChild() != null && nRightRectDist <= distance) {
                    nearstP = nearst(n.getRightChild(), p, nearstP);
                    tempDistance = nearstP.distanceSquaredTo(p);
                    if (distance > tempDistance)
                        distance = tempDistance;
                }
            }
            // search right subtree
            if (n.getRightChild() != null && nRightRectDist <= distance) {
                nearstP = nearst(n.getRightChild(), p, nearstP);
                double tempDistance = nearstP.distanceSquaredTo(p);
                if (distance > tempDistance)
                    distance = tempDistance;
                // search left subtree
                if (n.getLeftChild() != null && nLeftRectDist <= distance) {
                    nearstP = nearst(n.getLeftChild(), p, nearstP);
                }
            }
        }
        else {    // right is closer and rightChild != null
            // search right subtree
            if (nRightRectDist <= distance) {
                nearstP = nearst(n.getRightChild(), p, nearstP);
                double tempDistance = nearstP.distanceSquaredTo(p);
                if (distance > tempDistance)
                    distance = tempDistance;
                // search left subtree
                if (n.getLeftChild() != null && nLeftRectDist <= distance) {
                    nearstP = nearst(n.getLeftChild(), p, nearstP);
                    tempDistance = nearstP.distanceSquaredTo(p);
                    if (distance > tempDistance)
                        distance = tempDistance;
                }
            }
            if (n.getLeftChild() != null && nLeftRectDist <= distance) {
                nearstP = nearst(n.getLeftChild(), p, nearstP);
                double tempDistance = nearstP.distanceSquaredTo(p);
                if (distance > tempDistance)
                    distance = tempDistance;

                // search right subtree
                if (n.getRightChild() != null && nRightRectDist <= distance) {
                    nearstP = nearst(n.getRightChild(), p, nearstP);
                }
            }
        }

        return nearstP;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}
