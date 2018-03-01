package view;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AssistDialog {
	public AssistDialog(JFrame f){
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JPasswordField password = new JPasswordField();
		JPasswordField confirm = new JPasswordField();
		JTextField id = new JTextField();
		JTextField city = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"Password:", password,
								"Confirm Password:", confirm,
								"ID:", id,
								"City:", city};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Assistant", JOptionPane.OK_CANCEL_OPTION);
		
		if(nombre.getText().isEmpty() || lastname.getText().isEmpty() || password.getPassword().toString().isEmpty()
				|| confirm.getPassword().toString().isEmpty()	|| id.getText().isEmpty() || city.getText().isEmpty()) {
			JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
		}

		
		if(!(Arrays.equals(password.getPassword(), confirm.getPassword()))) {
			JOptionPane.showMessageDialog(f, "The password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
