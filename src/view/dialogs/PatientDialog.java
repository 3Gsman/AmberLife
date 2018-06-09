package view.dialogs;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MainFr;
import view.panels.JPanelWithBackground;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.MainCtrl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PatientDialog extends JDialog {

	ActionListener controller;

	private JTextField nameField;
	private JTextField surnameField;
	private JTextField idField;
	private JTextField ssnField;
	// private JTextField hospField;
	// private JTextField phoneField;
	private JTextField cityField;
	private JTextField addressField;
	private JComboBox<Object> boxstatus;
	private JComboBox<Object> boxgenders;

	/**
	 * Create the dialog.
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public PatientDialog(MainFr f, ActionListener controller, String doctorID, String patientID)
			throws IOException, ClassNotFoundException {
		this.controller = controller;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 468);
		setContentPane(new JPanelWithBackground(getClass().getResource("/resources/BG.png")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 10 };
		gridBagLayout.rowHeights = new int[] { 40, 10, 20, 10, 20, 10, 0, 10, 20, 10, 20, 30, 0, 0, 10 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.5, 0.0, 0.0,
				0.2, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(600, 450);
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("New Patient");

		Color grey = new Color(80, 77, 77, 255);

		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		Font font = new Font("Verdana", Font.PLAIN, 28); // Default font;
		Font sf = font; // will use sf to change the style;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf = font.deriveFont(22f);
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 16;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			{
				JLabel lblNewLabel_2 = new JLabel("Introduce new patient data");
				if (patientID != null) lblNewLabel_2.setText("Editing Patient");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setFont(sf);
				panel.add(lblNewLabel_2);
			}
		}
		sf = font.deriveFont(18f);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel lblNewLabel = new JLabel("Name");
					lblNewLabel.setFont(sf);
					lblNewLabel.setForeground(Color.WHITE);
					panel_1.add(lblNewLabel);
				}
			}
			{
				JLabel lblNewLabel_1 = new JLabel(" ");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				nameField = new JTextField();
				nameField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				nameField.setBorder(null);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.BOTH;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 1;
				panel.add(nameField, gbc_textField);
				nameField.setColumns(10);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 8;
			gbc_panel.gridy = 2;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label = new JLabel("Surname");
					label.setForeground(Color.WHITE);
					label.setFont(sf);
					panel_1.add(label);
				}
			}
			{
				JLabel label = new JLabel(" ");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 1;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				surnameField = new JTextField();
				surnameField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				surnameField.setColumns(10);
				surnameField.setBorder(null);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 2;
				gbc_textField_1.gridy = 1;
				panel.add(surnameField, gbc_textField_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}

		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 4;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel lblId = new JLabel("I.D.");
					lblId.setForeground(Color.WHITE);
					lblId.setFont(sf);
					panel_1.add(lblId);
				}
			}
			{
				JLabel label = new JLabel(" ");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 1;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				idField = new JTextField();
				idField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				idField.setColumns(10);
				idField.setBorder(null);
				GridBagConstraints gbc_textField_4 = new GridBagConstraints();
				gbc_textField_4.fill = GridBagConstraints.BOTH;
				gbc_textField_4.gridx = 2;
				gbc_textField_4.gridy = 1;
				panel.add(idField, gbc_textField_4);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 8;
			gbc_panel.gridy = 4;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel lblSsn = new JLabel("S.S.N.");
					lblSsn.setForeground(Color.WHITE);
					lblSsn.setFont(sf);
					panel_1.add(lblSsn);
				}
			}
			{
				JLabel label = new JLabel(" ");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 1;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				ssnField = new JTextField();
				ssnField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				ssnField.setColumns(10);
				ssnField.setBorder(null);
				GridBagConstraints gbc_textField_5 = new GridBagConstraints();
				gbc_textField_5.fill = GridBagConstraints.BOTH;
				gbc_textField_5.gridx = 2;
				gbc_textField_5.gridy = 1;
				panel.add(ssnField, gbc_textField_5);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		/*
		 * { JPanel panel = new JPanel(); panel.setBackground(Color.WHITE);
		 * GridBagConstraints gbc_panel = new GridBagConstraints();
		 * gbc_panel.gridwidth = 6; gbc_panel.insets = new Insets(0, 0, 5, 5);
		 * gbc_panel.fill = GridBagConstraints.BOTH; gbc_panel.gridx = 1;
		 * gbc_panel.gridy = 6; getContentPane().add(panel, gbc_panel);
		 * GridBagLayout gbl_panel = new GridBagLayout(); gbl_panel.columnWidths
		 * = new int[]{110, 10, 80, 0}; gbl_panel.rowHeights = new int[]{0, 30,
		 * 3, 0}; gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0,
		 * Double.MIN_VALUE}; gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0,
		 * Double.MIN_VALUE}; panel.setLayout(gbl_panel); { JPanel panel_1 = new
		 * JPanel(); panel_1.setBackground(grey); GridBagConstraints gbc_panel_1
		 * = new GridBagConstraints(); gbc_panel_1.fill =
		 * GridBagConstraints.BOTH; gbc_panel_1.gridx = 0; gbc_panel_1.gridy =
		 * 1; panel.add(panel_1, gbc_panel_1); panel_1.setLayout(new
		 * FlowLayout(FlowLayout.LEFT, 5, 5)); { JLabel lblHosp = new
		 * JLabel("Hospital"); lblHosp.setForeground(Color.WHITE);
		 * lblHosp.setFont(sf); panel_1.add(lblHosp); } } { JLabel label = new
		 * JLabel(" "); GridBagConstraints gbc_label = new GridBagConstraints();
		 * gbc_label.anchor = GridBagConstraints.EAST; gbc_label.gridx = 1;
		 * gbc_label.gridy = 1; panel.add(label, gbc_label); } { hospField = new
		 * JTextField(); hospField.setFont(new Font("Source Code Pro Medium",
		 * Font.PLAIN, 16)); hospField.setColumns(10);
		 * hospField.setBorder(null); GridBagConstraints gbc_textField_6 = new
		 * GridBagConstraints(); gbc_textField_6.fill = GridBagConstraints.BOTH;
		 * gbc_textField_6.gridx = 2; gbc_textField_6.gridy = 1;
		 * panel.add(hospField, gbc_textField_6); } { JPanel panel_1 = new
		 * JPanel(); panel_1.setBackground(grey); GridBagConstraints gbc_panel_1
		 * = new GridBagConstraints(); gbc_panel_1.fill =
		 * GridBagConstraints.BOTH; gbc_panel_1.gridwidth = 3; gbc_panel_1.gridx
		 * = 0; gbc_panel_1.gridy = 2; panel.add(panel_1, gbc_panel_1); } } {
		 * JPanel panel = new JPanel(); panel.setBackground(Color.WHITE);
		 * GridBagConstraints gbc_panel = new GridBagConstraints();
		 * gbc_panel.gridwidth = 6; gbc_panel.insets = new Insets(0, 0, 5, 5);
		 * gbc_panel.fill = GridBagConstraints.BOTH; gbc_panel.gridx = 8;
		 * gbc_panel.gridy = 6; getContentPane().add(panel, gbc_panel);
		 * GridBagLayout gbl_panel = new GridBagLayout(); gbl_panel.columnWidths
		 * = new int[]{110, 10, 80, 0}; gbl_panel.rowHeights = new int[]{0, 30,
		 * 3, 0}; gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0,
		 * Double.MIN_VALUE}; gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0,
		 * Double.MIN_VALUE}; panel.setLayout(gbl_panel); { JPanel panel_1 = new
		 * JPanel(); panel_1.setBackground(grey); GridBagConstraints gbc_panel_1
		 * = new GridBagConstraints(); gbc_panel_1.fill =
		 * GridBagConstraints.BOTH; gbc_panel_1.gridx = 0; gbc_panel_1.gridy =
		 * 1; panel.add(panel_1, gbc_panel_1); panel_1.setLayout(new
		 * FlowLayout(FlowLayout.LEFT, 5, 5)); { JLabel lblPhone = new
		 * JLabel("Phone"); lblPhone.setForeground(Color.WHITE);
		 * lblPhone.setFont(sf); panel_1.add(lblPhone); } } { JLabel label = new
		 * JLabel(" "); GridBagConstraints gbc_label = new GridBagConstraints();
		 * gbc_label.anchor = GridBagConstraints.EAST; gbc_label.gridx = 1;
		 * gbc_label.gridy = 1; panel.add(label, gbc_label); } { phoneField =
		 * new JTextField(); phoneField.setFont(new
		 * Font("Source Code Pro Medium", Font.PLAIN, 16));
		 * phoneField.setColumns(10); phoneField.setBorder(null);
		 * GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		 * gbc_textField_7.fill = GridBagConstraints.BOTH; gbc_textField_7.gridx
		 * = 2; gbc_textField_7.gridy = 1; panel.add(phoneField,
		 * gbc_textField_7); } { JPanel panel_1 = new JPanel();
		 * panel_1.setBackground(grey); GridBagConstraints gbc_panel_1 = new
		 * GridBagConstraints(); gbc_panel_1.fill = GridBagConstraints.BOTH;
		 * gbc_panel_1.gridwidth = 3; gbc_panel_1.gridx = 0; gbc_panel_1.gridy =
		 * 2; panel.add(panel_1, gbc_panel_1); } }
		 */
		{
			// MARK
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 8;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label = new JLabel("Municip.");
					label.setForeground(Color.WHITE);
					label.setFont(sf);
					panel_1.add(label);
				}
			}
			{
				JLabel label = new JLabel(" ");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 1;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				cityField = new JTextField();
				cityField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				cityField.setColumns(10);
				cityField.setBorder(null);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 2;
				gbc_textField_1.gridy = 1;
				panel.add(cityField, gbc_textField_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		{
			// MARK
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 8;
			gbc_panel.gridy = 8;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label = new JLabel("Address");
					label.setForeground(Color.WHITE);
					label.setFont(sf);
					panel_1.add(label);
				}
			}
			{
				JLabel label = new JLabel(" ");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 1;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				addressField = new JTextField();
				addressField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				addressField.setColumns(10);
				addressField.setBorder(null);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 2;
				gbc_textField_1.gridy = 1;
				panel.add(addressField, gbc_textField_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 10;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label = new JLabel("Gender");
					label.setForeground(Color.WHITE);
					label.setFont(sf);
					panel_1.add(label);
				}
			}
			{
				Object[] genders = { "Male", "Female" };
				boxgenders = new JComboBox<Object>(genders);
				boxgenders.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				boxgenders.setBorder(null);
				boxgenders.setOpaque(false);
				GridBagConstraints gbc_boxgenders = new GridBagConstraints();
				gbc_boxgenders.fill = GridBagConstraints.BOTH;
				gbc_boxgenders.gridwidth = 2;
				gbc_boxgenders.gridx = 1;
				gbc_boxgenders.gridy = 1;
				panel.add(boxgenders, gbc_boxgenders);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 8;
			gbc_panel.gridy = 10;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 10, 80, 0 };
			gbl_panel.rowHeights = new int[] { 0, 30, 3, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.2, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label = new JLabel("Status");
					label.setForeground(Color.WHITE);
					label.setFont(sf);
					panel_1.add(label);
				}
			}

			{
				Object[] status = { "Low", "Mild", "Moderate", "Critical" };
				boxstatus = new JComboBox<Object>(status);
				boxstatus.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				GridBagConstraints gbc_boxstatus = new GridBagConstraints();
				gbc_boxstatus.fill = GridBagConstraints.BOTH;
				gbc_boxstatus.gridwidth = 2;
				gbc_boxstatus.gridx = 1;
				gbc_boxstatus.gridy = 1;
				panel.add(boxstatus, gbc_boxstatus);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(grey);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 3;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
		sf = font.deriveFont(22f);
		{
			JButton btnNewButton = new JButton("CANCEL");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton.setActionCommand("CANCEL");
			btnNewButton.setBorderPainted(false);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(grey);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton.gridwidth = 3;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 4;
			gbc_btnNewButton.gridy = 12;
			getContentPane().add(btnNewButton, gbc_btnNewButton);
		}
		{
			JButton btnConfirm = new JButton("CONFIRM");
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Patient creation confirmed");
					if (checkBoxesFilled()) {
						JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							if (patientID == null) createNewPatient(doctorID);
							else updatePatient(patientID, doctorID);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			});
			btnConfirm.setActionCommand("CONFIRM");
			btnConfirm.setBorderPainted(false);
			btnConfirm.setBackground(grey);
			btnConfirm.setForeground(Color.WHITE);
			btnConfirm.setFont(sf);
			GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
			gbc_btnConfirm.fill = GridBagConstraints.BOTH;
			gbc_btnConfirm.gridwidth = 3;
			gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
			gbc_btnConfirm.gridx = 8;
			gbc_btnConfirm.gridy = 12;
			getContentPane().add(btnConfirm, gbc_btnConfirm);
		}
		if (patientID != null) {
			initializeFields(patientID);
			this.setTitle("New Patient");
		}
		this.setVisible(true);
	}

	void initializeFields(String id) throws ClassNotFoundException {
		System.out.println("Initialize Fields");
		try {
			Connection c = null;
			Class.forName("org.mariadb.jdbc.Driver");

			//String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
			//String userdb = "pi2_amberlife";
			//String pass = "rdysdhsks";

			String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
			String userdb = "dani";
			String pass = "gaja";

			c = DriverManager.getConnection(db, userdb, pass);
			Statement stmt = c.createStatement();
			ResultSet rs_ptt = stmt.executeQuery("SELECT * FROM Patient where IDptt LIKE '" + id + "'");
			if (rs_ptt.next()) {
				nameField.setText(rs_ptt.getString("Name"));
				surnameField.setText(rs_ptt.getString("LastName"));
				idField.setText(id);
				ssnField.setText(rs_ptt.getString("SSN"));
				cityField.setText(rs_ptt.getString("Municipality"));
				addressField.setText(rs_ptt.getString("Address"));

				// FALTAN AQUI QUE SEAN OBJECT
				//
				/*
				 * String status = rs_ptt.getString("Status");
				 * boxstatus.setSelectedItem(anObject);
				 */

				boxgenders.setSelectedIndex((rs_ptt.getString("Sex").equals("Male")) ? 0 : 1);
			}
			rs_ptt.close();
			stmt.close();
			c.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void createNewPatient(String doctorID) throws ClassNotFoundException {
		System.out.println("Creating new patient");
		try {
			Connection c = null;
			Class.forName("org.mariadb.jdbc.Driver");

			// String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
			// String userdb = "pi2_amberlife";
			// String pass = "rdysdhsks";

			String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
			String userdb = "dani";
			String pass = "gaja";
			c = DriverManager.getConnection(db, userdb, pass);
			String sql = "SELECT IDptt FROM Patient where IDptt LIKE '" + idField.getText() + "'";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			stmt.close();
			c.close();

			if (rs.next() == true)
				System.out.println("A Patient with that ID already exists");
			else
				uploadNewPatient(doctorID);

			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	void uploadNewPatient(String doctorID) {
		String sql1 = "INSERT INTO Patient(IDptt, Name, LastName, Municipality, Address, Sex, Status, SSN, Doctor)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			Connection c = null;
			Class.forName("org.mariadb.jdbc.Driver");

			// String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
			// String userdb = "pi2_amberlife";
			// String pass = "rdysdhsks";

			String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
			String userdb = "dani";
			String pass = "gaja";
			c = DriverManager.getConnection(db, userdb, pass);
			PreparedStatement st1 = c.prepareStatement(sql1);
			st1.setString(1, idField.getText());
			// Doubt this is the best for security, consider this only temporal
			st1.setString(2, nameField.getText());
			st1.setString(3, surnameField.getText());
			st1.setString(4, cityField.getText());
			st1.setString(5, addressField.getText());
			st1.setString(6, boxgenders.getSelectedItem().toString());
			st1.setString(7, boxstatus.getSelectedItem().toString());
			st1.setString(8, ssnField.getText());
			st1.setString(9, doctorID);
			// DOCTOR GOES HERE
			st1.executeUpdate();
			st1.close();
			c.close();

			controller.actionPerformed(new ActionEvent(this, 0, "PATIENT_UPDATE"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void updatePatient(String id, String docID) throws SQLException, ClassNotFoundException {
		System.out.println("Update Doctor launched");

		Connection c = null;
		Class.forName("org.mariadb.jdbc.Driver");

		// String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
		// String userdb = "pi2_amberlife";
		// String pass = "rdysdhsks";

		String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
		String userdb = "dani";
		String pass = "gaja";
		c = DriverManager.getConnection(db, userdb, pass);

		String sql = "SELECT IDptt FROM Patient where IDptt LIKE '" + idField.getText()+ "'";
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		stmt.close();
		c.close();

		if (id == idField.getText()) { // IF we are not changing the ID, update
										// it normally
			Connection c2 = null;
			Class.forName("org.mariadb.jdbc.Driver");

			c2 = DriverManager.getConnection(db, userdb, pass);
			// Update code
			String sql1 = "UPDATE Patient SET IIDptt = ?, Name = ?, LastName = ?, Municipality = ?, Address = ?, Sex = ?, "
					+ "Status = ?, SSN = ?, Doctor = ?";
			String ID = idField.getText();
			c2 = DriverManager.getConnection(db, userdb, pass);
			PreparedStatement st1 = c2.prepareStatement(sql1);
			st1.setString(1, idField.getText());
			// Doubt this is the best for security, consider this only temporal
			st1.setString(2, nameField.getName());
			st1.setString(3, surnameField.getName());
			st1.setString(4, cityField.getText());
			st1.setString(5, addressField.getText());
			st1.setString(6, boxgenders.getSelectedObjects().toString());
			st1.setString(7, boxstatus.getSelectedObjects().toString());
			st1.setString(8, ssnField.getText());
			st1.setString(9, docID);
			// DOCTOR GOES HERE
			st1.executeUpdate();
			st1.close();
			c.close();
			controller.actionPerformed(new ActionEvent(this, 0, "PATIENT_UPDATE"));
		} else { // If the ID is changed, delete the table and instead create
					// another one?
			Connection c3 = null;
			Class.forName("org.mariadb.jdbc.Driver");

			c3 = DriverManager.getConnection(db, userdb, pass);
			Statement stmt3 = c3.createStatement();
			// Delete the old one and create a new one with the new ID and data
			stmt3.execute("DELETE FROM Patient WHERE IDptt LIKE '" + id + "'");

			uploadNewPatient(docID);

			// UPDATE PATIENTS AND MESSAGES FOR NEW ID NOW
			stmt3.execute("UPDATE ECG SET IDptt = '" + idField.getText() + "' WHERE IDptt LIKE '" + id + "'");
			stmt3.execute("UPDATE Message SET IDptt = '" + idField.getText() + "' WHERE IDptt LIKE '" + id + "'");
			stmt3.close();
			c3.close();
		}

	}

	boolean checkBoxesFilled() {
		return idField.getText().isEmpty() || nameField.getText().isEmpty() || surnameField.getText().isEmpty()
				|| cityField.getText().isEmpty() || addressField.getText().isEmpty() || ssnField.getText().isEmpty();
	}

}
