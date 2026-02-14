package game;

import java.security.SecureRandom;

import frames.MainFrame;

// Class to represent Teaching Assistants. Extends the abstract class Enemy
public class TeachingAssistants extends Enemy {

	/**
	 * Constructor to initialize Teaching assistants, with constants for TAs.
	 * 
	 * @param username
	 * @param imageURL
	 */
	public TeachingAssistants(String username, String imageURL) {
		super(username);
		step = 5;
		this.imageURL = imageURL;
		
		pointGrant = 20;
		damageDeal = 10;
		chanceOfQuestion = 0.5;
		chanceOfInfo = 0.5;
		shotBoxStep = 5;

		super.addEnemyOnScreen();
	}

	/*
	 * Overriden from the Enemy class. Specified movement for TAs, there is small percentage that
	 * TA will move in the direction of the player
	 */
	@Override
	protected void moveInSpecifiedDirection() {
		SecureRandom secureRandom = new SecureRandom();
		double randomNumber = secureRandom.nextDouble(1);
		
		// Random number to define movement:
		// 30% in the direction of the player, 35% to the left, 35% to the right.
		if (randomNumber <= 0.3) {
			if (playerX > MainFrame.getPlayer().getxPosition()) {
				playerX -= step;
			} else {
				playerX += step;
			}
		} else if (randomNumber <= 0.65) {
			playerX -= step;
		} else {
			playerX += step;
		}
	}
	
	

}
