package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import control.MainCtrl;
import control.assistant.AssistMeasureCtrl;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import model.DBManagement;
import model.ECG;
//import model.JavaRXTX_vFINAL;
import view.assistant.AssistMeasureFr;
import view.assistant.AssistPatientFr;
import view.panels.JPanelWithBackground;

public class ECGConfDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));

	String assistID;
	String patientID;
	String dataPort;
	AssistPatientFr patient;

	private JLabel lfrec;
	private JLabel linfo;
	private JButton bEnd = new JButton("End");
	private JButton bStart = new JButton("Start");
	private JComboBox<Object> boxfrec;


	PanamaHitek_Arduino ino = new PanamaHitek_Arduino();

	static Vector<Double> ecg = new Vector<Double>();

	private SerialPortEventListener listener = new SerialPortEventListener() {
		@Override
		public void serialEvent(SerialPortEvent spe) {
			try {
				if (ino.isMessageAvailable()) {
					//System.out.println(ino.printMessage());
					ecg.add(Double.parseDouble(ino.printMessage()));
				}
			} catch (SerialPortException | ArduinoException ex) {
				// Logger.getLogger(ECGConfDialog.class.getName()).log(Level.SEVERE,
				// null, ex);
			}
		}
	};

	public ECGConfDialog(String assistID, String patientID, AssistPatientFr patient, String dataPort)
			throws IOException {
		super(MainCtrl.getMainFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		this.assistID = assistID;
		this.patientID = patientID;
		this.patient = patient;
		this.dataPort = dataPort;

		try {
			ino.arduinoRXTX(dataPort, 9600, listener);
		} catch (ArduinoException ex) {
			// Logger.getLogger(ECGConfDialog.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
		addItems();
		//frame.getContentPane().add(panel, BorderLayout.CENTER);
		contentPanel.validate();
		contentPanel.setVisible(true);
		this.setVisible(true);
	}

	private void addItems() throws IOException {
		this.setTitle("New ECG Properties");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 15, 0, 0, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 120, 15, 0 };
		gbl_contentPanel.rowHeights = new int[] { 40, 15, 30, 0, 40, 0, 0, 0, 0, 60, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(400, 300);
		this.setSize(d);
		this.setResizable(false);

		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		Font font = new Font("Verdana", Font.PLAIN, 28); // Default font;
		Font sf = font; // will use sf to change the style;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sf = font.deriveFont(28f);
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 14;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);

			JLabel lblNewLabel = new JLabel("Select ECG Properties");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(sf);
			panel.add(lblNewLabel);
		}

		bEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				try {
					ino.sendData("0");
					System.out.println(ecg);
				} catch (ArduinoException | SerialPortException ex) {
					// Logger.getLogger(ECGConfDialog.class.getName()).log(Level.SEVERE,
					// null, ex);
				}

				String datafrec = boxfrec.getSelectedItem().toString();
				int frecint = Integer.parseInt(datafrec);

				ECG ecginfo = new ECG(1, frecint, ecg, "");

				AssistMeasureFr tef = null;
				try {
					tef = new AssistMeasureFr(getClass().getResource("/resources/BG.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				AssistMeasureCtrl tec = new AssistMeasureCtrl(tef, ecginfo, assistID, patientID);
				tef.addController(tec);
				try {
					tef.initialize();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainCtrl.toBackStack(patient);
				MainCtrl.setPanel(tef);

				System.out.println("Pantalla ECG");

			}
		});
		bEnd.setForeground(Color.WHITE);
		bEnd.setFont(sf);
		bEnd.setBackground(Color.DARK_GRAY);
		bEnd.setBorder(null);
		bEnd.setEnabled(false);
		GridBagConstraints gbc_btnbEnd = new GridBagConstraints();
		gbc_btnbEnd.fill = GridBagConstraints.BOTH;
		gbc_btnbEnd.insets = new Insets(0, 0, 0, 5);
		gbc_btnbEnd.gridx = 3;
		gbc_btnbEnd.gridy = 9;
		contentPanel.add(bEnd, gbc_btnbEnd);

		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				linfo.setText("Measurement in process");
				
				String datafrec = boxfrec.getSelectedItem().toString();
				
				try {
					ino.sendData(datafrec);
					// ino.sendData(datatime);

				} catch (ArduinoException | SerialPortException ex) {
					// Logger.getLogger(ECGConfDialog.class.getName()).log(Level.SEVERE,
					// null, ex);
				}

				System.out.println("Frecuencia elegida: " + datafrec);

				bStart.setEnabled(false);
				boxfrec.setEnabled(false);
				bEnd.setEnabled(true);

			}
		});
		bStart.setForeground(Color.WHITE);
		bStart.setFont(sf);
		bStart.setBackground(Color.DARK_GRAY);
		bStart.setBorder(null);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 14;
		gbc_btnNewButton.gridy = 9;
		contentPanel.add(bStart, gbc_btnNewButton);

		lfrec = new JLabel("Frecuency (Hz)", SwingConstants.CENTER);
		lfrec.setFont(sf);
		lfrec.setForeground(Color.WHITE);
		lfrec.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbc_lfrec = new GridBagConstraints();
		gbc_lfrec.insets = new Insets(0, 0, 0, 5);
		gbc_lfrec.fill = GridBagConstraints.BOTH;
		gbc_lfrec.gridx = 1;
		gbc_lfrec.gridy = 2;
		gbc_lfrec.gridwidth = 8;
		contentPanel.add(lfrec, gbc_lfrec);
		
		linfo = new JLabel("Initiate the measurement", SwingConstants.CENTER);
		linfo.setFont(sf);
		linfo.setForeground(Color.WHITE);
		linfo.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbc_linfo = new GridBagConstraints();
		gbc_linfo.insets = new Insets(0, 0, 0, 5);
		gbc_linfo.fill = GridBagConstraints.BOTH;
		gbc_linfo.gridx = 1;
		gbc_linfo.gridy = 6;
		gbc_linfo.gridwidth = 16;
		contentPanel.add(linfo, gbc_linfo);

		Object[] frec = { "25", "50", "100", "150", "200", "250" };
		boxfrec = new JComboBox<Object>(frec);
		boxfrec.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		boxfrec.setBorder(null);
		boxfrec.setOpaque(false);
		boxfrec.setEnabled(true);
		GridBagConstraints gbc_boxfrec = new GridBagConstraints();
		gbc_boxfrec.insets = new Insets(0, 0, 0, 5);
		gbc_boxfrec.fill = GridBagConstraints.BOTH;
		gbc_boxfrec.gridx = 1;
		gbc_boxfrec.gridy = 4;
		gbc_boxfrec.gridwidth = 13;
		contentPanel.add(boxfrec, gbc_boxfrec);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

