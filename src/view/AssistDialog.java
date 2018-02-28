package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AssistDialog {
	public AssistDialog(JFrame f){
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JTextField password = new JTextField();
		JTextField id = new JTextField();
		JTextField city = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"Password:", password,
								"ID:", id,
								"City:", city};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Assistant", JOptionPane.OK_CANCEL_OPTION);
	}
}
