package Competition;
import java.util.Random;

public class Person {

	public String name;
	public String code;	
	public int score;	

	public Person(String name, String code, int score) {
		this.name = name;
		this.code = genCode();
		this.score = 0;
	}
	// method synchronized to enforce uniqueness
	public synchronized String genCode() {
		int charLength = 9;
		return String.valueOf(charLength < 1 ? 0: 
			new Random().nextInt((9*(int)Math.pow(10, charLength -1))-1)
			+ (int)Math.pow(10, charLength -1));
	}
}
