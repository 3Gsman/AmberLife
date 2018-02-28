package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TakeEcgCtrl extends ReturnsToFrame implements ActionListener{
	
	JFrame fr;
	
	public TakeEcgCtrl(JFrame thiscatwontshutthehellup) {
		this.fr = thiscatwontshutthehellup;
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
