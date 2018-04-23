package view.assistant;

import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.GraphCtrl;
import control.assistant.AssistMeasureCtrl;
import model.LocalizationService;
import view.panels.FullGraphPanel;
import view.panels.JPanelWithBackground;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class AssistMeasureFr extends JPanelWithBackground {

	public AssistMeasureFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}

	AssistMeasureCtrl controller;
	
	public void addController(AssistMeasureCtrl c) {
		this.controller = c;
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		setBounds(100, 100, 757, 535);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_this.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_this.columnWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		this.setLayout(gbl_this);
		
		Color grey = new Color(80, 77, 77, 255);
		
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
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 30, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 50, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 0.5, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		//GRAPH GOES HERE
		
		/*JPanel panel_1 = new JPanel();
		panel_1.setBackground(grey);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 22;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);*/
		
		//PANEL
		FullGraphPanel gr = new FullGraphPanel(controller.getECG());
		GraphCtrl gc = new GraphCtrl(gr);
		gr.addController(gc);
	   	gr.setBackground(Color.DARK_GRAY.darker());
    	gr.setOpaque(true);
    	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 22;
		gbc_panel_1.gridheight = 10;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panel.add(gr, gbc_panel_1);
		 
		
		/*if (controller.getECG() != null && controller.getECG().getReport() != null) {
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
			panel.add(scrollPane, gbc_scrollPane);
		}*/
		
		JButton btnNewButton = new JButton(LocalizationService.getWord("cancel"));
		sf = font.deriveFont(32f);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(sf);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(grey);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(controller);
		btnNewButton.setActionCommand("CANCEL");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 13;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton button = new JButton(LocalizationService.getWord("confirm"));
		sf = font.deriveFont(32f);
		button.setBorderPainted(false);
		button.setFont(sf);
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(grey);
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
