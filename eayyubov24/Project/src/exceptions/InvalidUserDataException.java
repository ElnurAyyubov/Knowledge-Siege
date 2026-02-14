package exceptions;

import frames.LoginFrame;

/**
 * Exception for invalid user data during login/register.
 */
public class InvalidUserDataException extends Exception {
	public InvalidUserDataException(String message) {
		super(message);
		LoginFrame.showError(message);
	}
}
