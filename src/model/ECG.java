package model;

import java.util.Vector;

public class ECG {
	String name;
	int frequency;
	Vector<Double> data;
	String report;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Vector<Double> getData() {
		return data;
	}
	public void setData(Vector<Double> data) {
		this.data = data;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}
