	package game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import exceptions.InvalidUserDataException;
import frames.LoginFrame;

// Handles registers and logins the user to the program
public class LoginController {
	
	// REGEX patterns for username and password
	private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{2,19}$");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_])[^\\s]{8,}$");
	
	/**
	 * Registers user to database.
	 * 
	 * @param username - username of user
	 * @param password - password of user
	 * @param selectedFile - selected photo url (form : "/photos/character1.png" || /photos/character2.png || /photos/character3.png)
	 */
	public static void registerUser(String username, String password, String selectedFile) {
        try {
        	// Checks whether username and password are valid
        	if (isUsernameValid(username)) {
        		if (isPasswordValid(password)) {
	            // Checks whether there is user with the same username in database.
		            if (findByUsername(username) == null) {  
		            	// Adds user to database
			            try (Formatter formatter = new Formatter(new FileWriter("src/database/usersdb.txt", true))) {
			            	formatter.format("%s,%s,%s\n", username.strip(), password, selectedFile);
			            }
			            
			            // Informs the user, and logs the info.
			            LoginFrame.showSuccess("User is registered.");
			            LoginFrame.clearFields();
			            LogGame.logEvent(String.format("User %s is registered", username));
		            } else {
		            	// If username is taken, shows error.
		            	LoginFrame.showError("Username " + username + " is taken.");
		            }
	        	} else {
	        		throw new InvalidUserDataException("Password is not correct.\n Password must contain at least 8 characters; at least one digit and special character; no space");
	        	}
        	} else {
        		throw new InvalidUserDataException("Username is not correct.\n Username must start with letter, length between 3 and 20, no special characters");
        	}
        } catch (IOException ex) {
        	System.err.print(ex.getMessage());
        } catch (InvalidUserDataException e) {
        	System.err.print(e.getMessage());
        }
        
	}
	
	/**
	 * Logs in the user to start the game.
	 * 
	 * @param username - username of user to log in
	 * @param password - password of user to log in
	 */
	public static void loginUser(String username, String password) {
			try {
				// Checks whether there exists a user with the given username
				String[] foundUserInDB = findByUsername(username);
				if (foundUserInDB == null) {
					// If does not exists, shows error
					LoginFrame.showError("Username or password is wrong");
				} else {
					// If exists, checks password to login
					if (foundUserInDB[1].equals(password)) {
						Main.closeLoginFrameOpenGame(new User(foundUserInDB[0], foundUserInDB[1], foundUserInDB[2]));
					} else {
						throw new InvalidUserDataException("Username or password is wrong");
					}
				}
			} catch (InvalidUserDataException e) {
				System.err.println(e.getMessage());
			}
	}
	
	/**
	 * Looks for user with given username in the database.
	 * 
	 * @param username - given username to look for
	 * @return - returns an 3 length array with username, password, and photo url respectively. If not found returns null
	 */
	public static String[] findByUsername(String username) {
		try(Scanner input = new Scanner(Paths.get("C:\\Users\\Lenovo\\Desktop\\KU\\Projects\\src\\database\\usersdb.txt"))) {
			while (input.hasNextLine()) {
				String data = input.nextLine();
				String[] dataSplited = data.split(",");
				if (dataSplited[0].equals(username)) {
					return dataSplited;
				}
			}
		} catch (IOException io) {
			System.err.println("Error opening input file. Terminating.");
			LoginFrame.showError("Error opening file, please try registering the user.");
		}
		return null;
	}
	
	/*
	 * Return true if username is valid according to regex
	 * 
	 * @return boolean
	 * @param username
	 */
	private static boolean isUsernameValid(String username) {
		return USERNAME_PATTERN.matcher(username).find();
	}
	
	/*
	 * Return true if password is valid according to regex
	 * 
	 * @return boolean
	 * @param password
	 */
	private static boolean isPasswordValid(String password) {
		return PASSWORD_PATTERN.matcher(password).find();
	}
}
