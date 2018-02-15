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
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;


import control.TecnMeasureCtrl;
import model.FileManager;

public class TecnMeasureFr{

	GraphFr gr;
	
	public TecnMeasureFr(){
	
	JFrame f = new JFrame("ECG:");
    //add(new GraphFr);
    f.setSize(400,400);
    f.setLocation(200,200);
    
    
    gr = new GraphFr();
    gr.setBackground(Color.DARK_GRAY.darker());
    gr.setOpaque(true);
    f.add(gr);
    f.setVisible(true);
	}
	public void addController(TecnMeasureCtrl tmc) {
		// TODO Auto-generated method stub
		
	}
	
	 
}