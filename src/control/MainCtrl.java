package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.LocalizationService;
import view.LoginFr;
import view.MainFr;
import view.dialogs.ExitDialog;

public class MainCtrl implements WindowListener{
	
	public static String DATABASE = "src/resources/BDAmberLife.db";
	
	
	/**
	 * Creates an initial LoginFr and a controller for it, initializes services, and starts the program.
	 * 
	 * @param args	arguments passed to the program
	 * @see LoginFr, LocalizationService
	 */
	
	public static MainFr window = new MainFr();
	
    public static void main(String[] args) throws ClassNotFoundException {
    	LocalizationService.initialize();

        try {

        	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
            MainCtrl m = new MainCtrl();
			LoginFr p = new LoginFr();
			LoginCtrl pc = new LoginCtrl(p);
		    p.addController(pc);
		    p.initialize();
			window.addController(m);
			window.initialize();
			window.setContentPane(p);
			window.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
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


}
