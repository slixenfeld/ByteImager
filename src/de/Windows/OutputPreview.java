package de.Windows;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.Utility.Util;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class OutputPreview extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image = new BufferedImage(Util.imageSize, Util.imageSize, BufferedImage.TYPE_INT_RGB);

	public OutputPreview() {
		applyDefaultSettings();
	}

	public void applyDefaultSettings() {
		this.setSize(image.getWidth(), image.getHeight());
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
}
