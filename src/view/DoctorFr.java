package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import model.*;
import control.DoctorCtrl;

public class DoctorFr extends JFrame {

	private JPanel contentPane;
	private DoctorCtrl controller;
	private boolean mode;
	public JTextField textField;



	public DoctorFr() {
		
	}
	
	public boolean getMode() {
		return mode;
	}
	
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	public String getID() {
		return textField.getText();
	}
	
	public void addController(DoctorCtrl a) {
		this.controller = a;
		
	}
	
	public DoctorCtrl getController() {
		return controller;
	}
	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public void initialize(Vector<Patient> patients) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 722);
		contentPane = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{40, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_contentPane.rowHeights = new int[]{60, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		Dimension d = new Dimension(600,400);
		this.setMinimumSize(new Dimension(800,600));
		this.setSize(d);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		this.setTitle(controller.getDoctor().getName() + " " +controller.getDoctor().getLastname());
		
		
		//Get PROMETHEUS font
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
			
		
		JButton btnNewButton = new JButton(LocalizationService.getWord("patients"));
			sf = font.deriveFont(22f);
			btnNewButton.setFont(sf);
			btnNewButton.setOpaque(true);
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorderPainted(false);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.DARK_GRAY);
			
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setFont(new Font("Verdana", Font.PLAIN, 26));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_textField.fill = GridBagConstraints.BOTH;
		
		gbc_textField.gridx = 15;
		gbc_textField.gridy = 2;
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridheight = gbc_btnNewButton.gridheight;
		textField.setColumns(10);
		contentPane.add(textField, gbc_textField);
		
		JButton btnSearchButton = new JButton(LocalizationService.getWord("search"));
		sf = font.deriveFont(22f);
		btnSearchButton.setFont(sf);
		btnSearchButton.setOpaque(true);
		btnSearchButton.setBackground(Color.WHITE);
		btnSearchButton.setBorderPainted(false);
		sf = font.deriveFont(Font.BOLD, 28f);
		btnSearchButton.setFont(sf);
		btnSearchButton.setForeground(Color.DARK_GRAY);
		btnSearchButton.setActionCommand("SEARCH");
		btnSearchButton.addActionListener(controller);
		
		
		GridBagConstraints gbc_btnSearchButton = new GridBagConstraints();
		gbc_btnSearchButton.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnSearchButton.fill = GridBagConstraints.BOTH;
		gbc_btnSearchButton.gridx = 14;
		gbc_btnSearchButton.gridy = 2;
		contentPane.add(btnSearchButton, gbc_btnSearchButton);
		
		
		JPanel panel = new JPanel();
		panel.setBackground( new Color(0, 0, 0, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 14;
		gbc_panel.gridwidth = 21;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(0, 0, 0, 0) );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.WHITE);	
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 255) );
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		loadUserPane(patients, viewport, controller);
		
		JButton btnNewButton2 = new JButton("");
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBorder(null);
		btnNewButton2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setActionCommand("NEW");
		btnNewButton2.addActionListener(controller);
		btnNewButton2.setIcon(new ImageIcon(getClass().getResource("/resources/addbutton.png")));
		viewport.add(btnNewButton2);
		
		scrollPane.setViewportView(viewport);
		
		JButton btnLogout = new JButton("");
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(null);
		btnLogout.setMargin(new Insets(0, 0, 0, 0));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setActionCommand("BACK");
		btnLogout.addActionListener(controller);
		btnLogout.setIcon(new ImageIcon(getClass().getResource("/resources/Backbutton.png")));
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.gridx = 0;
		gbc_btnLogout.gridy = 18;
		contentPane.add(btnLogout, gbc_btnLogout);
		
		

		
		

		this.setVisible(true);
	
	}
	
	
	public void loadUserPane(Vector<Patient> v, JPanel viewport, DoctorCtrl con) {		
		
		for(Patient d : v) viewport.add(new PatientPanel(d, con));
	}

	//For test only
	public void loadUserPane(JPanel viewport) {
		for(int i = 0; i<20;i++) viewport.add(new UserPanel());
	}

}
