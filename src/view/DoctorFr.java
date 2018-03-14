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
	public JTextField textField = new JTextField();
	private JPanel box = new JPanel();
	private JPanel searchpanel = new JPanel();


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
	
	public void initializeList() {
		//Get PROMETHEUS font
		box.removeAll();
		box.add(searchpanel,BorderLayout.PAGE_START);
		Vector<Patient> patients = controller.getDoctor().getPatientlist();
		java.io.InputStream is = getClass().getResourceAsStream("/resources/PROMETHEUS.ttf");
		Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
	
		Font sf = font; // will use sf to change the style;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(0, 0, 0, 0) );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		scrollPane.getViewport().setOpaque(false);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 255) );
		viewport.setOpaque(true);
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		String search = textField.getText();
		if(search != null)
			for(Patient d : patients) {	
				if (d.getName().toLowerCase().contains(search.toLowerCase()) ||
					d.getLastname().toLowerCase().contains(search.toLowerCase()) ||	
					d.getId().toLowerCase().contains(search.toLowerCase()) ||
					d.getSsn().toLowerCase().contains(search.toLowerCase())){
						viewport.add(new PatientPanel(d, controller));
				}	
		}	
		else for(Patient d : patients) viewport.add(new PatientPanel(d, controller));
				
		//str1.toLowerCase().contains(str2.toLowerCase())
		
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
		
		box.add(scrollPane,BorderLayout.CENTER);
		textField.requestFocus();
	}
	
	
	
	public DoctorCtrl getController() {
		return controller;
	}
	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		Vector<Patient> patients = controller.getDoctor().getPatientlist();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(controller);
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
		
		//User label
		JPanel paneluser = new JPanel();
		paneluser.setLayout(new FlowLayout(FlowLayout.RIGHT));
		paneluser.setOpaque(false);
		GridBagConstraints gbc_paneluser = new GridBagConstraints();
		gbc_paneluser.gridwidth = 26;
		gbc_paneluser.gridheight = 1;
		gbc_paneluser.insets = new Insets(0, 0, 5, 5);
		gbc_paneluser.fill = GridBagConstraints.BOTH;
		gbc_paneluser.gridx = 0;
		gbc_paneluser.gridy = 0;
		contentPane.add(paneluser,gbc_paneluser);
		
		JLabel lblUser = new JLabel(" " + LocalizationService.getWord("user") + ": " 
							+ controller.getDoctor().getName() + " " + controller.getDoctor().getLastname());
		sf = font.deriveFont(22f);
		lblUser.setForeground(Color.WHITE);
		lblUser.setOpaque(false);
		lblUser.setFont(sf);
		paneluser.add(lblUser);
			
		
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
		/*
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
		*/

		box.setBackground( new Color(255,255,255,140));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 14;
		gbc_panel.gridwidth = 21;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		contentPane.add(box, gbc_panel);
		box.setLayout(new BorderLayout(0, 0));

		
		
		initializeList();
		
		//Inicializar barra de busqueda aqui
		
		searchpanel.setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40,120, 120};
		gridBagLayout.rowHeights = new int[]{40};
		gridBagLayout.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		searchpanel.setLayout(gridBagLayout);
		searchpanel.setOpaque(false);
		box.add(searchpanel,BorderLayout.PAGE_START);
		
		//Crear "Boton"
		//Crear Textfield
		textField.setBorder(null);
		textField.setFont(new Font("Source Code Pro Medium", Font.ITALIC, 16));
		textField.addKeyListener(controller);
		GridBagConstraints gbc_textfield = new GridBagConstraints();
		gbc_textfield.fill = GridBagConstraints.BOTH;
		gbc_textfield.gridx = 1;
		gbc_textfield.gridy = 0;
		searchpanel.add(textField,gbc_textfield);
		
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

}
