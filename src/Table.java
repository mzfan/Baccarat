import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// takes care of game rules
public class Table {
	
	private ArrayList<Player> players;
	private Deck deck;
	private Player user;
	private int pointsPool;
	private int dealer;
	
	public Table(int totalBots, String username){
		deck = new Deck();
		players = new ArrayList<Player>();
		user = new Player(username);
		dealer = 0;
		
		for (int i=1; i<=totalBots;i++)
			players.add(new Player(i));
		players.add(new CheatPlayer());
		players.add(new BadPlayer());
		players.add(new OptimalPlayer());
		Collections.shuffle(players);
	}
	
	public void setPool(int i){
		pointsPool=i;
	}
	public void addPool(int bet){
		pointsPool+=bet;
	}
	public int getPool(){
		return pointsPool;
	}
	
	public boolean dealerHitAI(){
		return players.get(dealer).hitAI(deck);
	}
	
	public void betAI(){
		Random better = new Random();
		int wager;
		if (players.get(dealer).getPoints()<15)
			wager = better.nextInt(players.get(dealer).getPoints())+1;
		else 
			wager = better.nextInt(11)+5;
		addPool(wager);
		players.get(dealer).wager(wager);
	}
	
	public void undoBetAI(){
		players.get(dealer).givePoints(getPool());
		setPool(0);
	}
	
	public void userDrawCard(){
		user.drawCard(deck.getCard());
	}
	public void dealerDrawCard(){
		players.get(dealer).drawCard(deck.getCard());
	}
	
	public void betUser(int bet){
		addPool(bet);
		user.wager(bet);
	}
	public void allInUser(){
		betUser(user.getPoints());
	}
	public int userPoints(){
		return user.getPoints();
	}
	
	public int tableSize(){
		return players.size();
	}
	
	public String displayDeck(){
		return deck.toString();
		
	}
	public void shuffleDeck(){
		deck.shuffle();
	}
	public int getDeckSize(){
		return deck.getSize();
	}
	public void newDeck(){
		deck = new Deck();
		shuffleDeck();
	}
	
	public void displayRound(){
		System.out.println(players.get(dealer)+"\n");
		System.out.println(user);
	}
	public void displayRoundHidden(){
		System.out.println(players.get(dealer).toStringHidden()+"\n");
		System.out.println(user);
	}
	
	public String getDealer(){
		return players.get(dealer).getName();
	}
	public String getUser(){
		return user.getName();
	}
	public int getDealerHandVal(){
		return players.get(dealer).getHandVal();
	}
	public int getUserHandVal(){
		return user.getHandVal();
	}
	
	
	public void userWin(){
		user.givePoints(getPool());
		setPool(0);
	}
	public void userLose(){
		players.get(dealer).givePoints(getPool());
		setPool(0);
	}
	public void tieGame(){
		players.get(dealer).givePoints(getPool()/2);
		user.givePoints(getPool()/2);
		setPool(0);
	}
	
	public void displayWinQuote(){
		if (players.get(dealer).isSpecial())
			System.out.println(players.get(dealer).getName()+": "+players.get(dealer).winPhrase());
	}
	public void displayLoseQuote(){
		if (players.get(dealer).isSpecial())
			System.out.println(players.get(dealer).getName()+": "+players.get(dealer).losePhrase());
	}
	
	public void cycleDealer(){
		dealer++;
		if (dealer>=tableSize())
			dealer=0;
	}
	
	public void clearHands(){
		players.get(dealer).clearHand();
		user.clearHand();
	}
	
	
	public void scoreboard(){
		System.out.println("--Final Points--");
		System.out.println(user.getName()+": "+user.getPoints());
		for (Player p : players){
			System.out.println(p.getName()+": "+p.getPoints());
		}
	}
	
	public int getDealerPoints(){
		return players.get(dealer).getPoints();
	}
	public int getUserPoints(){
		return user.getPoints();
	}
	public void bankruptAI(){
		players.remove(dealer);
		dealer--;
	}
	
}
