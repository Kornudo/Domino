
import java.util.ArrayList;

public class Game {
	private Piece[][] gameTable;
	private ArrayList<Corner> corners = new ArrayList<Corner>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Piece> deck;
	
	public boolean collision() {
		return false;
	}
	
	public void dealHand() {
	}
	
	public int findFirstPlayer() {
		return 0;
	}
	
	public boolean playGame() {
		return false;
	}
	
	public void printTable() {
		}
	
	public boolean endGame() {
		return false;
	}
	
	public ArrayList<Piece> getDeck() {
		return deck;
	}
}
