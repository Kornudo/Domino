import java.util.ArrayList;

public class Table {
	Piece[][] pieces;
	ArrayList<Corner> corners = new ArrayList<>();
	
	public void printTable() {
		
	}
	
	public boolean isPlayable(ArrayList<Corner> corners) {
		return true;
	}
	
	public boolean collision() {
		return false;
	}
}
