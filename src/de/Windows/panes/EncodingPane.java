package de.Windows.panes;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class EncodingPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JTextArea inputText;
	JTextArea outputText;
	
	public EncodingPane() { }
	
	abstract void encode();
	
	abstract void decode();

}
