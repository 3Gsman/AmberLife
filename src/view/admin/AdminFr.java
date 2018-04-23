package view.admin;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.admin.AdminCtrl;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import model.*;
import view.layouts.WrapLayout;
import view.panels.AlphaContainer;
import view.panels.JPanelWithBackground;
import view.panels.UserPanel;

@SuppressWarnings("serial")
public class AdminFr extends JPanel {

	private JPanel contentPane;
	private AdminCtrl controller;
	private boolean mode;
	private JButton btnAssistants = new JButton(LocalizationService.getWord("assistants"));
	private JButton btnNewButton = new JButton(LocalizationService.getWord("doctors"));
	private JPanel display = new JPanel();
	JPanel jpanel2 = new JPanel();
	JPanel jpanel = new JPanel();

	public AdminFr() {
		
	}
	
	public boolean getMode() {
		return mode;
	}
	
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	
	public void addController(AdminCtrl a) {
		this.controller = a;
		
	}
	
	public ActionListener getController() {
		return controller;
	}
	
	
	public void initializeDoctors(Vector<? extends User> users) {
		display.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(255, 255, 255, 140) );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		//Scroll speed here
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		display.add(scrollPane, BorderLayout.CENTER);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.WHITE);	
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 255) );
		viewport.setOpaque(false);
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		loadUserPane(users, viewport);
		
		JButton btnNewButton2 = new JButton("");
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBorder(null);
		btnNewButton2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setActionCommand("NEWDOCTOR");
		btnNewButton2.addActionListener(controller);
		btnNewButton2.setIcon(new ImageIcon(getClass().getResource("/resources/addbutton.png")));
		viewport.add(btnNewButton2);
		
		scrollPane.setViewportView(viewport);
		mode = true;
	}
	
	public void initializeAssistants( Vector<? extends User> users) {
		display.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(255, 255, 255, 140) );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		//Scroll speed here
		scrollPane.getVerticalScrollBar().setUnitIncrement(18);
		display.add(scrollPane, BorderLayout.CENTER);
		
		//Viewport
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.WHITE);	
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		viewport.setBackground( new Color(255, 255, 255, 255) );
		viewport.setOpaque(false);
		//Contents go here
		//Si el bool es true, se inicia con doctores, si no, con assistants
		loadUserPane(users, viewport);
		
		JButton btnNewButton2 = new JButton("");
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBorder(null);
		btnNewButton2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setActionCommand("NEWASSIST");
		btnNewButton2.addActionListener(controller);
		btnNewButton2.setIcon(new ImageIcon(getClass().getResource("/resources/addbutton.png")));
		viewport.add(btnNewButton2);
		
		
		scrollPane.setViewportView(viewport);
		mode = false;
	}
	
	public void setButtons() {
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
		Color grey = new Color(80, 77, 77, 255);
		
		//Set Assistants button
		 if(!mode) {
			jpanel2.setBackground(new Color(255,255,255,255));
			jpanel2.setOpaque(true);
			btnAssistants.setBorderPainted(false);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnAssistants.setFont(sf);
			btnAssistants.setForeground(grey);
		}
		else {
			jpanel2.setBackground(new Color(255,255,255,0));
			jpanel2.setOpaque(false);
			btnAssistants.setBorderPainted(false);
			btnAssistants.setContentAreaFilled(false);
			btnAssistants.setBorder(null);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnAssistants.setFont(sf);
			btnAssistants.setForeground(Color.WHITE);
		}
		 //Set Doctors Button
		if(mode) {
			jpanel.setBackground(new Color(255,255,255,255));
			jpanel.setOpaque(true);
			btnNewButton.setBorderPainted(false);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(grey);
			}
		else {
			jpanel.setBackground(new Color(255,255,255,0));
			jpanel.setOpaque(false);
			btnNewButton.setBorderPainted(false);
			btnNewButton.setContentAreaFilled(false);
			btnNewButton.setBorder(null);
			sf = font.deriveFont(Font.BOLD, 28f);
			btnNewButton.setFont(sf);
			btnNewButton.setForeground(Color.WHITE);
			}
	}
	
	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public void initialize(boolean doctors, Vector<? extends User> users, Dimension d) throws IOException {
		this.mode = doctors;
		setBounds(100, 100, 850, 722);
		contentPane = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{40, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_contentPane.rowHeights = new int[]{60, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		if(d.getHeight()==0 || d.getWidth() == 0) d = new Dimension(600,400);
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
			
		//Set buttons
		
		jpanel.setBackground(new Color(255,255,255,255));
		jpanel.setLayout(new BorderLayout());
		btnNewButton.setActionCommand("DOCTORS");
		btnNewButton.addActionListener(controller);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		jpanel.add(btnNewButton,BorderLayout.CENTER);
		contentPane.add(jpanel, gbc_btnNewButton);
	
		jpanel2.setBackground(new Color(255,255,255,255));
		jpanel2.setOpaque(false);
		jpanel2.setLayout(new BorderLayout());
		btnAssistants.setActionCommand("ASSISTANTS");
		btnAssistants.addActionListener(controller);
		btnAssistants.setOpaque(false);
		btnAssistants.setContentAreaFilled(false);
		GridBagConstraints gbc_btnAssistants = new GridBagConstraints();
		gbc_btnAssistants.fill = GridBagConstraints.BOTH;
		gbc_btnAssistants.gridx = 2;
		gbc_btnAssistants.gridy = 2;
		jpanel2.add(btnAssistants,BorderLayout.CENTER);
		contentPane.add(jpanel2, gbc_btnAssistants);
		
		setButtons();
		
		//Set buttons done
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 14;
		gbc_panel.gridwidth = 21;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		display.setBackground(new Color(255,255,255,140));
		display.setLayout(new BorderLayout());
		initializeDoctors(users);
		contentPane.add(new AlphaContainer(display), gbc_panel);	
		
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
