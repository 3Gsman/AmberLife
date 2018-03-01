<<<<<<< HEAD:src/view/GraphFr.java
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;


import control.AssistMeasureCtrl;
import model.FileManager;

public class GraphFr extends JPanel{

    /**
	 * 
	 */
	String file;
	
	private static final long serialVersionUID = 1L;
		FileManager fichero;
   
		
	public GraphFr(String filename) {
		file = filename;
	}
		
	public Double[] addData() throws IOException{
		FileManager nums = new FileManager();
		
		nums.readECG(file);
		//String v;
		//System.out.print(ecg.toString(v));
		
		Double[] grapgArr = nums.readECG(file).getData().toArray(new Double[nums.readECG(file).getData().size()]);
		
		return grapgArr;
		
		
	}
		
		
		//Double[] data = addData();
	
		final int PAD = 20;
	
	
	
    
	public void paintComponent(Graphics g) {
	    
		
		Double [] data = null;
		
		try {
			data = addData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    	super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        //g2.setBackground(Color.DARK_GRAY);
	        int w = getWidth();
	        double h = getHeight()/1.1;
	
	        Font font = g2.getFont();
	        FontRenderContext frc = g2.getFontRenderContext();
	        LineMetrics lm = font.getLineMetrics("0", frc);
	        float sh = lm.getAscent() + lm.getDescent();
	  
	    
	        double xInc = (double)(w - 2*PAD)/(data.length-1);
	        double scale = ((double)(h - 2*PAD)/getMax());
	        //double scale2 = ((double)(h - 2*PAD)/getMin());
	       // g.drawRect (10, 10, 200, 200);
	        
	       g2.setPaint(new Color(255,191,0,255));
	        for(int i = 0; i < data.length-1; i++) {
	            double x1 = PAD + i*xInc;
	            double y1 = h - PAD - scale*data[i];
	            double x2 = PAD + (i+1)*xInc;
	            double y2 = h - PAD - scale*data[i+1];
	            g2.draw(new Line2D.Double(x1, y1, x2, y2));
	        }
	
	    	
	    }
	
	 
	public double getMax() {
		
	Double [] data = null;
			
			try {
				data = addData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    double max = -Integer.MAX_VALUE;
	    for(int i = 0; i < data.length; i++) {
	        if(data[i] > max)
	            max = data[i];
	    }
	    return max;
	}
=======
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;

import javax.swing.*;

import model.ECG;

public class PreviewGraphPanel extends JPanel{

	ECG ECGData;
	
	private static final long serialVersionUID = 1L;

		
	public PreviewGraphPanel(ECG ecgData) {
		ECGData = ecgData;
	}
		
		
		
		//Double[] data = addData();
	
		final int PAD = 20;
	
	
	
    
	public void paintComponent(Graphics g) {
	    
		
		 Double[] nums = ECGData.getData().toArray(new Double[ECGData.getData().size()]);
		
		
	    	super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        //g2.setBackground(Color.DARK_GRAY);
	        int w = getWidth()*25;
	        double h = getHeight()/1.1;
	
	        Font font = g2.getFont();
	        FontRenderContext frc = g2.getFontRenderContext();
	        LineMetrics lm = font.getLineMetrics("0", frc);
	        float sh = lm.getAscent() + lm.getDescent();
	  
	    
	        double xInc = (double)(w - 2*PAD)/(nums.length-1);
	        double scale = ((double)(h - 2*PAD)/getMax());
	        //double scale2 = ((double)(h - 2*PAD)/getMin());
	       // g.drawRect (10, 10, 200, 200);
	        
	       g2.setPaint(new Color(255,191,0,255));
	        for(int i = 0; i < nums.length-1; i++) {
	            double x1 = PAD + i*xInc;
	            double y1 = h - PAD - scale*nums[i];
	            double x2 = PAD + (i+1)*xInc;
	            double y2 = h - PAD - scale*nums[i+1];
	            g2.draw(new Line2D.Double(x1, y1, x2, y2));
	        }
	
	    	
	    }
	
	 
	public double getMax() {
		
		Double[] nums = ECGData.getData().toArray(new Double[ECGData.getData().size()]);
		
			
		
	    double max = -Integer.MAX_VALUE;
	    for(int i = 0; i < nums.length; i++) {
	        if(nums[i] > max)
	            max = nums[i];
	    }
	    return max;
	}
>>>>>>> Grafica:src/view/PreviewGraphPanel.java
}