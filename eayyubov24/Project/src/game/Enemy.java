package game;
import java.security.SecureRandom;
import java.util.ArrayList;

import frames.EnemyGraphicsGUI;
import frames.MainFrame;
import frames.ShotBoxesGraphicsGUI;

/**
 * Abstract class Enemy that is parent class to each type of Knowledge Keeper
 * Defines common methods that is used in for each type of Knowledge Keeper
 * 
 */
public abstract class Enemy {

	private static ArrayList<Enemy> enemiesOnScreen = new ArrayList();
	
	private String username; // username of enemy
	protected String imageURL; // path to its image (in the form "/photos/image_url.png")
	protected double chanceOfQuestion; // chance to drop question
	protected double chanceOfInfo; // chance to drop information 
	protected int playerX; // X position of player on the frame
	protected int playerY; // Y position of player on the frame
	protected int step; // number of pixels it moves on one method call move()
	protected int shotBoxStep; // number of pixels enemy's shotbox moves on one method call
	protected int damageDeal; // damage that question shotbox deals (0 for information shotbox)
	protected int pointGrant; // point that information shotbox grants (0 for question shotbox)

	// Each instance of enemy has its enemyGraphicsGUI that shows it on the frame
	// It has one-to-one relation with this class
	protected EnemyGraphicsGUI enemyGraphicsGUI;
	
	/**
	 * Default constructor that each type of enemy must implement
	 * @param username
	 */
	public Enemy(String username) {
		this.username = username;
	}
	
	/**
	 * Initilizes a shotbox to shoot for the enemy. This method is called in GameController.
	 * Uses SecureRandom to decide whether generate a question or information.
	 */
	public void attack() {
		SecureRandom random = new SecureRandom();
		double randomNumber = random.nextDouble(1);

		if (randomNumber <= chanceOfQuestion) {
			ShotBoxes shotbox = new ShotBoxes(ShotBoxesTypes.QUESTION, this.playerX, this.playerY, damageDeal, pointGrant, this);
		} else {
			ShotBoxes shotbox = new ShotBoxes(ShotBoxesTypes.INFORMATION, this.playerX, this.playerY, damageDeal, pointGrant, this);
		}
	}
	
	/**
	 * Randomly moves Enemy on ther screen.
	 * Fisrt it checks whether enemy is within the frame, after checks whether it collides 
	 * with any other enemy type. If not, it moves accordingly to method 
	 * moveInSpecifiedDirection() that defines a unique movement that each type of enemy has. 
	 * If it collides, it moves accordingly so that keep itself distant from other enemy.
	 * 
	 *  @return void
	 */
	public void move() {
		if (playerX - step > 0 && playerX + step + MainFrame.enemySize < MainFrame.enemyPaneWidth) {
			int actionNum = collidingLeftOrRight();
			
			if (actionNum == 0) {
				moveInSpecifiedDirection();
				
			} else if (actionNum == -1) {
				playerX -= step;
			} else if (actionNum == 1) {
				playerX += step;
			}
			
		} else if (playerX - step <= 0) {
			playerX += step;
		} else if (playerX + step + MainFrame.enemySize >= MainFrame.enemyPaneWidth) {
			playerX -= step;
		}
	}
	
	/**
	 * Method that defines unique movement of each Enemy type. Must be
	 * implement in each enemy type class.
	 */
	protected abstract void moveInSpecifiedDirection();
	
	/**
	 * Allocates enemies on the screen with equal distance from one another on the
	 * start of the game.
	 * 
	 * @return void
	 */
	public static void allocateEnemies() {
		double distBetweenEnemies = MainFrame.enemyPaneWidth / enemiesOnScreen.size();
		int xPostionForEnemy = 0;
		for (Enemy enemy : enemiesOnScreen) {
			enemy.setPlayerX(xPostionForEnemy);
			xPostionForEnemy += (int) distBetweenEnemies;
		}
	}
	
	/**
	 * Initializes enemyGraphicsGUI, adds it to MainFrame so that is visible,
	 * add the enemy to the list of enemies that are on the MainFrame(on screen)
	 * 
	 * @return void
	 */
	public void addEnemyOnScreen() {
		enemiesOnScreen.add(this);
		enemyGraphicsGUI = new EnemyGraphicsGUI(this);
		MainFrame.addEnemy(enemyGraphicsGUI);
	}
	
	/**
	 * Checks whether enemy is collides with other enemy on the next step. Returns either 0,-1,1
	 * that means it does not collide neither from right or left, collides on right collides on left
	 * respectively. Used in move() method.
	 * 
	 * @return int: 0 (will not collide), -1(should move to left), 1(should move to right)
	 */
	protected int collidingLeftOrRight() {
		int currentEnemyIndex = Enemy.getEnemiesOnScreen().indexOf(this);
		
		// If current enemy is last in row
		if (currentEnemyIndex + 1 == Enemy.getEnemiesOnScreen().size()) {
			Enemy beforeEnemy = Enemy.getEnemiesOnScreen().get(currentEnemyIndex - 1);
			if (beforeEnemy.getPlayerX() + MainFrame.enemySize >= playerX - step) {
				return 1;
			}
			return 0;
		}
		
		// If current enemy is first in row
		if (currentEnemyIndex == 0) {
			Enemy nextEnemy = Enemy.getEnemiesOnScreen().get(currentEnemyIndex + 1);
			if (playerX + MainFrame.enemySize + step >= nextEnemy.getPlayerX()) {
				return -1;
			}
			return 0;
		}
		
		// Other enemies
		Enemy nextEnemy = Enemy.getEnemiesOnScreen().get(currentEnemyIndex + 1);
		Enemy beforeEnemy = Enemy.getEnemiesOnScreen().get(currentEnemyIndex - 1);

		
		 if (playerX + MainFrame.enemySize + step >= nextEnemy.getPlayerX()) {
			return -1;
		}
		 if (beforeEnemy.getPlayerX() + MainFrame.enemySize >= playerX - step) {
				return 1;
			}
		
		return 0;
	}
	
	/**
	 * After level ends, removes enemies from the screen.
	 * 
	 * @return void
	 */
	public static void removeAllEnemies() {
		for (Enemy enemy : enemiesOnScreen) {
			MainFrame.getEnemyPane().remove(enemy.enemyGraphicsGUI);
			MainFrame.updateEnemyPane();
		}
		enemiesOnScreen.clear();
	}

	
	/*
	 * Getters and Setters
	 */
	public String getUsername() {
		return username;
	}

	public double getChanceOfQuestion() {
		return chanceOfQuestion;
	}

	public double getChanceOfInfo() {
		return chanceOfInfo;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public int getStep() {
		return step;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	public static ArrayList<Enemy> getEnemiesOnScreen() {
		return enemiesOnScreen;
	}

	public int getDamageDeal() {
		return damageDeal;
	}

	public int getPointGrant() {
		return pointGrant;
	}
	
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	
	public int getShotBoxStep() {
		return shotBoxStep;
	}
	
	public EnemyGraphicsGUI getEnemyGraphicsGUI() {
		return enemyGraphicsGUI;
	}
	
}
