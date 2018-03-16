package control.doctor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.ReturnsToFrame;
import model.Doctor;
import model.FileManagement;
import model.Patient;
import view.assistant.AssistFr;
import view.dialogs.ExitDialog;
import view.dialogs.PatientDialog;
import view.doctor.DoctorFr;
import view.doctor.DoctorPatientFr;
import view.panels.PatientPanel;

public class DoctorCtrl extends ReturnsToFrame implements ActionListener, MouseListener, WindowListener, KeyListener{

	String name;
	DoctorFr df;
	Doctor doctor;
	
	public DoctorCtrl(String user, DoctorFr vd) throws IOException {
		FileManagement file = new FileManagement();
		
		df = vd;
		name = user;
		doctor = file.readDoctor(name);
		
		int i = 0;
		FileManagement getpatients = new FileManagement();
		for(i = 0; i < doctor.getPatientlist().size(); i ++) {
			
			doctor.getPatientlist().set(i,getpatients.readPatient(doctor.getPatientlist().get(i).getNumber()));

		}
	}
	
	public void registerPatient() {
		//Pasar a MCV
		PatientDialog pd = new PatientDialog(df);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BACK")){ 
			returnToPrevious();
			df.dispose();
		}
		else if (e.getActionCommand().equals("NEW")) {
			registerPatient();
		}else if(e.getActionCommand().equals("SEARCH")) {
			try {
				searchPatient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	@Override
	//ONLY PATIENTPANELS ARE MEANT TO BE LISTENED TO.
	public void mouseClicked(MouseEvent e) {
		try {
			PatientPanel p = (PatientPanel) e.getSource();
			df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr();
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor,p.getPatient());
	        dpc.setPreviousWindow(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    dpf.setVisible(true);
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorCtrl");
		} catch (IOException e1) {
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
	
	public void searchPatient() throws IOException {
		String dni = df.getID();
		FileManagement id = new FileManagement();
		boolean found= false;
		
		Patient resultado = id.checkId(dni);
		Patient p = null;
		
		for(int i = 0; i<doctor.getPatientlist().size(); i++) {
			if(resultado.getId().equals(doctor.getPatientlist().get(i).getId())) {
				found = true;
				p = doctor.getPatientlist().get(i);
				
			}
		}
		resultado = p;
		
		if(found == true) {
			System.out.println("Patient found.\n");		
			
			df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr();
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor, resultado);
	        dpc.setPreviousWindow(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    dpf.setVisible(true);
			
		}else {
			Object frame = null;	//crea un objeto ventana
            JOptionPane.showMessageDialog((Component) frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error

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

	@Override
	public void keyTyped(KeyEvent e) {
		df.initializeList();
        df.repaint();
        df.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	

