package de.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.sun.glass.ui.Window;

import de.Utility.Util;

public class PreviewWindow extends JFrame implements UIDefault, ActionListener {

	private static final long serialVersionUID = 1L;
	public static OutputPreview preview = new OutputPreview();

	public PreviewWindow() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			Util.log("Windows Classic Look And Feel Not Supported.");
		}
		addComponents();
		applyDefaultSettings();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void applyDefaultSettings() {
		this.setTitle("Preview");
		this.setType(Type.UTILITY);
		this.setSize(Util.imageSize, Util.imageSize);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);
	}

	@Override
	public void addComponents() {
		preview.setLocation(0, 0);
		this.add(preview);
	}

}
