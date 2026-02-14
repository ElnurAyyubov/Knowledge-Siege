package game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Database class that holds information about Enemies (Knowledge keepers), like SL,
 * TA and Professor. Each Enemy type has its own list. 
 */
public class DataBase {
	private static ArrayList<String[]> SLList = new ArrayList<>();
	private static ArrayList<String[]> TAList = new ArrayList<>();
	private static ArrayList<String[]> profList = new ArrayList<>();
	private static SecureRandom random = new SecureRandom();
		
	/**
	 * Initializes the lists of SL, TA, Professors with data: name, position, path to image.
	 * The method is runned in GameCOntroller class before the start of the game.
	 */
	public static void initStaff() {
		profList.add(new String[]{"Öznur Özkasap", "professor", "/photos/oznurozkasap.png"});
		profList.add(new String[]{"Attila Gürsoy", "professor", "/photos/attilagursoy.png"});
		TAList.add(new String[]{"Vahideh Hayyolalam", "ta", "/photos/vahidehhayyolalam.png"});
		TAList.add(new String[]{"Abdulrezzak Zekiye", "ta", "/photos/abdulrezzakzekiye.png"});
		TAList.add(new String[]{"Hamza Abuzahra", "ta", "/photos/hamzaabuzahra.png"});
        TAList.add(new String[]{"Fatma Nur Yaşar", "ta", "/photos/fatmanuryasar.png"});
        TAList.add(new String[]{"Aylanur Ertürk", "ta", "/photos/aylanurerturk.png"});
        SLList.add(new String[]{"Efte Değişmiş", "sl", "/photos/eftedegismis.png"});
        SLList.add(new String[]{"Ekin Gün", "sl", "/photos/ekingun.png"});
        SLList.add(new String[]{"Ahmet Şükrü Kilıç", "sl", "/photos/ahmetsukrukilic.png"});
        SLList.add(new String[]{"Nazrin Mustafazadeh", "sl", "/photos/nazrinmustafazadeh.png"});
        SLList.add(new String[]{"Burak Gerçekaslan", "sl", "/photos/burakgercekaslan.png"});
        SLList.add(new String[]{"Ozan Özak", "sl", "/photos/ozanozak.png"});
        SLList.add(new String[]{"Abdullah Daoud", "sl", "/photos/abdullahdaoud.png"});
        SLList.add(new String[]{"Ertuğrul Recep Kocaman", "sl", "/photos/ertugrulrecepkocaman.png"});
	}
	
	/**
	 * Randomly peeks 4 SL from SLlist for round one. 
	 * 
	 * @return list of 4 SL in the form of String array that holds data like: name, position, path to image.
	 */
	public static ArrayList<String[]> pickEnemiesFirstRound() {
		ArrayList<String[]> roundOneEnemies = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int randomIndex = random.nextInt(SLList.size());
			roundOneEnemies.add(SLList.get(randomIndex));
			SLList.remove(SLList.get(randomIndex));
		}
		
		return roundOneEnemies;
	}
	
	/**
	 * Randomly picks 2 TAs and SL that was not used in level 1.
	 * 
	 * @return list of 2 TAs and SL in the form of String array that holds data 
	 * 		like: name, position, path to image.
	 */
	public static ArrayList<String[]> pickEnemiesSecondRound() {
		ArrayList<String[]> roundTwoEnemies = new ArrayList<>();
		roundTwoEnemies.addAll(SLList);
		for (int i = 0; i < 2; i++) {
			int randomIndex = random.nextInt(TAList.size());
			roundTwoEnemies.add(TAList.get(randomIndex));
			TAList.remove(TAList.get(randomIndex));
		}
		
		Collections.shuffle(roundTwoEnemies);
		return roundTwoEnemies;
	}
	
	/**
	 * Picks 2 professors and TAs that was not used on level 2
	 * 
	 * @return list of 2 professors and 3 TAs in the form of String array that holds data 
	 * 		like: name, position, path to image.
	 */
	public static ArrayList<String[]> pickEnemiesThirdRound() {
		ArrayList<String[]> roundThirdEnemies = new ArrayList<>();
		roundThirdEnemies.addAll(TAList);
		roundThirdEnemies.addAll(profList);
		Collections.shuffle(roundThirdEnemies);
		return roundThirdEnemies;
	}
	
	
}
