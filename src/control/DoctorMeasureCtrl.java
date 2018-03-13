package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ECG;
import model.FileManager;
import view.AssistMeasureFr;
import view.CompareGraphPanel;
import view.ExitDialog;

public class DoctorMeasureCtrl extends ReturnsToFrame implements ActionListener, WindowListener {

	JFrame fr;
	ECG e;

	public DoctorMeasureCtrl(JFrame thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}

	public ECG getECG() {
		return e;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: " + e.getActionCommand());
		if (e.getActionCommand().equals("BACK")) {
			returnToPrevious();
			fr.dispose();
		} else if (e.getActionCommand().equals("COMPARE")) {

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("ECGs", "txt");
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory(new java.io.File("./src/resources"));
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
				String filename = chooser.getSelectedFile().getName();
				FileManager f = new FileManager();
				ECG ecg;
				try {
					ecg = f.readECG(filename);
					CompareGraphPanel cgp = new CompareGraphPanel(ecg, ecg);
					// AssistMeasureCtrl tec = new AssistMeasureCtrl(tef,ecg);
					//tec.setPreviousWindow(patient);
					// patient.setVisible(false);
					// cgp.addController(tec);
					// cgp.initialize();
					cgp.setVisible(true);
					System.out.println("Pantalla ECG");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				System.out.println("Invalid Action");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ExitDialog.confirmExit();

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

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
