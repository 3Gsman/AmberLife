package control.assistant;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import control.ReturnsToFrame;
import model.ECG;
import model.FileManagement;
import view.assistant.AssistMeasureFr;
import view.assistant.AssistPatientFr;
import view.dialogs.ExitDialog;
import view.dialogs.FileChooserErrorDialog;
import view.dialogs.NewMessageDialog;
import view.panels.MessagePanel;

public class AssistPatientCtrl extends ReturnsToFrame implements ActionListener, KeyListener, WindowListener{
	
	private AssistPatientFr patient;
	
	/**
	 * Class constructor, sets the related frame.
	 *
	 * @param  f	Frame related to the controller	
	 * @see    AssistPatientFr
	 */
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
			 System.out.println(" Measure");
			 
			 JFileChooser chooser = new JFileChooser();
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
		       }else {
		    	   FileChooserErrorDialog.notECG();
		       }
		    }
			 

		}else  if (e.getActionCommand().equals("BACK")){ 
			System.out.println(" Back");
			returnToPrevious();
			patient.dispose();
			
		}else  if (e.getActionCommand().equals("REPLY")){ 	
			 System.out.println(" Reply");
			 try {
				MessagePanel mp = (MessagePanel) ((Component) e.getSource()).getParent();
				NewMessageDialog nmd = new NewMessageDialog(patient.getName(), patient.getLastname(),
						"From: " + mp.getUser().getName() + " " + mp.getUser().getLastname() + " on " + mp.getDate() + "\n"
						+ "RE: " + mp.getMessage() + "\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else  if (e.getActionCommand().equals("NEWMESSAGE")){ 
			try {
				NewMessageDialog nmd = new NewMessageDialog(patient.getName(), patient.getLastname(),"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else System.out.println("Invalid Action");
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Asks for a confirmation before the window is closed.
	 * 
	 * @param	e	WindowEvent triggering the method, in this case, the window closing.
	 * @see         ExitDialog
	 */
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