package exercise1_1;
import edu.princeton.cs.algs4.*;

public class Exercise1_1_31 {
    public static void drawRandConn(int N, double p)
    {
        StdDraw.setCanvasSize(640, 640);
        StdDraw.setScale(-1.2, 1.2);    //设定坐标系范围
        StdDraw.setPenRadius(.015);
        
        StdDraw.circle(0, 0, 1);
        double[][] d = new double[N][2];
        for (int i = 0; i < N; i++)
        {
            d[i][0] = Math.cos(2 * Math.PI * i / N);    //X
            d[i][1] = Math.sin(2 * Math.PI * i / N);    //Y
            StdDraw.point(d[i][0], d[i][1]); //默认圆的半径为1
        }
        
        StdDraw.setPenRadius();
        
        //把所有线的组成都遍历一遍。
        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                if (StdRandom.bernoulli(p))    //用于处理概率
                    StdDraw.line(d[i][0], d[i][1], d[j][0], d[j][1]);
    }
    
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        p = Math.max(0, Math.min(1, p));
        
        drawRandConn(N, p);
    }
}
