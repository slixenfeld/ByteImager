package de;

import de.Windows.MainWindow;
import lombok.Data;

@Data
public class ByteImager {
	
	public static final String version = "0.1";
	
	public static void main(String args[]) {
		new MainWindow();
	}
}
