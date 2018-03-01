
	package view;

	import javax.swing.JPanel;
	import java.awt.GridBagLayout;
	import javax.swing.JLabel;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.awt.Color;
	import java.awt.Font;
	import javax.swing.SwingConstants;
	import java.awt.FlowLayout;
	import javax.swing.ImageIcon;


public class EcgPanel extends JPanel {
		
		
		private String name;
		private String id;
		
		
		public  EcgPanel(String name, String id) {
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{10, 60, 180, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 60, 60, 10, 0, 0};
			gridBagLayout.rowHeights = new int[]{15, 30, 15, 30, 40, 15, 19, 0, 15};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.2, 0.0, 0.2, 0.0, 2.0, 0.2, 0.0, 1.0, 0.2};
			setLayout(gridBagLayout);
			
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 5;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			add(panel, gbc_panel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.DARK_GRAY);
			FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
			flowLayout_2.setAlignment(FlowLayout.LEADING);
			panel.add(panel_2);
			
			JLabel lblNewLabel = new JLabel("date");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			panel_2.add(lblNewLabel);
			
			JLabel label_1 = new JLabel(" ");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			label_1.setBackground(Color.DARK_GRAY);
			panel.add(label_1);
			
			JLabel label_4 = new JLabel("24 2 2020");
			label_4.setVerticalAlignment(SwingConstants.BOTTOM);
			label_4.setForeground(Color.DARK_GRAY);
			label_4.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
			panel.add(label_4);
			
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
			flowLayout_1.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.gridwidth = 5;
			gbc_panel_1.insets = new Insets(0, 0, 5, 5);
			gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_1.gridx = 1;
			gbc_panel_1.gridy = 3;
			add(panel_1, gbc_panel_1);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(Color.DARK_GRAY);
			panel_1.add(panel_3);
			
			JLabel lblId = new JLabel("freq");
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			panel_3.add(lblId);
			
			JLabel label_2 = new JLabel(" ");
			label_2.setForeground(Color.WHITE);
			label_2.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			label_2.setBackground(Color.DARK_GRAY);
			panel_1.add(label_2);
			
			JLabel lblms = new JLabel("50ms");
			lblms.setVerticalAlignment(SwingConstants.BOTTOM);
			lblms.setForeground(Color.DARK_GRAY);
			lblms.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
			panel_1.add(lblms);
			
			JPanel panel_4 = new JPanel();
			FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
			flowLayout_3.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.gridwidth = 5;
			gbc_panel_4.insets = new Insets(0, 0, 5, 5);
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 1;
			gbc_panel_4.gridy = 6;
			add(panel_4, gbc_panel_4);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBackground(Color.DARK_GRAY);
			panel_4.add(panel_5);
			
			JLabel lblAssist = new JLabel("assist");
			lblAssist.setForeground(Color.WHITE);
			lblAssist.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			panel_5.add(lblAssist);
			
			JLabel label_3 = new JLabel(" ");
			label_3.setForeground(Color.WHITE);
			label_3.setFont(new Font("PROMETHEUS", Font.PLAIN, 22));
			label_3.setBackground(Color.DARK_GRAY);
			panel_4.add(label_3);
			
			JLabel lblJohnDoe_1 = new JLabel("John Doe");
			lblJohnDoe_1.setToolTipText("");
			lblJohnDoe_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblJohnDoe_1.setForeground(Color.DARK_GRAY);
			lblJohnDoe_1.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
			panel_4.add(lblJohnDoe_1);
			
			JPanel panel_6 = new JPanel();
			panel_6.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_panel_6 = new GridBagConstraints();
			gbc_panel_6.gridwidth = 11;
			gbc_panel_6.gridheight = 9;
			gbc_panel_6.insets = new Insets(0, 0, 5, 5);
			gbc_panel_6.fill = GridBagConstraints.BOTH;
			gbc_panel_6.gridx = 7;
			gbc_panel_6.gridy = 0;
			add(panel_6, gbc_panel_6);
		
		}

	}
