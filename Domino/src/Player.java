
public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	private int score = 0;
	
	public boolean handEmpty() {	
		if(playerHand.length == 0) {
			return true;
		}
		return false;
	}
	
	public Piece[] removePiece(Piece piece) {
		Piece[] temp = null;
		for(int i = 0; i < this.playerHand.length; i++) {
			if(this.playerHand[i]!=piece) temp[i] = this.playerHand[i];
		}
		return temp;	
	}
	
	public Piece[] getPlayerHand() {
		return this.playerHand;
	}
	
	public Piece[] setPlayerHand(Piece[] playerHand) {
		this.playerHand = playerHand;
		return playerHand;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int setScore(int score) {
		this.score = score;
		return score;
	}
}
