package control.admin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import control.ReturnsToFrame;
import model.FileManagement;
import model.Doctor;
import model.Assistant;
import view.admin.AdminFr;
import view.dialogs.AssistDialog;
import view.dialogs.DoctorDialog;
import view.dialogs.ExitDialog;
import view.dialogs.PatientDialog;

public class AdminCtrl extends ReturnsToFrame implements ActionListener, WindowListener {

	public AdminFr af;
	private Vector<Assistant> listatecnicos;
	private Vector<Doctor> listamedicos;
	DoctorDialog dd;
	AssistDialog ad;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		FileManagement conseguirListas = new FileManagement();
		
		af = vm;
		listatecnicos = conseguirListas.getAssistants();
		listamedicos = conseguirListas.getDoctors();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	System.out.print("Action received: " + e.getActionCommand());
    	if (e.getActionCommand().equals("DOCTORS")){
    		if(af.getMode() != true) {
    				/*boolean max = false;
    				if (af.getExtendedState() == JFrame.MAXIMIZED_BOTH) max = true;
    				af.dispose();
    				af = new AdminFr();
    				af.addController(this);
					af.initialize(true, listamedicos,new Dimension(af.getWidth(),af.getHeight()));
					if (max)af.setExtendedState( af.getExtendedState()|JFrame.MAXIMIZED_BOTH );*/
    				af.initializeDoctors(listamedicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
				
    		}
    	}
    	else if (e.getActionCommand().equals("ASSISTANTS")){
    		if(af.getMode() != false) {
    				/*boolean max = false;
    				if (af.getExtendedState() == JFrame.MAXIMIZED_BOTH) max = true;
    				af.dispose();
    				af = new AdminFr();
    				af.addController(this);
					af.initialize(false, listatecnicos, new Dimension(af.getWidth(),af.getHeight()));
					if (max)af.setExtendedState( af.getExtendedState()|JFrame.MAXIMIZED_BOTH );*/
    				af.initializeAssistants(listatecnicos);
    				af.setButtons();
    				af.repaint();
    				af.setVisible(true);
    		}
    	}
    	else if (e.getActionCommand().equals("BACK")){
    		af.dispose();
    		returnToPrevious();  		
    	}else if (e.getActionCommand().equals("NEWDOCTOR")) {
			newDoctor();
		}else if (e.getActionCommand().equals("NEWASSIST")) {
			newAssist();
		}
    
    	
    }
	
	
	public Vector<Doctor> getDoctorList(){
		return listamedicos;
	}
	
	public Vector<Doctor> getAssistantList(){
		return listamedicos;
	}
	
	public void newDoctor() {
		//Pasar a MCV
		try {
			 dd = new DoctorDialog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void newAssist() {
		//Pasar a MCV
		try {
			ad = new AssistDialog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void windowClosing(WindowEvent e)
    { 
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
