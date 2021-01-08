package de.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import de.GUI.panes.FileEncodingPane;
import de.GUI.panes.TextEncodingPane;
import de.Utility.FileManager;
import de.Utility.Util;
import de.Utility.encoding.BWEncoder;
import de.Utility.encoding.Encoder;
import de.Utility.encoding.RGBEncoder;

public class MainWindow extends JFrame implements UIDefault, ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int defaultWidth = 640;
    private static final int defaultHeight = 450;

    private JTabbedPane tabs;
    private TextEncodingPane textPane = new TextEncodingPane();
    private FileEncodingPane filePane = new FileEncodingPane();

    public static OutputPreview preview = new OutputPreview();
    private JButton saveButton;
    private JButton loadButton;

    private JLabel encoderLabel;
    public static JRadioButton rgbRadio;
    public static JRadioButton bwRadio;
    private ButtonGroup encoderGroup;

    public static Encoder encoder = new RGBEncoder();

    public MainWindow() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (Exception e) { Util.log("Windows Classic Look And Feel Not Supported."); }
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

        textPane = new TextEncodingPane();
        filePane = new FileEncodingPane();

        tabs = new JTabbedPane();
        tabs.setSize(320,390);
        tabs.setLocation(10,10);
        tabs.addTab("Text", textPane);
        tabs.addTab("File", filePane);
        this.add(tabs);

        loadButton = new JButton("Load");
        loadButton.setSize(100,30);
        loadButton.setLocation( 380,350);
        loadButton.addActionListener(this);
        this.add(loadButton);

        saveButton = new JButton("Save");
        saveButton.setSize(100,30);
        saveButton.setLocation(490,350);
        saveButton.addActionListener(this);
        this.add(saveButton);

        encoderLabel = new JLabel("Encoding Type:");
        encoderLabel.setSize(100,25);
        encoderLabel.setLocation(350,280);
        this.add(encoderLabel);

        rgbRadio = new JRadioButton("RGB");
        rgbRadio.setSize(55,25);
        rgbRadio.setLocation(350,300);
        rgbRadio.addActionListener(this);
        rgbRadio.setSelected(true);
        this.add(rgbRadio);

        bwRadio = new JRadioButton("BW");
        bwRadio.setSize(55,25);
        bwRadio.setLocation(420,300);
        bwRadio.addActionListener(this);
        this.add(bwRadio);

        encoderGroup = new ButtonGroup();
        encoderGroup.add(rgbRadio);
        encoderGroup.add(bwRadio);

        preview.setLocation(350,30);
        this.add(preview);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton)
            preview.updateImage(FileManager.loadImage(this));
        else if (e.getSource() == saveButton)
            FileManager.saveImage(preview.getImage(), this);

        else if (e.getSource() == rgbRadio)
            encoder = new RGBEncoder();
        else if (e.getSource() == bwRadio)
            encoder = new BWEncoder();
    }
}
