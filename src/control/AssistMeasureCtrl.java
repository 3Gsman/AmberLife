package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ECG;

public class AssistMeasureCtrl extends ReturnsToFrame implements ActionListener{
	
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
	
}
