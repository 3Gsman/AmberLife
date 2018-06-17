package control.assistant;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.MainCtrl;
import model.ECG;
import view.assistant.AssistMeasureFr;
import view.assistant.AssistPatientFr;
import view.dialogs.ECGConfDialog;
import view.dialogs.ExitDialog;
import view.dialogs.FileChooserErrorDialog;
import view.dialogs.NewMessageDialog;
import view.panels.MessagePanel;

public class AssistPatientCtrl  implements ActionListener, KeyListener{
	
	private AssistPatientFr patient;
	private String assistID;
	private String patientID;
	
	
	/**
	 * Class constructor, sets the related frame.
	 *
	 * @param  f	Frame related to the controller	
	 * @see    AssistPatientFr
	 */
	public AssistPatientCtrl(AssistPatientFr f, String assistID, String patientID) {
		patient = f;
		this.assistID = assistID;
		this.patientID = patientID;
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
	
	/**
	 * Listens to event commands emitted by AssistPatientFr, and reacts to them accordingly:
	 * 
	 * MEASURE: 	Select an ECG file using the FileChooser class (in the final program, receive a measure) and open it 
	 *				for display in an AssistMeasureFr.
	 * BACK: 		Go back to the previous frame.
	 * REPLY:		Open a NewMessageDialog as a reply to the message related to the triggering button. (Placeholder only)
	 * NEWMESSAGE:	Open a NewMessageDialog for creating new messages. (Placeholder only)
	 *
	 * @param  e event triggering the action performed
	 * @see         AssistPatientFr, FileChooser, AssistMeasureFr, AssistMeasureCtrl, NewMessageDialog
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("MEASURE")){
			 
			 String dataPort = AssistPatientFr.boxPort.getSelectedItem().toString();
			 
			 System.out.println(" Measure");
			 
			 try {
				ECGConfDialog a = new ECGConfDialog(assistID,patientID,patient,dataPort);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			/* JFileChooser chooser = new JFileChooser();
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "ECGs", "txt");
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory(new java.io.File("./src/resources"));
		    int returnVal = chooser.showOpenDialog(null);
		    
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		       String filename = chooser.getSelectedFile().getName();
		       FileManagement f = new FileManagement();
		       ECG ecg;
		       
		       if(filename.substring(0,3).equals("ECG")) {
		    	   System.out.println(" ECG");
					try {
						   ecg = f.readECG(filename);
					       AssistMeasureFr tef = new AssistMeasureFr(getClass().getResource("/resources/BG.png"));
					       AssistMeasureCtrl tec = new AssistMeasureCtrl(tef,ecg, assistID, patientID);
					       MainCtrl.toBackStack(patient);
					       tef.addController(tec);
					       tef.initialize();
					       MainCtrl.setPanel(tef);  		       
					       System.out.println("Pantalla ECG");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		       }
		       else {
		    	   FileChooserErrorDialog.notECG();
		       }
		    }*/
			 

		}else  if (e.getActionCommand().equals("BACK")){ 
			System.out.println(" Back");
			MainCtrl.popBackStack();
			
		}else  if (e.getActionCommand().equals("REPLY")){ 	
			 System.out.println(" Reply");
			 try {
				MessagePanel mp = (MessagePanel) ((Component) e.getSource()).getParent();
				NewMessageDialog nmd = new NewMessageDialog(MainCtrl.getMainFrame(),this,patient.getName() + " " + patient.getLastname(), assistID, patientID,
						"From: " + mp.getUser().getName() + " " + mp.getUser().getLastname() + " on " + mp.getMessage().getTimestamp() + "\n"
						+ "RE: " + mp.getMessage().getMessage() + "\n");
	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else  if (e.getActionCommand().equals("NEWMESSAGE")){ 
			try {
				NewMessageDialog nmd = new NewMessageDialog(MainCtrl.getMainFrame(),this,patient.getName()+ " " + patient.getLastname(),assistID, patientID,"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getActionCommand().equals("MESSAGE_UPDATE")){
			try {
				patient.refreshMessages(patient.messageboard);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else System.out.println("Invalid Action");{
		
		 }
	}

}