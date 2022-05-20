import java.util.ArrayList;

public class Table {
	int y = 5; //number of vertical pieces, must be uneven ex: 1, 3, 5, 7, 9 or maybe not
	int x = 11; //number of horizontal pieces + 6/6, idk if ot must be uneven but probably best
	int upBoundaryIndex = 3*y - 1; // first printed index, will change
	int downBoundaryIndex = 9*y + 1; // last printed index, will change
	int downMostIndex; // index of down most print index of down most piece in the table
	int upMostIndex; // index of up most print index of up most piece in the table
	String[][] print = new String[12 * y + 1][x * 3 - 2]; 
	ArrayList<Corner> corners = new ArrayList<Corner>();

	
	public Table () {
		for (int i = 0; i < 12 * y + 1; i++) {
			for (int j = 0; j < x * 3 - 2; j++) {
				print[i][j] = " ";
			}
		}
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
	
	public Corner findCornerAI(int A, int B) { //looks for a corner with sides A and B,returns the found corner or returns null if no corner is found
		for(int i = 0; i < corners.size(); i++) {
			int cA = corners.get(i).getPiece().getSideA(); 
			int cB = corners.get(i).getPiece().getSideB();
			if(cA == A && cB == B) {
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
	
	private void collisionMarkVertical(int y, int x) {
		for(int i = -1; i < 2; i++) {
			print[y - 3][x + i] = "x";
			print[y - 1][x + i] = "x";
			print[y + 1][x + i] = "x";
			print[y + 3][x + i] = "x";
		}
		print[y - 2][x - 1] = "x";
		print[y - 2][x + 1] = "x";
		print[y][x - 1] = "x";
		print[y][x + 1] = "x";
		print[y + 2][x - 1] = "x";
		print[y + 2][x + 1] = "x";
	}
	
	private void collisionMarkHorizontal(int y, int x) {
		for(int i = -2; i < 2; i++) {
			print[y - 1][x + i] = "x";
			print[y + 1][x + i] = "x";
		}
		print[y][x - 2] = "x";
		print[y][x + 1] = "x";
	}
	
	private void collisionMarkHorizontalDual(int y, int x) {
		for(int i = -2; i < 3; i++) {
			print[y - 1][x + i] = "x";
			print[y + 1][x + i] = "x";
		}
		print[y][x - 2] = "x";
		print[y][x + 2] = "x";
		}
	
	private boolean collisionCheckVertical(int y, int x) { //returns true if there is collision
		for(int i = y - 3; i < y + 3; i++) {
			if (print[i][x] == "x") return true; 
		}
		return false;
	}
	
	private boolean collisionCheckHorizontal(int y, int x) { //returns true if there is collision
		for(int j = x - 1; j < x + 1; j++) {
			if (print[y][j] == "x") return true; 
		}
		return false;
	}
	
	private boolean collisionCheckHorizontalDual(int y, int x) { //returns true if there is collision
		for(int j = x - 1; j < x + 2; j++) {
			if (print[y][j] == "x") return true; 
		}
		return false;
	}
	
	public void printTable() {
		System.out.print("┌");
		for (int j = 0; j < x * 3 - 2; j++) System.out.print("─");
		System.out.println("┐");
		for (int i = upBoundaryIndex; i < downBoundaryIndex + 1; i++) {
			System.out.print("│");
			for (int j = 0; j < x * 3 - 2; j++) {
				if (print[i][j] != "x") System.out.print(print[i][j]);
				else System.out.print(" ");
			}
			System.out.println("│");
		}
		System.out.print("└");
		for (int j = 0; j < x * 3 - 2; j++) System.out.print("─");
		System.out.println("┘");
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void addPieceLeft (Piece piece, Corner corner) {
		if (piece.dual()) {
			print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
			print[corner.getiPrint()][corner.getjPrint()] = "-";
			print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
			addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
			addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
			addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
		}
		else {
			print[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
			print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideB());
			addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 3, piece));
			collisionMarkHorizontal(corner.getiPrint(), corner.getjPrint());
		}
	}
	
	public void addPieceRight (Piece piece, Corner corner) {
		if (piece.dual()) {
			print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
			print[corner.getiPrint()][corner.getjPrint()] = "-";
			print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
			addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
			addCorner(new Corner("right", corner.getiPrint(), corner.getjPrint() + 2, piece));
			addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
			}
		else {
			print[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideB());
			print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideA());
			addCorner(new Corner("right", corner.getiPrint(), corner.getjPrint() + 3, piece));
			collisionMarkHorizontal(corner.getiPrint(), corner.getjPrint());
		}
	}
	
	private void addDualPieceUp(Piece piece, Corner corner) {
		print[corner.getiPrint() + 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		print[corner.getiPrint() + 2][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() + 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("up", corner.getiPrint() - 2, corner.getjPrint(), piece));
		addCorner(new Corner("right", corner.getiPrint() + 2, corner.getjPrint() + 4, piece));
		collisionMarkHorizontalDual(corner.getiPrint() + 2, corner.getjPrint());
		if (corner.getiPrint() + 2 < upMostIndex) upMostIndex = corner.getiPrint() + 2;
	}
	
	private void addNonDualPieceUp(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
		collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
		if (corner.getiPrint() - 2 < upMostIndex) upMostIndex = corner.getiPrint() - 2;
	}
	
	private void addPieceUp (Piece piece, Corner corner) {
		if (piece.dual()) addDualPieceUp(piece, corner);
		else addNonDualPieceUp(piece, corner);
	}
	
	private void addDualPieceDown(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		print[corner.getiPrint() - 2][corner.getjPrint()] = "-";
		print[corner.getiPrint() - 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() - 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("right", corner.getiPrint() - 2, corner.getjPrint() + 4, piece));
		addCorner(new Corner("down", corner.getiPrint() + 2, corner.getjPrint(), piece));
		collisionMarkHorizontalDual(corner.getiPrint() - 2, corner.getjPrint());
		if (corner.getiPrint() - 2 > downMostIndex) downMostIndex = corner.getiPrint() - 2;
	}
	
	private void addNonDualPieceDown(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
		collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
		if (corner.getiPrint() + 2 > downMostIndex) downMostIndex = corner.getiPrint() + 2;
	}
	
	private void addPieceDown(Piece piece, Corner corner) {
		if (piece.dual()) addDualPieceDown(piece, corner);
		else addNonDualPieceDown(piece, corner);
	}
	
	public boolean addPiece(Piece piece, Corner corner) { //returns false if it fails to place piece
		if (corner == null) {
			print[y * 6 - 2][(3*x - 1)/2 - 1] = "6";
			print[y * 6][(3*x - 1)/2 - 1] = "-";
			print[y * 6 + 2][(3*x - 1)/2 - 1]= "6";
			addCorner(new Corner("left", y * 6, (3*x - 1)/2 - 3, piece));
			addCorner(new Corner("up",  y * 6 - 6, (3*x - 1)/2 - 1, piece));
			addCorner(new Corner("right", y * 6, (3*x - 1)/2 + 2, piece));
			addCorner(new Corner("down", y * 6 + 6, (3*x - 1)/2 - 1, piece));
			collisionMarkVertical(y * 6, (3*x - 1)/2 - 1);
			upMostIndex = y * 6 - 2;
			downMostIndex = y * 6 + 2;
			return true;
		}
		
		String direction = corner.getDirection();
		if (direction == "left") {
			boolean collision = collisionCheckHorizontal(corner.getiPrint(), corner.getjPrint());
			if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
			else if(collision) return handleCollisionLeft(piece, corner);
			else addPieceLeft(piece, corner);
		}
		else if (direction == "right") {
			boolean collision = collisionCheckHorizontal(corner.getiPrint(), corner.getjPrint());
			if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
//			else if(collision) return handleCollisionUp(piece, corner);
			else addPieceLeft(piece, corner);
		}
		else if (direction == "up") {
			boolean collision;
			try {
				collision = collisionCheckVertical(corner.getiPrint(), corner.getjPrint());
			} catch (IndexOutOfBoundsException e) {
				collision = true;
			}
			if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
			else if(collision) return handleCollisionUp(piece, corner);
			int outOfBounds = outOfBoundsUp(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && piece.dual())) addPieceUp(piece, corner);
			else {
				handleOutOfBoundsUp(piece, corner);
			}
		}
		else {
			int outOfBounds = outOfBoundsDown(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && piece.dual())) addPieceDown(piece, corner);
			else {
				handleOutOfBoundsDown(piece, corner);
			}
			
		}
		corners.remove(corner);
		return true;
	}
	
	private boolean handleCollisionDual(Piece piece, Corner corner) { //returns false if its not able to place piece
		if (corner.getPiece().dual()) {
			corners.remove(corner);
			if((corner = findCorner(corner.getPiece().getSideA(), corner.getPiece().getSideB())) == null) return false;
			return addPiece(piece, corner);
		}
		return true;
	}
	
	private boolean handleCollisionLeft(Piece piece, Corner corner) { //returns false if its not able to place piece
		corners.remove(corner);
		corner = new Corner("up", corner.getiPrint() - 4, corner.getjPrint() + 2, corner.getPiece());
		if (!collisionCheckVertical(corner.getiPrint(), corner.getjPrint())) return addPiece(piece, corner);
		corner = new Corner("down", corner.getiPrint() + 4, corner.getjPrint() + 2, corner.getPiece());
		if (!collisionCheckVertical(corner.getiPrint(), corner.getjPrint())) return addPiece(piece, corner);
		return false;
	}
	
	private boolean handleCollisionUp(Piece piece, Corner corner) { //returns false if its not able to place piece
		corners.remove(corner);
		corner = new Corner("left", corner.getiPrint() + 4, corner.getjPrint() - 2, corner.getPiece());
		if (!collisionCheckVertical(corner.getiPrint(), corner.getjPrint())) return addPiece(piece, corner);
		corner = new Corner("right", corner.getiPrint() + 4, corner.getjPrint() + 2, corner.getPiece());
		if (!collisionCheckVertical(corner.getiPrint(), corner.getjPrint())) return addPiece(piece, corner);
		return false;
	}
	
	private int outOfBoundsUp(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getiPrint() - 2 < upBoundaryIndex) { // a vertical piece would be out of bounds
			if (corner.getiPrint() + 2 < upBoundaryIndex) { // a horizontal piece would be out of bounds
				return 0;
			}
			return 1;
		}
		return 2;
	}
	

