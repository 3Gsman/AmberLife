package view.dialogs;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PatientDialog {
	
	public PatientDialog(JFrame f){
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JTextField id = new JTextField();
		JTextField ssn = new JTextField();
		JTextField municipality = new JTextField();
		JTextField address = new JTextField();
		Object[] genders = {"Male", "Female"};
		JComboBox boxgenders = new JComboBox(genders);
		Object[] status = {"Low", "Mild", "Moderate", "Critical"};
		JComboBox boxstatus = new JComboBox(status);
		JTextField message = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"ID:", id,
								"SSN:", ssn,
								"Municipality:", municipality,
								"Address:", address, 
								"Gender: ", boxgenders,
								"Status: ", boxstatus,
								"Message: ", message};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Patient", JOptionPane.OK_CANCEL_OPTION);
	}

}
