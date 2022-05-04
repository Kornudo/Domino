import java.util.ArrayList;

public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	
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
