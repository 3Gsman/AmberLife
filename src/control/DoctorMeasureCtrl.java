package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ECG;
import view.ExitDialog;
import view.DoctorPatientFr;
import view.ECGchooserFr;

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
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Action received: " + ev.getActionCommand());
		 if (ev.getActionCommand().equals("BACK")){
			 returnToPrevious();
			 fr.dispose();
		 }
		 else if (ev.getActionCommand().equals("COMPARE")){
			 try {
			 	DoctorPatientCtrl dpc = ((DoctorPatientFr)getPreviousWindow()).getController();
			 	ECGchooserFr ecgf = new ECGchooserFr();
			 	ECGchooserCtrl ecgc = new ECGchooserCtrl(dpc, ecgf, e);
			 	ecgc.setPreviousWindow(getPreviousWindow());
			 	ecgf.addController(ecgc);
			 	ecgf.initialize();
			 	ecgf.setVisible(true);
			 	
			 	fr.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
