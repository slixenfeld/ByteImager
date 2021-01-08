package de.Utility.encoding;

import de.Utility.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RGBEncoder extends Encoder {
    public static int add = 0;

    public RGBEncoder() { }

    @Override
    public BufferedImage encode(String text) {
        text = " " + text;
        BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();

        int charPos = 0;
        grid = new Point(0, 0);

        while (charPos < text.length()) {

            charPos++;
            int red = (charPos >= text.length()) ? 0 : (int) text.charAt(charPos);

            charPos++;
            int green = (charPos >= text.length()) ? 0 : (int) text.charAt(charPos);

            charPos++;
            int blue = (charPos >= text.length()) ? 0 : (int) text.charAt(charPos);

            try {
                Color pixelColor = new Color(red, green, blue);
                g.setColor(pixelColor);
            } catch (Exception e) {
                Util.log("pixel was outside of color range: ("+(blue+add)+","+(green+add)+","+(red+add)+")");
            }

            getNextCoordinate();

            g.fillRect(grid.x, grid.y, 1, 1);
        }
        return image;
    }

    @Override
    public String decode(BufferedImage image) {
        StringBuilder decodedText = new StringBuilder();

        for(int i = 0; i < image.getHeight(); i++)
            for(int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                decodedText.append((char) (red)).append((char) (green)).append((char) (blue));
            }
        return decodedText.toString();
    }
}