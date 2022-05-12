
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private Table gameTable;
	private Player[] players = new Player[4];
	private ArrayList<Piece> deck = new ArrayList<Piece>();
	private Random rand = new Random();
	
	public Game (Table gameTable, Player[] players) {
		
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
	
	
	public boolean playGame() {
		return false;
	}
	
	public boolean endGame() {
		return false;
	}
	
	public ArrayList<Piece> getDeck() {
		return deck;
	}
}
