package de.GUI;

import de.Utility.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OutputPreview extends JPanel {

    private BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);

    public OutputPreview() {
        applyDefaultSettings();
    }

    public void applyDefaultSettings() {
        this.setSize(image.getWidth(),image.getHeight());
    }

    public void updateImage(BufferedImage image) {
        setImage(image);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
