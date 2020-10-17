package de.Utility.encoding;

import java.awt.image.BufferedImage;

public abstract class Encoder {

    public Encoder() { }

    public abstract BufferedImage encode(String text);

    public abstract String decode(BufferedImage image);
}
