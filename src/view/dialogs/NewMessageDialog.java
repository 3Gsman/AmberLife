package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MainCtrl;
import view.panels.JPanelWithBackground;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class NewMessageDialog extends JDialog {

	private final JPanel contentPanel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));


	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public NewMessageDialog(JFrame f, ActionListener windowToRefresh, String fullname, String IDuser, String IDptt, String text) throws IOException {
		super(f,Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 10, 60, 15, 0};
		gbl_contentPanel.rowHeights = new int[]{40, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo.png"));
		this.setIconImage(img.getImage());
		Dimension d = new Dimension(400, 300);
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("New Message: " + fullname);
		
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
		
		sf = font.deriveFont(22f);
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
			
			JLabel lblNewLabel = new JLabel("New message");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(sf);
			panel.add(lblNewLabel);
		}
		
		JTextArea area = new JTextArea();
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setFont(new Font("Source Code Pro Medium",Font.PLAIN,14));
		area.append(text);
		
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.gridwidth = 14;
		gbc_text.gridheight = 7;
		gbc_text.insets = new Insets(0, 0, 5, 5);
		gbc_text.fill = GridBagConstraints.BOTH;
		gbc_text.gridx = 1;
		gbc_text.gridy = 1;
		
		contentPanel.add(area,gbc_text);
		
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(sf);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.setBorder(null);
		btnCancel.setActionCommand("CANCEL");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 12;
		gbc_btnCancel.gridy = 9;
		contentPanel.add(btnCancel, gbc_btnCancel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection c = null;
				try {
					Class.forName("org.mariadb.jdbc.Driver");
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
				// String userdb = "pi2_amberlife";
				// String pass = "rdysdhsks";

				String db = "jdbc:mariadb://51.15.70.19:3306/proyecto2";
				String userdb = "dani";
				String pass = "gaja";
				try {
					c = DriverManager.getConnection(db, userdb, pass);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String sql = "INSERT INTO Message(IDuser, IDptt, Data, Date, Seen) VALUES(?,?,?,?,?)";
				
				try {
					c = DriverManager.getConnection(db, userdb, pass);
					PreparedStatement st = c.prepareStatement(sql);
					st.setString(1, IDuser);
					st.setString(2, IDptt);
					st.setString(3, area.getText());
					st.setString(4, String.valueOf(new Timestamp(System.currentTimeMillis())));
					st.setInt(5,1);
					st.executeUpdate();			
					st.close();
					c.close();
					windowToRefresh.actionPerformed(new ActionEvent(this, 0, "MESSAGE_UPDATE"));
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("New Message confirmed");
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(sf);
		btnNewButton.setBorder(null);
		btnNewButton.setActionCommand("CONFIRM");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 14;
		gbc_btnNewButton.gridy = 9;
		contentPanel.add(btnNewButton, gbc_btnNewButton);
		
		this.setVisible(true);
	}


}
