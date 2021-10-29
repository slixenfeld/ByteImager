package de.Utility;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Util {

	public static int imageSize = 0xFF;

	public static void log(String line) {
		Date time = Calendar.getInstance().getTime();
		System.out.println(time + " " + line);
	}
	
	public static void addButton(Container window, String text, int x, int y, int w, int h, ActionListener act) {
		JButton button = new JButton(text);
		button.setBounds(x, y, w, h);
		button.addActionListener(act);
		window.add(button);
	}
	
	public static void addLabel(JFrame window, String text, int x, int y, int w, int h) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, w, h);
		window.add(label);
	}
	
	public static JRadioButton newRadioButton(JFrame window, String text, boolean selected, int x, int y, int w, int h, ActionListener act) {
		JRadioButton radio = new JRadioButton(text);
		radio.setBounds(x, y, w, h);
		radio.setSelected(selected);
		radio.addActionListener(act);
		return(radio);
	}
}
