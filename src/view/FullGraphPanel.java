package view;

import java.awt.*;
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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.ECG;
import model.FileManager;

public class FullGraphPanel extends JPanel implements ChangeListener {

    ECG ECGData;

    private static final long serialVersionUID = 1L;
    FileManager fichero;

    private static int initialValue = 0;
    private JSlider slider;
    JFreeChart chart;
    int lastValue = initialValue;

    XYSeriesCollection dataset = new XYSeriesCollection();
    
    int m;
    double rangeScroll = 1000;
    double minimum = 0;
    double maximum = 2000;
    double max;

    public FullGraphPanel(ECG ecgData) {
        super(new BorderLayout());

        ECGData = ecgData;
        
        m = 1000 / ECGData.getFrequency();//PERIODO
        max = (ECGData.getFrequency()*ECGData.getData().size())*0.001;
        System.out.println(max);
        XYDataset dataset = createDataset();
        chart = createChart(dataset);
        chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createEtchedBorder()
        );
        chartPanel.setBorder(border);
        add(chartPanel);

        JPanel dashboard = new JPanel(new BorderLayout());
        dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));

        this.slider = new JSlider(0,100, 0);
        this.slider.addChangeListener(this);
        dashboard.add(this.slider);
        add(dashboard, BorderLayout.SOUTH);
    }

    public XYDataset createDataset() {

        final XYSeries series1 = new XYSeries("");
        final XYSeries series2 = new XYSeries("");

        Double[] nums = ECGData.getData().toArray(new Double[ECGData.getData().size()]);

        int a = 0;
        for (int i = 0;i < nums.length; i++) {
            series2.add(m*i, nums[a]);
            a++;
        }
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;

    }

    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "INSERT_TITLE_HERE", // chart title
                "INSERT_UNIT_HERE", // x axis label
                "INSERT_UNIT_HERE", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                true, // tooltips
                false // urls
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.darkGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        return chart;

    }
    
    @Override
    public void stateChanged(ChangeEvent arg0) {
        // TODO Auto-generated method stub

        int value = this.slider.getValue();
        
        if (value < lastValue ) { // left
            minimum = minimum - rangeScroll;
            maximum = maximum - rangeScroll;
        } else if (value > lastValue) { // right
            minimum = minimum + rangeScroll;
            maximum = maximum + rangeScroll;
        }

        chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
        lastValue = value;
        //System.out.println(value);
    }

}
