/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.FileManagement;
import model.LocalizationService;
import view.*;
import view.admin.AdminFr;
import view.assistant.AssistFr;
import view.dialogs.ExitDialog;
import view.doctor.DoctorFr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import control.admin.AdminCtrl;
import control.assistant.AssistCtrl;
import control.doctor.DoctorCtrl;

public class LoginCtrl implements ActionListener, KeyListener {

    private LoginFr vistaLogin = new LoginFr();

   /**
    * Class constructor, set associated window.
    * @param w	associated frame
    * @see LoginFr
    */
    public LoginCtrl(LoginFr w) {
        vistaLogin = w;
    }

    /**
	 * Listens to event commands emitted by AdminFr, and reacts to them accordingly:
	 * 
	 * LOGIN:    	Check if the user exists, and open a window depending on their privileges
	 * LANGUAGE:	Switch to the next language
	 *
	 * @param  e event triggering the action performed
	 * @see         LoginFR
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
    	System.out.print("Action received: " + e.getActionCommand());
    	if (e.getActionCommand().equals("LOGIN")){
    		aceptarVentana();
    	}
    	else if (e.getActionCommand().equals("LANGUAGE")){
    		LocalizationService.rotate();
    		try {
    			/*vistaLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			vistaLogin.dispose();*/
				vistaLogin.addController(this);
				vistaLogin.initialize();
				vistaLogin.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		vistaLogin.setVisible(true);
    	}
    	else System.out.println(" Null");
    }
   
    /**
     * Equivalent to pressing the Login button, listen to the button in the associated frame and check if enter was 
     * pressed while it had the focus.
     * 
     */
    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed");
    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            aceptarVentana();
        } 
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	// TODO Auto-generated method stub
    }
     
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get username and password, check if such an user exists and what kind of user it is, and then open a window
	 * corresponding to the user's privileges.
	 */
    public void aceptarVentana() {
        String usuario = vistaLogin.getUsername();	//recoge el contenido del JTextField
        char caracteres[] = vistaLogin.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
        String Password = String.valueOf(caracteres);	//Convierte los elementos del array en un String
        FileManagement comprobar = new FileManagement();	//crea un nuevo gestor de ficheros
        try {
            String resultado[] = comprobar.checkUser(usuario, Password);
            if (resultado[0] == "true") {
            	vistaLogin.resetText();
                switch (resultado[1]) {
                    case "medico":
                        //Abre ventana DoctorFr
                        System.out.print("Opening Doctor Console\n");
                        openDoctor(usuario);
                        break;
                    case "tecnico":
                        //Abre ventana TecnFr
                        System.out.print("Opening Assistant Console\n");
                        openAssistant(usuario);
                        break;
                    case "admin":
                        //Abre ventana admin
                        System.out.print("Opening Admin Console\n");
                        openAdmin(usuario);
                        break;
                    default:
                        //En el caso de que no sea ninguno de los tres usuarios.
                        Object frame = null;	//crea un objeto ventana
                        JOptionPane.showMessageDialog((Component) frame, "Unknown user Type.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error
                }
            } else {	//si el resultado de la comparación es falso
                Object frame = null;	//crea un objeto ventana
                JOptionPane.showMessageDialog((Component) frame, "User or password doesn't match.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error
            }
        } catch (IOException e1) {
            e1.printStackTrace();	//imprime el registro de la pila donde se dio la excepción
        }
    }

	/**
	 * Open the Doctor window
	 * 
	 * @param usuario	the name of the program's user registered in the DB
	 * @throws IOException
	 * @see DoctorFr, DoctorCtrl
	 */
    public void openDoctor(String usuario) throws IOException {

    	MainCtrl.window.toBackStack(vistaLogin);	//Cierra la ventana de inicio
        DoctorFr vp = new DoctorFr(getClass().getResource("/resources/BG.png"));	//crea nueva ventana
        DoctorCtrl dc = new DoctorCtrl(usuario,vp);
        
        vp.addController(dc);
        MainCtrl.window.setContentPane(vp);
        vp.initialize();
        MainCtrl.window.setVisible(true);
        
    }
    
	/**
	 * Open the Assistant window
	 * 
	 * @param usuario	the name of the program's user registered in the DB
	 * @throws IOException
	 * @see AssistFr, AssistCtrl
	 */
    public void openAssistant(String usuario) throws IOException {

    	MainCtrl.window.toBackStack(vistaLogin);	
        AssistFr vm = new AssistFr(getClass().getResource("/resources/BG.png"));
        AssistCtrl tc = new AssistCtrl(usuario, vm);
        vm.addController(tc);
        vm.initialize();
        MainCtrl.window.setContentPane(vm);
        MainCtrl.window.setVisible(true);
    }
    
	/**
	 * Open the Admin window
	 * 
	 * @param usuario	the name of the program's user registered in the DB
	 * @throws IOException
	 * @see AdminFr, AdminCtrl
	 */
    public void openAdmin(String usuario) throws IOException {

    	MainCtrl.window.toBackStack(vistaLogin);	
        AdminFr vm = new AdminFr(getClass().getResource("/resources/BG.png"));	//crea nueva ventana
        AdminCtrl ac = new AdminCtrl(vm);
        vm.addController(ac);
        vm.initialize(true, ac.getDoctorList(),new Dimension(0,0));
        MainCtrl.window.setContentPane(vm);
        MainCtrl.window.setVisible(true);
        
        System.out.println("Opened the admin panel");
    }
}