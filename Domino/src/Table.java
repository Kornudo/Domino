import java.util.ArrayList;
import java.util.Scanner;

public class Table {

	Piece[][] pieces = new Piece[5][9];
	String[][] print = new String[31][28];
	ArrayList<Corner> corners = new ArrayList<Corner>();

	
	public Table () {
		print[0][0] = "┌";
		for (int j = 1; j < 26; j++) print[0][j]= "─";
		print[0][26]= "┐";
		print[0][27]= "\r\n";
		for (int i = 1; i < 30; i++) {
			print[i][0]= "│";
			for (int j = 1; j < 26; j++) {
				print[i][j]= " ";
			}
			print[i][26]= "│";
			print[i][27]= "\r\n";
		}
		print[30][0] = "└";
		for (int j = 1; j < 26; j++) print[30][j]= "─";
		print[30][26]= "┘";
		print[30][27]= "\r\n";
	
	}
	
	public Corner findCorner(int A, int B) { //looks for a corner with sides A and B,returns the found corner or returns null if no corner is found
		for(int i = 0; i < corners.size(); i++) {
			int cA = corners.get(i).getPiece().getSideA(); 
			int cB = corners.get(i).getPiece().getSideB();
			if((cA == A && cB == B) || (cB == A && cA == B)) {
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
		for (int i = 0; i < 31; i++) {
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
				addCorner(new Corner("left", i, j - 1, piece, corner.getOffset()));
				break;
			case "up":
				addCorner(new Corner("up", i + 1, j, piece, corner.getOffset()));
				break;
			case "right":
				addCorner(new Corner("right", i, j + 1, piece, corner.getOffset()));
				break;
			case "down":
				addCorner(new Corner("down", i - 1, j, piece, corner.getOffset()));
				break;
			default:
				return;
		}
	}
	
	public void addPiece(int i, int j, Piece piece, Corner corner) { //SECALHAR PODEMOS TIRAR A VAR PIECE DO CORNER E POMOS COMO VAR O VALOR DO OUTERSIDE E ASSIM N PRECISAMOS DO METODO OUTER SIDE
		pieces[i][j] = piece;
		
		if (corner == null) {
			print[i * 6 + 5][j * 3 + 1] = "6";
			print[i * 6 + 3][j * 3 + 1] = "-";
			print[i * 6 + 1][j * 3 + 1] = "6";
			addCorner(new Corner("left", i, j - 1, piece, 1));
			addCorner(new Corner("up", i + 1, j, piece, 0));
			addCorner(new Corner("right", i, j + 1, piece, 0));
			addCorner(new Corner("down", i - 1, j, piece, 0));
			return;
		}
		
		//CONSIGO SIMPLIfiCAR PK O LEFT E O RIGHT REPETEM MUITAS COISAS
		switch (corner.getDirection()) {
			case "left" :
				if (piece.dual()) {
					print[i * 6 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideA());
					print[i * 6 + 3][j * 3 + 1 + corner.getOffset()] = "-";
					print[i * 6 + 5][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
					corners.remove(corner);
					addCorner(new Corner("left", i, j - 1, piece, corner.getOffset() + 1));
					addCorner(new Corner("up", i + 1, j, piece, corner.getOffset()));
					addCorner(new Corner("down", i - 1, j, piece, corner.getOffset()));
				}
				else {
					print[i * 7 + 1][j * 3 + corner.getOffset()] = String.valueOf(piece.getSideA());
					print[i * 7 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
					corners.remove(corner);
					newCorner(i, j, piece, corner);
				}
				break;
			case "right" :
				if (piece.dual()) {
					print[i * 6 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideA());
					print[i * 6 + 3][j * 3 + 1 + corner.getOffset()] = "-";
					print[i * 6 + 5][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
					corners.remove(corner);
					addCorner(new Corner("up", i + 1, j, piece, corner.getOffset()));
					addCorner(new Corner("right", i, j + 1, piece, corner.getOffset() + 1));
					addCorner(new Corner("down", i - 1, j, piece, corner.getOffset()));
				}
				else {
					print[i * 7 + 1][j * 3 + corner.getOffset()] = String.valueOf(piece.getSideA());
					print[i * 7 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
					corners.remove(corner);
					newCorner(i, j, piece, corner);
				}
				break;
			case "up" :
				print[i * 6 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideA());
				print[i * 6 + 3][j * 3 + 1 + corner.getOffset()] = "-";
				print[i * 6 + 5][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
				corners.remove(corner);
				newCorner(i, j, piece, corner);
				break;
			case "down" :
				print[i * 6 + 1][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideA());
				print[i * 6 + 3][j * 3 + 1 + corner.getOffset()] = "-";
				print[i * 6 + 5][j * 3 + 1 + corner.getOffset()] = String.valueOf(piece.getSideB());
				corners.remove(corner);
				newCorner(i, j, piece, corner);
				break;
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
		
		// left
		table.addPiece(2, 3, new Piece(3, 6), table.findCorner(6, 6));
		table.addPiece(2, 2, new Piece(4, 3), table.findCorner(3, 6));
		table.addPiece(2, 1, new Piece(2, 4), table.findCorner(4, 3));
		table.addPiece(2, 0, new Piece(3, 2), table.findCorner(2, 4));
		
		//left double
//		table.addPiece(2, 2, new Piece(3, 3), table.findCorner(3, 6));
//		table.addPiece(2, 1, new Piece(4, 3), table.findCorner(3, 3));
//		table.addPiece(2, 0, new Piece(4, 4), table.findCorner(3, 4));
		//up
		table.addPiece(1, 4, new Piece(1, 6), table.findCorner(6, 6));
		table.addPiece(0, 4, new Piece(5, 1), table.findCorner(6, 1));
		//right
		table.addPiece(2, 5, new Piece(6, 2), table.findCorner(6, 6));
		table.addPiece(2, 6, new Piece(2, 5), table.findCorner(6, 2));
		table.addPiece(2, 7, new Piece(5, 4), table.findCorner(2, 5));
		table.addPiece(2, 8, new Piece(4, 6), table.findCorner(5, 4));
		
		//right double
//		table.addPiece(2, 2, new Piece(2, 2), table.findCorner(6, 2));
		//table.addPiece(2, 1, new Piece(4, 3), table.findCorner(2, 2));
		//table.addPiece(2, 0, new Piece(4, 4), table.findCorner(3, 4));
		//down
		table.addPiece(3, 4, new Piece(6, 0), table.findCorner(6, 6));
		table.addPiece(4, 4, new Piece(0, 1), table.findCorner(6, 0));
		
		table.printTable();
	}
}

