
import java.util.Scanner;
public class MainGame {
	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		
		boolean running = true;
		
		
		//gameStates
		final int MENU=0; final int PLAY=1; final int HELP=2; final int QUIT=3;
		int gameState=0;
		
		System.out.println("Enter Username: ");
		String name = in.nextLine();
		
		while (running){
			switch(gameState){
			
			case MENU:
				System.out.println("\n\n\n\n\n\nWelcome to Baccarat.");
				System.out.println("--- --- --- ---");
				System.out.println("1 - Start Game");
				System.out.println("2 - Instructions");
				System.out.println("3 - Exit");
				System.out.println("Input a number.");
				
				gameState = intIN(1,3);
				
				break;
				
			case HELP:
				
				
				System.out.println("\n\ninstructions go here. press any key to continue.");
				gameState=MENU;
				in.nextLine();
				break;
			
			case PLAY:
				
				System.out.println("How many bots? (1-4)");
				int botnum = intIN(1,4);
				Table t = new Table(botnum,name);
				
				System.out.println("\n"+t.displayDeck());
				System.out.println("Shuffling.");
				t.shuffleDeck();
				System.out.println(t.displayDeck());
				int rounds = t.tableSize();
				System.out.println("The game will last "+rounds*2+" rounds.\n");
				for (int i=0; i<rounds*2;i++){ // # ofrounds = table size x 2
					System.out.println("\n");
					if (t.getDeckSize()<52)
						t.newDeck();
					
					t.clearHands();
					
					t.cycleDealer(); //rotate the dealer shoe and let the AI place the first bet
					t.betAI();
					
					System.out.println(t.getDealer()+" bet "+t.getPool()+" points.");
					
					System.out.println("You have "+t.getUserPoints()+" points."); // see the current playing field
					
					System.out.println("Do you want to call? (Y/Other-No)");
					if (in.nextLine().trim().toUpperCase().charAt(0)=='Y'){
						if(t.getPool()>t.userPoints()){
							System.out.println("Going all in.");
							t.allInUser();
						}else
							t.betUser(t.getPool());
						
						// deal out first 4 cards
						t.dealerDrawCard(); t.userDrawCard();
						t.dealerDrawCard(); t.userDrawCard();
						t.displayRoundHidden();
						
						System.out.println("Hit? (Y/Other-No)");
						if (in.nextLine().trim().toUpperCase().charAt(0)=='Y')
							t.userDrawCard();
						
						System.out.println();
						if(t.dealerHitAI())
							System.out.println(t.getDealer()+" chose to hit.");
						else
							System.out.println(t.getDealer()+" chose to stand.");
						
						t.displayRound();
						System.out.println();
						if (t.getDealerHandVal()>t.getUserHandVal()){ // dealer wins
							t.displayWinQuote();
							System.out.println("You Lost!");
							t.userLose(); //points distribution
							
							if (t.getUserPoints()==0){
								System.out.println("\n\nGame over.");
								in.nextLine();
								break;
							}
						}else if(t.getDealerHandVal()<t.getUserHandVal()){ // dealer loses
							t.displayLoseQuote();
							System.out.println("You Won!");
							t.userWin();
							
							if (t.getDealerPoints()==0){
								System.out.println(t.getDealer()+" bankrupted!");
								t.bankruptAI();
							}
						}else{
							System.out.println("Tie!");
							t.tieGame();
						}
						System.out.println("\n"+t.displayDeck());
						System.out.println("Cards remaining:"+t.getDeckSize());
						
					}else{
						t.undoBetAI();
					}
					
				}
				System.out.println("\n");
				t.scoreboard();
				
				System.out.println("\nPlay again? (Y/Other-No)");
				if (in.nextLine().trim().toUpperCase().charAt(0)=='Y')
					gameState=PLAY;
				else
					gameState=MENU;
				
				break;
				
			case QUIT:
				System.out.println("Goodbye.");
				running =false;
				break;
				
				
			}//switch case end
			
			
			
		}//running loop end
		
		/* Menu
		 * 
		 * Start Game
		 * 
		 * Instructions
		 * 
		 * Exit
		 * 
		 * */
		in.close();
	}
	
	// Bulletproofed integer user input.
	public static int intIN(int lower,int upper){ //inclusive positive lower upper bound.
		boolean error = true;
		int out=-1;
		do{
			try{
				out = in.nextInt();in.nextLine();
				if (out>=lower && out<=upper)
					error=false;
			}catch(Exception e){
				System.out.println("InvalidInput.");
				in.nextLine();
				continue;
			}
		}while(error);
		
		return out;
	}

}
