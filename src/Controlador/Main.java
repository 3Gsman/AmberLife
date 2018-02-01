/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Fichero;
import Vista.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main implements ActionListener {

    ViewLogin vistaLogin;
    JTextField idBox;
    JPasswordField pwBox;
    JFrame frame;

    //Constructor por defecto
    public Main(ViewLogin w) {
        vistaLogin = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaLogin.blogin)) {
            aceptarVentana();
        }
    }

    public void aceptarVentana() {
        String usuario = vistaLogin.idBox.getText();	//recoge el contenido del JTextField
        char caracteres[] = vistaLogin.pwBox.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
        String Password = String.valueOf(caracteres);	//Convierte los elementos del array en un String
        Fichero comprobar = new Fichero();	//crea un nuevo gestor de ficheros
        try {
            String resultado[] = comprobar.comprobarUsuario(usuario, Password);
            if (resultado[0] == "true") {
                switch (resultado[1]) {
                    case "medico":
                        //Abre ventana Medico
                        System.out.print("Abriendo medico");
                        openMedico(usuario);
                        break;
                    case "tecnico":
                        //Abre ventana Tecnico
                        System.out.print("Abriendo tecnico");
                        openTecnico(usuario);
                        break;
                    case "admin":
                        //Abre ventana admin
                        System.out.print("Abriendo admin");
                        openAdmin(usuario);
                        break;

                    default:
                        //En el caso de que no sea ninguno de los tres usuarios.
                        Object frame = null;	//crea un objeto ventana
                        JOptionPane.showMessageDialog((Component) frame, "Tipo de usuario desconocido.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error
                }
            } else {	//si el resultado de la comparación es falso
                Object frame = null;	//crea un objeto ventana
                JOptionPane.showMessageDialog((Component) frame, "El usuario o la contraseña es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error
            }
        } catch (IOException e1) {
            e1.printStackTrace();	//imprime el registro de la pila donde se dio la excepción
        }
    }

    public void openMedico(String usuario) throws IOException {

        vistaLogin.frame.setVisible(false);	//Cierra la ventana de inicio
        Medico vp = new Medico();	//crea nueva ventana

    }

    public void openTecnico(String usuario) throws IOException {

        vistaLogin.frame.setVisible(false);	//Cierra la ventana de inicio
        Tecnico vm = new Tecnico();	//crea nueva ventana

    }

    public void openAdmin(String usuario) throws IOException {

        vistaLogin.frame.setVisible(false);	//Cierra la ventana de inicio
        Admin vm = new Admin();	//crea nueva ventana

    }
    //ESTO NO SE MUY BIEN QUE HACE

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            aceptarVentana();
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {

        }

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }

        ViewLogin ex = new ViewLogin();
        Main mc = new Main(ex);
        ex.addController(mc);
        ex.CreateViewLogin();

    }

}