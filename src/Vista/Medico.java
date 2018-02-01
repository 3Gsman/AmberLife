/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Fichero;
import javax.swing.*;

public class Medico extends JFrame {

    Fichero fichero;

    public Medico() {

        JFrame frame = new JFrame("Medico");
        frame.setSize(600, 300);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}