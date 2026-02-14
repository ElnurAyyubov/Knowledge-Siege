package frames;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.Enemy;

// PlayerGraphicsGUI to display the player on the screen, is a JPanel.
public class EnemyGraphicsGUI extends JPanel {
	private Enemy enemy; // has one-to-one relation with enemy class
	private Image enemyImage; // image of the enemy
	
	/*
	 * Constructor to initialize EnemyGraphicsGUI for the enemy
     * 
     * @param enemy to create GraphicsGUI for enemy
	 */
    public EnemyGraphicsGUI(Enemy enemy) {
    	this.enemy = enemy;
   		try {
            // Try to load the image
        	enemyImage = new ImageIcon(getClass().getResource(enemy.getImageURL())).getImage();
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        
    }

    /*
     * Synchronize enemy's x position and y position with the frame
     */
    public void updateEnemyLocation() {
        setLocation(enemy.getPlayerX(), enemy.getPlayerY());
        repaint();
    }
    
    /*
     * Method to draw the component on the frame
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (enemyImage != null) {
        	// Draw image if image is found
            g.drawImage(enemyImage,0, 0, getWidth(), getHeight(), this);
        } else {
            // Draw a rectangle if the image fails to load
            g.setColor(Color.BLACK);
            g.fillRect(enemy.getPlayerX(), enemy.getPlayerY(), getWidth(), getHeight());
        }
    }
    
    
}
