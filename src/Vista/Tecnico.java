/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Fichero;
import javax.swing.*;

public class Tecnico extends JFrame {

    Fichero fichero;

    public Tecnico() {

        JFrame frame = new JFrame("Tecnico");
        frame.setSize(600, 300);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}