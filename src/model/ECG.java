package model;

import java.util.Vector;

public class ECG {
	private String name;
	private int frequency;
	private Vector<Double> data;
	private String report;
	
	/**
	 * Class constructor. Sets the name of the file,  the frequency, and the report.
	 * 
	 * @param n		name of the ECG file
	 * @param f		number of measures per second
	 * @param r		message attached to the ECG
	 */		
	public ECG(String n, int f, String r) {
		name = n;
		frequency = f;
		report = r;
	}
	
	/**
	 * Class constructor. Sets the name of the file and the frequency.
	 * 
	 * @param n		name of the ECG file
	 * @param f		number of measures per second
	 */
	public ECG(String n, int f) {
		name = n;
		frequency = f;
		report = "";
	}

	public ECG() {
		
	}
	
	/**
	 * Getter for the name attribute.
	 * 
	 * @return the name of the file.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name attribute.
	 * 
	 * @param name		the name of the file
	 */
	public void setName(String name) {
		this.name = name;
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
	
}
