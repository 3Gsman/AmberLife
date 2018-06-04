package view.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.MainCtrl;
import model.LocalizationService;
import model.Patient;
import view.dialogs.AssistDialog;
import view.dialogs.DoctorDialog;
import view.dialogs.PatientDialog;

@SuppressWarnings("serial")
public class PatientPanel extends JPanel {
	
	private Patient p;
	private String doctorID;
	private ActionListener windowToRefresh;
	/**
	 * Create the panel.
	 */
	
	
	public PatientPanel() {
		try {
			initialize(new Patient("1","John","Doe","2222X"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PatientPanel(Patient p, MouseListener con, ActionListener windowToRefresh, String doctorID) {
		this.p = p;
		this.doctorID = doctorID;
		this.windowToRefresh = windowToRefresh;
		try {
			initialize(p,con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize(Patient p, MouseListener con) throws IOException {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 60, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 60, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 10, 40, 0, 15, 60, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.setBackground(new Color(250,250,250,255));
		
		Color grey = new Color(80, 77, 77, 255);
		
		//Get PROMETHEUS font
		java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
		Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
		Font sf = font; // will use sf to change the style;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			sf = font;
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 15;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(grey);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel(LocalizationService.getWord("name"));
		lblNewLabel.setForeground(Color.WHITE);
		sf = font.deriveFont(Font.PLAIN, 22f);
		lblNewLabel.setFont(sf);
		panel_2.add(lblNewLabel);
		
		JLabel label_1 = new JLabel(" ");
		label_1.setForeground(Color.WHITE);
		sf = font.deriveFont(Font.PLAIN, 22f);
		label_1.setFont(sf);
		label_1.setBackground(grey);
		panel.add(label_1);
		
		JLabel lblJohnDoe = new JLabel(p.getName() + " " + p.getLastname());
		lblJohnDoe.setForeground(grey);
		lblJohnDoe.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
		lblJohnDoe.setBackground(grey);
		panel.add(lblJohnDoe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(grey);
		panel_1.add(panel_3);
		
		JLabel lblId = new JLabel(LocalizationService.getWord("id"));
		lblId.setForeground(Color.WHITE);
		sf = font.deriveFont(Font.PLAIN, 22f);
		lblId.setFont(sf);
		panel_3.add(lblId);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setForeground(Color.WHITE);
		sf = font.deriveFont(Font.PLAIN, 22f);
		label_2.setFont(sf);
		label_2.setBackground(grey);
		panel_1.add(label_2);
		
		JLabel label = new JLabel(p.getId());
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(grey);
		label.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
		panel_1.add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PatientDialog pd = new PatientDialog(MainCtrl.window,windowToRefresh, doctorID ,p.getId());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(getClass().getResource("/resources/penicon.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.fill = GridBagConstraints.VERTICAL;
		gbc_button.gridx = 13;
		gbc_button.gridy = 5;
		add(button, gbc_button);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Patient Deletion initiated");
				int confirm = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this patient?",
															"Warning",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION){
					try {
						Connection c = null;

						Class.forName("org.mariadb.jdbc.Driver");

						//String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
						//String userdb = "pi2_amberlife";
						//String pass = "rdysdhsks";

						String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
						String userdb = "dani";
						String pass = "gaja";
						c = DriverManager.getConnection(db, userdb, pass);
						
						Statement stmt =  c.createStatement();
						
						stmt.execute("DELETE FROM Patient WHERE IDptt LIKE '" + p.getId()+ "'");
						//stmt.execute("DELETE FROM Patient WHERE IDptt LIKE " + p.getId());
						//stmt.execute("DELETE FROM Patient WHERE IDptt LIKE " + p.getId());
						stmt.close();
						c.close();
						windowToRefresh.actionPerformed(new ActionEvent(this, 0, "PATIENT_UPDATE"));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else System.out.println("Deletion Cancelled");
		}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resources/canicon.png")));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 14;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);
		
		this.addMouseListener(con);
	}
	
	public Patient getPatient() {
		return p;
	}
}
