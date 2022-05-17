
public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	private int size = 7;
	private int score = 0;
	
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
	
	public int reduceSize() {
		return this.size--;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int setScore(int score) {
		this.score = score;
		return score;
	}
}
