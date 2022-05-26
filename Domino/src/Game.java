import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * Represents the game itself
 * Creates a Table with a determined size, declare the Players and other important variables
 * for the execution of the game, and more importantly iniciatilizes the game itself.
 * @author José Lopes and João Leandro
 * 
 */
public class Game {
	private Table gameTable = new Table(5,7);
	
	private Person P1 = new Person();
	private AI AI0;
	private AI AI1; // DECLARE BOTS
	private AI AI2;
	private AI AI3;
	
	private AI_High AI_h1;
	private AI_High AI_h2;
	private AI_High AI_h3;
	
	private Player[] players = new Player[4];
	private ArrayList<Piece> deck = new ArrayList<Piece>();
	private Random rand = new Random();
	private int turn;

	private Level level = null;
	
	private enum Level {
	    LOW,
	    MEDIUM,
	    HIGH,
	    TEST
	}
	
	private ArrayList<Piece> createDeck(){
		for(int i = 0; i < 7; i++) {
			for(int j = i; j < 7; j++) {
				deck.add(new Piece(i, j));		
			}
		}
		return deck;
	}
	
	private int findFirstPlayer() {
		for(int i = 0; i < players.length; i++) { // loop through all existent players
			Piece[] pH = players[i].getPlayerHand();
			for(int j = 0; j < pH.length; j++) { // loop through the hand of all existent players
				if(pH[j].getSideA() == 6 && pH[j].getSideB() == 6) {
					return i;
				}
			}
		}
		return -1;
	}

	private Piece[] dealHand() {
		Piece[] temp = new Piece[7];
		int limit = deck.size();
		int int_random; 

		for(int i = 0; i < 7; i++) {
			int_random = rand.nextInt(limit);
			temp[i] = deck.get(int_random);
			deck.remove(int_random);
			limit--;
		}
		return temp;
	}
	
	private void placeFirstPiece() {
		turn = findFirstPlayer();
		
		int handLen = players[turn].getPlayerHand().length;
		Piece[] playerHand = players[turn].getPlayerHand();
		for(int i = 0; i < handLen; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			if(A==6 && B==6) {
				gameTable.addPiece(playerHand[i], null);
				players[turn].removePiece(playerHand[i]);
				if(turn!=3) turn++;
				else turn=0;
				break;
			}
		}
	}
	
	private void printWinnerScore() {
		System.out.println("WINNER MTF IS JOSEVALDO " + players[turn]);
		for(int i = 0; i < 4; i++) 	
			if(i!=turn)
				players[i].printScore();
		return ;
	}
	
	private void printDraftScore() {
		for(int i = 0; i < 4; i++) 	
			players[i].printScore();
		return ;
	}
	
	/**
	 * Plays the game itself until there's a winner or no more
	 * pieces can be played
	 * 
	 * @post turn is incremented by one and decremented to the
	 * value of zero along the game
	 *
	 */
	public void playGame() {
		
		startGame();
		while(true) {
			
//				if(!gameTable.isPlayable(players)) {
//					printDraftScore();
//					return ;
//				}
				
				players[turn].printHand();
			
				if(!level.toString().equals("HIGH")) 
					players[turn].addPiece(gameTable);
				else
					if(players[turn]!=P1) {
						if(players[turn]==AI_h1)
							AI_h1.addPiece(gameTable, P1);
						else if(players[turn]==AI_h2)
							AI_h2.addPiece(gameTable, P1);
						else if(players[turn]==AI_h3)
							AI_h3.addPiece(gameTable, P1);	
					}
					else
						players[turn].addPiece(gameTable);
					
				
				gameTable.printTable();
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(turn==3) {
					boolean isDraft = true;
					for(int i = 0; i < 4; i++) {
						if(players[i].getPass()==false)
							isDraft=false;
					}
					
					if(isDraft==true) { 
						printDraftScore();
						return ;
					}
				}
				
				if(players[turn].handEmpty()) { 
					printWinnerScore();
					return ;
				}
				
				if(turn!=3) turn++;
				else {
					for(int i = 0; i < 4; i++) {
						players[i].setPass(false);
					}		
					turn = 0;
				}
		}		
	}
	
	private void startGame() {
		// SELECT DIFFICULTY			
		System.out.println("SELECT LEVEL DIFFICULTY:");
		for (Level levels : Level.values()) {
			  System.out.println(levels);
		}
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		level =  Level.valueOf(scan.next());	
		System.out.println("SELECTED " + level + " DIFFICULTY");		
				
		switch (level) {
			case LOW:
				AI1 = new AI_Low();
				AI2 = new AI_Low();
				AI3 = new AI_Low();
				players[0] = P1;
				players[1] = AI1;
				players[2] = AI2;
				players[3] = AI3;
				break;
			case MEDIUM:
				AI1 = new AI_Medium();
				AI2 = new AI_Medium();
				AI3 = new AI_Medium();
				players[0] = P1;
				players[1] = AI1;
				players[2] = AI2;
				players[3] = AI3;
				break;
			case HIGH:
				AI_h1 = new AI_High();
				AI_h2 = new AI_High();
				AI_h3 = new AI_High();
				players[0] = P1;
				players[1] = AI_h1;
				players[2] = AI_h2;
				players[3] = AI_h3;		
				break;
			case TEST:
				AI0 = new AI_Low();
				AI1 = new AI_Low();
				AI2 = new AI_Low();
				AI3 = new AI_Low();
				players[0] = AI0;
				players[1] = AI1;
				players[2] = AI2;
				players[3] = AI3;	
				break;
			default:
				System.out.println("WRONG INPUT! TRY AGAIN!");
				startGame();
		}
		
		createDeck();
		
//		Piece[] h1 = {new Piece(1,3), new Piece(0,2), new Piece(4,5), new Piece(0,5), new Piece(3,6), new Piece(2,5), new Piece(3,5)};
//		Piece[] h2 = {new Piece(0,0), new Piece(3,3), new Piece(2,2), new Piece(4,4), new Piece(2,4), new Piece(1,5), new Piece(1,2)};
//		Piece[] h3 = {new Piece(4,6), new Piece(0,1), new Piece(5,6), new Piece(1,4), new Piece(5,5), new Piece(1,6), new Piece(0,6)};
//		Piece[] h4 = {new Piece(2,6), new Piece(3,4), new Piece(0,3), new Piece(1,1), new Piece(2,3), new Piece(0,4), new Piece(6,6)};
		
//		[<1,3> <0,2> <4,5> <0,5> <3,6> <2,5> <3,5>]
//		[<0,0> <3,3> <2,2> <4,4> <2,4> <1,5> <1,2>]
//		[<4,6> <0,1> <5,6> <1,4> <5,5> <1,6> <0,6>]
//		[<2,6> <3,4> <0,3> <1,1> <2,3> <0,4> <6,6>]		
		AI0.setPlayerHand(dealHand());
		AI1.setPlayerHand(dealHand());
		AI2.setPlayerHand(dealHand());
		AI3.setPlayerHand(dealHand());	
		
//		if(!level.toString().equals("HIGH")) {
//			P1.setPlayerHand(dealHand());
//			AI1.setPlayerHand(dealHand());
//			AI2.setPlayerHand(dealHand());
//			AI3.setPlayerHand(dealHand());	
//		}
//		else {
//			P1.setPlayerHand(dealHand());
//			AI_h1.setPlayerHand(dealHand());
//			AI_h2.setPlayerHand(dealHand());
//			AI_h3.setPlayerHand(dealHand());
//		}
		placeFirstPiece();
//		scan.close();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.playGame();	
		return ;
	}
	
}
