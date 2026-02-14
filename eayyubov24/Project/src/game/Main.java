package game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import frames.LoginFrame;
import frames.MainFrame;

/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own without
any help from anyone else. The effort in the project thus belongs completely to me.
I did not search for a solution, or I did not consult any program written by others
or did not copy any program from other sources. I read and followed the guidelines
provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Elnur Ayyubov, 88909>
*************************************************************************/

/**
 * KNOWLEDGE SIEGE PROJECT
 * @author Elnur Ayyubov
 */
public class Main {
	private static JFrame loginframe = new LoginFrame();
	private static JFrame mainframe;

	/**
	 * Main method of the proram that runs the application.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				loginframe.setVisible(true);
				loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	
	/**
	 * After successful login, loginframe is closed and game is opened for User user
	 * that is provided as a parameter to this method.
	 * 
	 * @param user
	 */
	public static void closeLoginFrameOpenGame(User user) {
		loginframe.dispose();
		LogGame.logEvent("Game started by: " + user.getUsername());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainframe = new MainFrame(user);
				mainframe.setVisible(true);
				mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GameController game = new GameController();
				game.runGame();
			}
		});
	}
	
	public static JFrame getLoginFrame() {
		return loginframe;
	}
}