	private int outOfBoundsDown(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getiPrint() + 2 > downBoundaryIndex) { // a vertical piece would be out of bounds
			if (corner.getiPrint() - 2 > downBoundaryIndex) { // a horizontal piece would be out of bounds
				return 0;
			}
			return 1;
		}
		return 2;
	}
	
	// NOT FINISHED
	private void handleOutOfBoundsUp(Piece piece, Corner corner) {
		if (piece.dual() && downMostIndex <= downBoundaryIndex - 2) { // if board can shift for dual vertical
			upBoundaryIndex -= 2;
			downBoundaryIndex -= 2;
			addPieceUp(piece,corner);
		}
		else if(downMostIndex <= downBoundaryIndex - 6) { // if board can shift for vertical piece
			upBoundaryIndex -= 6;
			downBoundaryIndex -= 6;
			addPieceUp(piece,corner);
		}
//		else { // cant shift
//			int offset;
//			if (piece.dual()) offset = 3;
//			else offset = 2;
//			Corner newCorner = new Corner("left", corner.getiPrint() - 4, corner.getjPrint() - offset, corner.getPiece());
//			int outOfBounds = outOfBoundsLeft(newCorner);
//			if (outOfBounds == 0 || outOfBounds == 1) {
//				handleOutOfBoundsLeftNonDual();
//			}
//			else {
//				addPieceRight(piece, new Corner("right", corner.getiPrint() - 4, corner.getjPrint() + offset, corner.getPiece()));
//			}
//		}
	}
	
	private void handleOutOfBoundsDown(Piece piece, Corner corner) {
		if (piece.dual() && upMostIndex >= upBoundaryIndex - 2) { // if piece is dual and board can shift for dual vertical
			upBoundaryIndex += 2;
			downBoundaryIndex += 2;
			addPieceDown(piece,corner);
		}
		else if(downMostIndex >= downBoundaryIndex - 6) { // if board can shift for vertical piece
			upBoundaryIndex += 6;
			downBoundaryIndex += 6;
			addPieceDown(piece,corner);
		}
	}
	
	//OUTDATED
