package de.GUI.panes;

import de.GUI.OutputPreview;
import de.GUI.UIDefault;
import de.Utility.Encoder;
import de.Utility.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEncodingPane extends JPanel implements UIDefault, ActionListener {

    JButton encodeButton;
    JButton decodeButton;
    JTextArea inputText;
    JTextArea outputText;

    OutputPreview preview;

    public TextEncodingPane(OutputPreview preview) {
        this.preview = preview;
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
        scroll_inputText.setSize(300,120);
        scroll_inputText.setLocation(5,5);
        this.add(scroll_inputText);

        encodeButton = new JButton("Encode");
        encodeButton.setSize(100,30);
        encodeButton.setLocation(200,130);
        encodeButton.addActionListener(this);
        this.add(encodeButton);

        outputText = new JTextArea("");
        outputText.setLineWrap(true);
        JScrollPane scroll_outputText = new JScrollPane(outputText);
        scroll_outputText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_outputText.setSize(300,150);
        scroll_outputText.setLocation(5,170);
        this.add(scroll_outputText);

        decodeButton = new JButton("Decode");
        decodeButton.setSize(100,30);
        decodeButton.setLocation(200,325);
        decodeButton.addActionListener(this);
        this.add(decodeButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encodeButton) {
            preview.updateImage(Encoder.encode(inputText.getText()));
        }
        else if (e.getSource() == decodeButton)
            outputText.setText(Encoder.decode(preview.getImage()));
    }
}
