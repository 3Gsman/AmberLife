package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Patient;

public class PatientPanel extends JPanel {
	
	private Patient p;
	/**
	 * Create the panel.
	 */
	
	
	public PatientPanel() {
		initialize(new Patient("1","John","Doe","2222X"), null);
	}
	
	public PatientPanel(Patient p, MouseListener con) {
		this.p = p;
		initialize(p,con);
	}
	
	public void initialize(Patient p, MouseListener con) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 60, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 60, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 10, 40, 0, 15, 60, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 15;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		panel_2.add(lblNewLabel);
		
		JLabel label_1 = new JLabel(" ");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		label_1.setBackground(Color.DARK_GRAY);
		panel.add(label_1);
		
		JLabel lblJohnDoe = new JLabel(p.getName() + " " + p.getLastname());
		lblJohnDoe.setForeground(Color.DARK_GRAY);
		lblJohnDoe.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		lblJohnDoe.setBackground(Color.DARK_GRAY);
		panel.add(lblJohnDoe);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_1.add(panel_3);
		
		JLabel lblId = new JLabel("i.d.");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		panel_3.add(lblId);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		label_2.setBackground(Color.DARK_GRAY);
		panel_1.add(label_2);
		
		JLabel label = new JLabel(p.getId());
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
		panel_1.add(label);
		
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(getClass().getResource("/resources/penicon.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.fill = GridBagConstraints.VERTICAL;
		gbc_button.gridx = 13;
		gbc_button.gridy = 5;
		add(button, gbc_button);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resources/canicon.png")));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 14;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);
		
		this.addMouseListener(con);
	}
	
	public Patient getPatient() {
		return p;
	}
}
