import java.util.ArrayList;
import java.util.Scanner;

public class Table {

	Piece[][] pieces = new Piece[5][9];
	String[][] print = new String[30][28];
	ArrayList<Corner> corners = new ArrayList<Corner>();

	
	public Table () {
		print[0][0] = "┌";
		for (int j = 1; j < 26; j++) print[0][j]= "─";
		print[0][26]= "┐";
		print[0][27]= "\r\n";
		for (int i = 1; i < 29; i++) {
			print[i][0]= "│";
			for (int j = 1; j < 26; j++) {
				print[i][j]= " ";
			}
			print[i][26]= "│";
			print[i][27]= "\r\n";
		}
		print[29][0] = "└";
		for (int j = 1; j < 26; j++) print[29][j]= "─";
		print[29][26]= "┘";
		print[29][27]= "\r\n";
	
	}
	
	public Corner existCorner(int A, int B) {
		for(int i = 0; i < corners.size(); i++) {
			int cA = corners.get(i).getPiece().getSideA(); 
			int cB = corners.get(i).getPiece().getSideB();
			if((cA == A && cB == B) || (cB == B && cA == A)) {
				return corners.get(i);
			}
		}
		return null;
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
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 28; j++) {
				System.out.print(print[i][j]);
			}
		}
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void newCorner(int i, int j, Piece piece, Corner corner) { // i and j piece position; corner = old corner 
		switch (corner.getDirection()) {
			case "left":
				addCorner(new Corner("left", i, j - 1, piece));
			case "up":
				addCorner(new Corner("up", i + 1, j, piece));
			case "right":
				addCorner(new Corner("right", i, j + 1, piece));
			case "down":
				addCorner(new Corner("down", i - 1, j, piece));
		}
	}
	
	public void addPiece(int i, int j, Piece piece, Corner corner) { //SECALHAR PODEMOS TIRAR A VAR PIECE DO CORNER E POMOS COMO VAR O VALOR DO OUTERSIDE E ASSIM N PRECISAMOS DO METODO OUTER SIDE
		pieces[i][j] = piece;
		
		if (corner == null) {
			print[17][13] = "6";
			print[15][13] = "-";
			print[13][13] = "6";
			addCorner(new Corner("left", i, j - 1, piece));
			addCorner(new Corner("up", i + 1, j, piece));
			addCorner(new Corner("right", i, j + 1, piece));
			addCorner(new Corner("down", i - 1, j, piece));
		}
		else if (piece.getSideA() == corner.outerSide()) { 
			print[i * 7 + 1][j * 3 + 2] = String.valueOf(piece.getSideA());
			print[i * 7 + 1][j * 3 + 1] = String.valueOf(piece.getSideB());
			corners.remove(corner);
			newCorner(i, j, piece, corner);
		} // if placed on a vertical double ^v^
		else {
			print[i * 7 + 1][j * 3 + 1] = String.valueOf(piece.getSideA());
			print[i * 7 + 1][j * 3 + 2] = String.valueOf(piece.getSideB());
			corners.remove(corner);
			newCorner(i, j, piece, corner);
		}
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
		table.addPiece(2, 4, new Piece(6, 6), null);
		
		table.addPiece(2, 3, new Piece(3, 6), table.corners.get(0));
		table.addPiece(2, 2, new Piece(4, 3), table.corners.get(1));
		table.addPiece(2, 1, new Piece(4, 2), table.corners.get(2));
		table.addPiece(2, 0, new Piece(3, 2), table.corners.get(3));
		
		table.addPiece(2, 3, new Piece(3, 6), table.corners.get(0));
		table.addPiece(2, 2, new Piece(4, 3), table.corners.get(1));
		table.addPiece(2, 1, new Piece(4, 2), table.corners.get(2));
		table.addPiece(2, 0, new Piece(3, 2), table.corners.get(3));
		table.printTable();
	}
}

