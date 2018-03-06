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
    double minimum;
    double maximum;
    int t;
    double frec;
    double zoom = 5.0;
    double zoom2 = -1.0;

    public FullGraphPanel(ECG ecgData) {
        super(new BorderLayout());
        
        ECGData = ecgData;
        frec = ECGData.getFrequency();
        //m = 1000 / ECGData.getFrequency();//PERIODO
        t = (ECGData.getFrequency()*ECGData.getData().size());
        XYDataset dataset = createDataset();
        chart = createChart(dataset);
        minimum = (t/100)*-1;
        maximum = (t/100)*zoom;
        chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);

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
            series2.add(frec*i, nums[a]);
            a++;
        }
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;

    }

    private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "", // chart title
                "", // x axis label
                "", // y axis label
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

        int value = this.slider.getValue();
        
        
        
        minimum = (t/100)*(value-zoom2);
        maximum = (t/100)*(value+zoom);
        
        chart.getXYPlot().getDomainAxis().setRange(minimum, maximum);

    }

}
