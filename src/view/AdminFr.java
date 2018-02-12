/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

import model.FIleManager;

public class AdminFr extends JFrame {

    FIleManager fichero;

    public AdminFr() {

        JFrame frame = new JFrame("AdminFr");
        frame.setSize(600, 300);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}