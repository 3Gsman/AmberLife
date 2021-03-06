package view;
import control.LoginCtrl;
import model.LocalizationService;
import view.panels.JPanelWithBackground;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.FontFormatException;


@SuppressWarnings("serial")
public class LoginFr extends JPanel {
	
	LoginCtrl controller;
	
	private JTextField textField;
	private JPasswordField passwordField;
	
	/*
	 * 
	 * 
	 * private JTextField textField;
		private JPasswordField passwordField;
	 */
	public String getUsername() {
		return textField.getText();
	}
	
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}
	
	public void resetText() {
		textField.setText("");
		passwordField.setText("");
	}
	
	
	public void addController(LoginCtrl a) {
		this.controller = a;
	}
	
	/**
	 * Create the application.
	 */
	public LoginFr() {	
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		
		//Set action on close
		this.setBackground(new Color(204, 0, 0));
		this.setLayout(new BorderLayout(0, 0));	
		
		Color grey = new Color(80, 77, 77, 255);
		
		//Get PROMETHEUS font
		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		java.io.InputStream is2 = getClass().getResourceAsStream("/resources/PROMETHEUS.ttf");
		Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
		Font font2 = new Font("Verdana", Font.PLAIN, 28); //Default font;
		Font sf = font; // will use sf to change the style;
		Font sf2 = font2; 
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
			font2 = Font.createFont(Font.TRUETYPE_FONT, is2);
			sf2 = font2;
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//WindowBuilder does NOT parse this one, switch by a normal JPanel to remove background and allow parsing.

		JPanelWithBackground panel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));	
		//JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{15, 80, 20, 20, 0, 0, 10, 60, 0, 10, 60, 20, 60, 30, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.2, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.2, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.5, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		
		JLabel lblNewLabel_3 = new JLabel("V2.0");
		sf = font.deriveFont(Font.BOLD, 21f);
		lblNewLabel_3.setFont(sf);
		lblNewLabel_3.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 19;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("/resources/Logo.png")));
		//Set label icon size
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 10;
		gbc_label.gridy = 1;
		
		panel.add(label, gbc_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridwidth = 16;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblNewLabel = new JLabel("amberlife");
		sf2 = font2.deriveFont(148f);
		lblNewLabel.setFont(sf2);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(false);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel(LocalizationService.getWord("username"));
		sf = font.deriveFont(36f);
		lblNewLabel_2.setFont(sf);
		lblNewLabel_2.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 10;
		gbc_lblNewLabel_2.gridy = 6;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		textField = new JTextField();
		textField.addKeyListener(controller);
		textField.setBorder(null);
		textField.setFont(new Font("Verdana", Font.PLAIN, 26));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 10, 5);
		gbc_textField.gridx = 10;
		gbc_textField.gridy = 7;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(LocalizationService.getWord("password"));
		lblNewLabel_1.setForeground(Color.WHITE);
		sf = font.deriveFont(36f);
		lblNewLabel_1.setFont(sf);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 10;
		gbc_lblNewLabel_1.gridy = 9;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(controller);
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 26));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 10;
		gbc_passwordField.gridy = 10;
		panel.add(passwordField, gbc_passwordField);
		
		JButton btnNewButton_1 = new JButton(LocalizationService.getWord("login"));
		btnNewButton_1.setForeground(new Color(255, 255, 255,255));
		btnNewButton_1.setBackground(grey);
		btnNewButton_1.addActionListener(controller);
		btnNewButton_1.addKeyListener(controller);
		btnNewButton_1.setActionCommand("LOGIN");
		btnNewButton_1.setBorderPainted(false);
		sf = font.deriveFont(Font.BOLD, 32f);
		btnNewButton_1.setFont(sf);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 10;
		gbc_btnNewButton_1.gridy = 12;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setHgap(1);
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 10;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 13;
		panel.add(panel_2, gbc_panel_2);
		
		JButton btnNewButton = new JButton("");
		
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setContentAreaFilled(false);
		ImageIcon flag;
		if(LocalizationService.getLanguage() == "GALICIAN") flag = new ImageIcon(getClass().getResource("/resources/Flag.png"));
		else if(LocalizationService.getLanguage() == "ENGLISH") flag = new ImageIcon(getClass().getResource("/resources/FlagSpain.png"));
		else if (LocalizationService.getLanguage() == "SPANISH") flag = new ImageIcon(getClass().getResource("/resources/FlagGalicia.png"));
		else flag = new ImageIcon(getClass().getResource("/resources/Flag.png"));
		btnNewButton.setIcon(flag);
		btnNewButton.setActionCommand("LANGUAGE");
		btnNewButton.addActionListener(controller);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnNewButton);
		
		this.setVisible(true);
	}

}
