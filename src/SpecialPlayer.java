import java.util.Random;


public class SpecialPlayer extends Player {
	
	
	public SpecialPlayer(String n){
		super(n);
	}
	
	// post-round catch phrases.
	// String arrays passed in must be length of 4. (2 win, 2 loss phrases)
	public String winPhrase(String[] phrases){
		Random r = new Random();
		return phrases[r.nextInt(2)];
	}
	public String losePhrase(String[] phrases){
		Random r = new Random();
		return phrases[r.nextInt(2)+2];
	}
	public boolean isSpecial(){ // all special players return true, regular players return false.
		return true;
	}

}
