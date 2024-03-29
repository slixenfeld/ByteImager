package de.Windows;

import static de.Utility.Util.log;

import javax.swing.JFrame;
import javax.swing.UIManager;

public abstract class Window extends JFrame implements UIDefault {

	private static final long serialVersionUID = 1L;

	public Window() {
		try {
			UIManager.setLookAndFeel(Windows);
		} catch (Exception e) {
			log("Look And Feel Not Supported.");
		}
	}
}
