import java.util.ArrayList;

public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	
	public abstract boolean addPiece();
	
	public Piece[] getPlayerHand() {
		return playerHand;
	}
	
	public Piece[] setPlayerHand(Piece[] playerHand) {
		this.playerHand = playerHand;
		return playerHand;
	}
	
}
