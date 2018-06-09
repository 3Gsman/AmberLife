package view.assistant;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.assistant.AssistPatientCtrl;
import model.DBManagement;
import model.LocalizationService;
import model.Message;
import model.User;
import view.layouts.WrapLayout;
import view.panels.AlphaContainer;
import view.panels.JPanelWithBackground;
import view.panels.MessagePanel;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

@SuppressWarnings("serial")
public class AssistPatientFr extends JPanelWithBackground {

	public AssistPatientFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}


	public AssistPatientCtrl controller;
	public JPanel messageboard;
	private MessagePanel mp;
	private Vector<Message> messages;
	
	private String name;
	private String psurname;
	private String id;
	private String ssn;
	private String user;
	
	public void addController(AssistPatientCtrl a) {
		this.controller = a;
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void initialize(String pname, String psurname, String id, String ssn, String user) throws IOException, ClassNotFoundException, SQLException {
		this.name = pname;
		this.psurname = psurname;
		this.id = id;
		this.ssn = ssn;
		this.user = user;
		messages = DBManagement.readMessages(id);
		
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{40, 40, 40, 40, 20, 30, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0};
		gbl_this.rowHeights = new int[]{80, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 80, 0};
		gbl_this.columnWeights = new double[]{0.5, 1, 1, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_this);
		
		//Get PROMETHEUS font
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground( new Color(255, 255, 255, 140) );
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridheight = 3;
		gbc_panel_5.gridwidth = 3;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		this.add(panel_5, gbc_panel_5);
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
		this.add(panel_1, gbc_panel_1);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{15, 80, 0, 0, 0, 0, 0, 0, 0, 15};
		gbl.rowHeights = new int[]{0, 30, 15, 30, 15, 30, 15, 30, 60, 15};
		gbl.columnWeights = new double[]{0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.2, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.2, Double.MIN_VALUE};
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
		this.add(panel, gbc_panel);
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
		this.add(new AlphaContainer(msg), gbc_panel_msg);	
		
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
			messageboard = new JPanel();
			messageboard.setLayout(new BorderLayout());
			messageboard.setOpaque(false);
			JLabel lblNewLabel_2 = new JLabel(LocalizationService.getWord("nomessages"));
			sf = font.deriveFont(28f);
			lblNewLabel_2.setFont(sf);
			lblNewLabel_2.setForeground(new Color(80, 77, 77, 255));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			messageboard.add(lblNewLabel_2, BorderLayout.CENTER);
			panel_2.setViewportView(messageboard);
		}else {
			messageboard = new JPanel();
			messageboard.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
			messageboard.setOpaque(false);
			refreshMessages(messageboard);
			panel_2.setViewportView(messageboard);
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
		this.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground( new Color(255, 255, 255, 140) );
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 0);
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 18;
		this.add(panel_3, gbc_panel_3);
		
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
		this.add(btnMeasure, gbc_btnMeasure);
	}



	public void refreshMessages(JPanel jp) throws ClassNotFoundException, SQLException {
		jp.removeAll();
		messages = DBManagement.readMessages(id);
		for(Message s : messages) {
			try {
				mp = new MessagePanel(controller, new User(s.getAuthorID(), s.getAuthorname(), s.getAuthorsurname()), s);
				jp.add(mp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.setVisible(true);
		this.validate();
	}


}