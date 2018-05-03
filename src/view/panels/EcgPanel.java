
package view.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.SwingConstants;
import model.ECG;
import model.LocalizationService;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class EcgPanel extends JPanel {
		
		ECG e;
		
		public ECG getECG() {
			return e;
		}
		
		
		public  EcgPanel(ECG e, MouseListener con) throws IOException {
			this.e=e;
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{10, 60, 180, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 60, 180, 10, 0, 0};
			gridBagLayout.rowHeights = new int[]{15, 30, 15, 30, 40, 15, 19, 0, 15};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.2, 0.0, 0.2, 0.0, 2.0, 0.2, 0.0, 1.0, 0.2, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			Color grey = new Color(80, 77, 77, 255);
			
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
			
			//Get PROMETHEUS font
			java.io.InputStream is = getClass().getResourceAsStream("/resources/Prime.otf");
			Font font = new Font("Verdana", Font.PLAIN, 28); //Default font;
			Font sf = font; // will use sf to change the style;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
				sf = font;
			} catch (FontFormatException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
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
			sf = font.deriveFont(22f);
			label_1.setFont(sf);
			label_1.setBackground(grey);
			panel.add(label_1);
			
			JLabel label_4 = new JLabel(e.getName());
			label_4.setVerticalAlignment(SwingConstants.BOTTOM);
			label_4.setForeground(grey);
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
			panel_3.setBackground(grey);
			panel_1.add(panel_3);
			
			JLabel lblId = new JLabel(LocalizationService.getWord("freq"));
			lblId.setForeground(Color.WHITE);
			sf = font.deriveFont(22f);
			lblId.setFont(sf);
			panel_3.add(lblId);
			
			JLabel label_2 = new JLabel(" ");
			label_2.setForeground(Color.WHITE);
			sf = font.deriveFont(22f);
			label_2.setFont(sf);
			label_2.setBackground(grey);
			panel_1.add(label_2);
			
			JLabel lblms = new JLabel(e.getFrequency() + " ms");
			lblms.setVerticalAlignment(SwingConstants.BOTTOM);
			lblms.setForeground(grey);
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
			panel_5.setBackground(grey);
			panel_4.add(panel_5);
			
			JLabel lblAssist = new JLabel(LocalizationService.getWord("assist"));
			lblAssist.setForeground(Color.WHITE);
			sf = font.deriveFont(22f);
			lblAssist.setFont(sf);
			panel_5.add(lblAssist);
			
			JLabel label_3 = new JLabel(" ");
			label_3.setForeground(Color.WHITE);
			sf = font.deriveFont(22f);
			label_3.setFont(sf);
			label_3.setBackground(grey);
			panel_4.add(label_3);
			
			JLabel lblJohnDoe_1 = new JLabel("unknown");
			lblJohnDoe_1.setToolTipText("");
			lblJohnDoe_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblJohnDoe_1.setForeground(grey);
			lblJohnDoe_1.setFont(new Font("Source Code Pro Medium", Font.PLAIN, 22));
			panel_4.add(lblJohnDoe_1);
			
			
			//PAnel aqui
			
			PreviewGraphPanel panel_6 = new PreviewGraphPanel(e);
			panel_6.setBackground(grey);
			GridBagConstraints gbc_panel_6 = new GridBagConstraints();
			gbc_panel_6.gridwidth = 11;
			gbc_panel_6.gridheight = 9;
			gbc_panel_6.insets = new Insets(0, 0, 0, 0);
			gbc_panel_6.fill = GridBagConstraints.BOTH;
			gbc_panel_6.gridx = 7;
			gbc_panel_6.gridy = 0;
			add(panel_6, gbc_panel_6);
			
			this.addMouseListener(con);
		
		}

	}
