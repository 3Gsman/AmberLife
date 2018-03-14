package control.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ReturnsToFrame;
import model.ECG;
import view.dialogs.ExitDialog;

public class AssistMeasureCtrl extends ReturnsToFrame implements ActionListener, WindowListener{
	
	JFrame fr;
	ECG e;
	
	public AssistMeasureCtrl(JFrame thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}
	
	public ECG getECG() {
		return e;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("CANCEL")){
			 returnToPrevious();
			 fr.dispose();
		 }
		 else if (e.getActionCommand().equals("CONFIRM")){
			 //TO BE CHANGED EVENTUALLY
			 System.out.println("UNIMPLEMENTED");
			 returnToPrevious();
			 fr.dispose();
			 
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
