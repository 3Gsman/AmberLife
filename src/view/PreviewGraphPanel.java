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
}