package de.Utility.encoding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.Utility.FileManager;
import de.Utility.Util;
import de.Windows.MainWindow;
import de.Windows.panes.FileEncodingPane;

public class RGBEncoder extends Encoder {
	public static int add = 0;

	public RGBEncoder() {
	}

	@Override
	public BufferedImage encode(byte[] bytes) {

		int temp_highest_red = 0;
		
		determineImageSize(bytes.length);

		BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();

		int charPos = -1;
		grid = new Point(0, 0);

		while (charPos < bytes.length) {

			charPos++;
			int red = (charPos >= bytes.length) ? 0 : bytes[charPos];
			if (red < 0) red = 127 + (-red);

			charPos++;
			int green = (charPos >= bytes.length) ? 0 : bytes[charPos];
			if (green < 0) green = 127 + (-green);

			charPos++;
			int blue = (charPos >= bytes.length) ? 0 : bytes[charPos];
			if (blue < 0) blue = 127 + (-blue);

			try {
				Color pixelColor = new Color(red, green, blue);
				g.setColor(pixelColor);
				if (red > temp_highest_red) {
					temp_highest_red = red;
				}
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
				if (red > 127) red = -(red-127);
				if (counter < Encoder.byteArraySize)
					decodedBytes[counter] = (byte) (red);

				counter++;
				if (green > 127) green = -(green-127);
				if (counter < Encoder.byteArraySize)
					decodedBytes[counter] = (byte) (green);

				counter++;
				if (blue > 127) blue = -(blue-127);
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

	@Override
	public void determineImageSize(int n) {
		for (int i = 5; i < 15; i++) {

			int newSize = (int) Math.pow(2, i);
			int allPixels = newSize * newSize;

			if ((n / 3) <= allPixels) {
				Util.imageSize = newSize;
				MainWindow.previewWindow.setSize(100 + newSize, 100 + newSize);
				break;
			}
		}
	}
}