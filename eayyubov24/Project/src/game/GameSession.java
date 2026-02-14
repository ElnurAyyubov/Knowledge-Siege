package game;

/**
 * Class to create Game session that we get from score table
 */
public class GameSession implements Comparable<GameSession> {
	private String username; // username of player that played the game
	private int score; // points that player got
	
	/**
	 * Constructor to initialize a session.
	 *  
	 * @param username
	 * @param score
	 */
	public GameSession(String username, int score) {
		this.username = username;
		this.score = score;
	}
	
	/**
	 * Method to compare two game session based on score. If scores are equal, then on alphabetic
	 * order. Used to sort game sessions.
	 * 
	 * @param other - another game session to compare
	 */
	@Override
	public int compareTo(GameSession other) {
		if (this.score != other.score) {
            return Integer.compare(other.score, this.score); // Descending
        }
        return this.username.compareTo(other.username); // Alphabetical if tied
	}

	// Getters
	public String getUsername() {
		return username;
	}
	
	public int getScore() {
		return score;
	}
}
