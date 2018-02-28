package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.AssistMeasureFr;
import view.TakeEcgFr;

public class AssistPatientCtrl extends ReturnsToFrame implements ActionListener, KeyListener{
	
	JFrame patient;
	
	public AssistPatientCtrl(JFrame f) {
		patient = f;
	}
	
	
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
			
			 
			 JFileChooser chooser = new JFileChooser();
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "ECGs", "txt");
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory(new java.io.File("./src/resources"));
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		       chooser.getSelectedFile().getName());
		    
		
		       /*AssistMeasureFr tmf = new AssistMeasureFr(chooser.getSelectedFile().getName());
		       AssistMeasureCtrl tmc = new AssistMeasureCtrl();
		       tmf.addController(tmc);*/
		       try {
		    	patient.setVisible(false);
				TakeEcgFr ecgfr = new TakeEcgFr();
				TakeEcgCtrl ecgc = new TakeEcgCtrl(ecgfr);
				ecgc.setPreviousWindow(patient);
				ecgfr.addController(ecgc);
				ecgfr.initialize(null);
				ecgfr.setVisible(true);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        
		       System.out.println("Pantalla ECG");
		    }
			 

		}else  if (e.getActionCommand().equals("BACK")){ 
			returnToPrevious();
			patient.dispose();
			
		}
		
	}

}