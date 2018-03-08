package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ECG;
import view.ExitDialog;

public class DoctorMeasureCtrl extends ReturnsToFrame implements ActionListener, WindowListener{
	
	JFrame fr;
	ECG e;
	
	public DoctorMeasureCtrl(JFrame thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}
	
	public ECG getECG() {
		return e;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: " + e.getActionCommand());
		 if (e.getActionCommand().equals("BACK")){
			 returnToPrevious();
			 fr.dispose();
		 }
		 else if (e.getActionCommand().equals("COMPARE")){
			 	System.out.println("Unimplemented");
			 
		 }
		 else System.out.println("Invalid Action");
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
