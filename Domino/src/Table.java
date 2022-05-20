import java.util.ArrayList;
import java.util.Scanner;

public class Table {
	int y = 3; //number of vertical pieces, must be uneven ex: 1, 3, 5, 7, 9 or maybe not
	int x = 9; //number of horizontal pieces + 6/6, idk if ot must be uneven but probably best
	Piece[][] pieces = new Piece[y + 2][x];
	String[][] print = new String[y * 6 + 5][x * 3 + 1]; //28
	ArrayList<Corner> corners = new ArrayList<Corner>();

	
	public Table () {
		print[0][0] = "â”Œ";
		for (int j = 1; j < x * 3 - 1; j++) print[0][j]= "â”€";
		print[0][x * 3 - 1]= "â”�";
		print[0][x * 3]= "\r\n";
		for (int i = 1; i < y * 6 + 4; i++) {
			print[i][0]= "â”‚";
			for (int j = 1; j < x * 3 - 1; j++) {
				print[i][j]= " ";
			}
			print[i][x * 3 - 1]= "â”‚";
			print[i][x * 3]= "\r\n";
		}
		print[y * 6 + 4][0] = "â””";
		for (int j = 1; j < x * 3 - 1; j++) print[y * 6 + 4][j]= "â”€";
		print[y * 6 + 4][x * 3 - 1]= "â”˜";
		print[y * 6 + 4][x * 3]= "\r\n";
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
	
	public Corner findPlayableCorner(int A, int B) { 
		for(int i = 0; i < corners.size(); i++) {
			int cornerOuterSide = corners.get(i).outerSide(); 
			if(cornerOuterSide == A || cornerOuterSide == B) {
				return corners.get(i);
			}
		}
		return null;
	}
	
	public Corner findPlayableCounterCorner(int A) { 
		for(int i = 0; i < corners.size(); i++) {
			int cornerOuterSide = corners.get(i).outerSide(); 
			if(cornerOuterSide == A) {
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
		for (int i = 0; i < y*6 + 5; i++) {
			for (int j = 0; j < x * 3 + 1; j++) {
				System.out.print(print[i][j]);
			}
		}
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void addPiece(int i, int j, Piece piece, Corner corner) { //SECALHAR PODEMOS TIRAR A VAR PIECE DO CORNER E POMOS COMO VAR O VALOR DO OUTERSIDE E ASSIM N PRECISAMOS DO METODO OUTER SIDE
		pieces[i][j] = piece;
		// SERA MESMO PRECISO A MATRIZ DE PIECES??
		if (corner == null) {
			print[y * 3][(3*x - 1)/2] = "6";
			print[y * 3 + 2][(3*x - 1)/2] = "-";
			print[y * 3 + 4][(3*x - 1)/2] = "6";
			addCorner(new Corner("left", y * 3 + 2, (3*x - 1)/2 - 2, piece));
			addCorner(new Corner("up",  y * 3 - 4, (3*x - 1)/2, piece));
			addCorner(new Corner("right", y * 3 + 2, (3*x - 1)/2 + 2, piece));
			addCorner(new Corner("down", y * 3 + 8, (3*x - 1)/2, piece));
			return;
		}
		
		String direction = corner.getDirection();
		boolean dual = piece.dual();
		if (direction == "left") {
			if (dual) {
				print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint()] = "-";
				print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
				addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
				addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
				addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
			}
			else {
				print[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideB());
				addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 3, piece));
			}
		}
		else if (direction == "right") {
			if (dual) {
				print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint()] = "-";
				print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
				addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
				addCorner(new Corner("right", corner.getiPrint(), corner.getjPrint() + 2, piece));
				addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
				}
			else {
				print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
				addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() + 3, piece));
			}
		}
		else if (direction == "up") {
			if (dual) {
				print[corner.getiPrint() + 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
				print[corner.getiPrint() + 2][corner.getjPrint()] = "-";
				print[corner.getiPrint() + 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
				addCorner(new Corner("left", corner.getiPrint() + 2, corner.getjPrint() - 3, piece));
				addCorner(new Corner("up", corner.getiPrint() - 2, corner.getjPrint(), piece));
				addCorner(new Corner("right", corner.getiPrint() + 2, corner.getjPrint() + 3, piece));
				}
			else {
				print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint()] = "-";
				print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
				addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
			}
		}
		else if (direction == "down") {
			if (dual) {
				print[corner.getiPrint() - 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
				print[corner.getiPrint() - 2][corner.getjPrint()] = "-";
				print[corner.getiPrint() - 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
				addCorner(new Corner("left", corner.getiPrint() - 2, corner.getjPrint() - 3, piece));
				addCorner(new Corner("right", corner.getiPrint() - 2, corner.getjPrint() + 3, piece));
				addCorner(new Corner("down", corner.getiPrint() + 2, corner.getjPrint(), piece));
				}
			else {
				print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
				print[corner.getiPrint()][corner.getjPrint()] = "-";
				print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
				addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
			}
		}
		corners.remove(corner);
	}
	
	public Piece[][] getPieces() {
		return pieces;
	}
	
	public Piece[][] setPieces(Piece[][] pieces) {
		this.pieces = pieces;
		return pieces;
	}
	
	public ArrayList<Corner> getCorners() {
		return corners;
	}
		
	public static void main(String[] args) {
		Table table = new Table();
		table.addPiece(0, (table.x - 1)/2, new Piece(6, 6), null);
		
		// left
		table.addPiece(2, 1, new Piece(3, 6), table.findCorner(6, 6));
//		table.addPiece(2, 2, new Piece(4, 3), table.findCorner(3, 6));
//		table.addPiece(2, 1, new Piece(2, 4), table.findCorner(4, 3));
//		table.addPiece(2, 0, new Piece(3, 2), table.findCorner(2, 4));
		
		//left double
//		table.addPiece(2, 2, new Piece(3, 3), table.findCorner(3, 6));
//		table.addPiece(2, 1, new Piece(4, 3), table.findCorner(3, 3));
//		table.addPiece(2, 0, new Piece(4, 4), table.findCorner(3, 4));
		
		//left double up
//		table.addPiece(1, 2, new Piece(0, 3), table.findCorner(3, 3));
//		table.addPiece(0, 2, new Piece(0, 0), table.findCorner(3, 0));
//		table.addPiece(0, 1, new Piece(2, 0), table.findCorner(0, 0));
//		table.addPiece(0, 0, new Piece(7, 2), table.findCorner(2, 0));
//		table.addPiece(0, 0, new Piece(7, 0), table.findCorner(0, 0));
//		table.addPiece(0, 0, new Piece(0, 8), table.findCorner(0, 0));
//		table.addPiece(0, 0, new Piece(8, 8), table.findCorner(0, 8));
		//
		
		//up
//		table.corners.remove(table.findCorner(6, 6));
		table.addPiece(1, 4, new Piece(5, 6), table.findCorner(6, 6));
		//
		table.addPiece(0, 4, new Piece(5, 5), table.findCorner(5, 6));
//		table.corners.remove(table.findCorner(1, 1));
//		table.corners.remove(table.findCorner(1, 1));
//		table.addPiece(0, 5, new Piece(1, 2), table.findCorner(1, 1));
//		table.addPiece(0, 6, new Piece(2, 8), table.findCorner(1, 2));
//		table.addPiece(0, 7, new Piece(8, 0), table.findCorner(2, 8));
//		table.addPiece(0, 8, new Piece(0, 4), table.findCorner(8, 0)); //faz colisÃ£o
		
		//right
		table.addPiece(2, 5, new Piece(6, 2), table.findCorner(6, 6));
//		table.addPiece(2, 6, new Piece(2, 5), table.findCorner(6, 2));
//		table.addPiece(2, 7, new Piece(5, 4), table.findCorner(2, 5));
//		table.addPiece(2, 8, new Piece(4, 6), table.findCorner(5, 4));
		
		//right double
//		table.addPiece(2, 6, new Piece(2, 2), table.findCorner(6, 2));
//		table.addPiece(2, 7, new Piece(2, 3), table.findCorner(2, 2));
//		table.addPiece(2, 8, new Piece(3, 3), table.findCorner(2, 3));
		
		//down
		table.addPiece(3, 4, new Piece(6, 0), table.findCorner(6, 6));
		table.addPiece(4, 4, new Piece(0, 0), table.findCorner(6, 0));
		table.addPiece(4, 4, new Piece(1, 0), table.findCorner(0, 0));
		table.addPiece(4, 4, new Piece(0, 2), table.findCorner(0, 0));
		try {
			table.addPiece(4, 4, new Piece(0, 3), table.findCorner(0, 0));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			table.corners.remove(table.findCorner(0, 0));
			table.addPiece(4, 4, new Piece(0, 3), table.findCorner(0, 0));
		}
		
		
//		table.addPiece(4, 4, new Piece(0, 1), table.findCorner(0, 0));
//		table.addPiece(4, 4, new Piece(0, 2), table.findCorner(0, 0));
		
		table.printTable();
	}
}

