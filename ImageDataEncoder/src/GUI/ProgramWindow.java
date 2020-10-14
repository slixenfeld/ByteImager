package GUI;

import Utility.Encoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ProgramWindow extends JFrame implements ActionListener {

    private static final int defaultWidth = 640;
    private static final int defaultHeight = 450;

    OutputPreview preview = new OutputPreview();
    private JButton encodeButton;
    private JButton decodeButton;
    private JTextArea textArea;

    public ProgramWindow() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        }
        catch (Exception e) { }
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

        JScrollPane sp = new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setSize(300,350);
        sp.setLocation(20,20);
        this.add(sp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encodeButton)
            preview.updateImage(Encoder.encode(textArea.getText()));
        if (e.getSource() == decodeButton)
            Encoder.decode(preview.getImage());
    }
}
