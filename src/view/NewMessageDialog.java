package view;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Patient;

public class NewMessageDialog {
	public NewMessageDialog(JFrame f, Patient p) {
		JTextArea message = new JTextArea();
		message.setRows(7);
		Object[] inputFields = {"New message about " + p.getName() + " " + p.getLastname() + ":", message};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Message", JOptionPane.OK_CANCEL_OPTION);

		
	}
	
	public NewMessageDialog(JFrame f, String name, String lastname) {
		JTextArea message = new JTextArea();
		message.setRows(7);
		Object[] inputFields = {"New message about " + name + " " + lastname + ":", message};
		
		JOptionPane.showConfirmDialog(f, inputFields, "New Message", JOptionPane.OK_CANCEL_OPTION);

		
	}
}
