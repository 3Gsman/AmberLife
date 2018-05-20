package view.dialogs;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
import java.util.Vector;
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
public class DoctorDialog extends JDialog {
	
	private ActionListener windowToRefresh;
	
	private JTextField nameField;
	private JTextField surnameField;
	private JPasswordField passField;
	private JPasswordField confirmField;
	private JTextField idField;
	private JTextField ssnField;
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField mlnField;
	private JTextField phoneField;

	//FIELDS AREKINDA DESORGANIZED

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public DoctorDialog(JFrame f, ActionListener windowToRefresh, String id) throws IOException {
		this.windowToRefresh = windowToRefresh;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 468);
		setContentPane( new JPanelWithBackground(getClass().getResource("/resources/BG.png")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0,0, 0, 10};
		gridBagLayout.rowHeights = new int[]{40, 10, 20, 10, 0, 20, 0, 10, 0, 20, 0, 10, 30, 0 , 0, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.2, 0.0, 0.5, 0.0, 0.0, 0.2, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(720, 480);
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("New Doctor");
		
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
				JLabel lblNewLabel_2;
				if(id != null) {
					lblNewLabel_2 = new JLabel("Introduce new doctor data");
				} else {lblNewLabel_2 = new JLabel("Editing doctor"); }
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
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.fill = GridBagConstraints.BOTH;
				gbc_nameField.gridx = 2;
				gbc_nameField.gridy = 1;
				panel.add(nameField, gbc_nameField);
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
				GridBagConstraints gbc_surnameField = new GridBagConstraints();
				gbc_surnameField.fill = GridBagConstraints.BOTH;
				gbc_surnameField.gridx = 2;
				gbc_surnameField.gridy = 1;
				panel.add(surnameField, gbc_surnameField);
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
					JLabel lblPassword = new JLabel("Pass");
					lblPassword.setForeground(Color.WHITE);
					lblPassword.setFont(sf);
					panel_1.add(lblPassword);
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
				passField = new JPasswordField();
				passField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				passField.setColumns(10);
				passField.setBorder(null);
				GridBagConstraints gbc_passField = new GridBagConstraints();
				gbc_passField.fill = GridBagConstraints.BOTH;
				gbc_passField.gridx = 2;
				gbc_passField.gridy = 1;
				panel.add(passField, gbc_passField);
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
					JLabel lblConfirm = new JLabel("Confirm");
					lblConfirm.setForeground(Color.WHITE);
					lblConfirm.setFont(sf);
					panel_1.add(lblConfirm);
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
				confirmField = new JPasswordField();
				confirmField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				confirmField.setColumns(10);
				confirmField.setBorder(null);
				GridBagConstraints gbc_confirmField = new GridBagConstraints();
				gbc_confirmField.fill = GridBagConstraints.BOTH;
				gbc_confirmField.gridx = 2;
				gbc_confirmField.gridy = 1;
				panel.add(confirmField, gbc_confirmField);
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
				GridBagConstraints gbc_idField = new GridBagConstraints();
				gbc_idField.fill = GridBagConstraints.BOTH;
				gbc_idField.gridx = 2;
				gbc_idField.gridy = 1;
				panel.add(idField, gbc_idField);
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
				GridBagConstraints gbc_ssnField = new GridBagConstraints();
				gbc_ssnField.fill = GridBagConstraints.BOTH;
				gbc_ssnField.gridx = 2;
				gbc_ssnField.gridy = 1;
				panel.add(ssnField, gbc_ssnField);
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
					JLabel lblHosp = new JLabel("Username");
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
				usernameField = new JTextField();
				usernameField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				usernameField.setColumns(10);
				usernameField.setBorder(null);
				GridBagConstraints gbc_usernameField = new GridBagConstraints();
				gbc_usernameField.fill = GridBagConstraints.BOTH;
				gbc_usernameField.gridx = 2;
				gbc_usernameField.gridy = 1;
				panel.add(usernameField, gbc_usernameField);
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
		//Email Field
		{
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
					JLabel lblEmail = new JLabel("Email");
					lblEmail.setForeground(Color.WHITE);
					lblEmail.setFont(sf);
					panel_1.add(lblEmail);
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
				emailField = new JTextField();
				emailField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				emailField.setColumns(10);
				emailField.setBorder(null);
				GridBagConstraints gbc_emailField = new GridBagConstraints();
				gbc_emailField.fill = GridBagConstraints.BOTH;
				gbc_emailField.gridx = 2;
				gbc_emailField.gridy = 1;
				panel.add(emailField, gbc_emailField);
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
		//MLN Field
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
					JLabel lblMLN = new JLabel("M.L.N.");
					lblMLN.setForeground(Color.WHITE);
					lblMLN.setFont(sf);
					panel_1.add(lblMLN);
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
				mlnField = new JTextField();
				mlnField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				mlnField.setColumns(10);
				mlnField.setBorder(null);
				GridBagConstraints gbc_emailField = new GridBagConstraints();
				gbc_emailField.fill = GridBagConstraints.BOTH;
				gbc_emailField.gridx = 2;
				gbc_emailField.gridy = 1;
				panel.add(mlnField, gbc_emailField);
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
		//PhoneField
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
				GridBagConstraints gbc_emailField = new GridBagConstraints();
				gbc_emailField.fill = GridBagConstraints.BOTH;
				gbc_emailField.gridx = 2;
				gbc_emailField.gridy = 1;
				panel.add(phoneField, gbc_emailField);
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
		
		//BUTTONS START HERE
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
					System.out.println("Doctor creation started");
						
					if(checkBoxesFilled()) 
						JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
					else if(!(Arrays.equals(passField.getPassword(), confirmField.getPassword()))) 
						JOptionPane.showMessageDialog(f, "The password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						try {
							if (id == null) createNewDoctor();
							else updateDoctor(id);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}									
					}
				}
			});
			
			//btnConfirm.setActionCommand("CONFIRM");
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
		if(id != null) {
			initializeFields(id);
		}
		
		this.setVisible(true);
	}
	
	void initializeFields(String id) {
		System.out.println("Initialize Fields");
		try {
		Connection c = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
		Statement stmt =  c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT User.Name, User.LastName, User.Password, User.Username," +
				"User.Email, Doctor.MLN, CLINICAL.SSN FROM User, Doctor, CLINICAL " +
				"WHERE User.IDUser LIKE '" + id + "' AND Doctor.IDUser LIKE '" + id + "' AND "
				+ " CLINICAL.IDUser LIKE '" + id + "'");

		nameField.setText(rs.getString("Name"));
		surnameField.setText(rs.getString("LastName"));
		passField.setText(rs.getString("Password"));
		confirmField.setText(rs.getString("Password"));
		idField.setText(id);
		ssnField.setText(String.valueOf(rs.getInt("SSN")));
		usernameField.setText(rs.getString("Username"));
		emailField.setText(rs.getString("Email"));
		mlnField.setText(String.valueOf(rs.getInt("MLN")));
		
		Statement stmt2 = c.createStatement();
		ResultSet rs_tlph = 
				stmt2.executeQuery("SELECT Number FROM Telephone where IDuser LIKE '" + id + "'");

		Vector<Integer> phones = new Vector<Integer>();

		while (rs_tlph.next()) {
			phones.add(rs_tlph.getInt("Number"));
		}
		
		phoneField.setText(String.valueOf(phones.firstElement()));
		
		rs.close();
		stmt.close();
		stmt2.close();
		rs_tlph.close();
		c.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createNewDoctor() {
		System.out.println("Creating new doctor");
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
			String sql = "SELECT IDuser FROM User WHERE IDuser = '" + idField.getText()+ "'";
			Statement stmt =  c.createStatement();
			ResultSet rs  = stmt.executeQuery(sql);
			stmt.close();
			c.close();
			
			if(rs.next() == true) System.out.println("A User with that ID already exists");
			else uploadNewDoctor();	
			
			rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}
	
	void uploadNewDoctor() {
		System.out.println("Upload new doctor launched");
		String sql1 = "INSERT INTO User(IDuser, Password, Active, Name, LastName, Username, Email)" +
						"VALUES(?,?,?,?,?,?,?)";
		String sql2	 = "INSERT INTO CLINICAL(IDUser, SSN) VALUES(?,?)";
		String sql3	 = "INSERT INTO Doctor(IDUser, MLN) VALUES(?,?)";
		String sql4 = "INSERT INTO Telephone(IDuser, Number) VALUES (?,?)";
		String ID = idField.getText();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
			PreparedStatement st1 = c.prepareStatement(sql1);
			st1.setString(1, ID);
			//Doubt this is the best for security, consider this only temporal
			st1.setString(2, String.valueOf(passField.getPassword()));
			st1.setString(3, "YES");
			st1.setString(4, nameField.getText());
			st1.setString(5, surnameField.getText());
			st1.setString(6, usernameField.getText());
			st1.setString(7, emailField.getText());
			st1.executeUpdate();			
			st1.close();
			PreparedStatement st2 = c.prepareStatement(sql2);
			st2.setString(1, ID);
			st2.setString(2, ssnField.getText());
			st2.executeUpdate();
			st2.close();
			PreparedStatement st3 = c.prepareStatement(sql3);
			st3.setString(1, ID);
			st3.setString(2, mlnField.getText());
			st3.executeUpdate();
			st3.close();
			PreparedStatement st4 = c.prepareStatement(sql4);
			st4.setString(1, ID);
			st4.setString(2,phoneField.getText());
			st4.executeUpdate();
			st4.close();
			c.close();
			windowToRefresh.actionPerformed(new ActionEvent(this, 0, "USER_UPDATE"));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	void updateDoctor(String id) throws SQLException {
		System.out.println("Update Doctor launched");
		
		Connection c = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
		String sql = "SELECT IDuser, Username FROM User where IDuser LIKE '" + idField.getText() + "'";
		Statement stmt =  c.createStatement();
		ResultSet rs  = stmt.executeQuery(sql);
		stmt.close();
		c.close();
		
		if(idField.getText().equals(id) ) { //IF we are not changing the ID, update it normally
			Connection c2 = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
			
			Statement check = c2.createStatement();
			ResultSet rs_check = check.executeQuery("SELECT Username From User WHERE Username LIKE '" + usernameField.getText() + 
								"' AND NOT IDuser LIKE '" + id + "'");
			if(rs_check.next()) {
				JOptionPane.showMessageDialog(MainCtrl.window, "That username is already in use");
				dispose();
			}
			else {
			check.close();
			rs_check.close();
			//Update code
			String sql1 = "UPDATE User SET Password = ?, Name = ?,"
					+ " LastName = ?, Username=?, Email =? WHERE IDuser LIKE '" + id + "'";	
			String sql2	 = "Update CLINICAL Set SSN = ? WHERE IDuser LIKE '" + id + "'";	
			String sql3	 = "Update  Doctor Set  MLN = ? WHERE IDuser LIKE '" + id + "'";	
			String sql4 = "Update Telephone Set  Number = ? WHERE IDuser LIKE '" + id + "'";	
			String ID = idField.getText();
			c2 = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);
			PreparedStatement st1 = c2.prepareStatement(sql1);
			//Doubt this is the best for security, consider this only temporal
			st1.setString(1, String.valueOf(passField.getPassword()));
			st1.setString(2, nameField.getText());
			st1.setString(3, surnameField.getText());
			st1.setString(4, usernameField.getText());
			st1.setString(5, emailField.getText());
			st1.executeUpdate();			
			st1.close();
			PreparedStatement st2 = c2.prepareStatement(sql2);
			st2.setString(1, ssnField.getText());
			st2.executeUpdate();
			st2.close();
			PreparedStatement st3 = c2.prepareStatement(sql3);
			st3.setString(1, mlnField.getText());
			st3.executeUpdate();
			st3.close();
			PreparedStatement st4 = c2.prepareStatement(sql4);
			st4.setString(1,phoneField.getText());
			st4.close();
			c2.close();
			rs.close();
			windowToRefresh.actionPerformed(new ActionEvent(this, 0, "USER_UPDATE"));
			}
		}
		else { //If the ID is changed, delete the table and instead create another one?
			Connection c3 = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE);	
			
			Statement check = c3.createStatement();
			ResultSet rs_check = check.executeQuery("SELECT IDuser From User WHERE Username LIKE '" + usernameField.getText() + "'");
			if(rs_check.next()) {
				JOptionPane.showMessageDialog(MainCtrl.window, "That ID is already in use");
				dispose();
			}
			check.close();
			rs_check.close();
			
			Statement stmt3=  c3.createStatement();
			//Delete the old one and create a new one with the new ID and data
			stmt3.execute("DELETE FROM Doctor WHERE IDuser LIKE '" + id + "'");
			stmt3.execute("DELETE FROM Clinical WHERE IDuser LIKE '" + id + "'");
			stmt3.execute("DELETE FROM Telephone WHERE IDuser LIKE '" + id + "'");
			stmt3.execute("DELETE FROM User WHERE IDuser LIKE '" + id + "'");
			
			uploadNewDoctor();
			
			//UPDATE PATIENTS AND MESSAGES FOR NEW ID NOW
			stmt3.execute("UPDATE Patient SET Doctor = '" + idField.getText() + "' WHERE Doctor LIKE '" + id + "' ");
			stmt3.execute("UPDATE Message SET IDuser = '" + idField.getText() + "' WHERE IDuser LIKE '" + id + "'");
			stmt3.close();
			c3.close();
		}
		
		
	}
	
	boolean checkBoxesFilled() {
		return nameField.getText().isEmpty() || surnameField.getText().isEmpty() || passField.getPassword().toString().isEmpty()
				|| confirmField.getPassword().toString().isEmpty()	|| idField.getText().isEmpty() 
				|| ssnField.getText().isEmpty() || usernameField.getText().isEmpty() || emailField.getText().isEmpty()  
				|| mlnField.getText().isEmpty() || phoneField.getText().isEmpty();
	}

}
