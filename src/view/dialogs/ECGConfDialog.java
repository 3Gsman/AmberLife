package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ECGConfDialog extends JDialog implements ActionListener {

	JFrame frame;
	JPanel panel;

	private JLabel lfrec;
	private JLabel ltime;
	private JButton cancel;
	private JButton confirm;
	private JComboBox<Object> boxfrec;
	private JComboBox<Object> boxtime;

	public ECGConfDialog() {

		frame = new JFrame("ECG Conf");
		panel = new JPanel();

		panel.setLayout(new GridLayout(3, 2));

		addItems();

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	private void addItems() {

		lfrec = new JLabel("Frecuency (Hz)", SwingConstants.CENTER);
		ltime = new JLabel("Duratio (S)", SwingConstants.CENTER);

		cancel = new JButton("Cancel");
		confirm = new JButton("Confirm");

		Object[] frec = { "25", "50", "100", "150", "200", "250" };
		boxfrec = new JComboBox<Object>(frec);
		boxfrec.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxfrec.setBorder(null);
		boxfrec.setOpaque(false);

		Object[] time = { "15", "30", "45", "60", "90","120" };
		boxtime = new JComboBox<Object>(time);
		boxtime.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxtime.setBorder(null);
		boxtime.setOpaque(false);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String datafrec = boxfrec.getSelectedItem().toString();
				int frecint = Integer.parseInt(datafrec);
				
				String datatime = boxtime.getSelectedItem().toString();
				int timeint = Integer.parseInt(datatime);
				
				//MANDAR ESTOS DATOS A ARDUINO
				System.out.println(frecint);
				System.out.println(timeint);
				
				frame.dispose();
			}
		});

		panel.add(lfrec);
		panel.add(ltime);
		panel.add(boxfrec);
		panel.add(boxtime);
		panel.add(cancel);
		panel.add(confirm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}