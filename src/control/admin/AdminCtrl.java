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
		DBManagement conseguirListas = new DBManagement();
		
		af = fr;
		listatecnicos = conseguirListas.getAssistants();
		listamedicos = conseguirListas.getDoctors();
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
    		}
    	}
    	else if (e.getActionCommand().equals("ASSISTANTS")){
    		if(af.getMode() != false) {
    				af.initializeAssistants(listatecnicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    		}
    	}
    	else if (e.getActionCommand().equals("BACK")){
    		MainCtrl.window.popBackStack();	
    	}else if (e.getActionCommand().equals("NEWDOCTOR")) {
			newDoctor();
		}else if (e.getActionCommand().equals("NEWASSIST")) {
			newAssist();
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
	 * 
	 * @throws		IOException
	 * @see         DoctorDialog
	 */
	public void newDoctor() {
		try {
			 dd = new DoctorDialog(MainCtrl.window, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates a dialog for adding new assistants to the DB (Placeholder only)
	 * 
	 * @throws		IOException
	 * @see         AssistDialog
	 */
	public void newAssist() {
		try {
			ad = new AssistDialog(MainCtrl.window, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
