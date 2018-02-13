/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

import model.FileManager;

public class DoctorFr extends JFrame {

    FileManager fichero;

    public DoctorFr() {

        JFrame frame = new JFrame("DoctorFr");
        frame.setSize(600, 300);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}