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

        int charPos = 1;
        grid = new Point(0,0);

        paintCharToGrid(text, g, charPos);
        
        g.setColor(new Color(255,0,0));
        g.fillRect(grid.x, grid.y, 1, 1);
        return image;
    }

	private void paintCharToGrid(String text, Graphics g, int charPos) {
		while (charPos < text.length()) {

            int n = text.charAt(charPos);
            for(int i = 7; i >= 0; i--) {
                if (((n >> i) & 1) == 0)
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.BLACK);
                g.fillRect(grid.x, grid.y, 1, 1);

                getNextCoordinate();
            }
            charPos++;
        }
	}

    @Override
    public String decode(BufferedImage image) {
        StringBuilder decodedText = new StringBuilder();

        int bitCounter = 0;
        int[] bits = new int[8];
        grid = new Point(0,0);

        while(true) {
            int pixel = image.getRGB(grid.x, grid.y);
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;

            if ((red + green + blue) > 200)
                bits[bitCounter] = 0;
            else
                bits[bitCounter] = 1;

            bitCounter++;
            if (bitCounter == 8) {
                decodedText.append(assembleChar(bits));
                bitCounter = 0;
                bits = new int[8];
            }
            getNextCoordinate();

            if(red > 250 && blue == 0 && green == 0)
                return decodedText.toString();
        }
    }

    private char assembleChar(int[] bits) {
        int n = 0;
        for(int i = 0; i < 7; i++)
            n += Math.pow(bits[i]*2,7-i);
        n += bits[7];
        return (char) n;
    }
}