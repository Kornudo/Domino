import java.util.ArrayList;

public abstract class Player {
	private Piece[][] playerHand;
	
	public void dealHand(){
		
	}
	
	public boolean isPlayable(ArrayList<Corner> corners) {
		return true;
	}
	
	public boolean isEmpty() {
		return true;
	}
	
	public abstract boolean addPiece();
}
