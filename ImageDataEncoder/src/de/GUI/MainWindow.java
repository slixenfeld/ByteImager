package de.GUI;

import de.GUI.panes.FileEncodingPane;
import de.GUI.panes.TextEncodingPane;
import de.Utility.FileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements UIDefault, ActionListener {

    private static final int defaultWidth = 640;
    private static final int defaultHeight = 450;

    JTabbedPane tabs;
    OutputPreview preview = new OutputPreview();
    private JButton saveButton;
    private JButton loadButton;
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

    public void addComponents() {
        tabs = new JTabbedPane();
        tabs.setSize(320,390);
        tabs.setLocation(10,10);
        tabs.addTab("Text", new TextEncodingPane(preview));
        tabs.addTab("File", new FileEncodingPane(preview));
        this.add(tabs);

        loadButton = new JButton("Load");
        loadButton.setSize(100,30);
        loadButton.setLocation( 380,300);
        loadButton.addActionListener(this);
        this.add(loadButton);

        saveButton = new JButton("Save");
        saveButton.setSize(100,30);
        saveButton.setLocation(490,300);
        saveButton.addActionListener(this);
        this.add(saveButton);

        preview.setLocation(350,30);
        this.add(preview);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton)
            preview.updateImage(FileManager.loadImage(this));
        if (e.getSource() == saveButton)
            FileManager.saveImage(preview.getImage(), this);
    }
}
