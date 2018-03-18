package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import view.panels.JPanelWithBackground;

import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssistDialog extends JDialog {
	private JTextField nameField;
	private JTextField surnameField;
	private JPasswordField passField;
	private JPasswordField confirmField;
	private JTextField idField;
	private JTextField cityField;



	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public AssistDialog(JFrame f) throws IOException {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 468);
		setContentPane( new JPanelWithBackground(getClass().getResource("/resources/BG.png")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 10};
		gridBagLayout.rowHeights = new int[]{40, 10, 20, 10, 0, 20, 0, 10, 0, 20, 30, 0 , 0, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.2, 0.0, 0.2, 0.0, 0.5, 0.0, 0.2, 0.0, 0.5, 0.0, 0.0, 0.2, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(600, 400);
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("New Assistant");
		
		Color grey = new Color(80, 77, 77, 255);
		
		java.io.InputStream is = getClass().getResourceAsStream("/resources/PROMETHEUS.ttf");
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
				JLabel lblNewLabel_2 = new JLabel("introduce new assistant data");
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
					JLabel lblNewLabel = new JLabel("name");
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
					JLabel label = new JLabel("surname");
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
					JLabel lblPassword = new JLabel("pass");
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
					JLabel lblConfirm = new JLabel("confirm");
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
					JLabel lblId = new JLabel("i.d.");
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
					JLabel lblSsn = new JLabel("city");
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
				cityField = new JTextField();
				cityField.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
				cityField.setColumns(10);
				cityField.setBorder(null);
				GridBagConstraints gbc_cityField = new GridBagConstraints();
				gbc_cityField.fill = GridBagConstraints.BOTH;
				gbc_cityField.gridx = 2;
				gbc_cityField.gridy = 1;
				panel.add(cityField, gbc_cityField);
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
			JButton btnNewButton = new JButton("cancel");
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
			gbc_btnNewButton.gridy = 10;
			getContentPane().add(btnNewButton, gbc_btnNewButton);
		}
		{
			JButton btnConfirm = new JButton("confirm");
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Assistant creation confirmed");
					if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || passField.getPassword().toString().isEmpty()
							|| confirmField.getPassword().toString().isEmpty() || idField.getText().isEmpty() || cityField.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(f, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if(!(Arrays.equals(passField.getPassword(), confirmField.getPassword()))) {
						
						JOptionPane.showMessageDialog(f, "The password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
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
			gbc_btnConfirm.gridy = 10;
			getContentPane().add(btnConfirm, gbc_btnConfirm);
		}
		this.setVisible(true);
	}

}
