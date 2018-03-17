package view.assistant;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.assistant.AssistPatientCtrl;
import model.LocalizationService;
import model.User;
import view.layouts.WrapLayout;
import view.panels.JPanelWithBackground;
import view.panels.MessagePanel;

import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AssistPatientFr extends JFrame {

	private JPanelWithBackground contentPane;
	public AssistPatientCtrl controller;
	
	String name;
	String psurname;
	String id;
	String ssn;
	String user;
	
	public void addController(AssistPatientCtrl a) {
		this.controller = a;
	}
	

	public AssistPatientFr() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return name;
	}
	public String getLastname(){
		return psurname;
	}
	public String getId(){
		return id;
	}
	public String getSsn(){
		return ssn;
	}
	public String getUser(){
		return user;
	}
	
	


	/**
	 * Initialize the frame.
	 * @throws IOException 
	 */
	public void initialize(String pname, String psurname, String id, String ssn, Vector<String> messages, String user) throws IOException {
		this.name = name;
		this.psurname = psurname;
		this.id = id;
		this.ssn = ssn;
		this.user = user;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(controller);
		setBounds(100, 100, 619, 632);
		Dimension d = new Dimension(1280, 820);
		this.setMinimumSize(d);
		this.setSize(d);
		contentPane = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{40, 40, 40, 40, 20, 30, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{80, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 80, 0};
		gbl_contentPane.columnWeights = new double[]{0.5, 1, 1, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		this.setTitle("Patient: " + pname + " " + psurname);
		
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground( new Color(255, 255, 255, 140) );
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridheight = 3;
		gbc_panel_5.gridwidth = 3;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		contentPane.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AssistPatientFr.class.getResource("/resources/user.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground( new Color(255, 255, 255, 140) );
	
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridheight = 15;
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		//Deberia usar un GridBaglayout para solucionar distribucion de tama;o
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{15, 80, 0, 0, 0, 0, 0, 0, 0, 15};
		gbl.rowHeights = new int[]{0, 30, 15, 30, 15, 30, 15, 30, 60, 15};
		gbl.columnWeights = new double[]{0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.2, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.2, Double.MIN_VALUE};
		BoxLayout bl = new BoxLayout(panel_1, BoxLayout.Y_AXIS);
		WrapLayout wl = new WrapLayout();
		panel_1.setLayout(gbl);
		
		JLabel label = new JLabel(" ");
		sf = font.deriveFont(18f);
		label.setFont(sf);
		panel_1.add(label);
		
		
		//Here STARTS data under icon
		
		Font labelfont = font.deriveFont(Font.PLAIN, 24f);
		Font datafont = new Font("Source Code Pro Medium", Font.PLAIN, 26);
		Color greyboxcolor = new Color(80, 77, 77, 255);
		
		
		// First Field
		JPanel field_1 = new JPanel();
		field_1.setOpaque(false);
		FlowLayout flowLayout_f1 = (FlowLayout) field_1.getLayout();
		flowLayout_f1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f1 = new GridBagConstraints();
		gbc_f1.gridheight = 1;
		gbc_f1.gridwidth = 1;
		gbc_f1.fill = GridBagConstraints.BOTH;
		gbc_f1.gridx = 1;
		gbc_f1.gridy = 1;
		panel_1.add(field_1, gbc_f1);
	
		JPanel greybox = new JPanel();
		greybox.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb = (FlowLayout) greybox.getLayout();
		flowLayout_gb.setAlignment(FlowLayout.LEFT);
		field_1.add(greybox);
		
		JLabel lblNewLabel3 = new JLabel(LocalizationService.getWord("name"));
		lblNewLabel3.setForeground(Color.WHITE);
		lblNewLabel3.setFont(labelfont);
		greybox.add(lblNewLabel3);
		
		JLabel lblNewLabel4= new JLabel("  " + pname);
		lblNewLabel4.setForeground(Color.DARK_GRAY);
		lblNewLabel4.setFont(datafont);
		field_1.add(lblNewLabel4);
		
		
		//Second Field
		JPanel field_2 = new JPanel();
		field_2.setOpaque(false);
		FlowLayout flowLayout_f2 = (FlowLayout) field_2.getLayout();
		flowLayout_f2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f2 = new GridBagConstraints();
		gbc_f2.gridheight = 1;
		gbc_f2.gridwidth = 1;
		gbc_f2.fill = GridBagConstraints.BOTH;
		gbc_f2.gridx = 1;
		gbc_f2.gridy = 3;
		panel_1.add(field_2,gbc_f2);
		
		JPanel greybox2 = new JPanel();
		greybox2.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb2 = (FlowLayout) greybox2.getLayout();
		flowLayout_gb2.setAlignment(FlowLayout.LEFT);
		field_2.add(greybox2);
		
		JLabel lblNewLabeldata2= new JLabel(LocalizationService.getWord("surname"));
		lblNewLabeldata2.setForeground(Color.WHITE);
		lblNewLabeldata2.setFont(labelfont);
		greybox2.add(lblNewLabeldata2);
		
		JLabel lblNewLabel5= new JLabel("  " + psurname);
		lblNewLabel5.setForeground(Color.DARK_GRAY);
		lblNewLabel5.setFont(datafont);
		field_2.add(lblNewLabel5);
		
		//Third Field
		JPanel field_3 = new JPanel();
		field_3.setOpaque(false);
		FlowLayout flowLayout_f3 = (FlowLayout) field_3.getLayout();
		flowLayout_f3.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f3 = new GridBagConstraints();
		gbc_f3.gridheight = 1;
		gbc_f3.gridwidth = 1;
		gbc_f3.fill = GridBagConstraints.BOTH;
		gbc_f3.gridx = 1;
		gbc_f3.gridy = 5;
		panel_1.add(field_3,gbc_f3);
		
		JPanel greybox3 = new JPanel();
		greybox3.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb3 = (FlowLayout) greybox3.getLayout();
		flowLayout_gb3.setAlignment(FlowLayout.LEFT);
		field_3.add(greybox3);
		
		JLabel lblNewLabeldata3= new JLabel(LocalizationService.getWord("id"));
		lblNewLabeldata3.setForeground(Color.WHITE);
		lblNewLabeldata3.setFont(labelfont);
		greybox3.add(lblNewLabeldata3);
		
		JLabel lblNewLabel6= new JLabel("  " + id);
		lblNewLabel6.setForeground(Color.DARK_GRAY);
		lblNewLabel6.setFont(datafont);
		field_3.add(lblNewLabel6);
		
		//Fourth Field
		JPanel field_4 = new JPanel();
		field_4.setOpaque(false);
		FlowLayout flowLayout_f4 = (FlowLayout) field_4.getLayout();
		flowLayout_f4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f4 = new GridBagConstraints();
		gbc_f4.gridheight = 1;
		gbc_f4.gridwidth = 1;
		gbc_f4.fill = GridBagConstraints.BOTH;
		gbc_f4.gridx = 1;
		gbc_f4.gridy = 7;
		panel_1.add(field_4,gbc_f4);
				
		JPanel greybox4 = new JPanel();
		greybox4.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb4 = (FlowLayout) greybox3.getLayout();
		flowLayout_gb4.setAlignment(FlowLayout.LEFT);
		field_4.add(greybox4);
				
		JLabel lblNewLabeldata4= new JLabel(LocalizationService.getWord("ssn"));
		lblNewLabeldata4.setForeground(Color.WHITE);
		lblNewLabeldata4.setFont(labelfont);
		greybox4.add(lblNewLabeldata4);
				
		JLabel lblNewLabel8= new JLabel("  " + ssn);
		lblNewLabel8.setForeground(Color.DARK_GRAY);
		lblNewLabel8.setFont(datafont);
		field_4.add(lblNewLabel8);
				
		//Here ENDS data under icon
		
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 17;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
	
		JLabel lblNewLabel = new JLabel(" " + LocalizationService.getWord("user") + ": "+ user);
		sf = font.deriveFont(24f);
		lblNewLabel.setFont(sf);
		lblNewLabel.setForeground(new Color(255, 255, 255, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel msg = new JPanel();
		msg.setLayout(new BorderLayout());
		msg.setBackground(new Color(255,255,255,140));
		GridBagConstraints gbc_panel_msg = new GridBagConstraints();
		gbc_panel_msg.insets = new Insets(0, 0, 5, 5);
		gbc_panel_msg.gridheight = 16;
		gbc_panel_msg.gridwidth = 15;
		gbc_panel_msg.fill = GridBagConstraints.BOTH;
		gbc_panel_msg.gridx = 4;
		gbc_panel_msg.gridy = 1;
		contentPane.add(msg, gbc_panel_msg);	
		
		JScrollPane panel_2 = new JScrollPane();
		panel_2.setBackground( new Color(255, 255, 255, 140) );
		panel_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.setOpaque(false);
		panel_2.getVerticalScrollBar().setUnitIncrement(18);
		panel_2.getViewport().setOpaque(false);
		panel_2.setViewportBorder(null);
		panel_2.setBorder(null);
		msg.add(panel_2,BorderLayout.CENTER);
		
		JPanel new_msg = new JPanel();
		new_msg.setLayout(new FlowLayout(FlowLayout.RIGHT));
		new_msg.setOpaque(false);
		msg.add(new_msg,BorderLayout.PAGE_END);
		
		JButton btnNewButton2 = new JButton("");
		ImageIcon reply = new ImageIcon(getClass().getResource("/resources/MessageButton.png"));
		btnNewButton2.setActionCommand("NEWMESSAGE");
		btnNewButton2.addActionListener(controller);
		btnNewButton2.setIcon(reply);
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBorder(null);
		btnNewButton2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton2.setContentAreaFilled(false);
		new_msg.add(btnNewButton2);
		
		
		//If there's messages
		
		if(messages.equals(null)) {
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			jp.setOpaque(false);
			JLabel lblNewLabel_2 = new JLabel(LocalizationService.getWord("nomessages"));
			sf = font.deriveFont(28f);
			lblNewLabel_2.setFont(sf);
			lblNewLabel_2.setForeground(new Color(80, 77, 77, 255));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			jp.add(lblNewLabel_2, BorderLayout.CENTER);
			panel_2.setViewportView(jp);
		}else {
			JPanel jp = new JPanel();
			jp.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
			jp.setOpaque(false);
			for(String s : messages) {
				MessagePanel mp = new MessagePanel(controller, new User("234553X", "John", "Doe"), "24-3-18", s);
				jp.add(mp);
			}
			panel_2.setViewportView(jp);
			/*for(int i = 0; i < messages.size(); i++) {	
				JPanel panelMessage = new JPanel();
				panelMessage.setBackground(Color.WHITE);
				panel_2.add(panelMessage);
				JLabel lblNewLabel_2 = new JLabel(messages.get(i));
				sf = font.deriveFont(28f);
				lblNewLabel_2.setFont(sf);
				lblNewLabel_2.setForeground(new Color(80, 77, 77, 255));
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel_2.add(lblNewLabel_2, BorderLayout.CENTER);
			}*/
		}
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setActionCommand("BACK");
		btnNewButton.addActionListener(controller);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resources/Backbutton.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 18;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground( new Color(255, 255, 255, 140) );
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 0);
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 18;
		contentPane.add(panel_3, gbc_panel_3);
		
		JButton btnMeasure = new JButton(LocalizationService.getWord("measure"));
		btnMeasure.addActionListener(controller);
		btnMeasure.setActionCommand("MEASURE");
		sf = font.deriveFont(32f);
		btnMeasure.setBorderPainted(false);
		btnMeasure.setFont(sf);
		btnMeasure.setForeground(new Color(0, 0, 0));
		btnMeasure.setBackground(new Color(80, 77, 77, 255));
		btnMeasure.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnMeasure = new GridBagConstraints();
		gbc_btnMeasure.fill = GridBagConstraints.BOTH;
		gbc_btnMeasure.gridwidth = 5;
		gbc_btnMeasure.insets = new Insets(0, 0, 5, 5);
		gbc_btnMeasure.gridx = 14;
		gbc_btnMeasure.gridy = 18;
		contentPane.add(btnMeasure, gbc_btnMeasure);
	}

}