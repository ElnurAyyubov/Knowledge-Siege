package game;
import java.security.SecureRandom;

import frames.MainFrame;

// Class to represent Section Leader. Extends the abstract class Enemy
public class SectionLeader extends Enemy {

	/*
	 * Constructor to initialize Professors, with constants for professors.
	 * 
	 * @param username
	 * @param imageURL
	 */
	public SectionLeader(String username, String imageURL) {
		super(username);
		step = 2;
		this.imageURL = imageURL;
		
		pointGrant = 10;
		damageDeal = 5;
		chanceOfQuestion = 0.2;
		chanceOfInfo = 0.8;
		shotBoxStep = 2;

		super.addEnemyOnScreen();
	}
	
	/*
	 * Overriden from the Enemy class. Specified movement for Section Leaders, they move randomly,
	 * either to left or right.
	 */
	@Override
	public void moveInSpecifiedDirection() {
		SecureRandom secureRandom = new SecureRandom();
		double randomNumber = secureRandom.nextDouble(1);
		
		// Random chance to move to the left or right:
		// 50 % to the left, 50% to the right
		if (randomNumber <= 0.5) {
			playerX -= step;
		} else {
			playerX += step;
		}
	}
	
	
}
