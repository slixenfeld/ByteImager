package de.Utility.encoding;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import de.Utility.Util;
import de.Windows.panes.FileEncodingPane;

public abstract class Encoder {

	public static int byteArraySize = 0;

	public Point grid;

	public Encoder() {
	}

	public void getNextCoordinate() {
		grid.x = ((grid.x + 1) >= Util.imageSize) ? 0 : (grid.x + 1);
		grid.y = (grid.x == 0) ? (grid.y + 1) : grid.y;
	}

	public abstract void determineImageSize(int n);

	public abstract BufferedImage encode(byte[] bytes);

	public abstract String decodeToText(BufferedImage image);

	public abstract File decodeToFile(FileEncodingPane pane, BufferedImage image);
}
