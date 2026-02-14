package game;

// Class to represent a user for login/register
public class User {
	private String username; // username of the user
	private String password; // password of the user
	private String photoUrl; // photoUrl of the user
	
	/*
	 * User constructor 
	 */
	public User(String username, String password, String photoUrl) {
		super();
		this.username = username;
		this.password = password;
		this.photoUrl = photoUrl;
	}
	
	// Getters
	public String getUsername() {
		return username;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
}
