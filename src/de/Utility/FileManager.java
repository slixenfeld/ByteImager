package de.Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import de.Utility.encoding.Encoder;
import de.Windows.panes.FileEncodingPane;

public class FileManager {

	final static JFileChooser fc = new JFileChooser();

	public static File saveFile(FileEncodingPane fileEncodingPane) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save File");
		File fileToSave = null;

		int userSelection = fileChooser.showSaveDialog(fileEncodingPane);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			Util.log("Save File: " + fileToSave.getAbsolutePath());
		}
		return fileToSave;
	}

	public static File chooseFile(FileEncodingPane fileEncodingPane) {
		File returnFile = null;
		int returnVal = fc.showOpenDialog(fileEncodingPane);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			returnFile = fc.getSelectedFile();
		} else {
			Util.log("Open cancelled by user.");
		}
		return returnFile;
	}

	public static BufferedImage loadImage(JFrame parent) {

		BufferedImage img = null;

		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			Util.log("Opening: " + file.getName() + ".");
			try {
				img = ImageIO.read(new File(file.getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Util.log("Open cancelled by user.");
		}
		return img;
	}

	public static void saveImage(BufferedImage image, JFrame parent) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(parent);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());

			try {
				ImageIO.write(image, "PNG", fileToSave);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static byte[] loadFileIntoArray(File file) {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		Encoder.byteArraySize = bFile.length;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;
	}
}
