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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFr frame = new AdminFr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	public void initialize(boolean doctors, Vector<? extends User> users) throws IOException {
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
			sf = font.deriveFont(Font.BOLD, 28f);
			btnAssistants.setFont(sf);
			btnAssistants.setForeground(Color.WHITE);
		}
		btnNewButton.setActionCommand("ASSISTANTS");
		btnNewButton.addActionListener(controller);
		GridBagConstraints gbc_btnAssistants = new GridBagConstraints();
		gbc_btnAssistants.fill = GridBagConstraints.BOTH;
		gbc_btnAssistants.gridx = 2;
		gbc_btnAssistants.gridy = 2;
		contentPane.add(btnAssistants, gbc_btnAssistants);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.WHITE);	
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		loadUserPane(users, viewport);
		
		scrollPane.setViewportView(viewport);
		
		

	}
	
	public void loadUserPane(Vector<? extends User> v, JPanel viewport) {		
		
		for(User d : v) viewport.add(new UserPanel(d.getName(),d.getId()));
	}

	//For test only
	public void loadUserPane(JPanel viewport) {
		for(int i = 0; i<20;i++) viewport.add(new UserPanel());
	}

}
