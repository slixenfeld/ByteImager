package de.Utility.encoding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.GUI.panes.FileEncodingPane;
import de.Utility.FileManager;
import de.Utility.Util;

public class RGBEncoder extends Encoder {
	public static int add = 0;

	public RGBEncoder() {
	}

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
				Util.log("pixel was outside of color range: (" + (blue + add) + "," + (green + add) + "," + (red + add)
						+ ")");
			}

			g.fillRect(grid.x, grid.y, 1, 1);
			getNextCoordinate();
		}
		return image;
	}

	@Override
	public BufferedImage encode(int[] bytes) {
		BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();

		int charPos = -1;
		grid = new Point(0, 0);

		while (charPos < bytes.length) {

			charPos++;
			int red = (charPos >= bytes.length) ? 0 : bytes[charPos];

			charPos++;
			int green = (charPos >= bytes.length) ? 0 : bytes[charPos];

			charPos++;
			int blue = (charPos >= bytes.length) ? 0 : bytes[charPos];

			try {
				Color pixelColor = new Color(red, green, blue);
				g.setColor(pixelColor);
			} catch (Exception e) {
				Util.log("pixel was outside of color range: (" + (blue + add) + "," + (green + add) + "," + (red + add)
						+ ")");
			}

			g.fillRect(grid.x, grid.y, 1, 1);
			getNextCoordinate();
		}

		return image;
	}

	@Override
	public String decodeToText(BufferedImage image) {
		StringBuilder decodedText = new StringBuilder();

		for (int i = 0; i < image.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++) {
				int pixel = image.getRGB(j, i);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				decodedText.append((char) (red)).append((char) (green)).append((char) (blue));
			}
		return decodedText.toString();
	}

	@Override
	public File decodeToFile(FileEncodingPane pane, BufferedImage image) {

		byte[] decodedBytes = new byte[Encoder.byteArraySize];
		int counter = -1;

		for (int i = 0; i < image.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++) {

				int pixel = image.getRGB(j, i);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				counter++;
				if (counter < Encoder.byteArraySize)
					decodedBytes[counter] = (byte) (red);

				counter++;
				if (counter < Encoder.byteArraySize)
					decodedBytes[counter] = (byte) (green);

				counter++;
				if (counter < Encoder.byteArraySize)
					decodedBytes[counter] = (byte) (blue);

			}

		File outputFile = FileManager.saveFile(pane);
		try (FileOutputStream stream = new FileOutputStream(outputFile)) {
			stream.write(decodedBytes);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return outputFile;
	}

}