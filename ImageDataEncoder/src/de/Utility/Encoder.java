package de.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Encoder {

    public static int add = 0;

    public static BufferedImage encode(String text) {
        text = " " + text;
        BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();

        int currentChar = 0;
        int gridX = 0;
        int gridY = 0;

        while (currentChar < text.length()) {

            currentChar++;
            int red = (currentChar >= text.length()) ? 0 : (int) text.charAt(currentChar);
            currentChar++;
            int green = (currentChar >= text.length()) ? 0 : (int) text.charAt(currentChar);
            currentChar++;
            int blue = (currentChar >= text.length()) ? 0 : (int) text.charAt(currentChar);

            red = ((red + add) > 0xFF) ? ((red+add) - 0xFF) : (red + add);
            green = ((green + add) > 0xFF) ? ((green+add) - 0xFF) : (green + add);
            blue = ((blue + add) > 0xFF) ? ((blue+add) - 0xFF) : (blue + add);

            try {
                Color pixelColor = new Color(red, green, blue);
                g.setColor(pixelColor);
            } catch (Exception e) {
                Util.log("pixel was outside of color range: ("+(blue+add)+","+(green+add)+","+(red+add)+")");
            }

            gridX++;
            if(gridX > Util.imageSize) {
                gridX = 0;
                gridY++;
            }
            g.fillRect(gridX, gridY, 1, 1);
        }
        return image;
    }

    public static String decode(BufferedImage image) {
        StringBuilder decodedText = new StringBuilder();
        for(int i = 0; i < image.getHeight(); i++)
            for(int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                red = ((red - add) <= 0) ? red : (red-add);
                green = ((green - add) <= 0) ? green : (green-add);
                blue = ((blue - add) <= 0) ? blue : (blue-add);

                decodedText.append((char) (red)).append((char) (green)).append((char) (blue));
            }
        return decodedText.toString();
    }
}
