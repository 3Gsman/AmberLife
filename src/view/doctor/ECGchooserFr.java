package view.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import control.doctor.ECGchooserCtrl;
import model.ECG;
import view.layouts.WrapLayout;
import view.panels.EcgPanel;
import view.panels.JPanelWithBackground;

@SuppressWarnings("serial")
public class ECGchooserFr extends JPanel {
	
	ECGchooserCtrl controller;
	
	public ECGchooserFr(){
	}
	
	public void addController(ECGchooserCtrl c) {
		controller = c;
	}
	
	
	public void initialize() throws IOException {

		/*Dimension d = new Dimension(1200, 800);
		this.setMinimumSize(d);
		this.setSize(d);*/
		
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
		this.add(jp);
		
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
