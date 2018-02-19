package view;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.LocalizationService;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import javax.swing.JScrollBar;
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
import java.awt.event.ActionEvent;

public class AssistPatientFr extends JFrame {

	private JPanelWithBackground contentPane;
	public ActionListener controller;
	
	
	public void addController(ActionListener a) {
		this.controller = a;
	}
	
	
	public AssistPatientFr(String dni, String pname, String psurname, String user) {
		
	}
	
	/**
	 * Initialize the frame.
	 * @throws IOException 
	 */
	public void initialize(String dni, String pname, String psurname, String user) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 632);
		Dimension d = new Dimension(800, 620);
		this.setMinimumSize(d);
		this.setSize(d);
		contentPane = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{40, 40, 40, 40, 20, 30, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{80, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 80, 0};
		gbl_contentPane.columnWeights = new double[]{0.2, 0.2, 0.2, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
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
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel(" ");
		sf = font.deriveFont(18f);
		label.setFont(sf);
		panel_1.add(label);
		
		JLabel lblNameXxxxx = new JLabel(" " +LocalizationService.getWord("name") +": " + pname);
		sf = font.deriveFont(22f);
		lblNameXxxxx.setFont(sf);
		lblNameXxxxx.setForeground(new Color(80, 77, 77, 255));
		panel_1.add(lblNameXxxxx);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		sf = font.deriveFont(18f);
		lblNewLabel_4.setFont(sf);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblSsnXxxxx = new JLabel(" " +LocalizationService.getWord("surname") +": " + psurname);
		sf = font.deriveFont(22f);
		lblSsnXxxxx.setForeground(new Color(80, 77, 77, 255));
		lblSsnXxxxx.setFont(sf);
		panel_1.add(lblSsnXxxxx);
		
		JLabel label_2 = new JLabel(" ");
		sf = font.deriveFont(18f);
		label_2.setFont(sf);
		panel_1.add(label_2);
		
		JLabel lblPhoneXxxxx = new JLabel(" " +LocalizationService.getWord("id") +": " + dni);
		sf = font.deriveFont(22f);
		lblPhoneXxxxx.setForeground(new Color(80, 77, 77, 255));
		lblPhoneXxxxx.setFont(sf);
		panel_1.add(lblPhoneXxxxx);
		
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground( new Color(255, 255, 255, 100));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridheight = 16;
		gbc_panel_2.gridwidth = 15;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel(LocalizationService.getWord("nomessages"));
		sf = font.deriveFont(28f);
		lblNewLabel_2.setFont(sf);
		lblNewLabel_2.setForeground(new Color(80, 77, 77, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2, BorderLayout.CENTER);
		
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