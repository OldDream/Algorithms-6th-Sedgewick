package seamCarver;

import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class CalcualteEnergy {
    public static double getEnergy(int x, int y, Picture pic, int width, int height) {
        double energyX = 0, energyY = 0;
        if (x > 0 && x + 1 < width) {
            Color left = pic.get(x - 1, y);
            Color right = pic.get(x + 1, y);
            energyX = Math.pow(left.getRed() - right.getRed(), 2)
                    + Math.pow(left.getGreen() - right.getGreen(), 2)
                    + Math.pow(left.getBlue() - right.getBlue(), 2);
        } else {
            // width == 1, left edge, right edge
            return 1000;
        }

        if (y > 0 && y + 1 < height) {
            Color up = pic.get(x, y - 1);
            Color down = pic.get(x, y + 1);
            energyY = Math.pow(up.getRed() - down.getRed(), 2)
                    + Math.pow(up.getGreen() - down.getGreen(), 2)
                    + Math.pow(up.getBlue() - down.getBlue(), 2);
        }  else {
            // height == 1, top edge, bottom edge
            return 1000;
        }
        
        return Math.sqrt(energyX + energyY);
    }
}
