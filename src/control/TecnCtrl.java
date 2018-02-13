package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.*;
import control.*;
import model.FIleManager;
import model.Assistant;

public class TecnCtrl implements ActionListener, KeyListener{

	TecnFr tf;
	String name;
	Assistant tecnico;
	
	public TecnCtrl(String user, TecnFr vm) throws IOException {
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
				buscarPaciente();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
		
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
				buscarPaciente();
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
	
	public void buscarPaciente() throws IOException {
		String dni = tf.getID();
		FIleManager id = new FIleManager();
		int i;
		String resultado[] = id.comprobarId(dni);
		Assistant[] test = id.obtenerTecnicos();
		if(resultado[0] == "true") {
			System.out.println("Patient found.\n");
			System.out.println("Technician: " + getName());
			
			String pname = resultado[2];
			String psurname = resultado[3];
			
			
			openPatientTecn(dni,pname,psurname,name);
			
		}else {
			Object frame = null;	//crea un objeto ventana
            JOptionPane.showMessageDialog((Component) frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error

		}

	}
	
	public void openPatientTecn(String dni, String pname, String psurname, String user) throws IOException {

    	//TecnFr.setVisible(false); 
        TecnPatientFr vm = new TecnPatientFr(dni, pname, psurname, name);
        TecnPatientCtrl tc = new TecnPatientCtrl();
        vm.addController(tc);
        vm.initialize(dni, pname, psurname, name);
        vm.setVisible(true);

    }


	public String getName() {
		return name;
	}
	
	public Assistant getTecnico() {
		return tecnico;
	}
	
	
		 


	public void volverLogin() throws IOException {
        tf.dispose();  //Cierra la ventana de tecnico
        LoginFr login = new LoginFr(); //crea nueva ventana
        Main mc = new Main (login);    //crea nuevo controlador de ventana
        login.addController(mc);   //asigna el controlador a la ventana creada
        login.initialize();
		
	}
}
