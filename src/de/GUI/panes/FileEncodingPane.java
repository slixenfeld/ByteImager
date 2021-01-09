package de.GUI.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import de.GUI.MainWindow;
import de.GUI.PreviewWindow;
import de.GUI.UIDefault;
import de.Utility.FileManager;
import de.Utility.Util;

public class FileEncodingPane extends EncodingPane implements UIDefault, ActionListener {

	private static final long serialVersionUID = 1L;
	JButton encodeButton;
	JButton decodeButton;

	JTextArea inputText;
	JTextArea outputText;

	public FileEncodingPane() {
		applyDefaultSettings();
		addComponents();
	}

	@Override
	public void applyDefaultSettings() {
		this.setLayout(null);
	}

	@Override
	public void addComponents() {

		inputText = new JTextArea("");
		inputText.setLineWrap(true);
		JScrollPane scroll_inputText = new JScrollPane(inputText);
		scroll_inputText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_inputText.setSize(300, 120);
		scroll_inputText.setLocation(5, 5);
		// this.add(scroll_inputText);

		encodeButton = new JButton("Encode");
		encodeButton.setSize(100, 30);
		encodeButton.setLocation(200, 130);
		encodeButton.addActionListener(this);
		this.add(encodeButton);

		outputText = new JTextArea("");
		outputText.setLineWrap(true);
		JScrollPane scroll_outputText = new JScrollPane(outputText);
		scroll_outputText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_outputText.setSize(300, 150);
		scroll_outputText.setLocation(5, 170);
		this.add(scroll_outputText);

		decodeButton = new JButton("Decode");
		decodeButton.setSize(100, 30);
		decodeButton.setLocation(200, 325);
		decodeButton.addActionListener(this);
		this.add(decodeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == encodeButton)
				PreviewWindow.preview.updateImage(
						MainWindow.encoder.encode(FileManager.loadFileIntoArray(FileManager.chooseFile(this))));
			else if (e.getSource() == decodeButton) {
				outputText.setText(MainWindow.encoder.decodeToText(PreviewWindow.preview.getImage()));
				MainWindow.encoder.decodeToFile(this, PreviewWindow.preview.getImage());
			}
		} catch (Exception ex) {
			Util.log("IO Error: " + ex);
		}
	}
}
