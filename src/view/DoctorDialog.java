package view;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DoctorDialog {
	
	public DoctorDialog(JFrame f){
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JPasswordField password = new JPasswordField();
		JPasswordField confirm = new JPasswordField();
		JTextField id = new JTextField();
		JTextField ssn = new JTextField();
		JTextField hospital = new JTextField();
		JTextField phone = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"Password:", password,
								"Confirm Password:", confirm,
								"ID:", id,
								"SSN:", ssn,
								"Hospital:", hospital,
								"Phone:", phone};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Doctor", JOptionPane.OK_CANCEL_OPTION);

		if(nombre.getText().isEmpty() || lastname.getText().isEmpty() || password.getPassword().toString().isEmpty()
				|| confirm.getPassword().toString().isEmpty()	|| id.getText().isEmpty() || ssn.getText().isEmpty() 
				|| hospital.getText().isEmpty() || phone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(!(Arrays.equals(password.getPassword(), confirm.getPassword()))) {
			JOptionPane.showMessageDialog(f, "The password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
