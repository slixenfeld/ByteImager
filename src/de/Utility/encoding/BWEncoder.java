package de.Utility.encoding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.GUI.MainWindow;
import de.GUI.panes.FileEncodingPane;
import de.Utility.FileManager;
import de.Utility.Util;

public class BWEncoder extends Encoder {

	public BWEncoder() {
	}

	@Override
	public BufferedImage encode(String text) {
		text = " " + text;

		determineImageSize(text.length());

		BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.createGraphics();

		int charPos = 0;
		grid = new Point(0, 0);

		while (charPos < text.length()) {
			paintCharToGrid(text.charAt(charPos), g, charPos);
			charPos++;
		}
		placeEndpoint(g);

		return image;
	}

	@Override
	public BufferedImage encode(int[] bytes) {

		determineImageSize(bytes.length);

		BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.createGraphics();

		int charPos = 0;
		grid = new Point(0, 0);

		for (int i = 0; i < bytes.length; i++) {
			paintByteToGrid((char) bytes[i], g, charPos);
		}

		placeEndpoint(g);

		return image;
	}

	private void paintCharToGrid(char character, Graphics g, int charPos) {
		int n = character;
		for (int i = 7; i >= 0; i--) {
			if (((n >> i) & 1) == 0)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			g.fillRect(grid.x, grid.y, 1, 1);

			getNextCoordinate();
		}
	}

	private void paintByteToGrid(char value, Graphics g, int charPos) {
		int n = value;
		for (int i = 7; i >= 0; i--) {
			if (((n >> i) & 1) == 0)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			g.fillRect(grid.x, grid.y, 1, 1);

			getNextCoordinate();
		}
		charPos++;
	}

	private void placeEndpoint(Graphics g) {
		g.setColor(new Color(1, 1, 1));

		g.fillRect(grid.x, grid.y, 1, 1);
	}

	@Override
	public String decodeToText(BufferedImage image) {
		StringBuilder decodedText = new StringBuilder();

		int bitCounter = 0;
		int[] bits = new int[8];
		grid = new Point(0, 0);

		while (true) {
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

			if (red == 13 && blue == 13 && green == 13 || grid.x > Util.imageSize - 1 || grid.y > Util.imageSize - 1) {
				return decodedText.toString();
			}
		}
	}

	private char assembleChar(int[] bits) {

		int n = 0;
		for (int i = 0; i < 7; i++)
			n += Math.pow(bits[i] * 2, 7 - i);
		n += bits[7];
		return (char) n;
	}

	@Override
	public File decodeToFile(FileEncodingPane pane, BufferedImage image) {

		byte[] decodedBytes = new byte[Encoder.byteArraySize];
		int counter = -1;
		int bitCounter = 0;
		int[] bits = new int[8];
		grid = new Point(0, 0);

		while (true) {

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
				counter++;
				decodedBytes[counter] = (byte) assembleChar(bits);
				bitCounter = 0;
				bits = new int[8];
			}

			getNextCoordinate();

			if (red == 13 && blue == 13 && green == 13 || grid.x > Util.imageSize - 1 || grid.y > Util.imageSize - 1)
				break;
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

			if ((n * 8) <= allPixels) {
				Util.imageSize = newSize;
				MainWindow.previewWindow.setSize(100 + newSize, 100 + newSize);
				break;
			}
		}
	}
}