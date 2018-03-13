package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import model.ECG;
import view.DoctorMeasureFr;
import view.ECGchooserFr;
import view.EcgPanel;

public class ECGchooserCtrl extends ReturnsToFrame implements ActionListener, MouseListener{
	
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
			this.returnToPrevious();
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

}
