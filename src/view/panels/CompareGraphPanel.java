package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


import control.GraphCtrl;
import model.ECG;

public class CompareGraphPanel extends JPanel{

	ECG ECGData;
	ECG ECGData2;

	private static final long serialVersionUID = 1L;


	public CompareGraphPanel(ECG ecgData,ECG ecgData2) {
		super(new BorderLayout());

		ECGData = ecgData;
		ECGData2 = ecgData2;
		
		
		JPanel graphBoard = new JPanel();
		graphBoard.setOpaque(false);
		GridBagLayout gl1 = new GridBagLayout();
		// dashboard_gbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
		gl1.columnWidths = new int[] { 180 };
		gl1.rowHeights = new int[] { 80,80 };
		gl1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gl1.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		graphBoard.setLayout(gl1);
		
		
		FullGraphPanel gr = new FullGraphPanel(ecgData);
		GraphCtrl gc = new GraphCtrl(gr);
		gr.addController(gc);
    	gr.setBackground(Color.DARK_GRAY.darker());
    	gr.setOpaque(true);
    	
    	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 1;
		//gbc_panel_1.gridheight = 9;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		graphBoard.add(gr, gbc_panel_1);
		
		FullGraphPanel gr2 = new FullGraphPanel(ecgData2);
		GraphCtrl gc2 = new GraphCtrl(gr2);
		gr2.addController(gc2);
		gr2.setBackground(Color.DARK_GRAY.darker());
		gr2.setOpaque(true);
    	
    	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
    	gbc_panel_2.gridwidth = 1;
    	//gbc_panel_2.gridheight = 9;
    	gbc_panel_2.fill = GridBagConstraints.BOTH;
    	gbc_panel_2.gridx = 0;
    	gbc_panel_2.gridy = 1;
		graphBoard.add(gr2, gbc_panel_2);

		add(graphBoard);
		
		
		JPanel dashboard = new JPanel();
		GridBagLayout dashboard_gbl = new GridBagLayout();
		// dashboard_gbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
		dashboard_gbl.columnWidths = new int[] { 20, 120, 20, 20, 0, 0 };
		dashboard_gbl.rowHeights = new int[] { 30 };
		dashboard_gbl.columnWeights = new double[] { 0.1, 2.0, 0.1, 0.1, 0.0, 0.0, Double.MIN_VALUE };
		dashboard_gbl.rowWeights = new double[] { 0.25, Double.MIN_VALUE };
		dashboard.setLayout(dashboard_gbl);

		dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));

		
	}


	
}
