package game;

import java.security.SecureRandom;

import frames.MainFrame;

// Class to represent Professors. Extends the abstract class Enemy
public class Professors extends Enemy {
	
	/**
	 * Constructor to initialize Professors, with constants for professors.
	 * 
	 * @param username
	 * @param imageURL
	 */
	public Professors(String username, String imageURL) {
		super(username);
		step = 10;
		this.imageURL = imageURL;
		
		pointGrant = 30;
		damageDeal = 20;
		chanceOfQuestion = 0.8;
		chanceOfInfo = 0.2;
		shotBoxStep = 10;

		super.addEnemyOnScreen();
	}

	/**
	 * Overriden from the Enemy class. Specified movement for Professors, there is big
	 * percentage for the professor to move in the direction of the player.
	 * 
	 */
	@Override
	protected void moveInSpecifiedDirection() {
		SecureRandom secureRandom = new SecureRandom();
		double randomNumber = secureRandom.nextDouble(1);
		
		// Random number to define movement:
		// 50% in the direction of the player, 25% to the left, 25% to the right.
		if (randomNumber <= 0.5) {
			if (playerX > MainFrame.getPlayer().getxPosition()) {
				playerX -= step;
			} else {
				playerX += step;
			}
		} else if (randomNumber <= 0.75) {
			playerX -= step;
		} else {
			playerX += step;
		}
		
	}

}
