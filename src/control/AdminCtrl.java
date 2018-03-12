package control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import model.FileManager;
import model.Doctor;
import model.Assistant;
import view.AdminFr;
import view.AssistDialog;
import view.DoctorDialog;
import view.ExitDialog;
import view.PatientDialog;

public class AdminCtrl extends ReturnsToFrame implements ActionListener, WindowListener {

	public AdminFr af;
	private Vector<Assistant> listatecnicos;
	private Vector<Doctor> listamedicos;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		FileManager conseguirListas = new FileManager();
		
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
		DoctorDialog dd = new DoctorDialog(af);
		
	}
	
	public void newAssist() {
		//Pasar a MCV
		AssistDialog dd = new AssistDialog(af);
		
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
