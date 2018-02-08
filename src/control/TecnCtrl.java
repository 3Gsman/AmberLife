package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextField;

import view.*;
import control.*;

public class TecnCtrl implements ActionListener{

	TecnFr tf;
	private JTextField textField;
	
	public TecnCtrl(TecnFr vm) {
		tf = vm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("SEARCH")){
			System.out.println(" Search");
			buscarPaciente();
				 
		
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
	
	public void buscarPaciente() {
		System.out.println(tf.getID());
	}
	
	
		 


	public void volverLogin() throws IOException {
        tf.dispose();  //Cierra la ventana de tecnico
        LoginFr login = new LoginFr(); //crea nueva ventana
        Main mc = new Main (login);    //crea nuevo controlador de ventana
        login.addController(mc);   //asigna el controlador a la ventana creada
        login.initialize();
		
	}
}
