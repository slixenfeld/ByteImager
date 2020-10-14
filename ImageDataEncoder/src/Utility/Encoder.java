package Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;

public class Encoder {

    public static int add = 0;

    public static BufferedImage encode(String text) {
        text = " " + text;
        BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();

        int index = 0;
        int x = 0;
        int y = 0;

        while (index < text.length()) {

            index++;
            int red = (index >= text.length()) ? 0 : (int) text.charAt(index);
            index++;
            int green = (index >= text.length()) ? 0 : (int) text.charAt(index);
            index++;
            int blue = (index >= text.length()) ? 0 : (int) text.charAt(index);

            red = ((red + add) > 0xFF) ? ((red+add) - 0xFF) : (red + add);
            green = ((green + add) > 0xFF) ? ((green+add) - 0xFF) : (green + add);
            blue = ((blue + add) > 0xFF) ? ((blue+add) - 0xFF) : (blue + add);

            try {
                Color pixelColor = new Color(red, green, blue);
                g.setColor(pixelColor);
            } catch (Exception e) {
                Util.log("pixel was outside of color range: ("+(blue+add)+","+(green+add)+","+(red+add)+")");
            }

            x++;
            if(x > Util.imageSize) {
                x = 1;
                y++;
            }
            g.fillRect(x, y, 1, 1);
        }
        return image;
    }

    public static String decode(BufferedImage image) {
        String ret = "";
        int index = 0;
        for(int i = 0; i < image.getHeight(); i++)
            for(int j = 0; j < image.getWidth(); j++) {

                int pixel = image.getRGB(j, i);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                red = ((red - add) <= 0) ? red : (red-add);
                green = ((green - add) <= 0) ? green : (green-add);
                blue = ((blue - add) <= 0) ? blue : (blue-add);

                ret +=  ((char) (red)) + "" +((char) (green)) + "" +((char) (blue)) +"";
            }

        Util.log("Decoded: " + ret);
        return ret;
    }
}
