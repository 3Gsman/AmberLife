package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

import model.Doctor;
import model.ECG;
import model.FileManagement;
import model.Patient;
import view.doctor.DoctorPatientFr;
import view.panels.FullGraphPanel;

public class GraphCtrl implements ChangeListener, ActionListener, MouseWheelListener {
	
	ECG ECGData;
	FileManagement fichero;
	FullGraphPanel graph;

	public GraphCtrl(FullGraphPanel graph){
		this.graph = graph;
	}

	@Override
	public void stateChanged(ChangeEvent a) {

		graph.value = graph.slider.getValue();

		graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
		graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);

		graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
		
		System.out.println(graph.value);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("zoomin")) {
			if (graph.zoom >= -1.0) {
				graph.zoom = graph.zoom - 1;
			}
			System.out.println("zoom in");
			graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
			graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);
			graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
			
			System.out.println('+');
		}

		if (e.getActionCommand().equals("zoomout")) {
			graph.zoom = graph.zoom + 1;
			System.out.println("zoom out");
			graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
			graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);
			graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
			
			System.out.println('-');
		}

		if (e.getActionCommand().equals("reset")) {

			graph.zoom = 3;

			graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
			graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);
			graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
			
			System.out.println("reset");
		}
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			System.out.println("mouse wheel Up");

			if (graph.zoom >= -1.0) {
				graph.zoom = graph.zoom - 1;
			}

			System.out.println("zoom in");
			graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
			graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);
			graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);

		} else {

			if (graph.zoom >= 250.0) {
				graph.zoom = graph.zoom - 1;
			}

			System.out.println("mouse wheel Down" + graph.zoom);
			graph.zoom = graph.zoom + 1;
			System.out.println("zoom out");
			graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
			graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);
			graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
		}
		
		System.out.println("wheel");
	}
}
