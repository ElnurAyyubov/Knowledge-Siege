package game;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

import frames.MainFrame;
import frames.ShotBoxesGraphicsGUI;

// Class to represent Shot boxes in the game
public class ShotBoxes {
	

	private static ArrayList<ShotBoxes> shotBoxesList = new ArrayList<>(); // list to keep shotboxes that are on the frame
	
	private ShotBoxesTypes type; // type of shotbox: either question or information
	private String content; // content of shotbox, question or information based on type
	private String photoURL; // photoURL of shotbox, is either "/photos/info.png" or "/photos/question.png"
	private int shotBoxX;// x position of the shotbox on the frame
	private int shotBoxY;// y position of the shotbox on the frame
	private int damage; // damage that is dealt of question shotbox (0 for information shotbox)
	private int point;// points that is grant for information shotbox (0 for question shotbox)
	private int step; // step of the shotbox
	private Enemy enemy; // enemy that dropped the shotbox
	
	// Special field that is true when the shotbox is marked for removal, to be removed
	private boolean markedForRemoval = false; 
	
	private ShotBoxesGraphicsGUI shotBoxesGraphicsGUI; // shotBoxesGraphicsGUI to represent the shotbox on the screen


	/*
	 * Constructor to initialize the shotbox
	 */
	public ShotBoxes(ShotBoxesTypes type, int shotBoxX, int shotBoxY, int damage, int point, Enemy enemy) {
		this.type = type;
		this.shotBoxX = shotBoxX;
		this.shotBoxY = shotBoxY;
		step = enemy.getShotBoxStep();
	    this.damage = damage;
	    this.point = point;
	    this.enemy = enemy;
		if (type == ShotBoxesTypes.INFORMATION) {
			photoURL = "/photos/info.png";
		} else {
			photoURL = "/photos/question.png";
		}
		content = readDataForInfoOrQuestion();
		shotBoxesList.add(this);
		this.addShotBoxOnScreen();
		
	}
	
	/*
	 * Initialize the content field of the shotbox based on the type of the shotbox. 
	 * Gets data from the file.
	 * 
	 * @return String pickedContent : question or info
	 */
	private String readDataForInfoOrQuestion() {
		Path path;
		// Determine from which file to get data
		if (type == ShotBoxesTypes.INFORMATION) {
			path = Paths.get("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\KU\\\\Projects\\\\src\\\\database\\\\info.txt");
		} else {
			path = Paths.get("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\KU\\\\Projects\\\\src\\\\database\\\\questions.txt");
		}
		String position = "";
		// Define the postion of the enemy to look for
		if (enemy instanceof SectionLeader) {
			position = "[SL]";
		} else if (enemy instanceof TeachingAssistants) {
			position = "[TA]";
		} else {
			position = "[PR]";
		}
		
		String pickedContent = "";
		try (Scanner sc = new Scanner(path)) {
			// Randomly pick the data: Each position has 10 datas(question or info) to choose from 
			SecureRandom random = new SecureRandom();
			int randomNum = random.nextInt(0,9);
			int counter = 0;
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				if (data.contains(position)) {
					if (randomNum==counter) {
						pickedContent = data.substring(5);
						break;
					} else {
						counter++;
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			
			return "<ERROR opening file>";
		}
		
		return pickedContent;
	}
	
	/*
	 * Removes shotbox from the screen.
	 */
	public void removeShotBox() {
		// Removes from the frame
		MainFrame.getShotBoxPane().remove(shotBoxesGraphicsGUI);
		// Removes from list of the shotboxes
		shotBoxesList.remove(this);
		// Marked for removal to avoid during iteration
		markForRemoval();
		// Update the shotbox pane to illustrate the change(removal)
		MainFrame.updateShotBoxPane();
	}
	
	/*
	 * Removes all shotBoxes from the screen. Used in when advancing to the next level
	 */
	public static void removeAllShotBoxes() {
		for (ShotBoxes shotbox : shotBoxesList) {
			MainFrame.getShotBoxPane().remove(shotbox.shotBoxesGraphicsGUI);
			MainFrame.updateShotBoxPane();
		}
		shotBoxesList.clear();
	}
	
	/*
	 * Movement of the shotbox, updates the y coordinate of shotbox by step on the frame
	 */
	public void move() {
		shotBoxY += step;
	}

	/*
	 * Initilizes the shotBoxesGraphicsGUI to show the shotbox on the screen
	 */
	public void addShotBoxOnScreen() {
		shotBoxesGraphicsGUI = new ShotBoxesGraphicsGUI(this);
		MainFrame.addShotBox(shotBoxesGraphicsGUI);
	}
	
	/*
	 * ShotBox is marked for removal to ignore during the iteration.
	 */
	public void markForRemoval() {
		markedForRemoval = true;
	}

	// Getters
	
	public ShotBoxesTypes getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public int getShotBoxX() {
		return shotBoxX;
	}


	public int getShotBoxY() {
		return shotBoxY;
	}

	public static ArrayList<ShotBoxes> getShotBoxesonTheScreen() {
		return shotBoxesList;
	}
	
	public ShotBoxesGraphicsGUI getShotBoxesGraphicsGUI() {
		return shotBoxesGraphicsGUI;
	}

	public int getStep() {
		return step;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
	

	public int getDamage() {
		return damage;
	}

	public int getPoint() {
		return point;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public String getPhotoURL() {
		return photoURL;
	}
	
	public boolean isMarkedForRemoval() {
	    return markedForRemoval;
	}

}
