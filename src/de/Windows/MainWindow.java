package de.Windows;

import static de.Utility.Util.addButton;
import static de.Utility.Util.addLabel;
import static de.Utility.Util.newRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import de.ByteImager;
import de.Utility.FileManager;
import de.Utility.encoding.BWEncoder;
import de.Utility.encoding.Encoder;
import de.Utility.encoding.RGBEncoder;
import de.Windows.panes.FileEncodingPane;
import de.Windows.panes.TextEncodingPane;

public class MainWindow extends Window {

	private static final long serialVersionUID = 1L;
	private static final int defaultWidth = 450;
	private static final int defaultHeight = 430;

	private JTabbedPane tabs;
	private TextEncodingPane textPane;
	private FileEncodingPane filePane;


	public static JRadioButton rgbRadio;
	public static JRadioButton bwRadio;
	private ButtonGroup encoderGroup;

	public static PreviewWindow previewWindow = new PreviewWindow();
	private JFrame thisWindow = this;

	public static Encoder encoder = new RGBEncoder();

	public MainWindow() {
		super();
		
		addComponents();
		applyDefaultSettings();

		this.addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {

				previewWindow.setLocation(MainWindow.this.getLocation().x + MainWindow.this.getWidth(),
						MainWindow.this.getLocation().y);
			}
		});
	}

	@Override
	public void applyDefaultSettings() {
		this.setTitle("ByteImager " + ByteImager.version);
		this.setSize(defaultWidth, defaultHeight);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
	}

	@Override
	public void addComponents() {

		addPanes();
		
		addButton(thisWindow, "Preview", 335, 30, 100, 30,
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						previewWindow.setVisible(true);
						previewWindow.setLocation(
								thisWindow.getLocation().x + thisWindow.getWidth(), 
								thisWindow.getLocation().y);
					}
				}
		);
		
		addButton(thisWindow, "Load...", 335, 65, 100, 30 ,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});
		
		addButton(thisWindow, "Save...", 335, 100, 100, 30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveImage();
			}
		});

		addLabel(thisWindow, "Encoding Type:" ,335, 130, 150, 25);
		
		add(rgbRadio = newRadioButton(thisWindow, "RGB", true, 335, 150, 50, 25, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				encoder = new RGBEncoder();
			}
		}));
		
		add(bwRadio = newRadioButton(thisWindow, "BW", true, 390, 150, 65, 25, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				encoder = new BWEncoder();
			}
		}));
		
		encoderGroup = new ButtonGroup();
		encoderGroup.add(rgbRadio);
		encoderGroup.add(bwRadio);

	}

	private void addPanes() {
		textPane = new TextEncodingPane();
		filePane = new FileEncodingPane();

		tabs = new JTabbedPane();
		tabs.setBounds(10, 10, 320, 420);
		tabs.addTab("Text", textPane);
		tabs.addTab("File", filePane);
		this.add(tabs);
	}
	
	private void saveImage() {
		FileManager.saveImage(PreviewWindow.preview.getImage(), this);
	}

	private void loadImage() {
		PreviewWindow.preview.updateImage(FileManager.loadImage(this));
	}
}
