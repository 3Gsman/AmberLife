package view.dialogs;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.panels.JPanelWithBackground;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField idField;
	private JTextField ssnField;
	//private JTextField hospField;
	//private JTextField phoneField;
	private JTextField cityField;
	private JTextField addressField;
	private JComboBox<Object> boxstatus;
	private JComboBox<Object> boxgenders;
	
	
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public PatientDialog(JPanelWithBackground f) throws IOException {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 468);
		setContentPane( new JPanelWithBackground(getClass().getResource("/resources/BG.png")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 10};
		gridBagLayout.rowHeights = new int[]{40, 10, 20, 10, 20, 10, 0, 10, 20, 10, 20, 30, 0 , 0, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.5, 0.0, 0.0, 0.2, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(600, 450);
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("New Patient");
		
		Color grey = new Color(80, 77, 77, 255);
		
		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		/*{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 6;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
					JLabel lblHosp = new JLabel("Hospital");
					lblHosp.setForeground(Color.WHITE);
					lblHosp.setFont(sf);
					panel_1.add(lblHosp);
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
				hospField = new JTextField();
				hospField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				hospField.setColumns(10);
				hospField.setBorder(null);
				GridBagConstraints gbc_textField_6 = new GridBagConstraints();
				gbc_textField_6.fill = GridBagConstraints.BOTH;
				gbc_textField_6.gridx = 2;
				gbc_textField_6.gridy = 1;
				panel.add(hospField, gbc_textField_6);
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
			gbc_panel.gridy = 6;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
					JLabel lblPhone = new JLabel("Phone");
					lblPhone.setForeground(Color.WHITE);
					lblPhone.setFont(sf);
					panel_1.add(lblPhone);
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
				phoneField = new JTextField();
				phoneField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				phoneField.setColumns(10);
				phoneField.setBorder(null);
				GridBagConstraints gbc_textField_7 = new GridBagConstraints();
				gbc_textField_7.fill = GridBagConstraints.BOTH;
				gbc_textField_7.gridx = 2;
				gbc_textField_7.gridy = 1;
				panel.add(phoneField, gbc_textField_7);
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
		}*/
		{
			//MARK
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			//MARK
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				Object[] genders = {"Male", "Female"};
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
			gbl_panel.columnWidths = new int[]{110, 10, 80, 0};
			gbl_panel.rowHeights = new int[]{0, 30, 3, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				Object[] status = {"Low", "Mild", "Moderate", "Critical"};
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
					dispose();				}
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
					if (idField.getText().isEmpty() || nameField.getText().isEmpty() || surnameField.getText().isEmpty()
								|| cityField.getText().isEmpty()	|| addressField.getText().isEmpty() || ssnField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						//Add to DB
						String sql1 = "INSERT INTO Patient(IDptt, Name, LastName, Municipality, Address, Sex, Status, SSN, Doctor)" +
										"VALUES(?,?,?,?,?,?,?,?,?)";
						Connection c = null;
						try {
							c = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
							PreparedStatement st1 = c.prepareStatement(sql1);
							st1.setString(1, idField.getText());
							//Doubt this is the best for security, consider this only temporal
							st1.setString(2, nameField.getName());
							st1.setString(3, surnameField.getName());
							st1.setString(4, cityField.getText());
							st1.setString(5, addressField.getText());
							st1.setString(6, boxgenders.getSelectedObjects().toString());
							st1.setString(7, boxstatus.getSelectedObjects().toString());
							st1.setString(8, ssnField.getText());
							st1.executeUpdate();		
							st1.close();
							c.close();
						}
						catch (Exception ex) {
							ex.printStackTrace();
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
		this.setVisible(true);
	}

}
