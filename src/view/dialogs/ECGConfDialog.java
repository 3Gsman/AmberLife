package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	private	JButton cancel;
	private JButton confirm;
	private JComboBox<Object> boxfrec;
	private JComboBox<Object> boxtime;

	public ECGConfDialog() {

		frame = new JFrame("ECG Conf");
		panel = new JPanel();

		panel.setLayout(new GridLayout(3, 2));

		addWidgets();

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
	}

	private void addWidgets() {

		
		lfrec = new JLabel("Frecuency", SwingConstants.CENTER);
		ltime = new JLabel("Seconds", SwingConstants.CENTER);
		
		cancel = new JButton("Cancel");
		confirm = new JButton("Confirm");
		
		Object[] frec = { "25","50", "100", "150", "250"};
		boxfrec = new JComboBox<Object>(frec);
		boxfrec.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxfrec.setBorder(null);
		boxfrec.setOpaque(false);
		
		Object[] time = { "15s","30s", "60s", "90s", "120s"};
		boxtime = new JComboBox<Object>(time);
		boxtime.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxtime.setBorder(null);
		boxtime.setOpaque(false);
		
		cancel.addActionListener(this);
		confirm.addActionListener(this);

		panel.add(lfrec);
		panel.add(ltime);
		panel.add(boxfrec);
		panel.add(boxtime);
		panel.add(cancel);
		panel.add(confirm);
	}

	public void actionPerformed(ActionEvent event) {

	}

}