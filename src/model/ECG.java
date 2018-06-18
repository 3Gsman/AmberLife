package model;

import java.util.Vector;

public class ECG {
	private int id;
	private int frequency;
	private Vector<Double> data;
	private String report;
	private String date;
	
	/**
	 * Class constructor. Sets the name of the file,  the frequency, and the report.
	 * 
	 * @param n		name of the ECG file
	 * @param f		number of measures per second
	 * @param r		message attached to the ECG
	 */		
	public ECG(int id, int frequency, Vector<Double> data, String report, String date) {
		this.setId(id);
		this.frequency = frequency;
		this.data = data;
		this.report = report;
		this.date = date;
	}

	/**
	 * Getter for the frequency attribute.
	 * 
	 * @return the number of measurements per second
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Setter for the frequency attribute.
	 * 
	 * @param frequency		the number of measurements per second
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * Getter for the data attribute.
	 * 
	 * @return the vector of measurements
	 */
	public Vector<Double> getData() {
		return data;
	}

	/**
	 * Setter for the data attribute.
	 * 
	 * @param data		the vector of measurements
	 */
	public void setData(Vector<Double> data) {
		this.data = data;
	}
	
	/**
	 * Getter for the report attribute.
	 * 
	 * @return the report message
	 */
	public String getReport() {
		return report;
	}
	
	/**
	 * Setter for the report attribute.
	 * 
	 * @param report		the report message
	 */
	public void setReport(String report) {
		this.report = report;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
