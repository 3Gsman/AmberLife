package view.dialogs;

import javax.swing.JOptionPane;

public class InvalidPatientDialog {

	public static void noPatientFound() {
		JOptionPane.showMessageDialog(null,"ID or SSN don't match any patient's", "No patient found",
			    JOptionPane.WARNING_MESSAGE);
	}
}

