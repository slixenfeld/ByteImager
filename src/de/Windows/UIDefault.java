package de.Windows;

public interface UIDefault {

	public final String WindowsClassic = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
	public final String Windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public final String JGoodies_Windows = "com.jgoodies.looks.windows.WindowsLookAndFeel";
	public final String JGoodies_Plastic = "com.jgoodies.looks.plastic.PlasticLookAndFeel";
	public final String JGoodies_Plastic3D = "com.jgoodies.looks.plastic.Plastic3DLookAndFeel";
	public final String JGoodies_PlasticXP = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
	
	public void applyDefaultSettings();

	public void addComponents();
}
