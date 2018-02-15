package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import view.DoctorFr;
import view.TecnFr;
import view.TecnMeasureFr;

public class TecnPatientCtrl implements ActionListener, KeyListener{
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("MEASURE")){
			
			 	//.setVisible(false);	/
		        TecnMeasureFr tmf = new TecnMeasureFr();
		        TecnMeasureCtrl tmc = new TecnMeasureCtrl();
		        tmf.addController(tmc);
		        //tmf.setVisible(true);
		        
			 System.out.println("Pantalla ECG");
			 }else System.out.println(" Null");
		
	}

}