
import java.util.ArrayList;

public class Game {
	private Table gameTable;
	private Player[] players = new Player[4];
	private ArrayList<Piece> deck = new ArrayList<Piece>();

	public Game (Table gameTable, Player[] players) {
		
	}
	
	public ArrayList<Piece> createDeck(){
		deck.add(new Piece(0, 0));
		deck.add(new Piece(0, 1));
		deck.add(new Piece(0, 2));
		deck.add(new Piece(0, 3));
		deck.add(new Piece(0, 4));
		deck.add(new Piece(0, 5));
		deck.add(new Piece(0, 6));
		
		deck.add(new Piece(1, 1));
		deck.add(new Piece(1, 2));
		deck.add(new Piece(1, 3));
		deck.add(new Piece(1, 4));
		deck.add(new Piece(1, 5));
		deck.add(new Piece(1, 6));
		
		deck.add(new Piece(2, 2));
		deck.add(new Piece(2, 3));
		deck.add(new Piece(2, 4));
		deck.add(new Piece(2, 5));
		deck.add(new Piece(2, 6));
		
		deck.add(new Piece(3, 3));
		deck.add(new Piece(3, 4));
		deck.add(new Piece(3, 5));
		deck.add(new Piece(3, 6));
		
		deck.add(new Piece(4, 4));
		deck.add(new Piece(4, 4));
		deck.add(new Piece(4, 5));
		deck.add(new Piece(4, 6));
		
		deck.add(new Piece(5, 5));
		deck.add(new Piece(5, 6));
		
		deck.add(new Piece(6, 6));
		return deck;
	}
	
	public Piece[] dealHand() {
		return null;
	}
	
	public int findFirstPlayer() {
		return 0;
	}
	
	public boolean playGame() {
		return false;
	}
	
	public boolean endGame() {
		return false;
	}
	
	public boolean handEmpty() {
		return false;
	}
	
	public ArrayList<Piece> getDeck() {
		return deck;
	}
}
