package game;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.List;

import javax.swing.Timer;

import frames.EnemyGraphicsGUI;
import frames.MainFrame;
import frames.ShotBoxesGraphicsGUI;

public class GameController {
	
	private static Player player; // player that is logged in and plays the game
	private static int level = 0; // current level the player is on. Might be 0,1,2,3.
	
	/*
	 * Runs the game: initializes the database of enemies, initializes level, starts timer.
	 */
	public void runGame() {
		player = MainFrame.getPlayer();
		DataBase.initStaff();
		initializeAndUpdateLevel();
		startTimer();
	}
	
	/**
	 * Initilizes and updates the level. Checks on which level player is,
	 * increments the level and initializes the needed enemies for the level.
	 */
	public static void initializeAndUpdateLevel() {
		Enemy.removeAllEnemies();
		ShotBoxes.removeAllShotBoxes();
		switch (level) {
		case 0:
			createEnemiesByData(DataBase.pickEnemiesFirstRound());
			level++;
			break;
		case 1:
			createEnemiesByData(DataBase.pickEnemiesSecondRound());
			level++;
			break;
		case 2:
			createEnemiesByData(DataBase.pickEnemiesThirdRound());
			level++;
			break;
		}
		Enemy.allocateEnemies();
	}
	
	
	/**
	 * Starts the timer. Timer is used for: move each enemy, move each shotbox
	 * that is on the screen, check whether player got enough points to advance 
	 * to the next level.
	 */
	private static void startTimer() {
		// Initialize the timer
		
	    Timer enemyTimer = new Timer(5, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {    	
	            for (Enemy enemy : Enemy.getEnemiesOnScreen()) {
	                enemy.move();
	            }
	            // Updates enemies postion on the frame.
	            updateEnemyGraphics();
	            
	            // Iterates through shotbox to move, remove, and check for collison with player
	            for (int i = 0; i < ShotBoxes.getShotBoxesonTheScreen().size(); i++) {
	            	ShotBoxes shotBox = ShotBoxes.getShotBoxesonTheScreen().get(i);
	            	if (!shotBox.isMarkedForRemoval() || shotBox == null) {
		            	shotBox.move();
		            	
		            	// Removes shotbox if passed beyond screen
		            	if (shotBox.getShotBoxY() > 1058) {
		            		shotBox.removeShotBox();		
		            	}
		            	// checks collision with the player
		            	checkCollision(shotBox);
	            	}
	            }
	            // Updates shotbox position on frame.
	            updateShotBoxGraphics();
	            
	            // Checkers to check if player has enough points to move to next level
	            if (level == 1 && player.getPoints() >= 50) {
	            	// Get to level 2
	            	LogGame.logEvent(String.format("Player '%s' got on level 2", player.getUsername()));
	            	initializeAndUpdateLevel();
	            } else if (level == 2 && player.getPoints() >= 100 ) {
	            	// Get to level 3
	            	LogGame.logEvent(String.format("Player '%s' got on level 3", player.getUsername()));
	            	initializeAndUpdateLevel();
	            } else if (level == 3 && player.getPoints() >= 150) {
	            	// Finish the game
	            	MainFrame.showInfo("You have passed COMP132!", "You won!");
	            	ScoreBoardManager manager = new ScoreBoardManager();
	            	manager.addSession(player.getUsername(), player.getPoints());
	            	LogGame.logEvent("Game won! Score: " + player.getPoints());
	            	GameController.endGame();
	            } 
	        }
	    });
	    
	    // Seperate time for attacking shotboxes
	    Timer attackTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SecureRandom random = new SecureRandom();	        	
	            for (Enemy enemy : Enemy.getEnemiesOnScreen()) {
	            	double randNum = random.nextDouble(1);
	                if (randNum < 0.03) {
	                	enemy.attack();
	                }
	            }
			}
	    	
	    });
	    enemyTimer.start();
	    attackTimer.start();
	}
	
	
	/**
	 * Checks whether player collides with a shotbox. If collides, it applies the damage and point grant,
	 * and removes it from the screen.
	 * @param shotBox - with which the colliion is checked
	 */
	private static void checkCollision(ShotBoxes shotBox) {
		int shotBoxSize = MainFrame.shotBoxesSize;
		int playerSize = MainFrame.playerSize;
		int x1 = player.getxPosition();
        int y1 = player.getyPosition();
        int x2 = shotBox.getShotBoxX();
        int y2 = shotBox.getShotBoxY();
		
		if (x1 < x2 + shotBoxSize &&
	               x1 + playerSize > x2 &&
	               y1 < y2 + shotBoxSize &&
	               y1 + playerSize > y2) {
			
			shotBox.removeShotBox();
			if (shotBox.getType() == ShotBoxesTypes.INFORMATION) {
				LogGame.logEvent(String.format("User '%s' collected info: '%s' (+%d points)", player.getUsername(), shotBox.getContent(), shotBox.getPoint()));
				player.increaseScore(shotBox.getEnemy().getPointGrant());
				MainFrame.updatePointField();
				MainFrame.setTextToInfoField(shotBox.getContent());
			} else if (shotBox.getType() == ShotBoxesTypes.QUESTION) {
				LogGame.logEvent(String.format("User '%s' hit by question: '%s' (-%d health)", player.getUsername(), shotBox.getContent(), shotBox.getDamage()));
				player.decreaseHealth(shotBox.getEnemy().getDamageDeal());
				MainFrame.updateHealthBar();
				MainFrame.setTextToQuestionField(shotBox.getContent());
			}
		}
	}
	
	/**
	 * Updates each enemies position on the frame.
	 */
	private static void updateEnemyGraphics() {
	    for (Component comp : MainFrame.getEnemyPane().getComponents()) {
	        if (comp instanceof EnemyGraphicsGUI) {
	            ((EnemyGraphicsGUI) comp).updateEnemyLocation();
	        }
	    }
	}
	
	/**
	 * Updates each shotBox position on the frame.
	 */
	private static void updateShotBoxGraphics() {
		for (Component comp : MainFrame.getShotBoxPane().getComponents()) {
			if (comp instanceof ShotBoxesGraphicsGUI) {
				((ShotBoxesGraphicsGUI) comp).updateShotBoxLocation();
			}
		}
	}
	
	/**
	 * Getting data of enemies from database and creates corresponding objects.
	 * 
	 * @param enemies - list of enemies that we got from database
	 */
	public static void createEnemiesByData(List<String[]> enemies) {
			for (String[] enemyData : enemies) {
				String username = enemyData[0];
				String position = enemyData[1];
				String photoUrl = enemyData[2];
				switch (position) {
				case "professor":
					Enemy prof = new Professors(username, photoUrl);
					break;
				case "ta":
					Enemy ta = new TeachingAssistants(username, photoUrl);
					break;
				case "sl":
					Enemy sl = new SectionLeader(username, photoUrl);
					break;
				}
			}
	}
	
	
	// Getter method for player
	public static Player getPlayer() {
		return player;
	}
	
	/**
	 * Terminates the program.
	 */
	public static void endGame() {
		System.exit(1);
	}
}
