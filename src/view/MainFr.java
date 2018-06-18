package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.MainCtrl;
import view.panels.LoadingPanel;


public class MainFr extends JFrame{

	MainCtrl controller;
	JPanel mainView = new JPanel();
	LoadingPanel loadingScreen = new LoadingPanel();
	//int pY,pX;

	
	public static Stack<JPanel> backstack = new Stack<>();
	
	public void popBackStack() {
		JPanel panel = backstack.pop();
		panel.setSize(this.getSize());	
		this.getContentPane().remove(mainView);
		this.mainView = panel;
		mainView.setVisible(true);
		this.getContentPane().add(mainView, 1);
		this.getContentPane().validate();
		this.repaint();
		this.validate();
	}
	
	public void toBackStack(JPanel panel) {
		panel.setVisible(false);
		backstack.push(panel);
	}

	public void switchView(JPanel panel) {
		this.getContentPane().remove(mainView);
		mainView = panel;
		mainView.setVisible(true);
		this.getContentPane().add(mainView, 1);
		this.getContentPane().validate();
		this.getContentPane().repaint();
		this.repaint();
		this.validate();
		
	}
	
	public JPanel getMainView() {
		return mainView;
	}
	
	
	public void addController(MainCtrl a) {
		this.controller = a;
	}
	
	/**
	 * Create the application.
	 */
	public MainFr() {	
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
		this.setContentPane(new JLayeredPane());
		this.getContentPane().setLayout(new BorderLayout());
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
		
		this.getContentPane().add(mainView, 1);
		
		JPanel menu = new JPanel();
		menu.setLayout(new BorderLayout());
		menu.setBackground(new Color(242, 137, 118));
		
		JPanel menubar = new JPanel();
		menubar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(getClass().getResource("/resources/Heart_icon.png")));
		menubar.add(icon);
		
		JLabel bar = new JLabel("AmberLife");
		bar.setForeground(Color.black);
		menubar.add(bar);
		

		

		
		/*JPanel dummy = new JPanel(); //Only used for the mouse listener
		dummy.setOpaque(false);
		
		dummy.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                pX = me.getX();
                pY = me.getY();

            }

             public void mouseDragged(MouseEvent me) {

                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });*/
        
		//menu.add(menubar,BorderLayout.WEST);
		//this.getContentPane().add(menu, BorderLayout.PAGE_START );
		
	}
	
	public void startLoading() {
		this.getContentPane().add(loadingScreen, 0);
		loadingScreen.setVisible(true);
		Thread t = new Thread(loadingScreen);
		t.start();
		
	}
	
	public void stopLoading() {
		loadingScreen.setVisible(false);
		this.getContentPane().remove(loadingScreen);
		this.validate();
	}
	
}
