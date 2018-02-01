/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.*;

public class ViewLogin extends JFrame {

    public JFrame frame;
    public JPanel panel;
    public JTextField idBox;
    public JPasswordField pwBox;
    public JLabel labelA;
    public JLabel labelB;
    public JLabel labelC;
    public JLabel labelD;
    public JLabel labelE;
    public JLabel labelF;
    public JButton blogin;
    private EventObject e;
    Main controlador;

    public void addController(Main mc) {
        controlador = mc;
    }

    public void CreateViewLogin() {

        frame = new JFrame("Login");
        panel = new JPanel();

        panel.setLayout(new GridLayout(3, 3));

        addWidgets();

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setSize(600, 300);
        frame.setVisible(true);
    }

    private void addWidgets() {

        labelA = new JLabel("", SwingConstants.CENTER);

        idBox = new JTextField("User");
        idBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idBox.setText("");
            }
        });

        labelB = new JLabel("", SwingConstants.CENTER);
        labelC = new JLabel("", SwingConstants.CENTER);

        pwBox = new JPasswordField("Password");
        pwBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pwBox.setText("");
            }
        });

        labelD = new JLabel("");
        labelE = new JLabel("");
        blogin = new JButton("Login");
        labelF = new JLabel("");

        //blogin.addActionListener(controlador);
        panel.add(labelA);
        panel.add(idBox);
        panel.add(labelB);
        panel.add(labelC);
        panel.add(pwBox);
        panel.add(labelD);
        panel.add(labelE);
        panel.add(blogin);
        panel.add(labelF);

        blogin.addActionListener(controlador);
    }

}