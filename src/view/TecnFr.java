	package view;
	
	import java.awt.EventQueue;
	
	import javax.swing.JFrame;
	import javax.swing.JButton;
	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.GridLayout;
	import java.awt.Image;
	import java.awt.GridBagLayout;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.image.BufferedImage;
	import java.awt.image.ImageObserver;
	import java.io.File;
	import java.io.IOException;
	import java.net.URL;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.awt.FlowLayout;
	import javax.swing.JPanel;
	import javax.imageio.ImageIO;
	import javax.swing.BoxLayout;
	import javax.swing.JLabel;
	import java.awt.Color;
	import javax.swing.border.MatteBorder;
	
	import org.omg.CORBA.portable.InputStream;

import control.TecnCtrl;
import view.LoginFr.JPanelWithBackground;
	
	import javax.swing.JComboBox;
	import javax.swing.ImageIcon;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.JPasswordField;
	import java.awt.Font;
	import java.awt.FontFormatException;
	import java.awt.Graphics;
	import java.awt.GraphicsEnvironment;
	
	public class TecnFr extends JFrame {
	
		private JTextField textField;
		TecnCtrl controller;
		
	
		/**
		 * Launch the application.
		 */
		
		//Create a panel that allows for background
		public class JPanelWithBackground extends JPanel {
	
			  private Image backgroundImage;
	
			  // Some code to initialize the background image.
			  // Here, we use the constructor to load the image. This
			  // can vary depending on the use case of the panel.
			  public JPanelWithBackground(String fileName) throws IOException {
			    backgroundImage = ImageIO.read(new File(fileName));
			  }
			  
			  public JPanelWithBackground(URL url) throws IOException {
				    backgroundImage = ImageIO.read(url);
				  }
			  
	
			  public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    // Draw the background image.
			     
			    if(this.getWidth() > backgroundImage.getWidth(null) && this.getWidth() > this.getHeight()){
			    	Image newImage = backgroundImage.getScaledInstance(this.getWidth(),
			    	(int)(((float)this.getWidth()/backgroundImage.getWidth(null))*(float)backgroundImage.getHeight(null)), Image.SCALE_DEFAULT);
			    	g.drawImage(newImage, this.getWidth()/2-newImage.getWidth(null)/2,
					    		this.getHeight()/2-newImage.getHeight(null)/2, this);
			    }
			    else if(this.getHeight() > backgroundImage.getHeight(null)){
			    	Image newImage = backgroundImage.getScaledInstance(
			    			(int)(((float)this.getHeight()/backgroundImage.getHeight(null) *(float)backgroundImage.getWidth(null))),
			    			this.getHeight(), Image.SCALE_DEFAULT);
					g.drawImage(newImage, this.getWidth()/2-newImage.getWidth(null)/2,
							this.getHeight()/2-newImage.getHeight(null)/2, this);
			    }
			    else {
			    	g.drawImage(backgroundImage, this.getWidth()/2-backgroundImage.getWidth(null)/2,
				    		this.getHeight()/2-backgroundImage.getHeight(null)/2, this);
			    }
			    
			  }
			}
		
		
		/**
		 * Create the application.
		 */
		
		public TecnFr() {
			
		}
		
		public void addController(TecnCtrl controlador){
			this.controller = controlador;
		}
		

		/**
		 * Initialize the contents of the frame.
		 * @throws IOException 
		 */
		public void initialize() throws IOException {
			this.setBackground(new Color(204, 0, 0));
			this.setBounds(100, 100, 798, 913);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.getContentPane().setLayout(new BorderLayout(0, 0));	
			this.setTitle("AmberLife");
			ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
			this.setIconImage(img.getImage());
			
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
			gbl_panel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 80, 50, 20, 0, 0, 10, 60, 60, 10, 60, 0, 0, 30, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			sf = font.deriveFont(Font.BOLD, 11f);
			
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
			
			JLabel lblNewLabel = new JLabel("search patient");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			sf = font.deriveFont(148f);
			lblNewLabel.setFont(sf);
			lblNewLabel.setOpaque(false);
			panel_1.add(lblNewLabel, BorderLayout.CENTER);
			sf = font.deriveFont(28f);
			sf = font.deriveFont(28f);
			
			JButton btnNewButton = new JButton("i.d.");
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			sf = font.deriveFont(28f);
			btnNewButton.setFont(sf);
			btnNewButton.setContentAreaFilled(true);
			btnNewButton.setBackground(Color.white);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 9;
			gbc_btnNewButton.gridy = 7;
			panel.add(btnNewButton, gbc_btnNewButton);
			
			JButton button = new JButton("s.s.n.");
			sf = font.deriveFont(28f);
			button.setFont(sf);
			button.setBorderPainted(false);
			button.setBorder(null);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setContentAreaFilled(false);
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setForeground(Color.white);
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.BOTH;
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 10;
			gbc_button.gridy = 7;
			panel.add(button, gbc_button);
			
			
			textField = new JTextField();
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
			
			JButton btnNewButton_1 = new JButton("search");
			btnNewButton_1.setBackground(Color.DARK_GRAY);
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
			
			JPanel panel_2 = new JPanel();
			panel_2.setOpaque(false);
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setVgap(1);
			flowLayout.setHgap(1);
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_2 = new GridBagConstraints();
			gbc_panel_2.gridwidth = 11;
			gbc_panel_2.insets = new Insets(0, 0, 0, 5);
			gbc_panel_2.fill = GridBagConstraints.BOTH;
			gbc_panel_2.gridx = 0;
			gbc_panel_2.gridy = 13;
			panel.add(panel_2, gbc_panel_2);
		}
	
	}