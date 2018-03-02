package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ECG;

public class DoctorMeasureCtrl extends ReturnsToFrame implements ActionListener{
	
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
	
}
