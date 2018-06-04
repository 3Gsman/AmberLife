package control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import control.MainCtrl;
import model.FileManagement;
import model.Doctor;
import model.Assistant;
import model.DBManagement;
import view.admin.AdminFr;
import view.dialogs.AssistDialog;
import view.dialogs.DoctorDialog;


public class AdminCtrl implements ActionListener {

	private AdminFr af;
	private Vector<Assistant> listatecnicos;
	private Vector<Doctor> listamedicos;
	private DoctorDialog dd;
	private AssistDialog ad;
	boolean doctor_mode = true;
	
	
	/**
	 * Class constructor, sets the related frame and gets the lists of Doctors and Assistants
	 *
	 * @throws IOException
	 * @param  fr	Frame related to the controller	
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see    AdminFr
	 */
	public AdminCtrl(AdminFr fr) throws IOException, ClassNotFoundException, SQLException {
		af = fr;
		listatecnicos = DBManagement.getAssistants();
		listamedicos = DBManagement.getDoctors();
	}
	
	
	/**
	 * Listens to event commands emitted by AdminFr, and reacts to them accordingly:
	 * 
	 * DOCTORS:     Show the list of doctors in the display panel
	 * ASSISTANTS:  Show the list of assistants in the display panel
	 * BACK: 		Go back to the previous window
	 * NEWDOCTOR:	Create a dialog for adding new doctors to the DB (Placeholder only)
	 * NEWASSIST:	Create a dialog for adding new assistants to the DB (Placeholder only)
	 *
	 * @param  e event triggering the action performed
	 * @see         AdminFr
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
    	System.out.print("Action received: " + e.getActionCommand());
    	if (e.getActionCommand().equals("DOCTORS")){
    		if(af.getMode() != true) {
    				af.initializeDoctors(listamedicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    				af.validate();
    				doctor_mode = true;
    		}
    	}
    	else if (e.getActionCommand().equals("ASSISTANTS")){
    		if(af.getMode() != false) {
    				af.initializeAssistants(listatecnicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    				af.validate();
    				doctor_mode = false;
    		}
    	}
    	else if (e.getActionCommand().equals("BACK")){
    		MainCtrl.window.popBackStack();	
    	}else if (e.getActionCommand().equals("NEWDOCTOR")) {
			try {
				newDoctor();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getActionCommand().equals("NEWASSIST")) {
			try {
				newAssist();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getActionCommand().equals("USER_UPDATE")) {
			if(doctor_mode) {
				try {
					listamedicos = DBManagement.getDoctors();
					af.initializeDoctors(listamedicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    				af.validate();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				try {
					listatecnicos = DBManagement.getAssistants();
    				af.initializeAssistants(listatecnicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    				af.validate();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else System.out.println("Invalid Action");
    
    	
    }
	
	/**
	 * Returns a list of all the doctors in the DB (uses a text file as a placeholder)
	 *
	 * @return      list of the doctors in the DB (uses a text file as a placeholder)
	 * @see         Doctor
	 */
	public Vector<Doctor> getDoctorList(){
		return listamedicos;
	}
	/**
	 * Returns a list of all the assistants in the DB (uses a text file as a placeholder)
	 *
	 * @return      list of the assistants in the DB (uses a text file as a placeholder)
	 * @see         Assistant
	 */
	public Vector<Doctor> getAssistantList(){
		return listamedicos;
	}
	
	/**
	 * Creates a dialog for adding new doctors to the DB (Placeholder only)
	 * @throws ClassNotFoundException 
	 * 
	 * @throws		IOException
	 * @see         DoctorDialog
	 */
	public void newDoctor() throws ClassNotFoundException {
		try {
			 dd = new DoctorDialog(MainCtrl.window, this, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates a dialog for adding new assistants to the DB (Placeholder only)
	 * @throws ClassNotFoundException 
	 * 
	 * @throws		IOException
	 * @see         AssistDialog
	 */
	public void newAssist() throws ClassNotFoundException {
		try {
			ad = new AssistDialog(MainCtrl.window, this, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
