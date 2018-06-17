package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.MainCtrl;

public class LoadingPanel extends JPanel implements Runnable{

	ImageIcon img = new ImageIcon(getClass().getResource("/resources/LoadingIcon.png"));
	JLabel icon = new JLabel();
	double angle = 0.0;
	
	public LoadingPanel() {
		this.setBackground(new Color(0,0,0,140));
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(SwingConstants.RIGHT));
		buttonPane.setOpaque(false);
		//Button goes here
		icon.setHorizontalTextPosition(SwingConstants.CENTER);
		icon.setVerticalTextPosition(SwingConstants.CENTER);
		icon.setIcon(img);
		buttonPane.add(icon);
		
		this.add(buttonPane, BorderLayout.PAGE_END);
	}
	
	@Override  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // Draw the background image.
	    //g.drawImage(buffered, this.getWidth()-80, this.getHeight()-80, null);
	    
	  }
	
	@Override
	public void run() {
		while (this.isVisible()) {
			try {
				//icon.validate();
				//this.setBackground(new Color(0,0,0,0));
				//this.repaint();
				//this.validate();
				angle +=0.5;
				if (angle > 360.0) angle -= 360.0;
				BufferedImage buffered =  rotate(convertToBufferedImage(img.getImage()),angle);
				icon.setIcon(new ImageIcon(buffered));
				MainCtrl.getMainFrame().getMainView().repaint();
				MainCtrl.getMainFrame().repaint();
				MainCtrl.getMainFrame().validate();
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public  BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww - w) / 2, (newh - h) / 2);
	    g.rotate(angle, w / 2, h / 2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}

	private GraphicsConfiguration getDefaultConfiguration() {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    return gd.getDefaultConfiguration();
	}
	
	public static BufferedImage convertToBufferedImage(Image image)
	{
	    BufferedImage newImage = new BufferedImage(
	        image.getWidth(null), image.getHeight(null),
	        BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = newImage.createGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    return newImage;
	}

}
