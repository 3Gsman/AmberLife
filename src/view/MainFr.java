package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.MainCtrl;


public class MainFr extends JFrame{

	MainCtrl controller;
	
	public static Stack<JPanel> backstack = new Stack<>();
	
	public void popBackStack() {
		JPanel panel = backstack.pop();
		this.remove(this.getContentPane());
		panel.setSize(this.getSize());	
		this.setContentPane(panel);
		panel.setVisible(true);
		this.getContentPane().validate();
		this.repaint();
		this.validate();
	}
	
	public void toBackStack(JPanel panel) {
		panel.setVisible(false);
		backstack.push(panel);
	}

	
	public void addController(MainCtrl a) {
		this.controller = a;
	}
	
	/**
	 * Create the application.
	 */
	public MainFr() {	
		//setUndecorated(true);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		
		//Set action on close
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(controller);
		
		this.setBackground(new Color(204, 0, 0));
		this.setBounds(100, 100, 798, 913);
		this.getContentPane().setLayout(new BorderLayout(0, 0));	
		this.setTitle("AmberLife");
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(900, 820);
		this.setMinimumSize(d);
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
		
	}
}
