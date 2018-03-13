package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.*;
import model.FileManager;
import model.Patient;
import model.Assistant;

public class AssistCtrl extends ReturnsToFrame implements ActionListener, KeyListener, WindowListener{

	AssistFr tf;
	String name;
	Assistant tecnico;
	
	public AssistCtrl(String user, AssistFr vm) throws IOException {
		tf = vm;
		name = user;
		tecnico = new Assistant(user);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("SEARCH")){
			System.out.println(" Search");
			try {
				searchPatient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
		
		 }else if (e.getActionCommand().equals("BACK")) {
			 tf.dispose();
			 returnToPrevious();
		 
		 }else if (e.getActionCommand().equals("ID")) {
			 if(!tf.getMode()) tf.switchbuttons();
			 tf.repaint();
		 }else if (e.getActionCommand().equals("SSN")) {
			 if(tf.getMode()) tf.switchbuttons();
			 tf.repaint();
		 }else System.out.println(" Null");
			/* try {
				volverLogin();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else System.out.println(" Null");
		
        if (e.getSource().equals(tf.blogout)){
        			System.out.println("Test1\n");
        }
        
       */
		
	}
	
    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed");
    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
				searchPatient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {
        	
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	
    }
     
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void searchPatient() throws IOException {
		String dni = tf.getID();
		FileManager id = new FileManager();
		Vector<String> messages = new Vector<>();
		
		Patient resultado = id.checkId(dni);
		messages = id.readPatientMessages(resultado.getNumber());
		
		if(resultado.getNumber() != "null") {
			System.out.println("Patient found.\n");
			System.out.println("Technician: " + getName());			
			
			openPatientTecn(resultado.getName(),resultado.getLastname(),resultado.getId(), resultado.getSsn(), messages, name);
			
		}else {
			Object frame = null;	//crea un objeto ventana
            JOptionPane.showMessageDialog((Component) frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error

		}

	}
	
	public void openPatientTecn(String name, String lastname, String id, String ssn, Vector<String> messages, String user) throws IOException {

    	//TecnFr.setVisible(false); 
        AssistPatientFr vm = new AssistPatientFr();
        AssistPatientCtrl tc = new AssistPatientCtrl(vm);
        tc.setPreviousWindow(tf);
        vm.addController(tc);
        vm.initialize(name, lastname, id, ssn, messages, user);
        vm.setVisible(true);
        tf.setVisible(false);

    }


	public String getName() {
		return name;
	}
	
	public Assistant getTecnico() {
		return tecnico;
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
	
	
		 

	//en desuso
	/*public void volverLogin() throws IOException {
        tf.setVisible(false);  //Cierra la ventana de tecnico
        LoginFr login = new LoginFr(); //crea nueva ventana
        Main mc = new Main (login);    //crea nuevo controlador de ventana
        login.addController(mc);   //asigna el controlador a la ventana creada
        login.initialize();
		
	}*/
}
