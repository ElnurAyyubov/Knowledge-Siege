package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.ShotBoxes;

// ShotBoxesGraphicsGUI to display the player on the screen, is a JPanel
public class ShotBoxesGraphicsGUI extends JPanel {
	private ShotBoxes shotbox; // has one-to-one relation with shotbox class 
	private Image shotboxImage; // image of the shotbox
	
	 /*
	  * Constructor to initialize ShotBoxesGraphicsGUI for shotbox
      * 
      * @param shotbox to create GraphicsGUI for
      */
	public ShotBoxesGraphicsGUI(ShotBoxes shotbox) {
    	this.shotbox = shotbox;
   		try {
            // Tries to load the image
        	shotboxImage = new ImageIcon(getClass().getResource(shotbox.getPhotoURL())).getImage();
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }
	
	/*
	 * Synchronize shotbox's x position and y position with the frame
	 */
    public void updateShotBoxLocation() {
        setLocation(shotbox.getShotBoxX(), shotbox.getShotBoxY());
        repaint();
    }
    
    /*
     * Method to draw the shotbox on the frame
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shotboxImage != null) {
        	// Draw image if image is found
        	g.drawImage(shotboxImage, 0, 0, getWidth(), getHeight(), this);
        } else {
        	// Draw a rectangle if the image fails to load
        	g.setColor(Color.BLACK);
            g.fillRect(shotbox.getShotBoxX(), shotbox.getShotBoxY(), getWidth(), getHeight());
        }
    }
    
    /*
     * Getter for shotbox
     */
    public ShotBoxes getShotBox() {
    	return shotbox;
    }
}
