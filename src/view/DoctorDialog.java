package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DoctorDialog {
	
	public DoctorDialog(JFrame f){
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JTextField password = new JTextField();
		JTextField id = new JTextField();
		JTextField ssn = new JTextField();
		JTextField hospital = new JTextField();
		JTextField phone = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"Password:", password,
								"ID:", id,
								"SSN:", ssn,
								"Hospital:", hospital,
								"Phone:", phone};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Doctor", JOptionPane.OK_CANCEL_OPTION);
	}
}
