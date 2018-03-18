	package view.assistant;
	
	import javax.swing.JFrame;
	import javax.swing.JButton;
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
	import java.awt.GridBagLayout;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import java.io.IOException;
	import java.awt.FlowLayout;
	import javax.swing.JPanel;
	import javax.swing.JLabel;

import control.assistant.AssistCtrl;
import model.LocalizationService;
import view.panels.JPanelWithBackground;
	import javax.swing.ImageIcon;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import java.awt.Font;
	import java.awt.FontFormatException;
	
	@SuppressWarnings("serial")
	public class AssistFr extends JFrame {
		
		public JTextField textField;
		AssistCtrl controller;
		JButton btnNewButton = new JButton(LocalizationService.getWord("id"));
		JButton button = new JButton(LocalizationService.getWord("ssn"));
		boolean mode = true;
		
		public boolean getMode() {
			return mode;
		}
	
		/**
		 * Launch the application.
		 */
		
		//Create a panel that allows for background
		
		
		/**
		 * Create the application.
		 */
		
		public  AssistFr() {
			
		}
		
		public String getID() {
			return textField.getText();
		}
		
		public void addController(AssistCtrl controlador){
			this.controller = controlador;
		}
		
		public void switchbuttons() {
			mode = !mode;
			setButtons();
		}
		
		public void setButtons() {
			java.io.InputStream is = getClass().getResourceAsStream("/resources/PROMETHEUS.ttf");
			Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
			Font sf = font; // will use sf to change the style;
			Color grey = new Color(80, 77, 77, 255);
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
			//Set Assistants button
			 if(!mode) {
				sf = font.deriveFont(22f);
				button.setFont(sf);
				button.setOpaque(true);
				button.setBackground(new Color(255,255,255,255));
				button.setBorderPainted(false);
				sf = font.deriveFont(Font.BOLD, 28f);
				button.setFont(sf);
				button.setForeground(grey);
			}
			else {
				sf = font.deriveFont(22f);
				button.setFont(sf);
				button.setOpaque(false);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setBorder(null);
				sf = font.deriveFont(Font.BOLD, 28f);
				button.setFont(sf);
				button.setForeground(Color.WHITE);
			}
			 //Set Doctors Button
			if(mode) {
				btnNewButton.setOpaque(true);
				btnNewButton.setBackground(new Color(255,255,255,255));
				btnNewButton.setBorderPainted(false);
				sf = font.deriveFont(Font.BOLD, 28f);
				btnNewButton.setFont(sf);
				btnNewButton.setForeground(grey);
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				btnNewButton.setContentAreaFilled(true);
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
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				}
		}
		

		/**
		 * Initialize the contents of the frame.
		 * @throws IOException 
		 */
		public void initialize() throws IOException {
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(controller);
			this.setBackground(new Color(204, 0, 0));
			this.setBounds(100, 100, 798, 913);
			this.getContentPane().setLayout(new BorderLayout(0, 0));	
			this.setTitle("AmberLife");
			ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
			this.setIconImage(img.getImage());
			Dimension d = new Dimension(1280,720);
			this.setMinimumSize(d);
			this.setSize(d);
			
			Color grey = new Color(80, 77, 77, 255);
			
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
			
			//WindowBuilder does NOT parse this one, switch by a normal JPanel to remove background and allow parsing.
	
			//JPanelWithBackground panel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));	
			JPanelWithBackground panel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));	
			this.getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{30, 80, 50, 20, 0, 0, 10, 60, 60, 10, 60, 0, 0, 30, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.2, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			sf = font.deriveFont(Font.BOLD, 11f);
			
			JPanel paneluser = new JPanel();
			paneluser.setLayout(new FlowLayout(FlowLayout.RIGHT));
			paneluser.setOpaque(false);
			GridBagConstraints gbc_paneluser = new GridBagConstraints();
			gbc_paneluser.gridwidth = 22;
			gbc_paneluser.gridheight = 1;
			gbc_paneluser.insets = new Insets(5, 0, 5, 5);
			gbc_paneluser.fill = GridBagConstraints.BOTH;
			gbc_paneluser.gridx = 0;
			gbc_paneluser.gridy = 0;
			panel.add(paneluser,gbc_paneluser);
			
			JLabel lblUser = new JLabel(" " + LocalizationService.getWord("user") + ": " 
								+ controller.getAssist().getName() + " " + controller.getAssist().getLastname());
			sf = font.deriveFont(22f);
			lblUser.setForeground(Color.WHITE);
			lblUser.setOpaque(false);
			lblUser.setFont(sf);
			paneluser.add(lblUser);
			
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
			
			JLabel lblNewLabel = new JLabel(LocalizationService.getWord("searchpatient"));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			sf = font.deriveFont(124f);
			lblNewLabel.setFont(sf);
			lblNewLabel.setOpaque(false);
			panel_1.add(lblNewLabel, BorderLayout.CENTER);
			sf = font.deriveFont(28f);
			sf = font.deriveFont(28f);
			
			
			
			//Buttons
			button.addActionListener(controller);
			button.setActionCommand("SSN");
			btnNewButton.addActionListener(controller);
			btnNewButton.setActionCommand("ID");
			setButtons();
			
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
			gbc_btnNewButton.gridx = 9;
			gbc_btnNewButton.gridy = 7;
			panel.add(btnNewButton, gbc_btnNewButton);
			
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.BOTH;
			gbc_button.insets = new Insets(0, 0, 0, 0);
			gbc_button.gridx = 10;
			gbc_button.gridy = 7;
			panel.add(button, gbc_button);
			
			
			textField = new JTextField();
			textField.addKeyListener(controller);
			textField.setBorder(null);
			textField.setFont(new Font("Verdana", Font.PLAIN, 26));
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.BOTH;
			gbc_textField.gridwidth = 4;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 9;
			gbc_textField.gridy = 8;
			panel.add(textField, gbc_textField);
			textField.setColumns(10);
			
			JButton btnNewButton_1 = new JButton(LocalizationService.getWord("search"));
			btnNewButton_1.setBackground(grey);
			btnNewButton_1.addActionListener(controller);
			btnNewButton_1.setActionCommand("SEARCH");
			btnNewButton_1.setBorder(null);
			sf = font.deriveFont(28f);
			btnNewButton_1.setFont(sf);
			btnNewButton_1.setForeground(Color.WHITE);
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton_1.gridwidth = 2;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 10;
			gbc_btnNewButton_1.gridy = 10;
			panel.add(btnNewButton_1, gbc_btnNewButton_1);
			
			
			
			//User label
			//Breaks the layout for some reason.
			/*JPanel panel_u = new JPanel();
			panel_u.setOpaque(false);
			FlowLayout flowLayout = (FlowLayout) panel_u.getLayout();
			flowLayout.setVgap(1);
			flowLayout.setHgap(1);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			GridBagConstraints gbc_panel_u = new GridBagConstraints();
			gbc_panel_u.gridheight = 1;
			gbc_panel_u.gridwidth = 1;
			gbc_panel_u.insets = new Insets(0, 0, 0, 5);
			gbc_panel_u.fill = GridBagConstraints.BOTH;
			gbc_panel_u.gridx = 2;
			gbc_panel_u.gridy = 1;
			panel.add(panel_u, gbc_panel_u);
			
			JLabel lblUser = new JLabel("user: ");
			sf = font.deriveFont(24f);
			lblUser.setFont(sf);
			lblUser.setForeground(new Color(255, 255, 255, 255));
			lblUser.setVerticalAlignment(SwingConstants.TOP);
			lblUser.setHorizontalAlignment(SwingConstants.CENTER);
			panel_u.add(lblNewLabel);*/
			
			//Back button
			JPanel panel_2 = new JPanel();
			panel_2.setOpaque(false);
			FlowLayout flowLayout2 = (FlowLayout) panel_2.getLayout();
			flowLayout2.setVgap(1);
			flowLayout2.setHgap(1);
			flowLayout2.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_2 = new GridBagConstraints();
			gbc_panel_2.gridwidth = 11;
			gbc_panel_2.insets = new Insets(0, 0, 0, 5);
			gbc_panel_2.fill = GridBagConstraints.BOTH;
			gbc_panel_2.gridx = 0;
			gbc_panel_2.gridy = 13;
			panel.add(panel_2, gbc_panel_2);
			
			JButton btnLogout = new JButton("");
			btnLogout.setBorderPainted(false);
			btnLogout.setBorder(null);
			btnLogout.setMargin(new Insets(0, 0, 0, 0));
			btnLogout.setContentAreaFilled(false);
			btnLogout.setActionCommand("BACK");
			btnLogout.addActionListener(controller);
			btnLogout.setIcon(new ImageIcon(getClass().getResource("/resources/Backbutton.png")));
			panel_2.add(btnLogout);
			
			
		}
	
	}