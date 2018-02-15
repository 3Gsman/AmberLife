package control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import model.FileManager;
import model.Doctor;
import model.Assistant;
import view.AdminFr;

public class AdminCtrl extends ReturnsToFrame implements ActionListener {

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
    			try {
    				boolean max = false;
    				if (af.getExtendedState() == JFrame.MAXIMIZED_BOTH) max = true;
    				af.dispose();
    				af = new AdminFr();
    				af.addController(this);
					af.initialize(true, listamedicos,new Dimension(af.getWidth(),af.getHeight()));
					if (max)af.setExtendedState( af.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	}
    	else if (e.getActionCommand().equals("ASSISTANTS")){
    		if(af.getMode() != false) {
    			try {
    				boolean max = false;
    				if (af.getExtendedState() == JFrame.MAXIMIZED_BOTH) max = true;
    				af.dispose();
    				af = new AdminFr();
    				af.addController(this);
					af.initialize(false, listatecnicos, new Dimension(af.getWidth(),af.getHeight()));
					if (max)af.setExtendedState( af.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	}
    	else if (e.getActionCommand().equals("BACK")){
    		af.dispose();
    		returnToPrevious();  		
    	}
    
    	
    }
	
	
	public Vector<Doctor> getDoctorList(){
		return listamedicos;
	}
	
	public Vector<Doctor> getAssistantList(){
		return listamedicos;
	}
	
}
