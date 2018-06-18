package view.doctor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.GraphCtrl;
import control.doctor.DoctorMeasureCtrl;
import model.LocalizationService;
import view.TextPrompt;
import view.panels.AlphaContainer;
import view.panels.FullGraphPanel;
import view.panels.JPanelWithBackground;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DoctorMeasureFr extends JPanelWithBackground {
	
	JTextArea text; //Diagnostic

	
	public void updateDiagnostic(String text) {
		this.text.setText(text);
		this.repaint();
		this.validate();
	}

	public DoctorMeasureFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}

	

	DoctorMeasureCtrl controller;
	
	public void addController(DoctorMeasureCtrl dmc) {
		this.controller = dmc;
	}

	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_this.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_this.columnWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, Double.MIN_VALUE};
		this.setLayout(gbl_this);
		
		//Get PROMETHEUS font
		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
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
		this.add(new AlphaContainer(panel), gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 30, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 50, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 0.5, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//GRAPH GOES HERE
		
		//PANEL
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 22;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		gbc_panel_1.insets = new Insets(0,0,5,0);
		
		if(controller.getCGP() == null && controller.getECG() != null) {
			FullGraphPanel gr = new FullGraphPanel(controller.getECG());   	
			GraphCtrl gc = new GraphCtrl(gr);
			gr.addController(gc);
			gr.setBackground(Color.DARK_GRAY.darker());
	    	gr.setOpaque(true);
	    	panel.add(gr, gbc_panel_1);
	    	

			JPanel information = new JPanel();
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{40,40};
			gridBagLayout.rowHeights = new int[]{30};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			information.setLayout(gridBagLayout);
			
			
			GridBagConstraints gbc_information = new GridBagConstraints();
			gbc_information.gridwidth = 22;
			gbc_information.fill = GridBagConstraints.BOTH;
			gbc_information.gridx = 1;
			gbc_information.gridy = 10;
			
			//Set Left Panel
			{
			JPanel lp = new JPanel();
			lp.setOpaque(false);
			lp.setLayout(new BorderLayout());
			
			GridBagConstraints gbc_sp1 = new GridBagConstraints();
			gbc_sp1.gridwidth = 1;
			gbc_sp1.gridheight = 1;
			gbc_sp1.fill = GridBagConstraints.BOTH;
			gbc_sp1.gridx = 0;
			gbc_sp1.gridy = 0;
			
				{//Set Diagnose Panel Begin
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.getVerticalScrollBar().setUnitIncrement(18);
				
	
				JPanel viewport = new JPanel();
				viewport.setLayout(new BorderLayout());
				text = new JTextArea();
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setEditable(false);
				TextPrompt tp = new TextPrompt("No report to show.", text);
				tp.setVerticalTextPosition(SwingConstants.TOP);
				tp.setHorizontalTextPosition(SwingConstants.CENTER);
				tp.setForeground(new Color(140,140,140,255));
				sf = font.deriveFont(16f);
				tp.setFont(sf);
				String report = controller.getECG().getReport();
				if(!report.equals("")) {
					text.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 18));		
					text.setText(report);
				}
				/*else {
					text.setFont(new Font("Source Code Pro Medium", Font.ITALIC, 18));
					text.setForeground(new Color(140,140,140,255));
					text.setText("No report to show.");
				}*/
				
				viewport.add(text, BorderLayout.CENTER);
				
				scrollPane.setViewportView(viewport);
				lp.add(scrollPane,BorderLayout.CENTER);
				}//Set Diagnose Panel End
				
				{//Set Edit Button Panel and button Begin
					
				JPanel edit_panel = new JPanel();
				edit_panel.setLayout(new FlowLayout(FlowLayout.LEADING));
				edit_panel.setOpaque(false);
				edit_panel.setBackground(Color.white);
				JButton btnEdit = new JButton("");
				btnEdit.setBorderPainted(false);
				btnEdit.setBorder(null);
				btnEdit.setMargin(new Insets(0, 0, 0, 0));
				btnEdit.setContentAreaFilled(false);
				btnEdit.setActionCommand("EDIT_DIAGNOSIS");
				btnEdit.addActionListener(controller);
				ImageIcon icon = new ImageIcon(getClass().getResource("/resources/penicon_small.png"));
				btnEdit.setHorizontalTextPosition(SwingConstants.TRAILING);
				btnEdit.setIcon(icon);
				btnEdit.setPreferredSize(new Dimension(24,24));
				edit_panel.add(btnEdit);
				lp.add(btnEdit,BorderLayout.PAGE_END);
				}//Set Edit Button Panel and button End
				
			information.add(lp,gbc_sp1);
			}//Set Left Panel End
			
			//Set Right Panel
			JScrollPane scrollPane2 = new JScrollPane();
			scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane2.getVerticalScrollBar().setUnitIncrement(18);
			GridBagConstraints gbc_sp2 = new GridBagConstraints();
			gbc_sp2.gridwidth = 1;
			gbc_sp2.fill = GridBagConstraints.BOTH;
			gbc_sp2.gridx = 1;
			gbc_sp2.gridy = 0;
			
			JPanel viewport2 = new JPanel();
			viewport2.setLayout(new BorderLayout());
			JTextArea text2 = new JTextArea();
			text2.setLineWrap(true);
			text2.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 18));
			//INFO VA AQUI
			text2.setEditable(false);
			String nameword = LocalizationService.getWord("name");
			String frequencyword = LocalizationService.getWord("frequency");
			//Aqui para cambiar el panel con los datos del ECG
			text2.setText(nameword.substring(0, 1).toUpperCase() + nameword.substring(1) + ": " + controller.getECG().getId() + "\n" +
						frequencyword.substring(0, 1).toUpperCase() + frequencyword.substring(1)  + ": " + controller.getECG().getFrequency());
			viewport2.add(text2, BorderLayout.CENTER);
			
			scrollPane2.setViewportView(viewport2);
			information.add(scrollPane2,gbc_sp2);
			
			panel.add(information,gbc_information);
		}
		else {
			controller.getCGP().setBackground(Color.DARK_GRAY.darker());
			controller.getCGP().setOpaque(true);
			gbc_panel_1.gridheight = 11;
			panel.add(controller.getCGP(), gbc_panel_1);
			
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
		if(controller.getCGP() == null) {
			JButton button = new JButton(LocalizationService.getWord("compare"));
			sf = font.deriveFont(32f);
			button.setBorderPainted(false);
			button.setFont(sf);
			button.setForeground(new Color(0, 0, 0));
			button.setBackground(new Color(80, 77, 77, 255));
			button.setForeground(Color.WHITE);
			button.addActionListener(controller);
			button.setActionCommand("COMPARE");
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.fill = GridBagConstraints.BOTH;
			gbc_button.gridwidth = 3;
			gbc_button.gridx = 20;
			gbc_button.gridy = 13;
			panel.add(button, gbc_button);
		
		this.setVisible(true);
		}
	}

}
