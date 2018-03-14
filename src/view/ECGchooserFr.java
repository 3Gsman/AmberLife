package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import control.DoctorCtrl;
import control.ECGchooserCtrl;
import model.ECG;

public class ECGchooserFr extends JFrame {
	
	ECGchooserCtrl controller;
	
	public ECGchooserFr(){
	}
	
	public void addController(ECGchooserCtrl c) {
		controller = c;
	}
	
	
	public void initialize() throws IOException {

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(controller);
		
		this.setTitle("Compare");
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(1200, 800);
		this.setMinimumSize(d);
		this.setSize(d);
		
		JPanel jp = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));
		JScrollPane sp = new JScrollPane();
		sp.setBackground( new Color(0, 0, 0, 0) );
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setOpaque(false);
		sp.getVerticalScrollBar().setUnitIncrement(18);
		JPanel viewport = new JPanel();
		viewport.setOpaque(false);
		viewport.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 40));
		loadECGPanel(controller.getController().getPatient().getECGs(),viewport);
		sp.setViewportView(viewport);
		sp.getViewport().setOpaque(false);
		sp.setViewportBorder(null);
		sp.setBorder(null);
		jp.setOpaque(false);
		jp.setLayout(new BorderLayout(0, 0));
		jp.add(sp, BorderLayout.CENTER);
		this.setContentPane(jp);
		
		this.setVisible(true);
	}
	
	public void loadECGPanel(Vector<ECG> v, JPanel viewport) {		
		
		for(ECG e : v)
			try {
				viewport.add(new EcgPanel(e,this.controller));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
