package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.panels.JPanelWithBackground;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class NewMessageDialog extends JDialog {

	private final JPanel contentPanel = new JPanelWithBackground(getClass().getResource("/resources/BG.png"));


	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public NewMessageDialog(String name, String lastname, String text) throws IOException {
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
		this.setTitle("New Message: " + name + " " + lastname);
		
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
