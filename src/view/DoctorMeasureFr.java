package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.AssistMeasureCtrl;
import control.DoctorMeasureCtrl;
import model.ECG;
import model.LocalizationService;

import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class DoctorMeasureFr extends JFrame {

	private JPanel contentPane;
	DoctorMeasureCtrl controller;
	
	public void addController(DoctorMeasureCtrl dmc) {
		this.controller = dmc;
	}

	public DoctorMeasureFr() {
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 535);
		contentPane =  new JPanelWithBackground(getClass().getResource("/resources/BG.png"));	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		this.setTitle("ECG Confirmation");
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		
		//Get PROMETHEUS font
		java.io.InputStream is = getClass().getResourceAsStream("/resources/PROMETHEUS.ttf");
		Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
		Font sf = font; // will use sf to change the style;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
		} catch (FontFormatException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 140));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 23;
		gbc_panel.gridheight = 15;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 30, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 50, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 0.5, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		//GRAPH GOES HERE
		
		/*JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 22;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);*/
		
		//PANEL
		FullGraphPanel gr = new FullGraphPanel(controller.getECG());
    	gr.setBackground(Color.DARK_GRAY.darker());
    	gr.setOpaque(true);
    	
    	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 22;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panel.add(gr, gbc_panel_1);
		 
		
		if (controller.getECG() != null && controller.getECG().getReport() != null) {
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			//Make invisible if there's no messages
			//scrollPane.setVisible(false);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 22;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 10;
			
			JPanel viewport = new JPanel();
			viewport.setLayout(new BorderLayout());
			JTextArea text = new JTextArea();
			text.setLineWrap(true);
			text.setText(this.controller.getECG().getReport());
			text.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 18));
			viewport.add(text, BorderLayout.CENTER);
			
			scrollPane.setViewportView(viewport);
			panel.add(scrollPane, gbc_scrollPane);
		}
		
		JButton btnLogout = new JButton("");
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(null);
		btnLogout.setMargin(new Insets(0, 0, 0, 0));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setActionCommand("BACK");
		btnLogout.addActionListener(controller);
		btnLogout.setOpaque(false);
		btnLogout.setIcon(new ImageIcon(getClass().getResource("/resources/Backbutton.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 13;
		panel.add(btnLogout, gbc_btnNewButton);
		
		JButton button = new JButton(LocalizationService.getWord("compare"));
		sf = font.deriveFont(32f);
		button.setBorderPainted(false);
		button.setFont(sf);
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(80, 77, 77, 255));
		button.setForeground(Color.WHITE);
		button.addActionListener(controller);
		button.setActionCommand("CONFIRM");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridwidth = 3;
		gbc_button.gridx = 20;
		gbc_button.gridy = 13;
		panel.add(button, gbc_button);
	}

}
