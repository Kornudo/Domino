import java.util.ArrayList;

public class Table {
	private int TABLE_SIDE = 60; 
	
	Piece[][] pieces = new Piece[30][30];
	String[][] print = new String[TABLE_SIDE][TABLE_SIDE];
	static ArrayList<Corner> corners = new ArrayList<Corner>();
	
	public Table () {
		for (int i = 0; i < TABLE_SIDE; i++) {
			for (int u = 0; i < TABLE_SIDE; i++) {
				print[i][u]= " ";
			}
		}
	}
	
	public static boolean existCorner(int A, int B) {
		
		for(int i = 0; i < corners.size(); i++) {
			int cA = corners.get(i).getPiece().getSideA(); 
			int cB = corners.get(i).getPiece().getSideB();
			if((cA == A && cB == B) || (cB == B && cA == A)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlayable(Player[] players) {
		
		for(int i = 0; i < players.length; i++) { // loop through all existent players
			Piece[] pH = players[i].getPlayerHand();
			for(int j = 0; j < pH.length; j++) { // loop through the hand of all existent players
				for(int k = 0; k < corners.size(); k++) { // loop through corner arraylist
					int A = corners.get(k).getPiece().getSideA(); // get corner from corner arraylist | get Piece from corner | get side values
					int B = corners.get(k).getPiece().getSideB();
					if((pH[j].getSideA() == A) || (pH[j].getSideB() == B)) {
						return true;
					}			
				}
			}
		}
		return false;
	}
	
	public boolean collision(Corner corner) {
		return false;
	}
	
	public void printTable() {
		for (int i = 0; i < 100; i++) {
			System.out.print(".");
		}
		
		for (int i = 0; i < 31; i++) 
			for (int j = 0; j < 31; j++) {
				
			}
		
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void addPiece(int i, int j, Piece piece) {
		pieces[i][j] = piece;
		
	}
	
	public Piece[][] getPieces() {
		return pieces;
	}
	
	public Piece[][] setPieces(Piece[][] pieces) {
		this.pieces = pieces;
		return pieces;
	}
		
	public static void main(String[] args) {
		Table table = new Table();
		table.addPiece(15, 15, null);
		table.printTable();
	}
}

