package de.Windows.panes;

import static de.Utility.Util.addButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import de.Utility.encoding.Encoder;
import de.Windows.MainWindow;
import de.Windows.PreviewWindow;
import de.Windows.UIDefault;

public class TextEncodingPane extends EncodingPane implements UIDefault {

	private static final long serialVersionUID = 1L;

	public TextEncodingPane() {
		applyDefaultSettings();
		addComponents();
	}

	@Override
	public void applyDefaultSettings() {
		this.setLayout(null);
	}

	@Override
	public void addComponents() {

		addButton(this, "Encode", 200, 130, 100, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {encode();}
		});
		
		addButton(this, "Decode", 200, 325, 100, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {decode();}
		});
		
		inputText = new JTextArea("");
		inputText.setLineWrap(true);
		JScrollPane scroll_inputText = new JScrollPane(inputText);
		scroll_inputText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_inputText.setSize(300, 120);
		scroll_inputText.setLocation(5, 5);
		this.add(scroll_inputText);
		
		outputText = new JTextArea("");
		outputText.setLineWrap(true);
		JScrollPane scroll_outputText = new JScrollPane(outputText);
		scroll_outputText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_outputText.setSize(300, 150);
		scroll_outputText.setLocation(5, 170);
		this.add(scroll_outputText);
	}

	@Override
	void encode() {
		Encoder.byteArraySize = inputText.getText().length();
		PreviewWindow.preview.updateImage(MainWindow.encoder.encode(inputText.getText().getBytes()));
	}

	@Override
	void decode() {
		outputText.setText(MainWindow.encoder.decodeToText(PreviewWindow.preview.getImage()));
	}
}