//	public void addPiece (Piece piece, Corner corner) {
//		try { addPrintPiece(piece, corner);
//		} catch(IndexOutOfBoundsException e) {
//			if (corner.getDirection() == "left") {
//				try { addPrintPiece(piece, new Corner("up", corner.getiPrint() - 4, corner.getjPrint() + 2, corner.getPiece()));
//				} catch(IndexOutOfBoundsException E) {addPrintPiece(piece, new Corner("down", corner.getiPrint() + 4, corner.getjPrint() + 2, corner.getPiece()));}
//			}
//			else if (corner.getDirection() == "right") {
//				try { addPrintPiece(piece, new Corner("up", corner.getiPrint() - 4, corner.getjPrint() - 2, corner.getPiece()));
//				} catch(IndexOutOfBoundsException E) {addPrintPiece(piece, new Corner("down", corner.getiPrint() + 4, corner.getjPrint() - 2, corner.getPiece()));}
//			}
//		}
//	}
		
	public static void main(String[] args) {
		Table table = new Table();
		table.addPiece(new Piece(6, 6), null);
		
		// left
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6));

		
		//up
//		table.addPiece(new Piece(1, 6), table.findCorner(6, 6));
//		table.addPiece(new Piece(1, 1), table.findCorner(6, 1));

		
		//right
