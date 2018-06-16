package control;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.DBManagement;
import model.LocalizationService;
import view.ComponentResizer;
import view.LoginFr;
import view.MainFr;
import view.dialogs.ExitDialog;

public class MainCtrl implements WindowListener,MouseMotionListener {
	
	public static String DATABASE = "src/resources/BDAmberLife.db";
	int pX, pY;
	
	/**
	 * Creates an initial LoginFr and a controller for it, initializes services, and starts the program.
	 * 
	 * @param args	arguments passed to the program
	 * @see LoginFr, LocalizationService
	 */
	
	private static MainFr window = new MainFr();
	
    public static void main(String[] args) throws ClassNotFoundException {
    	LocalizationService.initialize();
        try {
        	//DBManagement.createDatabase();
        	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
            MainCtrl m = new MainCtrl();
			LoginFr p = new LoginFr();
			LoginCtrl pc = new LoginCtrl(p);
			//FrameDragListener frameDragListener = new FrameDragListener(window);
			ComponentResizer cr = new ComponentResizer();
			cr.registerComponent(window);
			cr.setSnapSize(new Dimension(5, 5));
			
		    p.addController(pc);
		    p.initialize();
		    //window.setUndecorated(true);
			window.addController(m);
			//window.addMouseListener(frameDragListener);
			//window.addMouseMotionListener(frameDragListener);
			
			window.initialize();
			window.setLocationRelativeTo(null);
			setPanel(p);
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    public static void popBackStack() {
    	window.popBackStack();
    }
    
    public static void setPanel(JPanel panel) {

    	window.switchView(panel);
    	window.repaint(); 
    	window.validate();
    }
    
    public static void toBackStack(JPanel panel) {
    	window.toBackStack(panel);
    }
    
    public static void validateMainFrame() {
    	window.validate();
    }
    
    public static MainFr getMainFrame() {
    	return window;
    }
    
    
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ExitDialog.confirmExit();	
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public static class FrameDragListener extends MouseAdapter {

	    private final JFrame frame;
	    private Point mouseDownCompCoords = null;

	    public FrameDragListener(JFrame frame) {
	        this.frame = frame;
	    }

	    public void mouseReleased(MouseEvent e) {
	        mouseDownCompCoords = null;
	    }

	    public void mousePressed(MouseEvent e) {
	        mouseDownCompCoords = e.getPoint();
	    }

	    public void mouseDragged(MouseEvent e) {
	        Point currCoords = e.getLocationOnScreen();
	        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
	    }
	}//FrameDragListener



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}


