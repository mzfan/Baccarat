
public class BadPlayer extends SpecialPlayer{
	private static String[] phrases = {
		"Wow, I won! Beginner's luck I guess...",
		"Yay I did it! I won!",
		
		"Fuyukai desu!",
		"... Fuyukai desu..."
		
	};
	
	public BadPlayer(){
		super("Kuriyama Mirai");
	}
	
	public boolean hitAI(Deck d){ // always takes a card
		drawCard(d.getCard());
		return true;
	}
	// post-round catch phrases.
	public String winPhrase(){
		return super.winPhrase(phrases);
	}
	public String losePhrase(){
		return super.losePhrase(phrases);
	}
}
