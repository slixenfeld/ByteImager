package de.Windows.panes;

import static de.Utility.Util.addButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import de.Utility.FileManager;
import de.Windows.MainWindow;
import de.Windows.PreviewWindow;
import de.Windows.UIDefault;

public class FileEncodingPane extends EncodingPane implements UIDefault {

	private static final long serialVersionUID = 1L;

	private FileEncodingPane thisPane = this;
	
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

		addButton(this, "Encode", 200, 130, 100, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {encode();}
		});
		
		addButton(this, "Decode", 200, 325, 100, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {decode();}
		});
		
		
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
		PreviewWindow.preview.updateImage(
				MainWindow.encoder.encode(FileManager.loadFileIntoArray(FileManager.chooseFile(thisPane))));
	}

	@Override
	void decode() {
		outputText.setText(MainWindow.encoder.decodeToText(PreviewWindow.preview.getImage()));
		MainWindow.encoder.decodeToFile(thisPane, PreviewWindow.preview.getImage());
	}
	
}
