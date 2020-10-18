package de.Utility.encoding;

import de.Utility.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Encoder {

    public Point grid;

    public Encoder() { }

    public void getNextCoordinates() {
        grid.x = ((grid.x+1) >= Util.imageSize) ? 0 : (grid.x+1);
        grid.y = (grid.x == 0) ? (grid.y+1) : grid.y;
    }

    public abstract BufferedImage encode(String text);

    public abstract String decode(BufferedImage image);
}
