package de.GUI;

import de.Utility.Encoder;
import de.Utility.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

    private static final int defaultWidth = 640;
    private static final int defaultHeight = 450;

    OutputPreview preview = new OutputPreview();
    private JButton encodeButton;
    private JButton decodeButton;
    private JTextArea textArea;

    public MainWindow() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (Exception e) { }
        addComponents();
        applyDefaultSettings();
    }

    public void applyDefaultSettings() {
        this.setTitle("Byte Image Encoder");
        this.setSize(defaultWidth,defaultHeight);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void addComponents() {
        preview.setLocation(350,30);
        this.add(preview);

        encodeButton = new JButton("Encode");
        encodeButton.setSize(100,30);
        encodeButton.setLocation(360,300);
        encodeButton.addActionListener(this);
        this.add(encodeButton);

        decodeButton = new JButton("Decode");
        decodeButton.setSize(100,30);
        decodeButton.setLocation(480,300);
        decodeButton.addActionListener(this);
        this.add(decodeButton);

        textArea = new JTextArea("");
        textArea.setLineWrap(true);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setSize(300,350);
        scroll.setLocation(20,20);
        this.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encodeButton)
            preview.updateImage(Encoder.encode(textArea.getText()));
        else if (e.getSource() == decodeButton)
            Util.log("Decoded: " + Encoder.decode(preview.getImage()));
    }
}
