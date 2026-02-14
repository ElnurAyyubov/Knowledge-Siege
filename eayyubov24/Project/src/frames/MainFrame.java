package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import game.Player;
import game.User;

// Frame on which the game is played. Is a JFrame.
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; // Auto generated
	private JPanel contentPane; // pane on which is everything
	private static JPanel enemyPane; // pane on which enemies move
	private static JPanel shotBoxPane; // pane on which shot boxes and player move
	private static Player player; // player that plays the game
	private static JProgressBar healthBar; // progress bar to represent health of player
	private static JLabel pointField; // laver to show score of player
	private static JTextArea questionField; // field where the question is asked
	private static JTextArea infoField; // field where info is displayed
	
	// CONSTANTS
	public static final int enemyPaneWidth = 753;
	public static final int shotBoxesSize = 40;
	public static final int playerSize = 100;
	public static final int enemySize = 70;


	// TODO To be deleted.
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(new User("","",""));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Initilizes object on the frame, initializes player that plays the game with user.
	 * 
	 * @param user that logged in
	 */
	public MainFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1058, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(752, 0, 292, 554);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Health bar
		JLabel lblNewLabel = new JLabel("HEALTH");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 32, 91, 36);
		panel.add(lblNewLabel);
		
		healthBar = new JProgressBar();
		healthBar.setForeground(new Color(0, 255, 64));
		healthBar.setValue(50);
		healthBar.setBounds(10, 74, 272, 27);
		panel.add(healthBar);
		
		// Score label
		JLabel lblScore = new JLabel("SCORE");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblScore.setBounds(10, 131, 91, 36);
		panel.add(lblScore);
		
		pointField = new JLabel("0025");
		pointField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		pointField.setBounds(20, 165, 91, 36);
		panel.add(pointField);
		
		
		// Question field
		JLabel lblQuestion = new JLabel("QUESTION");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblQuestion.setBounds(10, 239, 128, 36);
		panel.add(lblQuestion);
		
		questionField = new JTextArea("");
		questionField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		questionField.setBounds(10, 272, 262, 111);
		questionField.setLineWrap(true);
		questionField.setWrapStyleWord(true);
		questionField.setEditable(false);	
		questionField.setFocusable(false);
		panel.add(questionField);
		
		// Info field
		JLabel lblInfo = new JLabel("INFO");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInfo.setBounds(10, 394, 91, 36);
		panel.add(lblInfo);
		
		infoField = new JTextArea("");
		infoField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoField.setBounds(10, 425, 262, 107);
		infoField.setLineWrap(true);
		infoField.setWrapStyleWord(true);
		infoField.setFocusable(false);
		infoField.setEditable(false);
		panel.add(infoField);
		
		// Enemy pane 
		enemyPane = new JPanel();
		enemyPane.setBackground(new Color(192, 192, 192));
		enemyPane.setBounds(0, 0, 753, 70);
		enemyPane.setLayout(null);
		contentPane.add(enemyPane);
		
		// Shotbox pane
		shotBoxPane = new JPanel();
		shotBoxPane.setBackground(new Color(255,255,255));
		shotBoxPane.setBounds(0,70, 753, 484);
		shotBoxPane.setLayout(null);
		initializePlayer(user);
		contentPane.add(shotBoxPane);
		

	}
	
	/*
	 * Initilizes player to play the game, add him on the screen
	 * 
	 * @param user
	 */
	public static void initializePlayer(User user) {
		player = new Player(user.getUsername(), user.getPhotoUrl());
		PlayerGraphicsGUI playerGUI = new PlayerGraphicsGUI(player);
		int xPosition = player.getxPosition();
		int yPosition = player.getyPosition();
		playerGUI.setBounds(xPosition, yPosition, playerSize, playerSize);
		
		playerGUI.setLayout(null);
		playerGUI.requestFocusInWindow();
		shotBoxPane.add(playerGUI);
		updateShotBoxPane();
		updateHealthBar();
		updatePointField();
	}

	
	/*
	 * Adds enemy on the sceen.
	 * 
	 * @param enemyGraphicsGUI
	 */
	public static void addEnemy(EnemyGraphicsGUI enemyGraphicsGUI) {
		enemyGraphicsGUI.setBounds(0,0, enemySize, enemySize);
		enemyPane.add(enemyGraphicsGUI);
	    updateEnemyPane();
	}
	
	/*
	 * Adds shotbox on the screen.
	 * 
	 * @param shotBoxesGraphicsGUI
	 */
	public static void addShotBox(ShotBoxesGraphicsGUI shotBoxesGraphicsGUI) {
		int xPosition = shotBoxesGraphicsGUI.getShotBox().getShotBoxX();
		int yPosition = shotBoxesGraphicsGUI.getShotBox().getShotBoxY();
		shotBoxesGraphicsGUI.setBounds(xPosition, yPosition, shotBoxesSize, shotBoxesSize);
		shotBoxPane.add(shotBoxesGraphicsGUI);
		updateShotBoxPane();
	}
	
	/*
	 * Sets info on the information field
	 * 
	 * @param info
	 */
	public static void setTextToInfoField(String info) {
		infoField.setText(info);
	}
	
	/*
	 * Sets question on the question field
	 * 
	 * @param question
	 */
	public static void setTextToQuestionField(String question) {
		questionField.setText(question);
	}
	
	/*
	 * Display an message to user
	 * 
	 * @param title
	 * @param message
	 */
	public static void showInfo(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*
	 * Redraws shotbox pane with its contents
	 */
	public static void updateShotBoxPane() {
		shotBoxPane.revalidate();
		shotBoxPane.repaint();
	}
	
	/*
	 * Redraws enemy pane with its contents
	 */
	public static void updateEnemyPane() {
		enemyPane.revalidate();
		enemyPane.repaint();
	}
	
	/*
	 * Updates healt bar with player's current health
	 */
	public static void updateHealthBar() {
		healthBar.setValue(player.getHealth());
	}
	
	/*
	 * Updated point field with player's current score
	 */
	public static void updatePointField() {
		pointField.setText(String.valueOf(player.getPoints()));
	}

	// Getters 
	public static JPanel getEnemyPane() {
		return enemyPane;
	}
	
	public static JPanel getShotBoxPane() {
		return shotBoxPane;
	}
	
	public static Player getPlayer() {
		return player;
	}
}
