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
import model.Fichero;
import model.Tecnico;

public class TecnCtrl implements ActionListener, KeyListener{

	TecnFr tf;
	String name;
	Tecnico tecnico;
	private JTextField textField;
	
	public TecnCtrl(String user, TecnFr vm) throws IOException {
		tf = vm;
		name = user;
		tecnico = new Tecnico(user);
	
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
		Fichero id = new Fichero();
		int i;
		String resultado[] = id.comprobarId(dni);
		Tecnico[] test = id.obtenerTecnicos();
		if(resultado[0] == "true") {
			System.out.println("Patient found.\n");
			System.out.println("Technician: " + getName());


			for(i = 0; i < 6;i++) {
				System.out.println("DNI de tecnico: " + test[i].getId());
				
			}
			
			//PONER LO QUE SE HAGA CUANDO ENCUENTRA A UN PACIENTE. NUEVA VENTANA?
		}else {
			Object frame = null;	//crea un objeto ventana
            JOptionPane.showMessageDialog((Component) frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error

		}

	}
	
	public String getName() {
		return name;
	}
	
	public Tecnico getTecnico() {
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
