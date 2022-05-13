
import java.util.ArrayList;

public class Game {
	private Table gameTable;
	private Player[] players = new Player[4];
	private ArrayList<Piece> deck = new ArrayList<Piece>();

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
		return 0;
	}
	
	public Piece[] dealHand() {
		return null;
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
