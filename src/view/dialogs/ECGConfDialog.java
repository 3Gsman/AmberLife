package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import control.MainCtrl;
import control.assistant.AssistMeasureCtrl;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import model.ECG;
import model.JavaRXTX_vFINAL;
import view.assistant.AssistMeasureFr;
import view.assistant.AssistPatientFr;

public class ECGConfDialog extends JDialog implements ActionListener {
	
	JFrame frame;
	JPanel panel;
	
	private AssistPatientFr patient;
	private String assistID;
	private String patientID;

	private JLabel lfrec;
	private JLabel ltime;
	private JButton cancel;
	private JButton confirm;
	private JComboBox<Object> boxfrec;
	private JComboBox<Object> boxtime;

	PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	
	static Vector<Double> ecg = new Vector<Double>();

	private SerialPortEventListener listener = new SerialPortEventListener() {
		@Override
		public void serialEvent(SerialPortEvent spe) {
			try {
				if (ino.isMessageAvailable()) {

					// System.out.println(ino.printMessage());
					ecg.add(Double.parseDouble(ino.printMessage()));

				}
			} catch (SerialPortException | ArduinoException ex) {
				// Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE,
				// null, ex);
			}
		}
	};

	public ECGConfDialog() {

		frame = new JFrame("ECG Conf");
		panel = new JPanel();

		panel.setLayout(new GridLayout(3, 2));

		try {
			ino.arduinoRXTX("COM6", 9600, listener);
		} catch (ArduinoException ex) {
			Logger.getLogger(JavaRXTX_vFINAL.class.getName()).log(Level.SEVERE, null, ex);
		}

		addItems();

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

	}

	private void addItems() {

		lfrec = new JLabel("Frecuency (Hz)", SwingConstants.CENTER);
		ltime = new JLabel("Duratio (S)", SwingConstants.CENTER);

		cancel = new JButton("End");
		confirm = new JButton("Start");

		Object[] frec = { "25", "50", "100", "150", "200", "250" };
		boxfrec = new JComboBox<Object>(frec);
		boxfrec.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxfrec.setBorder(null);
		boxfrec.setOpaque(false);

		Object[] time = { "15", "30", "45", "60", "90", "120" };
		boxtime = new JComboBox<Object>(time);
		boxtime.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxtime.setBorder(null);
		boxtime.setOpaque(false);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				//ESTO NO VA AQUI VA EN PAUSA
				
				try {
					ino.sendData("0");
					System.out.println(ecg);
				} catch (ArduinoException | SerialPortException ex) {
					Logger.getLogger(JavaRXTX_vFINAL.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				
				ECG ecginfo = new ECG(1,200,ecg,"");
				
				AssistMeasureFr tef = null;
				try {
					tef = new AssistMeasureFr(getClass().getResource("/resources/BG.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    AssistMeasureCtrl tec = new AssistMeasureCtrl(tef,ecginfo, assistID, patientID);
			    tef.addController(tec);
			    try {
					tef.initialize();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    MainCtrl.setPanel(tef); 
			    
			    
			    
			    System.out.println("Pantalla ECG");

			}
		});

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String datafrec = boxfrec.getSelectedItem().toString();
				int frecint = Integer.parseInt(datafrec);

				String datatime = boxtime.getSelectedItem().toString();
				int timeint = Integer.parseInt(datatime);

				try {
					ino.sendData(datafrec);
					ino.sendData(datatime);

				} catch (ArduinoException | SerialPortException ex) {
					Logger.getLogger(JavaRXTX_vFINAL.class.getName()).log(Level.SEVERE, null, ex);
				}

				// MANDAR ESTOS DATOS A ARDUINO
				System.out.println(frecint);
				System.out.println(timeint);
				
				
				

				//frame.dispose();
			}
		});

		panel.add(lfrec);
		panel.add(ltime);
		panel.add(boxfrec);
		panel.add(boxtime);
		panel.add(cancel);
		panel.add(confirm);
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}


/*


void setup() {
  // Declaramos que utilizaremos el pin 13 como salida
  pinMode(13, OUTPUT);
  //Iniciamos la comunicación con el puerto serie
  Serial.begin(9600);
  
  
}

float output;

void loop() {
  //En caso que haya información en el Serial Port, se entra en esta estructura

  
  
  for (int ia=0; ia <= 10; ia++){
  
  for (int i=0; i <= 20; i++){
      
      if (i == 10){
      output = 800;
      Serial.println(output);
      delay(200);
      }else if (i == 11){
        output = -50;
      Serial.println(output);
      delay(200);
      }else if (i == 16){
      output = 400;
      Serial.println(output);
      delay(200);
      }else{
      output = 0;
      Serial.println(output);
      delay(200);
      }
  } 
  }
}


*/