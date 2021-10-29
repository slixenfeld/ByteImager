package de.Windows;

import de.Utility.Util;

public class PreviewWindow extends Window {

	private static final long serialVersionUID = 1L;
	public static OutputPreview preview = new OutputPreview();

	public PreviewWindow() {
		super();
		
		addComponents();
		applyDefaultSettings();
	}

	@Override
	public void applyDefaultSettings() {
		this.setTitle("Preview");
		this.setType(Type.UTILITY);
		this.setSize(Util.imageSize, Util.imageSize);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);
	}

	@Override
	public void addComponents() {
		preview.setLocation(0, 0);
		this.add(preview);
	}
}
