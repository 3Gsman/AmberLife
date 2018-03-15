package view.dialogs;

import javax.swing.JOptionPane;

public class FileChooserErrorDialog {

	public static void notECG() {
		JOptionPane.showMessageDialog(null,"Invalid file type. Choose an ECG", "Invalid file",
			    JOptionPane.ERROR_MESSAGE);
	}
}
