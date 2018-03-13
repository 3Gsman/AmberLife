package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.ECG;
import model.FileManager;

public class CompareGraphPanel extends JPanel implements ChangeListener, ActionListener {

	ECG ECGData;
	ECG ECGData2;

	private static final long serialVersionUID = 1L;
	FileManager fichero;

	static int initialValue = 0;
	JSlider slider;
	JButton bzoomin;
	JButton bzoomout;
	JButton reset;
	JFreeChart chart;
	JFreeChart chart2;
	
	int lastValue = initialValue;

	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeriesCollection dataset2 = new XYSeriesCollection();

	public int m;
	public int value;
	public double minimum;
	public double maximum;
	public double t;
	public double frec;
	public double frec2;
	public double frecsec;
	public double frecsec2;
	public double firstsec;
	public double zoom = 3;
	public double zoom2 = -1 * (zoom);
	
	double datanum;
	double prop;
	double wi ;

	public CompareGraphPanel(ECG ecgData,ECG ecgData2) {
		super(new BorderLayout());

		ECGData = ecgData;
		ECGData2 = ecgData2;
		
		frec = ECGData.getFrequency();
		frec2 = ECGData2.getFrequency();
		datanum = ECGData.getData().size();
		prop = datanum/frec; // tiempo total
		firstsec = prop / frec;		
		wi = firstsec/frec;	
		frecsec = 1000 / frec;
		frecsec2 = 1000 / frec2;
		t = (frecsec * ECGData.getData().size());
		XYDataset dataset = createDataset();
		XYDataset dataset2 = createDataset2();
		
		
		JPanel graphBoard = new JPanel();
		graphBoard.setOpaque(false);
		GridBagLayout gl1 = new GridBagLayout();
		// dashboard_gbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
		gl1.columnWidths = new int[] { 180 };
		gl1.rowHeights = new int[] { 80,80 };
		gl1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gl1.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		graphBoard.setLayout(gl1);
		
		chart = createChart(dataset);
		chart2 = createChart(dataset2);
		minimum = (t / prop) * zoom2;
		maximum = (t / prop) * zoom;
		chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		chart2.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		ChartPanel chartPanel = new ChartPanel(chart);
		ChartPanel chartPanel2 = new ChartPanel(chart2);
		chartPanel.setDomainZoomable(true);
		chartPanel.setRangeZoomable(true);
		
		GridBagConstraints gbc_chartPanel = new GridBagConstraints();
		gbc_chartPanel.gridwidth = 1;
		gbc_chartPanel.insets = new Insets(0, 0, 0, 0);
		gbc_chartPanel.fill = GridBagConstraints.BOTH;
		gbc_chartPanel.gridx = 0;
		gbc_chartPanel.gridy = 0;
		
		GridBagConstraints gbc_chartPanel2 = new GridBagConstraints();
		gbc_chartPanel2.gridwidth = 1;
		gbc_chartPanel2.insets = new Insets(0, 0, 0, 0);
		gbc_chartPanel2.fill = GridBagConstraints.BOTH;
		gbc_chartPanel2.gridx = 0;
		gbc_chartPanel2.gridy = 1;
		
		graphBoard.add(chartPanel,gbc_chartPanel);
		graphBoard.add(chartPanel2,gbc_chartPanel2);
		
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

		this.bzoomin = new JButton("+");
		this.bzoomin.setActionCommand("zoomin");

		this.bzoomout = new JButton("-");
		this.bzoomout.setActionCommand("zoomout");
		this.reset = new JButton("Reset");
		this.reset.setActionCommand("reset");

		this.slider = new JSlider(0, 100, 0);
		this.slider.addChangeListener(this);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 1;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		dashboard.add(this.slider, gbc_slider);

		GridBagConstraints gbc_bzoomout = new GridBagConstraints();
		gbc_bzoomout.gridwidth = 1;
		gbc_bzoomout.insets = new Insets(0, 0, 5, 5);
		gbc_bzoomout.fill = GridBagConstraints.BOTH;
		gbc_bzoomout.gridx = 2;
		gbc_bzoomout.gridy = 0;
		dashboard.add(this.bzoomout, gbc_bzoomout);

		GridBagConstraints gbc_bzoomin = new GridBagConstraints();
		gbc_bzoomin.gridwidth = 1;
		gbc_bzoomin.insets = new Insets(0, 0, 5, 5);
		gbc_bzoomin.fill = GridBagConstraints.BOTH;
		gbc_bzoomin.gridx = 3;
		gbc_bzoomin.gridy = 0;
		dashboard.add(this.bzoomin, gbc_bzoomin);

		GridBagConstraints gbc_reset = new GridBagConstraints();
		gbc_reset.gridwidth = 1;
		gbc_reset.insets = new Insets(0, 0, 5, 5);
		gbc_reset.fill = GridBagConstraints.BOTH;
		gbc_reset.gridx = 0;
		gbc_reset.gridy = 0;
		dashboard.add(this.reset, gbc_reset);

		bzoomin.addActionListener(this);
		bzoomout.addActionListener(this);
		reset.addActionListener(this);

		add(dashboard, BorderLayout.SOUTH);

	}

	public XYDataset createDataset() {

		final XYSeries series1 = new XYSeries("");
		final XYSeries series2 = new XYSeries("");

		Double[] nums = ECGData.getData().toArray(new Double[ECGData.getData().size()]);

		int a = 0;
		for (int i = 0; i < nums.length; i++) {
			series2.add(frecsec * i, nums[a]);
			a++;
			
		}

		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;

	}
	public XYDataset createDataset2() {

		final XYSeries series12 = new XYSeries("");
		final XYSeries series22 = new XYSeries("");

		Double[] nums2 = ECGData.getData().toArray(new Double[ECGData.getData().size()]);
		
		int a = 0;
		for (int i = 0; i < nums2.length; i++) {
			series22.add(frecsec * i, nums2[a]);
			a++;
		}

		dataset2.addSeries(series12);
		dataset2.addSeries(series22);

		return dataset2;

	}

	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart chart = ChartFactory.createXYLineChart("", // chart
																	// title
				"", // x axis label
				"", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, false, // include legend
				true, // tooltips
				false// urls
		);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = chart.getXYPlot();
		XYItemRenderer renderer = chart.getXYPlot().getRenderer();
		renderer.setSeriesPaint(1, new Color(247, 205, 39, 255));
		plot.setBackgroundPaint(Color.darkGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		return chart;

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

		value = this.slider.getValue();

		minimum = (t / prop) * (value + zoom2);
		maximum = (t / prop) * (value + zoom);
				
		chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		chart2.getXYPlot().getDomainAxis().setRange(minimum, maximum);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("zoomin")) {
			if (zoom >= -1.0) {
				zoom = zoom - 1;
			}
			System.out.println("zoom in");
			minimum = (t / prop) * (value + zoom2);
			maximum = (t / prop) * (value + zoom);
			chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
			chart2.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		}

		if (e.getActionCommand().equals("zoomout")) {
			zoom = zoom + 1;
			System.out.println("zoom out");
			minimum = (t / prop) * (value + zoom2);
			maximum = (t / prop) * (value + zoom);
			chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
			chart2.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		}

		if (e.getActionCommand().equals("reset")) {

			zoom = 3;

			minimum = (t / prop) * (value + zoom2);
			maximum = (t / prop) * (value + zoom);
			chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
			chart2.getXYPlot().getDomainAxis().setRange(minimum, maximum);
		}
	}

}
