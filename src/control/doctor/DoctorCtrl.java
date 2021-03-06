package control.doctor;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.MainCtrl;
import model.DBManagement;
import model.Doctor;
import model.Patient;
import view.dialogs.PatientDialog;
import view.doctor.DoctorFr;
import view.doctor.DoctorPatientFr;
import view.panels.PatientPanel;
import javax.swing.JFrame;


public class DoctorCtrl implements ActionListener, MouseListener, KeyListener{

	private String username;
	private DoctorFr df;
	private Doctor doctor;
	private String lastTyped = "";
	
	/**
	 * Class constructor, sets the related frame and the username of the current user. From that username, it reads
	 * the information of the doctor in the DB (currently a text file).
	 *
	 * @param  user	Username of the current user	
	 * @param  f	Frame related to the controller	
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see    DoctorFr
	 */
	public DoctorCtrl(String user, DoctorFr f) throws IOException, ClassNotFoundException, SQLException {
		df = f;
		username = user;
		doctor = DBManagement.readDoctor(username);
			
		doctor.setPatientlist(DBManagement.readPatients(doctor.getId()));
	}

	/**
	 * Creates a dialog for adding new patients to the DB (Placeholder only)
	 * @throws ClassNotFoundException 
	 * 
	 * @see         PatientDialog
	 */
	public void registerPatient() throws ClassNotFoundException {
		try {
			PatientDialog pd = new PatientDialog(MainCtrl.getMainFrame(),this,doctor.getId(),null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateList() {
		df.initializeList();
	    df.repaint();
		lastTyped = df.getText();
		MainCtrl.validateMainFrame();
	}

	/**
	 * Listens to event commands emitted by DoctorFr, and reacts to them accordingly:
	 * 
	 * BACK:		Returns to the previous frame.
	 * NEW:			Calls the registerPatient() method to open a dialog to register a new Patient.
	 * SEARCH:		Deprecated. No longer used as the program's functionality evolved. Calls the searchPatient() method.
	 *
	 * @param  e event triggering the action performed
	 * @see         DoctorFr
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print("Action received: " + e.getActionCommand());
		if (e.getActionCommand().equals("BACK")){ 
			MainCtrl.popBackStack();
			
		}
		else if (e.getActionCommand().equals("NEW")) {
			try {
				registerPatient();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getActionCommand().equals("PATIENT_UPDATE")) {
			try {
				doctor.setPatientlist(DBManagement.readPatients(doctor.getId()));
				updateList();
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else System.out.println("Invalid Action");
		//DEPRECATED OPTION
		/*else if(e.getActionCommand().equals("SEARCH")) {
			try {
				searchPatient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
	}
	
	/**
	 * Returns a Doctor object representing the user
	 * 
	 * @return the user object
	 * @see Doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	
	/**
	 * Opens a new DoctorPatientFr detailing that patient's information, messages, and ECGs when a PatientPanel is clicked.
	 * 
	 * @param e The event triggering the method. Only PatientPanels are meant to be listened to.
	 * @see PatientPanel, DoctorPatientFr, DoctorPatientCtrl
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			PatientPanel p = (PatientPanel) e.getSource();
			df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr(getClass().getResource("/resources/BG.png"));
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor,p.getPatient());
	        MainCtrl.toBackStack(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    MainCtrl.setPanel(dpf);
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorCtrl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Repaints the list of patients when the doctor types in the searchbar, to make sure only relevant patients are displayed.
	 * 
	 * @param	e	KeyEvent triggering the method, in this case, the window closing.
	 * @see         DoctorFr
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(df.getText().length() > 0 || lastTyped.length() > 0) {
			df.initializeList();
	        df.repaint();
		}
		lastTyped = df.getText();
		MainCtrl.validateMainFrame();
	}

}
	

