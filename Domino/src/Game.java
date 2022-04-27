
import java.util.ArrayList;

public class Game {
	int rabu;
	private Piece[][] playerHand;
	private Piece[][] gameTable;
	ArrayList<Corner> corners = new ArrayList<Corner>();
	
	public boolean inHand() {
		return false;
	}
	
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
	
	public boolean addPiece(Piece a, Piece b) {
		return false;
	}
}
