/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Fichero;
import view.*;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main implements ActionListener, KeyListener {

    LoginFr vistaLogin;
    JTextField idBox;
    JPasswordField pwBox;
    JFrame frame;

    //Constructor por defecto
    public Main(LoginFr w) {
        vistaLogin = w;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	System.out.print("Action received: ");
    	if (e.getActionCommand().equals("LOGIN")){
    		System.out.println(" Login");
    		aceptarVentana();
    	}
    	else System.out.println(" Null");
    }
  
    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed");
    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            aceptarVentana();
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

    public void aceptarVentana() {
        String usuario = vistaLogin.getUsername();	//recoge el contenido del JTextField
        char caracteres[] = vistaLogin.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
        String Password = String.valueOf(caracteres);	//Convierte los elementos del array en un String
        Fichero comprobar = new Fichero();	//crea un nuevo gestor de ficheros
        try {
            String resultado[] = comprobar.comprobarUsuario(usuario, Password);
            if (resultado[0] == "true") {
                switch (resultado[1]) {
                    case "medico":
                        //Abre ventana DoctorFr
                        System.out.print("Opening Doctor Console");
                        openMedico(usuario);
                        break;
                    case "tecnico":
                        //Abre ventana TecnFr
                        System.out.print("Opening Assistant Console");
                        openTecnico(usuario);
                        break;
                    case "admin":
                        //Abre ventana admin
                        System.out.print("Opening Admin Console");
                        openAdmin(usuario);
                        break;
                    default:
                        //En el caso de que no sea ninguno de los tres usuarios.
                        Object frame = null;	//crea un objeto ventana
                        JOptionPane.showMessageDialog((Component) frame, "Unknown user Type.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error
                }
            } else {	//si el resultado de la comparaci�n es falso
                Object frame = null;	//crea un objeto ventana
                JOptionPane.showMessageDialog((Component) frame, "User or password doesn't match.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error
            }
        } catch (IOException e1) {
            e1.printStackTrace();	//imprime el registro de la pila donde se dio la excepci�n
        }
    }

    public void openMedico(String usuario) throws IOException {

    	vistaLogin.setVisible(false);	//Cierra la ventana de inicio
        DoctorFr vp = new DoctorFr();	//crea nueva ventana

    }

    public void openTecnico(String usuario) throws IOException {

    	vistaLogin.setVisible(false);	//Cierra la ventana de inicio
        TecnFr vm = new TecnFr();
        TecnCtrl tc = new TecnCtrl(vm);
        vm.addController(tc);
        vm.initialize();
        vm.setVisible(true);

    }

    public void openAdmin(String usuario) throws IOException {

    	vistaLogin.setVisible(false);	//Cierra la ventana de inicio
        AdminFr vm = new AdminFr();	//crea nueva ventana

    }
    
    //ESTO NO SE MUY BIEN QUE HACE

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
			LoginFr window = new LoginFr();
			Main mc = new Main(window);
		    window.addController(mc);
		    window.initialize();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    
    /*EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				LoginFr window = new LoginFr();
				vistaLogin.setVisible(true);
				Main mc = new Main(window);
			    window.addController(mc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});*/

}