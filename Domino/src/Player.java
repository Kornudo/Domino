import java.util.ArrayList;

public abstract class Player {
	private Piece[] playerHand;
	
	public ArrayList<Piece> dealHand(ArrayList<Piece> deck) {
		return deck;
	}
	
	public boolean isPlayable(ArrayList<Corner> corners) {
		return true;
	}
	
	public boolean isEmpty() {
		return true;
	}
	
	public abstract boolean addPiece();
	
	public Piece[] getPlayerHand() {
		return playerHand;
	}
}
