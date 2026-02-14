package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.Player;

// PlayerGraphicsGUI to display the player on the screen, is a JPanel, implements KeyListener
// to implement movement for the player
public class PlayerGraphicsGUI extends JPanel implements KeyListener {
	
    private Player player; // has one-to-one relation with Player
    private final int STEP = 10; // number of pixels to move when the button is clicked
    private Image playerImage; // image of the player

    /*
     * Constructor to initialize PlayerGraphicsGUI for player
     * 
     * @param player to create GhphicsGUI for
     */
    public PlayerGraphicsGUI(Player player) {
    	this.player = player;
        try {
        	// Tries to get the image with url
            playerImage = new ImageIcon(getClass().getResource(player.getPhotoUrl())).getImage();
        } catch (Exception e) {
        	// Prints error message
            System.err.println("Error image: " + e.getMessage());
        }
        setFocusable(true);
        addKeyListener(this);
    }

    /*
     * Method to draw the player on the frame
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerImage != null) {
        	// Draw image if image is found
            g.drawImage(playerImage,0,0, getWidth(), getHeight(), this);
        } else {
            // Draw a rectangle if the image fails to load
            g.setColor(Color.BLACK);
            g.fillRect(player.getxPosition(), player.getyPosition(), 50, 50);
        }
    }

    /*
     * Method to run when the key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
    	 int key = e.getKeyCode();
    	 // Moves left if left button is pressed and the player is not beyond the screen
    	 if (key == KeyEvent.VK_LEFT && player.getxPosition() > 0) {
    	     player.move(-STEP);
    	 // Move right if right button is pressed and the player is not beyond the screen
    	 } else if (key == KeyEvent.VK_RIGHT && player.getxPosition() < 650) {
    	    player.move(STEP);
    	 }
    	 
    	 // Synchronize GUI position with model
    	 setLocation(player.getxPosition(), player.getyPosition());
    	 repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    public Player getPlayer() {
    	return player;
    }
}