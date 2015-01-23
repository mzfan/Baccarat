import java.util.*;

public class Deck {
	
	private static char[] suits = {'D','C','H','S'};
	
	private ArrayList<Integer> cards;
	
	public Deck(){
		cards = new ArrayList<Integer>();
		for (int j=0;j<2;j++)
			for (int i=0;i<52;i++)
				cards.add(i);
	}
	
	//show full deck (toString)
	public String toString(){
		String fulldeck = "";
		for (int i : cards){
			fulldeck+=cardName(i); 
		}
		return fulldeck;
	}
	//shuffle deck
	public void shuffle(){
		Collections.shuffle(cards);
	}
	//return top card
	public int getCard(){
		return cards.remove(0);
	}
	//for the cheaterPlayer only
	public int peekCard(){
		return cards.get(0);
	}
	
	//return card name
	public static String cardName(int cardval){ // returns card name
		if (cardval%13 == 0)
			return "["+"K"+suits[cardval/13]+"]";
		else if (cardval%13==11)
			return "["+"J"+suits[cardval/13]+"]";
		else if (cardval%13==12)
			return "["+"Q"+suits[cardval/13]+"]";
		else if (cardval%13==1)
			return "["+"A"+suits[cardval/13]+"]";
		return "["+Integer.toString(cardval%13)+suits[cardval/13]+"]";
	}
	
	//return card value
	public static int cardVal(int cardval){ //returns card value
		if (cardval%13==10 || cardval%13==11 || cardval%13==12)
			return 0;
		return cardval%13;
	}
	
	public int getSize(){ // need to reshuffle deck once deck is less than 104 cards
		return cards.size();
	}
	
}
