import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
	private Table gameTable = new Table(5, 7);
	
	private Person P1 = new Person();
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
	
	enum Level {
	    LOW,
	    MEDIUM,
	    HIGH
	}
	
	public ArrayList<Piece> createDeck(){
		for(int i = 0; i < 7; i++) {
			for(int j = i; j < 7; j++) {
				deck.add(new Piece(i, j));		
			}
		}
		return deck;
	}
	
	public int findFirstPlayer() {
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

	public Piece[] dealHand() {
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
	
	public void placeFirstPiece() {
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
	
	public void playGame() {
		
		startGame();
		while(true) {
			
				//if(!gameTable.isPlayable(players)) endGame();
				
				players[turn].printHand();
			
				if(!level.toString().equals("High")) 
					players[turn].addPiece(gameTable);
				else
					if(players[turn]!=P1) {
						if(players[turn]==AI_h1)
							AI_h1.addPiece(gameTable, P1);
						else if(players[turn]==AI_h2)
							AI_h2.addPiece(gameTable, P1);
						else if(players[turn]==AI_h3)
							AI_h3.addPiece(gameTable, P1);	
						else
							players[turn].addPiece(gameTable);
					}
				
				if(turn!=3) turn++;
				else turn = 0;
				
				gameTable.printTable();
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(players[turn].handEmpty()) { 
					for(int i = 0; i < 4; i++) 	
						players[i].printScore();
					break;
				}
		}	
		return ;		
	}
	
	public void startGame() {
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
				players[0] = P1;
				players[1] = AI_h1;
				players[2] = AI_h2;
				players[3] = AI_h3;		
				break;
			default:
				System.out.println("WRONG INPUT! TRY AGAIN!");
				startGame();
		}
		
		createDeck();
		
		Piece[] h1 = {new Piece(0,2), new Piece(1,6), new Piece(1,3), new Piece(2,3), new Piece(0,1), new Piece(2,4), new Piece(0,6)};
		Piece[] h2 = {new Piece(5,5), new Piece(0,5), new Piece(3,4), new Piece(2,6), new Piece(1,2), new Piece(4,6), new Piece(5,6)};
		Piece[] h3 = {new Piece(0,4), new Piece(3,6), new Piece(4,5), new Piece(0,3), new Piece(4,4), new Piece(1,5), new Piece(2,5)};
		Piece[] h4 = {new Piece(2,2), new Piece(0,0), new Piece(1,4), new Piece(1,1), new Piece(3,5), new Piece(3,3), new Piece(6,6)};
		
		P1.setPlayerHand(h1);
		AI1.setPlayerHand(h2);
		AI2.setPlayerHand(h3);
		AI3.setPlayerHand(h4);	
		
//		P1.setPlayerHand(dealHand());
//		AI1.setPlayerHand(dealHand());
//		AI2.setPlayerHand(dealHand());
//		AI3.setPlayerHand(dealHand());
		
		placeFirstPiece();
//		scan.close();
	}
	
	public ArrayList<Piece> getDeck() {
		return deck;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.playGame();	
		return ;
	}
	
}
