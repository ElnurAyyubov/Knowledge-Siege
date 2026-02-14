package game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Score board manager to load, save, add sessions to file.
public class ScoreBoardManager {
	
	private List<GameSession> gameSessionsList = new ArrayList<>(); // all the game sessions
	
	/*
	 * Constructor that loads scores from database and initializes the gameSessionsList
	 */
	public ScoreBoardManager() {
		loadScores();
	}
	
	/*
	 * Loads scores from the database(scoretable.txt file)
	 */
	public void loadScores() {
		try(Scanner sc = new Scanner(Paths.get("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\KU\\\\Projects\\\\src\\\\database\\\\scoretable.txt"))) {
			while (sc.hasNext()) {
				String data = sc.nextLine();
				String[] dataSplitted = data.split(",");
				GameSession session = new GameSession(dataSplitted[0], Integer.parseInt(dataSplitted[1]));
				gameSessionsList.add(session);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(gameSessionsList);
	}
	
	/*
	 * Saves the scores to the database.
	 */
	public void saveScore() {
		Collections.sort(gameSessionsList);
		try(Formatter formatter = new Formatter("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\KU\\\\Projects\\\\src\\\\database\\\\scoretable.txt")) {
			for (GameSession session : gameSessionsList) {
				formatter.format("%s,%d\n", session.getUsername(), session.getScore());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Adds session to gameSessionsList.
	 */
	public void addSession(String username, int score) {
		GameSession session = new GameSession(username, score);
		gameSessionsList.add(session);
		saveScore();
	}
	
	/*
	 * Getter method for gameSessionsList
	 */
	public List<GameSession> getGameSessionsList() {
		return gameSessionsList;
	}
	
	
}
