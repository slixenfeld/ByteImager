package de.Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileManager {

    final static JFileChooser fc = new JFileChooser();

    public static BufferedImage loadImage(JFrame parent) {

        int returnVal = fc.showOpenDialog(parent);

        BufferedImage img = null;

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
}
