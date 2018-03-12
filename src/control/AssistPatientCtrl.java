package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ECG;
import model.FileManager;
import view.AssistMeasureFr;
import view.AssistPatientFr;
import view.DoctorDialog;
import view.ExitDialog;
import view.NewMessageDialog;

public class AssistPatientCtrl extends ReturnsToFrame implements ActionListener, KeyListener, WindowListener{
	
	AssistPatientFr patient;
	
	public AssistPatientCtrl(AssistPatientFr f) {
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
		       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		       String filename = chooser.getSelectedFile().getName();
		       FileManager f = new FileManager();
		       ECG ecg;
			try {
				   ecg = f.readECG(filename);
			       AssistMeasureFr tef = new AssistMeasureFr();
			       AssistMeasureCtrl tec = new AssistMeasureCtrl(tef,ecg);
			       tec.setPreviousWindow(patient);
			       patient.setVisible(false);
			       tef.addController(tec);
			       tef.initialize();
			       tef.setVisible(true);
			       System.out.println("Pantalla ECG");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		    }
			 

		}else  if (e.getActionCommand().equals("BACK")){ 
			returnToPrevious();
			patient.dispose();
			
		}else  if (e.getActionCommand().equals("NEWMESSAGE")){ 
			NewMessageDialog nmd = new NewMessageDialog(patient, patient.getName(), patient.getLastname());
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