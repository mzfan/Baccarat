import java.util.ArrayList;
import java.util.Random;


public class Player {
	
	//Attributes
	private String name;
	private ArrayList<Integer> hand;
	private int points;
	private int handVal;
	
	
	//Constructors
	public Player(String n){
		name = n;
		hand = new ArrayList<Integer>();
		handVal = 0;
		points = 100;
	}
	public Player(int i){
		this("Bot"+i);
	}
	
	//Behaviours 
	public void drawCard(int i){
		hand.add(i);
	}
	public boolean hitAI(Deck d){
		Random r = new Random();
		if(r.nextInt(2)==0){
			drawCard(d.getCard());
			return true;
		}
		return false;
	}
	
	public boolean isSpecial(){ // check if the object is a special player or bot.
		return false;
	}
	
	public int getHandVal(){ // card value
		handVal=0;
		for (int i : hand)
			handVal+=Deck.cardVal(i);
		return handVal%10;
	}
	public String getHand(){ //actual card names
		String strHand="";
		for (int i : hand)
			strHand+=Deck.cardName(i);
		return strHand;
	}
	public int getPoints(){
		return points;
	}
	
	public String getName(){
		return name;
	}
	
	public void givePoints(int p){
		points+=p;
	}
	public void wager(int w){
		points-=w;
	}
	
	public void clearHand(){
		hand.clear();
		handVal=0;
	}
	
	public String toString(){
		String display="--"+name+"--\nHand: "+getHand()+"\nValue: "+getHandVal()+"\nPoints: "+points;
		return display;
			
	}
	public String toStringHidden(){ //hides hand and hand value.
		String hidCards = "";
		for (int i=0; i<hand.size();i++)
			hidCards+="[ ]";
		return "--"+name+"--\nHand: "+hidCards+"\nPoints: "+points;
		
	}
	public String winPhrase() {
		return null;
	}
	public String losePhrase(){
		return null;
	}
	
}
