package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MessagePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MessagePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 60, 30, 0, 0, 0, 0, 0, 0, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.2, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.2, 0.2, 0.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.2, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{5, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{20, 20, 0, 0};
		gbl_panel_3.rowHeights = new int[]{40, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_3.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("from");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("John Doe");
		lblNewLabel_1.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 8;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 10;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_1.add(panel_4, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{5, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.CENTER);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{20, 20, 0, 0};
		gbl_panel_5.rowHeights = new int[]{40, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		panel_5.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDate = new JLabel("date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
		panel_7.add(lblDate, BorderLayout.CENTER);
		
		JLabel label = new JLabel("28 / 3 / 18");
		label.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		panel_5.add(label, gbc_label);
		
		JButton btnNewButton = new JButton("");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 19;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 19;
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 16));
		scrollPane.setViewportView(textPane);

	}

}
