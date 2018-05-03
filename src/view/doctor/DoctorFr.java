package view.doctor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MainCtrl;
import control.doctor.DoctorCtrl;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import model.*;
import view.layouts.WrapLayout;
import view.panels.AlphaContainer;
import view.panels.JPanelWithBackground;
import view.panels.PatientPanel;

@SuppressWarnings("serial")
public class DoctorFr extends JPanelWithBackground {

	public DoctorFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}
	private DoctorCtrl controller;
	private boolean mode;
	public JTextField textField = new JTextField();
	private JPanel box = new JPanel();
	private JPanel searchpanel = new JPanel();


	
	public String getText() {
		return textField.getText();
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
		box.removeAll();
		box.add(searchpanel,BorderLayout.PAGE_START);
		Vector<Patient> patients = controller.getDoctor().getPatientlist();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(255, 255, 255, 140) );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 255) );
		viewport.setOpaque(false);
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		String search = textField.getText();
		if(search != null)
			for(Patient d : patients) {	
				if ((d.getName() + " " + d.getLastname()).toLowerCase().contains(search.toLowerCase()) ||	
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
		setBounds(100, 100, 850, 722);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{40, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_this.rowHeights = new int[]{60, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_this.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_this);
		Dimension d = new Dimension(600,400);
		this.setMinimumSize(new Dimension(800,600));
		this.setSize(d);
		
		
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
		this.add(paneluser,gbc_paneluser);
		
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
			//btnNewButton.setContentAreaFilled(false);
			btnNewButton.setBorder(null);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.DARK_GRAY);
			
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		
		this.add(btnNewButton, gbc_btnNewButton);

		box.setBackground( new Color(255,255,255,140));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 14;
		gbc_panel.gridwidth = 21;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		this.add(new AlphaContainer(box), gbc_panel);
		box.setLayout(new BorderLayout(0, 0));

		
		initializeList();
		
		//Inicializar barra de busqueda aqui
		
		//searchpanel.setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40,120, 120};
		gridBagLayout.rowHeights = new int[]{40};
		gridBagLayout.columnWeights = new double[]{0.0, 0.2, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		searchpanel.setLayout(gridBagLayout);
		searchpanel.setBackground(Color.WHITE);
		box.add(searchpanel,BorderLayout.PAGE_START);
		
		//Crear "Boton"
		JLabel icon = new JLabel("");
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(getClass().getResource("/resources/searchicon.png")));
		//Set label icon size
		GridBagConstraints gbc_icon = new GridBagConstraints();
		gbc_icon.fill = GridBagConstraints.BOTH;
		gbc_icon.gridx = 0;
		gbc_icon.gridy = 0;
		searchpanel.add(icon,gbc_icon);
		
		//Crear Textfield
		textField.setBorder(null);
		textField.setBackground(new Color(245,245,245,255));
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
		this.add(btnLogout, gbc_btnLogout);

		this.setVisible(true);
		textField.requestFocus();
	
	}

}
