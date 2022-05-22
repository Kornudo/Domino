import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private Table gameTable = new Table();
	
	Person P1 = new Person();
	AI AI1; // DECLARE BOTS
	AI AI2;
	AI AI3;
	
	private Player[] players = {P1, AI1, AI2, AI3};
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
		int handLen = players[turn].getPlayerHand().length;
		Piece[] playerHand = players[turn].getPlayerHand();
		for(int i = 0; i < handLen; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			if(A==6 && B==6) {
				gameTable.addPiece(playerHand[i], null);
				players[turn].removePiece(playerHand[i]);
				break;
			}
			//pinto gordo e grosso eclipse e um osso
			var xota;
		}
	}
	
	public void playGame() {
		
		startGame();
		while(true) {
			
			if(players[turn]==P1) {
				P1.addPiece(gameTable);
			}
			else
				if(level.toString().equals("Low")) {
					players[turn].addPiece(gameTable);
				}
				else if(level.toString().equals("Medium")) {
					
				}
			
			
		}
			
	}
	
	public void startGame() {
		// SELECT DIFFICULTY			
		System.out.println("SELECT LEVEL DIFFICULTY:");
		for (Level levels : Level.values()) {
			  System.out.println(levels);
		}
		
		Scanner scan = new Scanner(System.in);
		level =  Level.valueOf(scan.next());	
		System.out.println("SELECTED" + level + "DIFFICULTY");		
				
		switch (level) {
			case LOW:
				AI1 = new AI_Low();
				AI2 = new AI_Low();
				AI3 = new AI_Low();
				break;
			case MEDIUM:
				AI1 = new AI_Medium();
				AI2 = new AI_Medium();
				AI3 = new AI_Medium();
				break;
			case HIGH:
				AI1 = new AI_High();
				AI2 = new AI_High();
				AI3 = new AI_High();			
				break;
			default:
				System.out.println("WRONG INPUT! TRY AGAIN!");
				startGame();
		}
		
		createDeck();
		P1.setPlayerHand(dealHand());
		AI1.setPlayerHand(dealHand());
		AI2.setPlayerHand(dealHand());
		AI3.setPlayerHand(dealHand());	
		turn = findFirstPlayer();
		placeFirstPiece();
	}
	
	public boolean endGame() {
		return false;
	}
	
	public ArrayList<Piece> getDeck() {
		return deck;
	}
}
