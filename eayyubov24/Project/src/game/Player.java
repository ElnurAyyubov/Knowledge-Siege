package game;

import frames.MainFrame;

// Class represents the player that plays the game
public class Player {
	private String username; // username of the player
	private String photoUrl; // photo url of the player
	private int health; // health of the player in the game
	private int points; // points/score of the player in the game
	private int xPosition; // x position on the frame of the player
	private int yPosition; // y position on the frame of the player
	
	/**
	 * Constructor to create the player
	 * 
	 * @param username - username of the player 
	 * @param photoUrl - photoUrl of the player
	 */
	public Player(String username, String photoUrl) {
		super();
		this.username = username;
		this.photoUrl = photoUrl;
		health = 100;
		points = 0;
		xPosition  = 0;
		yPosition = 381;
	}
	
	/**
	 * Moves / changes x postition of the player by step pixels
	 * @param step - number of pixels to change the position of the player
	 */
	public void move(int step) {
		xPosition += step;
	}
	
	/**
	 * Increases score
	 * 
	 * @param change
	 */
	public void increaseScore(int change) {
		points += change;
		LogGame.logEvent(String.format("Score changed: score: %d; health %d", points, health));
	}
	
	/**
	 * Deacreses health points of the player. Also ends the game if the player's health falls below 0
	 * @param change
	 */
	public void decreaseHealth(int change) {
		health -= change;
		LogGame.logEvent(String.format("Health changed: score: %d; health %d", points, health));
		if (health <= 0) {
			// Shows player the end of the game, logs the sessions to the scoreboard, terminates the program
			MainFrame.showInfo("You failed COMP132.", "Game Over!");
			ScoreBoardManager manager = new ScoreBoardManager();
        	manager.addSession(username, points);
			LogGame.logEvent("Game over: Health depleted. Final score: " + points);
			GameController.endGame();
		}
	}

	// Getters
	public int getHealth() {
		return health;
	}

	public int getPoints() {
		return points;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public String getUsername() {
		return username;
	}
}
