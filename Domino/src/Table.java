import java.util.ArrayList;

public class Table {
	Piece[][] pieces = new Piece[31][31];
	ArrayList<Corner> corners = new ArrayList<>();
	
	public void printTable() {
		
	}
	
	public boolean isPlayable(Player[] players) {
		return true;
	}
	
	public boolean collision(Corner corner) {
		return false;
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void addPiece(int i, int j, Piece piece) {
		pieces[i][j] = piece;
	}
}
