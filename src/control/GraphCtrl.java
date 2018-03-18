package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ECG;
import model.FileManagement;
import view.panels.FullGraphPanel;

public class GraphCtrl implements ChangeListener, ActionListener, MouseWheelListener {
	
	private ECG ECGData;
	private FileManagement fichero;
	private FullGraphPanel graph;

	/**
	 * Class constructor. Sets an associated FullGraphPanel
	 * 
	 * @param graph	the associated FullGraphPanel
	 */
	public GraphCtrl(FullGraphPanel graph){
		this.graph = graph;
	}

	/**
	 * Detects when the slider changes, and scrolls the graph according to the slider.
	 * 
	 * @param a triggering event 
	 */
	@Override
	public void stateChanged(ChangeEvent a) {

		graph.value = graph.slider.getValue();

		graph.minimum = (graph.t / 100) * (graph.value + graph.zoom2);
		graph.maximum = (graph.t / 100) * (graph.value + graph.zoom);

		graph.chart.getXYPlot().getDomainAxis().setRange(graph.minimum, graph.maximum);
		
		System.out.println(graph.value);
	}
	
	/**
	 * Listens to event commands emitted by FullGraphPanel, and reacts to them accordingly:
	 * 
	 * zoomin:      Zooms the chart in
	 * zoomout		Zooms the chart out.
	 * reset:		Resets the zoom to default levels.
	 *
	 * @param e triggering event
	 */
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
	
	/**
	 * Listens to the mouse wheel, and changes when the graph is scrolled up or down.
	 * 
	 * @param e 	triggering event
	 */
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
