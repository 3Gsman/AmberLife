package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

	  private Image backgroundImage;

	  // Some code to initialize the background image.
	  // Here, we use the constructor to load the image. This
	  // can vary depending on the use case of the panel.
	  public JPanelWithBackground(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	  }
	  
	  public JPanelWithBackground(URL url) throws IOException {
		    backgroundImage = ImageIO.read(url);
		  }
	  

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // Draw the background image.
	     
	    if(this.getWidth() > backgroundImage.getWidth(null) && this.getWidth() > this.getHeight()){
	    	Image newImage = backgroundImage.getScaledInstance(this.getWidth(),
	    	(int)(((float)this.getWidth()/backgroundImage.getWidth(null))*(float)backgroundImage.getHeight(null)), Image.SCALE_DEFAULT);
	    	g.drawImage(newImage, this.getWidth()/2-newImage.getWidth(null)/2,
			    		this.getHeight()/2-newImage.getHeight(null)/2, this);
	    }
	    else if(this.getHeight() > backgroundImage.getHeight(null)){
	    	Image newImage = backgroundImage.getScaledInstance(
	    			(int)(((float)this.getHeight()/backgroundImage.getHeight(null) *(float)backgroundImage.getWidth(null))),
	    			this.getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(newImage, this.getWidth()/2-newImage.getWidth(null)/2,
					this.getHeight()/2-newImage.getHeight(null)/2, this);
	    }
	    else {
	    	g.drawImage(backgroundImage, this.getWidth()/2-backgroundImage.getWidth(null)/2,
		    		this.getHeight()/2-backgroundImage.getHeight(null)/2, this);
	    }
	    
	  }
	}