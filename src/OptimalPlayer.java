
public class OptimalPlayer extends SpecialPlayer{
	private static String[] phrases = {
		"Guess I won.",
		"Good game.",
		
		"Good game.",
		"Guess I lost."
		
	};
	
	public OptimalPlayer(){
		super("Oreki Houtaro");
	}
	
	public boolean hitAI(Deck d){ // always takes a card
		if (getHandVal()<=5){
			drawCard(d.getCard());
			return true;
		}
		return false;
	}
	// post-round catch phrases.
	public String winPhrase(){
		return super.winPhrase(phrases);
	}
	public String losePhrase(){
		return super.losePhrase(phrases);
	}
}
