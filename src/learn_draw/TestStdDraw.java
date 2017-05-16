package learn_draw;

import edu.princeton.cs.algs4.StdDraw;

public class TestStdDraw {
    static int size = 20;
    static int painter = 16;
    static double max = 30;

    public static void main(String[] args) {
        int canvasWidth = painter * size;
        int canvasHeight = painter * (1 + (int)max);
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.filledRectangle(0.5, 0.5, 0.25, 0.25);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
        
    }

}
