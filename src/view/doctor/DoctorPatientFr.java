package view.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.doctor.DoctorPatientCtrl;
import model.DBManagement;
import model.ECG;
import model.FileManagement;
import model.LocalizationService;
import model.Message;
import model.User;
import view.assistant.AssistPatientFr;
import view.layouts.WrapLayout;
import view.panels.AlphaContainer;
import view.panels.EcgPanel;
import view.panels.JPanelWithBackground;
import view.panels.MessagePanel;

@SuppressWarnings("serial")
public class DoctorPatientFr extends JPanelWithBackground {
	
	private Font font;


	public DoctorPatientFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public JPanel messagePanel = new JPanel(new BorderLayout());
	public DoctorPatientCtrl controller;
	private String mode = "ECGS";
	
	
	public void addController(DoctorPatientCtrl a) {
		this.controller = a;
	}
	
	public DoctorPatientCtrl getController() {
		return controller;
	}
	
	
	//DOESN'T REALLY WORK
	private JPanel initializeMessages() throws IOException, ClassNotFoundException, SQLException {
		
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

			JPanel jp2 = new JPanel(new BorderLayout());
			jp2.setBackground(new Color(255,255,255,140));
			JScrollPane panel_2 = new JScrollPane();
			panel_2.setBackground( new Color(255, 255, 255, 140) );
			panel_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel_2.setOpaque(false);
			panel_2.getVerticalScrollBar().setUnitIncrement(18);
			panel_2.getViewport().setOpaque(false);
			panel_2.setViewportBorder(null);
			panel_2.setBorder(null);
			jp2.add(panel_2,BorderLayout.CENTER);
				
			JPanel new_msg = new JPanel();
			new_msg.setLayout(new FlowLayout(FlowLayout.RIGHT));
			new_msg.setOpaque(false);
			jp2.add(new_msg,BorderLayout.PAGE_END);
			
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
			
			//controller.getPatient().getID()
			
			
			
			Vector<Message> messages = DBManagement.readMessages(controller.getPatient().getId());
			
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
				for(Message s : messages) {
					MessagePanel mp = new MessagePanel(controller, new User(s.getAuthorID(), s.getAuthorname(), 
										s.getAuthorsurname()), s);
					jp.add(mp);
				}
				panel_2.setViewportView(jp);
			}
			return jp2;
	}
	
	private JPanel initializeECG() throws IOException {
			JPanel jp = new JPanel();
			JScrollPane sp = new JScrollPane();
			sp.setBackground( new Color(0, 0, 0, 0) );
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setOpaque(false);
			sp.getVerticalScrollBar().setUnitIncrement(18);
			JPanel viewport = new JPanel();
			viewport.setOpaque(false);
			viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
			loadECGPanel(controller.getPatient().getECGs(),viewport);
			sp.setViewportView(viewport);
			sp.getViewport().setOpaque(false);
			sp.setViewportBorder(null);
			sp.setBorder(null);
			mode = "ECGS";
			jp.setOpaque(false);
			jp.setLayout(new BorderLayout(0, 0));
			jp.add(sp, BorderLayout.CENTER);
			return jp;
	}
	
	public void setModeECG() throws IOException {
		if(mode != "ECGS") {
			//this.getthis().remove(messagePanel);
			messagePanel.removeAll();
			messagePanel.add(initializeECG(), BorderLayout.CENTER);
			this.repaint();
			mode = "ECGS";
		}
	}
	
	public void setModeMessages() throws IOException, ClassNotFoundException, SQLException {
		if(mode != "MESSAGES") {
			//this.getthis().remove(messagePanel);
			messagePanel.removeAll();
			messagePanel.add(new AlphaContainer(initializeMessages()), BorderLayout.CENTER);
			this.repaint();
			mode = "MESSAGES";
		}
	}
	
	public void loadECGPanel(Vector<ECG> v, JPanel viewport) {		
		if(v!= null) {
			for(ECG e : v)
				try {
					viewport.add(new EcgPanel(e,this.controller));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else {
			JLabel noecgs = new JLabel("Patient has no ECGs to display");
			System.out.println("NULL ECG VECTOR");	
			Font sf = font.deriveFont(28f);
			noecgs.setFont(sf);
			noecgs.setForeground( new Color(255, 255, 255, 140) );
			viewport.add(noecgs);
			}
		
	}
	
	/**
	 * Initialize the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
	
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{40, 40, 40, 40, 20, 30, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 360, 0, 0, 0, 0};
		gbl_this.rowHeights = new int[]{80, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 80, 0};
		gbl_this.columnWeights = new double[]{0.1, 0.1, 0.1, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 3.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, Double.MIN_VALUE};
		this.setLayout(gbl_this);

		//Get PROMETHEUS font
		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		font = new Font("Verdana", Font.PLAIN, 28); //Default font;
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
		gbl.columnWidths = new int[]{15, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15};
		gbl.rowHeights = new int[]{0, 30, 15, 30, 15, 30, 15, 30, 15, 30, 15, 30, 15, 30, 15, 0, 0, 20};
		gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.2, Double.MIN_VALUE};
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
		
		JLabel lblNewLabel4= new JLabel("  " + controller.getPatient().getName());
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
		
		JLabel lblNewLabel5= new JLabel("  " + controller.getPatient().getLastname());
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
		
		JLabel lblNewLabel6= new JLabel("  " + controller.getPatient().getId());
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
		FlowLayout flowLayout_gb4 = (FlowLayout) greybox4.getLayout();
		flowLayout_gb4.setAlignment(FlowLayout.LEFT);
		field_4.add(greybox4);
						
		JLabel lblNewLabeldata4= new JLabel(LocalizationService.getWord("ssn"));
		lblNewLabeldata4.setForeground(Color.WHITE);
		lblNewLabeldata4.setFont(labelfont);
		greybox4.add(lblNewLabeldata4);
						
		JLabel lblNewLabel8= new JLabel("  " + controller.getPatient().getSsn());
		lblNewLabel8.setForeground(Color.DARK_GRAY);
		lblNewLabel8.setFont(datafont);
		field_4.add(lblNewLabel8);
		
		//Fifth Field
		JPanel field_5 = new JPanel();
		field_5.setOpaque(false);
		FlowLayout flowLayout_f5 = (FlowLayout) field_5.getLayout();
		flowLayout_f5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f5 = new GridBagConstraints();
		gbc_f5.gridheight = 1;
		gbc_f5.gridwidth = 1;
		gbc_f5.fill = GridBagConstraints.BOTH;
		gbc_f5.gridx = 1;
		gbc_f5.gridy = 9;
		panel_1.add(field_5,gbc_f5);
								
		JPanel greybox5 = new JPanel();
		greybox5.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb5 = (FlowLayout) greybox5.getLayout();
		flowLayout_gb5.setAlignment(FlowLayout.LEFT);
		field_5.add(greybox5);
								
		JLabel lblNewLabeldata5= new JLabel(LocalizationService.getWord("sex"));
		lblNewLabeldata5.setForeground(Color.WHITE);
		lblNewLabeldata5.setFont(labelfont);
		greybox5.add(lblNewLabeldata5);
								
		JLabel lblNewLabel9= new JLabel("  " + controller.getPatient().getGender().split(".png")[0]);
		lblNewLabel9.setForeground(Color.DARK_GRAY);
		lblNewLabel9.setFont(datafont);
		field_5.add(lblNewLabel9);

		JPanel field_6 = new JPanel();
		field_6.setOpaque(false);
		FlowLayout flowLayout_f6 = (FlowLayout) field_6.getLayout();
		flowLayout_f6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f6 = new GridBagConstraints();
		gbc_f6.gridheight = 1;
		gbc_f6.gridwidth = 1;
		gbc_f6.fill = GridBagConstraints.BOTH;
		gbc_f6.gridx = 1;
		gbc_f6.gridy = 11;
		panel_1.add(field_6,gbc_f6);
		
		JPanel greybox6 = new JPanel();
		greybox6.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb6 = (FlowLayout) greybox6.getLayout();
		flowLayout_gb6.setAlignment(FlowLayout.LEFT);
		field_6.add(greybox6);
								
		JLabel lblNewLabeldata6= new JLabel(LocalizationService.getWord("municipality"));
		lblNewLabeldata6.setForeground(Color.WHITE);
		lblNewLabeldata6.setFont(labelfont);
		greybox6.add(lblNewLabeldata6);
								
		JLabel lblNewLabel10= new JLabel("  " + controller.getPatient().getMunicipality());
		lblNewLabel10.setForeground(Color.DARK_GRAY);
		lblNewLabel10.setFont(datafont);
		field_6.add(lblNewLabel10);
		
		JPanel field_7 = new JPanel();
		field_7.setOpaque(false);
		FlowLayout flowLayout_f7 = (FlowLayout) field_7.getLayout();
		flowLayout_f7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f7 = new GridBagConstraints();
		gbc_f7.gridheight = 1;
		gbc_f7.gridwidth = 1;
		gbc_f7.fill = GridBagConstraints.BOTH;
		gbc_f7.gridx = 1;
		gbc_f7.gridy = 13;
		panel_1.add(field_7,gbc_f7);
		
		JPanel greybox7 = new JPanel();
		greybox7.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb7 = (FlowLayout) greybox7.getLayout();
		flowLayout_gb7.setAlignment(FlowLayout.LEFT);
		field_7.add(greybox7);
								
		JLabel lblNewLabeldata7= new JLabel(LocalizationService.getWord("address"));
		lblNewLabeldata7.setForeground(Color.WHITE);
		lblNewLabeldata7.setFont(labelfont);
		greybox7.add(lblNewLabeldata7);
								
		JLabel lblNewLabel11= new JLabel("  " + controller.getPatient().getAddress());
		lblNewLabel11.setForeground(Color.DARK_GRAY);
		lblNewLabel11.setFont(datafont);
		field_7.add(lblNewLabel11);
		
		JPanel field_8 = new JPanel();
		field_8.setOpaque(false);
		FlowLayout flowLayout_f8 = (FlowLayout) field_8.getLayout();
		flowLayout_f8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_f8 = new GridBagConstraints();
		gbc_f8.gridheight = 1;
		gbc_f8.gridwidth = 1;
		gbc_f8.fill = GridBagConstraints.BOTH;
		gbc_f8.gridx = 1;
		gbc_f8.gridy = 15;
		panel_1.add(field_8,gbc_f8);
		
		JPanel greybox8 = new JPanel();
		greybox8.setBackground(greyboxcolor);
		FlowLayout flowLayout_gb8 = (FlowLayout) greybox8.getLayout();
		flowLayout_gb8.setAlignment(FlowLayout.LEFT);
		field_8.add(greybox8);
								
		JLabel lblNewLabeldata8= new JLabel(LocalizationService.getWord("status"));
		lblNewLabeldata8.setForeground(Color.WHITE);
		lblNewLabeldata8.setFont(labelfont);
		greybox8.add(lblNewLabeldata8);
								
		JLabel lblNewLabel12= new JLabel("  " + controller.getPatient().getStatus());
		lblNewLabel12.setForeground(Color.DARK_GRAY);
		lblNewLabel12.setFont(datafont);
		field_8.add(lblNewLabel12);
		
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
	
		JLabel lblNewLabel = new JLabel(" " + LocalizationService.getWord("user") + ": "+ controller.getDoctor().getName() + controller.getDoctor().getLastname().split(" ")[0]);
		sf = font.deriveFont(24f);
		lblNewLabel.setFont(sf);
		lblNewLabel.setForeground(new Color(255, 255, 255, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.EAST);
			
		
		/*messagePanel = new JPanel();
		messagePanel.setBackground( new Color(255, 255, 255, 100));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridheight = 17;
		gbc_panel_2.gridwidth = 15;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 1;
		
		messagePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel(LocalizationService.getWord("nomessages"));
		sf = font.deriveFont(28f);
		lblNewLabel_2.setFont(sf);
		lblNewLabel_2.setForeground(new Color(80, 77, 77, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		messagePanel.add(lblNewLabel_2, BorderLayout.CENTER);
		
		this.add(messagePanel, gbc_panel_2);*/
	
		
		messagePanel.setOpaque(false);
		messagePanel.setLayout(new BorderLayout());
		messagePanel.add(initializeECG(), BorderLayout.CENTER);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridheight = 17;
		gbc_panel_2.gridwidth = 15;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 1;
		
		initializeECG();
		this.add(messagePanel, gbc_panel_2);
		
		
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
		
		JButton btnNewButton2 = new JButton("");
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBorder(null);
		btnNewButton2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setActionCommand("MESSAGES");
		btnNewButton2.addActionListener(controller);
		btnNewButton2.setIcon(new ImageIcon(getClass().getResource("/resources/Alerts.png")));
		GridBagConstraints gbc_btnNewButton2 = new GridBagConstraints();
		gbc_btnNewButton2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton2.gridx = 1;
		gbc_btnNewButton2.gridy = 18;
		this.add(btnNewButton2, gbc_btnNewButton2);
		
		JButton btnNewButton3 = new JButton("");
		btnNewButton3.setBorderPainted(false);
		btnNewButton3.setBorder(null);
		btnNewButton3.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton3.setContentAreaFilled(false);
		btnNewButton3.setActionCommand("ECGS");
		btnNewButton3.addActionListener(controller);
		btnNewButton3.setIcon(new ImageIcon(getClass().getResource("/resources/Data.png")));
		GridBagConstraints gbc_btnNewButton3 = new GridBagConstraints();
		gbc_btnNewButton3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton3.gridx = 2;
		gbc_btnNewButton3.gridy = 18;
		this.add(btnNewButton3, gbc_btnNewButton3);
		
	}
}
