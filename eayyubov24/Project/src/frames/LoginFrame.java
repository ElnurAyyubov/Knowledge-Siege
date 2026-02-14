package frames;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import game.GameSession;
import game.LoginController;
import game.ScoreBoardManager;

// Frame for login, register, show score board. Is a JFrame.
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L; // Auto-generated
	private JPanel contentPane;// pane on which is everything
	private static JTextField loginUsernameField;// login username field
	private static JPasswordField loginPasswordField; // login password field
	private static JTextField regUsernameField; // register username field
	private static JPasswordField regPasswordField; // register password field


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Initializes its content.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblNewLabel = new JLabel("Log In");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 38));
		lblNewLabel.setBounds(154, 26, 119, 68);
		contentPane.add(lblNewLabel);
		
		loginUsernameField = new JTextField();
		loginUsernameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loginUsernameField.setBounds(79, 135, 250, 37);
		contentPane.add(loginUsernameField);
		loginUsernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblUsername.setBounds(79, 98, 119, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblPassword.setBounds(79, 215, 119, 37);
		contentPane.add(lblPassword);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(79, 252, 250, 37);
		contentPane.add(loginPasswordField);
		
		JButton loginButton = new JButton("Log in");
		loginButton.setFont(new Font("Sylfaen", Font.PLAIN, 28));
		loginButton.setBounds(133, 326, 140, 48);
		contentPane.add(loginButton);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Sylfaen", Font.PLAIN, 38));
		lblRegister.setBounds(572, 26, 140, 68);
		contentPane.add(lblRegister);
		
		regUsernameField = new JTextField();
		regUsernameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		regUsernameField.setColumns(10);
		regUsernameField.setBounds(518, 135, 250, 37);
		contentPane.add(regUsernameField);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblUsername_1.setBounds(518, 98, 119, 37);
		contentPane.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblPassword_1.setBounds(518, 215, 119, 37);
		contentPane.add(lblPassword_1);
		
		regPasswordField = new JPasswordField();
		regPasswordField.setBounds(518, 252, 250, 37);
		contentPane.add(regPasswordField);
		
		JButton regButton = new JButton("Register");
		regButton.setFont(new Font("Sylfaen", Font.PLAIN, 28));
		regButton.setBounds(572, 410, 140, 48);
		contentPane.add(regButton);
		
		JLabel lblScoreTable = new JLabel("Score Table:");
		lblScoreTable.setFont(new Font("Sylfaen", Font.PLAIN, 38));
		lblScoreTable.setBounds(10, 427, 222, 48);
		contentPane.add(lblScoreTable);
		
		JButton openScoreTableButton = new JButton("Open");
		openScoreTableButton.setFont(new Font("Sylfaen", Font.PLAIN, 28));
		openScoreTableButton.setBounds(228, 428, 140, 48);
		contentPane.add(openScoreTableButton);
		
		// ================= Character Buttons =================
		
		// Character 1
		JToggleButton character1ToggleButton = new JToggleButton();
		character1ToggleButton.setBounds(480, 306, 85, 80);
		contentPane.add(character1ToggleButton);
		ImageIcon icon = new ImageIcon("src/photos/character1.png");
		Image img = icon.getImage().getScaledInstance(
		    64, 64, 
		    Image.SCALE_SMOOTH
		);
		character1ToggleButton.setIcon(new ImageIcon(img));
		
		// Character 2
		JToggleButton character2ToggleButton = new JToggleButton();
		character2ToggleButton.setBounds(601, 306, 85, 80);
		contentPane.add(character2ToggleButton);
		ImageIcon icon2 = new ImageIcon("src/photos/character2.png");
		
		Image img2 = icon2.getImage().getScaledInstance(
		    64, 64, 
		    Image.SCALE_SMOOTH
		);
		character2ToggleButton.setIcon(new ImageIcon(img2));
		
		// Character 3
		JToggleButton character3ToggleButton = new JToggleButton();
		character3ToggleButton.setBounds(720, 306, 85, 80);
		contentPane.add(character3ToggleButton);
		
		ImageIcon icon3 = new ImageIcon("src/photos/character3.png");
		Image img3 = icon3.getImage().getScaledInstance(
		    64, 64, 
		    Image.SCALE_SMOOTH
		);
		character3ToggleButton.setIcon(new ImageIcon(img3));
		
		// By selected either of toggle buttons, it deselects the other ones
		character1ToggleButton.addActionListener(e -> {
			character2ToggleButton.setSelected(false);
			character3ToggleButton.setSelected(false);
		});
		
		character2ToggleButton.addActionListener(e -> {
			character1ToggleButton.setSelected(false);
			character3ToggleButton.setSelected(false);
		});
		
		character3ToggleButton.addActionListener(e -> {
			character1ToggleButton.setSelected(false);
			character2ToggleButton.setSelected(false);
		});
		
		// ================= Action listeners =================
		
		regButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = regUsernameField.getText();
				String password = new String(regPasswordField.getPassword());
				String selectedFile = null;
				if (character1ToggleButton.isSelected()) {
					selectedFile = "/photos/character1.png";
				} else if (character2ToggleButton.isSelected()) {
					selectedFile = "/photos/character2.png";
				} else if (character3ToggleButton.isSelected()) {
					selectedFile = "/photos/character3.png";
				}
				
				if (!username.isBlank() && !password.isBlank() && selectedFile != null) {
					LoginController.registerUser(username, new String(password), selectedFile);
				} else if (selectedFile == null) {
					showError("No photo selected.");
				} else {
					showError("Username or password is empty");
				}
			}
		});
		
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = loginUsernameField.getText();
				char[] password = loginPasswordField.getPassword();
				if (!username.isBlank() && !password.toString().isBlank()) {
					LoginController.loginUser(username, new String(password));
				} else {
					showError("Username or password is empty");
				}
			}
			
		});
		
		openScoreTableButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(createScoresString())), "Scoreboard", JOptionPane.INFORMATION_MESSAGE);
		});

	}
	
	/*
	 * Friendly show the error occured to user
	 * 
	 * @param message
	 */
	public static void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/*
	 * Friendly show the successful operation to user
	 * 
	 * @param message
	 */
	public static void showSuccess(String message) {
		JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*
	 * Clears all fields. The method is used after clicking login/register
	 */
	public static void clearFields() {
		loginUsernameField.setText("");
		loginPasswordField.setText("");
		regUsernameField.setText("");
		regPasswordField.setText("");		
	}
	
	/*
	 * Creates a single string of scores
	 * 
	 * @return scores board
	 */
	public String createScoresString() {
        ScoreBoardManager manager = new ScoreBoardManager();
        List<GameSession> sessions = manager.getGameSessionsList();
        Map<String, Integer> repetingSessions = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        int counter = 1;
        for (GameSession session : sessions) {
        	if (repetingSessions.containsKey(session.getUsername())) {
        		stringBuilder.append(String.format("%d - %s,%d\n", counter, session.getUsername() + "(Game" + repetingSessions.get(session.getUsername()) + ")", session.getScore()));
        		repetingSessions.put(session.getUsername(), repetingSessions.get(session.getUsername()) + 1);
        	} else {
        		stringBuilder.append(String.format("%d - %s,%d\n", counter, session.getUsername(), session.getScore()));
        		repetingSessions.put(session.getUsername(), 2);
        	}
        	counter++;
        }
        return stringBuilder.toString();
	}
}
