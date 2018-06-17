package view.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import control.doctor.ECGchooserCtrl;
import model.ECG;
import view.layouts.WrapLayout;
import view.panels.EcgPanel;
import view.panels.JPanelWithBackground;

@SuppressWarnings("serial")
public class ECGchooserFr extends JPanelWithBackground {
	
	public ECGchooserFr(URL url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}

	ECGchooserCtrl controller;
	
	
	public void addController(ECGchooserCtrl c) {
		controller = c;
	}
	
	
	public void initialize() throws IOException, SQLException {

		/*Dimension d = new Dimension(1200, 800);
		this.setMinimumSize(d);
		this.setSize(d);*/
		
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
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 0));
		this.add(sp, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void loadECGPanel(Vector<ECG> v, JPanel viewport) throws SQLException {		
		
		for(ECG e : v)
			try {
				viewport.add(new EcgPanel(e,this.controller));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
