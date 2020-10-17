package de.Utility.encoding;

import de.Utility.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BWEncoder extends Encoder {

    public BWEncoder() {}

    @Override
    public BufferedImage encode(String text) {
        text = " " + text;
        BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();

        int currentChar = 1;
        int gridX = 0;
        int gridY = 0;

        while (currentChar < text.length()) {

            int n = text.charAt(currentChar);

            for(int i = 7; i >= 0; i--) {

                if (((n >> i) & 1) == 0) {
                    g.setColor(Color.WHITE);
                }else {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(gridX, gridY, 1, 1);
                gridX++;
                if (gridX >= Util.imageSize) { gridX = 0; gridY++; }
            }
            currentChar++;
        }
        g.setColor(new Color(255,0,0));
        g.fillRect(gridX, gridY, 1, 1);
            return image;
    }

    @Override
    public String decode(BufferedImage image) {
        StringBuilder decodedText = new StringBuilder();

        int count = 0;
        int[] bits = new int[8];

        int gridX = 0;
        int gridY = 0;

        while(true) {

            int pixel = image.getRGB(gridX, gridY);
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;

            if ((red + green + blue) > 200)
                bits[count] = 0;
            else
                bits[count] = 1;

            count++;

            if (count == 8) {
                int n = 0;

                n += Math.pow(bits[0]*2,7);
                n += Math.pow(bits[1]*2,6);
                n += Math.pow(bits[2]*2,5);
                n += Math.pow(bits[3]*2,4);
                n += Math.pow(bits[4]*2,3);
                n += Math.pow(bits[5]*2,2);
                n += Math.pow(bits[6]*2,1);
                n += bits[7];

                count = 0;
                bits = new int[8];
                decodedText.append((char) n);
            }
            gridX++;
            if (gridX >= Util.imageSize) { gridX = 0; gridY++; }
            if(red > 250 && blue == 0 && green == 0)
            {
                return decodedText.toString();
            }
        }
    }
}
