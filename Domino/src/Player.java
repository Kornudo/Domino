
public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	
	public abstract boolean addPiece();
	
	public boolean handEmpty() {	
		if(playerHand.length == 0) {
			return true;
		}
		return false;
	}
	
	public Piece[] getPlayerHand() {
		return playerHand;
	}
	
	public Piece[] setPlayerHand(Piece[] playerHand) {
		this.playerHand = playerHand;
		return playerHand;
	}	
}
