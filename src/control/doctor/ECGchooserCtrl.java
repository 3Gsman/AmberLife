package control.doctor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import control.ReturnsToFrame;
import model.ECG;
import view.doctor.DoctorMeasureFr;
import view.doctor.ECGchooserFr;
import view.panels.EcgPanel;

public class ECGchooserCtrl extends ReturnsToFrame implements ActionListener, MouseListener, WindowListener{
	
	ECGchooserFr frame;
	ECG first;
	DoctorPatientCtrl controller;
	
	public ECGchooserCtrl(DoctorPatientCtrl c ,ECGchooserFr f, ECG first){
		frame = f;
		controller = c;
		this.first = first;
	}

	public DoctorPatientCtrl getController() {
		return controller;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			EcgPanel ecg = (EcgPanel) e.getSource();
			controller.compare(first,ecg.getECG());
			frame.dispose();
			//this.returnToPrevious();
			System.out.println("Selected ECG: " + ecg.getECG().getName());
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		returnToPrevious();
		frame.dispose();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}