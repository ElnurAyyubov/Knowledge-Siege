package game;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

// Logs info in log file of the game.
public class LogGame {
	/**
	 * Logs message on the log file of the game.
	 * 
	 * @param message - message to log
	 */
	public static void logEvent(String message) {
		try (Formatter formatter = new Formatter(new FileWriter("src/database/log_game.txt", true))) {
        	formatter.format(getCurrentTimestamp() + " " + message + "\n");
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns current time.
	 * 
	 * @return - current time
	 */
	public static String getCurrentTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");
        return formatter.format(new Date());
    }
}