/*
 * 
 * SIMULADOR NUEVO
 * 
 * boolean flag=false; int frec=0; int
 * ecg[]={336,280,366,385,399,435,495,397,299,288,292,300,300,296,293,293,295,
 * 312,299,316,0,329,354,374,427,480,350,274,267,280,293,295,294,0,299,319,307,
 * 348,0,354,378,402,463,493,350,298,298,309,319,318,316,317,320,334,320,335,0,
 * 361,381,396,432,493,390,307,299,304,314,317,314,315,320,322,328,323,472,0,372
 * ,389,413,479,480,333,296,297,304,311,309,307,307,306,314,322,310,353,0,350,
 * 371,392,451,484,337,278,271,283,0,286,285,286,291,293,310,296,314,0,344,367,
 * 386,432,492,382,300,294,300,313,314,312,313,317,319,325,332,326,331,586,328,
 * 381,396,422,486,441,324,304,310,314,320,317,311,315,314,317,319,323,316,320,
 * 571,316,366,380,406,474,428,305,280,283,288,291,291,291,295,296,296,303,311,
 * 303,0,0,347,371,392,458,480,337,287,282,294,305,307,307,311,311,315,336,319,
 * 355,0,365,390,407,463,507,373,309,302,311,322,322,317,320,320,324,327,334,325
 * ,475,0,369,387,405,462,471,336,295,292,298,304,303,299,300,302,305,304,321,
 * 304,331,0,336,365,380,424,476,358,283,279,282,289,292,293,296,299,302,306,323
 * ,310,338,0,349,377,400,454,508,372,300,294,301,313,317,316,318,322,326,345,
 * 332,354,0,371,394,413,462,521,393,313,305,311,320,321,319,317,314,313,313,326
 * ,313,391,0,348,368,385,440,466,335,283,280,286,295,295,295,294,297,299,302,
 * 314,303,313,308,339,367,381,417,485,408,306,293,299,306,309,312,312,314,319,
 * 323,333,320,333,0,360,383,401,444,509,401,303,291,296,308,310,305,306,307,306
 * ,320,308,320,0,347,366,383,421,485,380,287,276,281,290,298,293,294,294,297,
 * 316,299,307,551,307,356,372,404,473,420,305,284,288,296,306,304,306,308,315,
 * 320,326,325,450,0,371,396,419,482,498,355,316,313,323,333,332,328,328,332,348
 * ,334,338,595,298,391,408,442,522,465,324,299,300,309,316,312,306,307,316,306,
 * 311,561,242,359,372,400,475,425,293,271,271,285,289,286,290,291,291,307,296,
 * 369,0,341,365,384,440,469,338,288,289,301,310,316,317,322,331,337,330,340,561
 * ,359,386,397,431,506,431,313,298,299,310,315,313,317,319,328,324,331,575,222,
 * 385,402,436,512,461,317,288,288,298,304,300,299,299,303,307,301,423,0,348,367
 * ,384,453,458,317,278,275,286,295,295,296,297,300,301,304,316,309,329,0,340,
 * 370,388,428,484,388,307,300,307,312,318,320,322,322,325,328,345,329,345,0,362
 * ,392,412,460,514,397,316,302,308,318,320,317,315,319,321,340,323,326,500,51,
 * 367,384,406,473,467,318,279,277,288,301,294,289,285,287,291,301,292,298,514,
 * 317,348,366,395,461,409,299,281,286,297,304,304,306,311,314,320,333,321,334,
 * 103,361,384,404,440,504,420,323,307,314,320,324,323,323,322,326,327,338,317,
 * 345,0,346,365,383,432,475,337,262,249,257,266,265,259,259,265,268,280,272,283
 * ,78,324,343,360,399,463,379,284,274,281,294,299,300,302,308,310,336,341}; int
 * contador =0;
 * 
 * void setup() { // initialize the serial communication: Serial.begin(9600);
 * //pinMode(10, INPUT); // Setup for leads off detection LO + //pinMode(11,
 * INPUT); // Setup for leads off detection LO -
 * 
 * }
 * 
 * void loop() {
 * 
 * if (Serial.available()>0){ String intro=Serial.readString();
 * frec=intro.toInt();
 * 
 * if (frec>0){ flag=true;
 * 
 * }else if (frec==0){ flag=false; }
 * 
 * }
 * 
 * if (flag==true){
 * 
 * Serial.println(ecg[contador]); contador++; delay(1000/frec); } if
 * (flag==false){
 * 
 * }
 * 
 * 
 * }//loop
 * 
 * 
 * 
 * SIMULADOR ANTIGUO
 * 
 * void setup() { // Declaramos que utilizaremos el pin 13 como salida
 * pinMode(13, OUTPUT); //Iniciamos la comunicación con el puerto serie
 * Serial.begin(9600);
 * 
 * 
 * }
 * 
 * float output;
 * 
 * void loop() { //En caso que haya información en el Serial Port, se entra en
 * esta estructura
 * 
 * 
 * 
 * for (int ia=0; ia <= 10; ia++){
 * 
 * for (int i=0; i <= 20; i++){
 * 
 * if (i == 10){ output = 800; Serial.println(output); delay(200); }else if (i
 * == 11){ output = -50; Serial.println(output); delay(200); }else if (i == 16){
 * output = 400; Serial.println(output); delay(200); }else{ output = 0;
 * Serial.println(output); delay(200); } } } }
 * 
 * 
 * 
 */