package control.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import control.MainCtrl;
import view.assistant.AssistFr;
import view.assistant.AssistPatientFr;
import view.dialogs.InvalidPatientDialog;
import model.FileManagement;
import model.Patient;
import model.Assistant;
import model.DBManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssistCtrl implements ActionListener, KeyListener{

	private AssistFr tf;
	private String name;
	private Assistant tecnico;
	
	/**
	 * Class constructor, sets the related frame, and the username of the current user for display in it.
	 *
	 * @param  user	Name of the current user
	 * @param  fr	Frame related to the controller	
	 * @throws IOException
	 * @see    AssistFr
	 */
	public AssistCtrl(String user, AssistFr fr) throws IOException {
		tf = fr;
		name = user;
		tecnico = new Assistant(user);
	
	}
	
	/**
	 * Listens to event commands emitted by AssistFr, and reacts to them accordingly:
	 * 
	 * SEARCH:		Search a patient by ID or SSN in the DB (Uses a text file as a placeholder)
	 * BACK:	   	Go back to the previous window
	 * ID:			Set the mode to ID Search (If it's not already set that way)
	 * SSN:			Set the mode to SSN Search (If it's not already set that way)
	 *
	 * @param  e event triggering the action performed
	 * @see         AssistFR
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print("Action received: " + e.getActionCommand());
		 if (e.getActionCommand().equals("SEARCH")){;
			try {
				searchPatient();
			} catch (ClassNotFoundException | SQLException  | IOException e1) {
				e1.printStackTrace();
			}
		 }else if (e.getActionCommand().equals("BACK")) {
			 MainCtrl.window.popBackStack();
		 }else if (e.getActionCommand().equals("ID")) {
			 if(!tf.getMode()) tf.switchbuttons();
			 tf.repaint();
		 }else if (e.getActionCommand().equals("SSN")) {
			 if(tf.getMode()) tf.switchbuttons();
			 tf.repaint();
		 }else System.out.println("Invalid Action");
		
	}
	
	/**
	
	 * Listens to Keys pressed in components at AssistFr, and reacts to them accordingly:
	 * 
	 * ENTER:	Search for a patient in ID or SSN
	 *
	 * @param  e event triggering the action performed
	 * @see         AssistFR
	 */
    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed");
    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
            	if(tf.getID() != "")
            		searchPatient();
			} catch (ClassNotFoundException | SQLException  | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } 
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	
    }
     
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Searches for a patient by ID or SSN, depending on the active mode, and if it's found, call as method to open a 
	 * new window with the patient data as argument.
	 *
	 * @throws	IOException
	 */
	public void searchPatient() throws ClassNotFoundException, SQLException, IOException {
		String dni = tf.getID();
		Vector<String> messages = new Vector<>();
		//DBManagement id = new DBManagement();
		
		if(tf.getMode() == true) {
			if(dni != null) {
				Patient resultado = DBManagement.checkId(dni);
				System.out.println("DNI: " + resultado.getId());
				
				if (resultado.getId() != "null") {
					//Mensajes de DB
					//messages = id.readPatientMessages(resultado.getNumber());
					
					messages = DBManagement.readPatientMessages(resultado.getId());
					
					
					System.out.println("Patient found.\n");
					System.out.println("Assistant: " + getName());			
					
					openPatientTecn(resultado.getName(),resultado.getLastname(),resultado.getId(), 
									resultado.getSsn(), messages, name);
					
				}else {
					InvalidPatientDialog.noPatientFound();
				}
			}
		}else {
			if(dni != null) {
				Patient resultado = DBManagement.checkSsn(dni);
				
				System.out.println("SSN: " +resultado.getSsn());
				
				if (resultado.getSsn() != "null") {
	
					//messages = id.readPatientMessages(resultado.getNumber());
					
					messages = DBManagement.readPatientMessages(resultado.getId());
					
					System.out.println("Patient found.\n");
					System.out.println("Assistant: " + getName());			
					
					openPatientTecn(resultado.getName(),resultado.getLastname(),resultado.getId(), 
									resultado.getSsn(), messages, name);
					
				}else {
					InvalidPatientDialog.noPatientFound();
				}
			}
		}

	}

	/**
	 * Opens a new ASsistPatientFr to see the opened patient in as much detail as an Assistant is allowed to.
	 * 
	 * @param	name		Name of the patient.
	 * @param	lastname	Last name of the patient.
	 * @param	id			ID of the patient.
	 * @param	ssn			SSN of the patient.
	 * @param	messages	Any messages related to the patient.
	 * @param	user		Username of the Assistant using the program.
	 * @see     AssistPatientCtrl, AssistPatientFr
	 * @throws	IOException
	 */
	public void openPatientTecn(String name, String lastname, String id, String ssn, Vector<String> messages, String user) throws IOException {
        AssistPatientFr vm = new AssistPatientFr(getClass().getResource("/resources/BG.png"));
        AssistPatientCtrl tc = new AssistPatientCtrl(vm);
        MainCtrl.window.toBackStack(tf);
        vm.addController(tc);
        vm.initialize(name, lastname, id, ssn, messages, user);
        MainCtrl.window.setContentPane(vm);
    }

	/**
	 * Returns the name of the user
	 * 
	 * @return name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns an Assistant object representing the user
	 * 
	 * @return the user object
	 * @see Assistant
	 */
	public Assistant getAssist() {
		return tecnico;
	}
}
