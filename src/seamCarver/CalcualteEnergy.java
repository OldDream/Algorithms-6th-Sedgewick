package seamCarver;

import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class CalcualteEnergy {
    protected static double getEnergy(int x, int y, Picture pic) {
        double energyX = 0, energyY = 0;
        if (x > 0 && x + 1 < pic.width()) {
            Color left = pic.get(x - 1, y);
            Color right = pic.get(x + 1, y);
            energyX = Math.pow(2, left.getRed() - right.getRed())
                    + Math.pow(2, left.getGreen() - right.getGreen())
                    + Math.pow(2, left.getBlue() - right.getBlue());
        } else if (x - 1 == 0 && x + 1 < pic.width()) {
            // left edge
            energyX = -1;
        } else if (x > 0 && x + 1 == pic.width()) {
            // right edge
            energyX = -1;
        } else {
            // width == 1
            energyX = -1;
        }

        if (y > 0 && y + 1 < pic.height()) {
            Color up = pic.get(x, y - 1);
            Color down = pic.get(x, y + 1);
            energyY = Math.pow(2, up.getRed() - down.getRed())
                    + Math.pow(2, up.getGreen() - down.getGreen())
                    + Math.pow(2, up.getBlue() - down.getBlue());
        } else if (y - 1 == 0 && y + 1 < pic.width()) {
            // top edge
            energyY = -1;
        } else if (x > 0 && x + 1 == pic.width()) {
            // bottom edge
            energyY = -1;
        } else {
            // height == 1;
            energyY = -1;
        }
        
        // on the border
        if (energyX < 0 || energyY < 0) {
            return 1000;
        }
        
        return Math.sqrt(energyX + energyY);
    }
}
