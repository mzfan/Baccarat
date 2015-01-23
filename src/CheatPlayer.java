


public class CheatPlayer extends SpecialPlayer {
	
	private static String[] phrases = {
		"Nyahahaha! The Magical Devil Girl is victorious once again!",
		"Nyahahaha! Angels are no match for Sophia Ring SP Saturn VII!",
		
		"Iyaa! How could The Magical Devil Girl lose to you!",
		"Nobody defeats Sophia Ring SP Saturn VII and gets away with it!"
		
	};
	
	
	public CheatPlayer(){
		super("Shichimiya Satone");
	}
	
	public boolean hitAI(Deck d){ // only takes a card if it gives greater hand value (looks into the deck)
		
		if((getHandVal()+Deck.cardVal(d.peekCard()))%10>getHandVal()){
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