//		table.addPiece(new Piece(6, 2), table.findCorner(6, 6));		

		
		//down
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6));
//		table.addPiece(new Piece(0, 0), table.findCorner(6, 0));

		//COLLISION TESTS
		////////////////////////////////////////////////////////////////
//		//corner piece is double test
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(3, 3), table.findCorner(3, 6)); //left
//		table.addPiece(new Piece(0, 3), table.findCorner(3, 3)); //left
//		table.addPiece(new Piece(1, 3), table.findCorner(3, 3)); //left
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 3)); //left
//		
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(0, 0), table.findCorner(0, 6)); //up
//		table.addPiece(new Piece(2, 0), table.findCorner(0, 0)); //up
//		//its working
//		//alternate test, iterates on previous
//		table.addPiece(new Piece(3, 0), table.findCorner(0, 0)); //up
//		//its working
		////////////////////////////////////////////////////////////////
		//corner piece is not double test
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(0, 0), table.findCorner(0, 6)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 0)); //up
//		table.addPiece(new Piece(1, 1), table.findCorner(1, 0)); //up
//		table.addPiece(new Piece(2, 1), table.findCorner(1, 1)); //up
//		table.addPiece(new Piece(3, 1), table.findCorner(1, 1)); //up
//		table.addPiece(new Piece(4, 1), table.findCorner(1, 1)); //up
//		table.addPiece(new Piece(2, 4), table.findCorner(1, 4)); //up
//		table.addPiece(new Piece(0, 3), table.findCorner(3, 6)); //left
//		table.addPiece(new Piece(4, 0), table.findCorner(0, 3)); //left 
		//its working
		////////////////////////////////////////////////////////////////
		//going up; hits border; shifts till max; turns left;
		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
		table.addPiece(new Piece(1, 0), table.findCorner(0, 6)); //up
		table.addPiece(new Piece(2, 1), table.findCorner(1, 0)); //up
		table.addPiece(new Piece(0, 2), table.findCorner(2, 1)); //up
		table.addPiece(new Piece(3, 0), table.findCorner(0, 2)); //up
		table.addPiece(new Piece(0, 0), table.findCorner(0, 2)); //up
		
		table.printTable();
	}
}

