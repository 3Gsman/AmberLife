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
import control.AdminCtrl;

public class AdminFr extends JFrame {

	private JPanel contentPane;
	private ActionListener controller;
	private boolean mode = true;



	public AdminFr() {
		
	}
	
	public boolean getMode() {
		return mode;
	}
	
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	
	public void addController(ActionListener a) {
		this.controller = a;
		
	}
	
	public ActionListener getController() {
		return controller;
	}
	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public void initialize(boolean doctors, Vector<? extends User> users, Dimension d) throws IOException {
		this.mode = doctors;
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
		if(d.getHeight()==0 || d.getWidth() == 0) d = new Dimension(600,400);
		this.setMinimumSize(new Dimension(800,600));
		this.setSize(d);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		this.setTitle("Administrator Console");
		
		
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
			
		
		JButton btnNewButton = new JButton("doctors");
		if(mode) {
			sf = font.deriveFont(22f);
			btnNewButton.setFont(sf);
			btnNewButton.setOpaque(true);
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorderPainted(false);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.DARK_GRAY);
		}
		else {
			sf = font.deriveFont(22f);
			btnNewButton.setFont(sf);
			btnNewButton.setOpaque(false);
			btnNewButton.setBorderPainted(false);
			btnNewButton.setContentAreaFilled(false);
			btnNewButton.setBorder(null);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.WHITE);
		}
		btnNewButton.setActionCommand("DOCTORS");
		btnNewButton.addActionListener(controller);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnAssistants = new JButton("assistants");
		if(!mode) {
			sf = font.deriveFont(22f);
			btnAssistants.setFont(sf);
			btnAssistants.setOpaque(true);
			btnAssistants.setBackground(Color.WHITE);
			btnAssistants.setBorderPainted(false);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnAssistants.setFont(sf);
			btnAssistants.setForeground(Color.DARK_GRAY);
		}
		else {
			sf = font.deriveFont(22f);
			btnAssistants.setFont(sf);
			btnAssistants.setOpaque(false);
			btnAssistants.setBorderPainted(false);
			btnAssistants.setContentAreaFilled(false);
			btnAssistants.setBorder(null);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnAssistants.setFont(sf);
			btnAssistants.setForeground(Color.WHITE);
		}
		btnAssistants.setActionCommand("ASSISTANTS");
		btnAssistants.addActionListener(controller);
		GridBagConstraints gbc_btnAssistants = new GridBagConstraints();
		gbc_btnAssistants.fill = GridBagConstraints.BOTH;
		gbc_btnAssistants.gridx = 2;
		gbc_btnAssistants.gridy = 2;
		contentPane.add(btnAssistants, gbc_btnAssistants);
		
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
		panel.add(scrollPane, BorderLayout.CENTER);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.WHITE);	
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 140) );
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		loadUserPane(users, viewport);
		
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
	
	public void loadUserPane(Vector<? extends User> v, JPanel viewport) {		
		
		for(User d : v) viewport.add(new UserPanel(d.getName() + " " + d.getLastname(),d.getId()));
	}

	//For test only
	public void loadUserPane(JPanel viewport) {
		for(int i = 0; i<20;i++) viewport.add(new UserPanel());
	}

}
