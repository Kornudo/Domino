import java.util.ArrayList;

public class Table {
	private int y = 5; //number of vertical pieces, must be uneven ex: 1, 3, 5, 7, 9 or maybe not
	private int x = 7; //number of horizontal pieces + 6/6, idk if ot must be uneven but probably best
	private int upBoundaryIndex = 3*y - 1; // first printed index, will change
	private int downBoundaryIndex = 9*y + 1; // last printed index, will change
	private int downMostIndex; // index of down most print index of down most piece in the table
	private int upMostIndex; // index of up most print index of up most piece in the table
	private int leftBoundaryIndex = (3*x - 3)/2; // first printed index, will change
	private int rightBoundaryIndex = (9*x - 9)/2; // last printed index, will change
	private int leftMostIndex; // index of down most print index of down most piece in the table
	private int rightMostIndex; // index of up most print index of up most piece in the table
	private String[][] print = new String[12 * y + 1][6 * x - 5]; 
	private ArrayList<Corner> corners = new ArrayList<Corner>();

	public Table () {
		for (int i = 0; i < 12 * y + 1; i++) {

			for (int j = 0; j < 6 * x - 5; j++) {
				print[i][j] = " ";
			}
		}
	}
	
	public Corner findPlayableCorner(int A, int B) { 
        for(int i = 0; i < corners.size(); i++) {
            int cornerOuterSide = corners.get(i).getPiece().getSideA(); 
            if(cornerOuterSide == A || cornerOuterSide == B) {
                return corners.get(i);
            }
        }
        return null;
    }
	
	public Corner findPlayableCounterCorner(int A) { 
        for(int i = 0; i < corners.size(); i++) {
            int cornerOuterSide = corners.get(i).getPiece().getSideA(); 
            if(cornerOuterSide == A) {
                return corners.get(i);
            }
        }
        return null;
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
		try {
			print[y - 3][x] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 2; i++) {
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
			return;
		}
		try {
			print[y + 3][x] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 2; i++) {
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
			}
			print[y - 2][x - 1] = "x";
			print[y - 2][x + 1] = "x";
			print[y][x - 1] = "x";
			print[y][x + 1] = "x";
			print[y + 2][x - 1] = "x";
			print[y + 2][x + 1] = "x";
			return;
		}
		try {
			print[y][x - 1] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = 0; i < 2; i++) {
				print[y - 3][x + i] = "x";
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
				print[y + 3][x + i] = "x";
			}
			print[y - 2][x + 1] = "x";
			print[y][x + 1] = "x";
			print[y + 2][x + 1] = "x";
			return;
		}
		try {
			print[y][x + 1] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 1; i++) {
				print[y - 3][x + i] = "x";
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
				print[y + 3][x + i] = "x";
			}
			print[y - 2][x - 1] = "x";
			print[y][x - 1] = "x";
			print[y + 2][x - 1] = "x";
			return;
		}

		for(int i = -1; i < 2; i++) {
			print[y - 3][x + i] = "x";
			print[y - 1][x + i] = "x";
			print[y + 1][x + i] = "x";
			print[y + 3][x + i] = "x";
		}
		print[y - 2][x - 1] = "x";
		print[y - 2][x + 1] = "x";
		print[y + 2][x - 1] = "x";
		print[y + 2][x + 1] = "x";
	}
	
	private void collisionMarkLeftHorizontal(int y, int x) {
		try {
			for(int i = 1; i > -3; i--) {
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
			}
			print[y][x - 2] = "x";
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private void collisionMarkRightHorizontal(int y, int x) {
		try {
			for(int i = -2; i < 2; i++) {
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
			}
			print[y][x + 1] = "x"; 
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private void collisionMarkHorizontalDual(int y, int x) {
		try {
			for(int i = -2; i < 3; i++) {
				print[y - 1][x + i] = "x";
				print[y + 1][x + i] = "x";
			}
			print[y][x - 2] = "x";
			print[y][x + 2] = "x";
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private boolean collisionCheckUp(int y, int x) { //returns true if there is collision
		try {
			for(int i = y - 3; i < y + 3; i++) {
				if (print[i][x] == "x") return true; 
			}
		} catch (IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckDown(int y, int x) { //returns true if there is collision
		try {
			for(int i = y - 2; i < y + 4; i++) {
				if (print[i][x] == "x" || print[i][x - 1] == "x" || print[i][x + 1] == "x") return true; 
			}
		} catch (IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckLeft(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 2; j < x + 1; j++) {
				if (print[y][j] == "x" || print[y - 1][j] == "x" || print[y + 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckRight(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 1; j < x + 2; j++) {
				if (print[y][j] == "x" || print[y - 1][j] == "x" || print[y + 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckVerticalDual(int y, int x) { //returns true if there is collision
		try {
			for(int i = y - 3; i < y + 4; i++) {
				if (print[i][x - 1] == "x" || print[i][x] == "x" || print[i][x + 1] == "x") return true;
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckUpDual(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 2; j < x + 3; j++) {
				if (print[y][j] == "x") return true; 
				if (print[y - 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckDownDual(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 2; j < x + 3; j++) {
				if (print[y][j] == "x") return true; 
				if (print[y + 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	public void printTable() { // CAN BE OPTIMIZED
		System.out.print("┌");
		for (int j = 0; j < x * 3 - 2; j++) System.out.print("─");
		System.out.println("┐");
		for (int i = upBoundaryIndex; i < downBoundaryIndex + 1; i++) {
			System.out.print("│");
			for (int j = leftBoundaryIndex; j < rightBoundaryIndex + 1; j++) {
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
	
	private void addDualPieceLeft (Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceLeft (Piece piece, Corner corner) {
		print[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 3, piece));
	}
	
	private void addPieceLeft (Piece piece, Corner corner) {
		if (piece.dual()) {
			addDualPieceLeft(piece, corner);
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
			if (corner.getjPrint() < leftMostIndex) leftMostIndex = corner.getjPrint();
		}
		else {
			addNonDualPieceLeft(piece, corner);
			collisionMarkLeftHorizontal(corner.getiPrint(), corner.getjPrint());
			if (corner.getjPrint() - 1 < leftMostIndex) leftMostIndex = corner.getjPrint() - 1;
		}
	}
	
	private void addDualPieceRight (Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceRight (Piece piece, Corner corner) {
		print[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideB());
		print[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideA());
		addCorner(new Corner("right", corner.getiPrint(), corner.getjPrint() + 3, piece));
		collisionMarkRightHorizontal(corner.getiPrint(), corner.getjPrint());
	}
	
	private void addPieceRight(Piece piece, Corner corner) {
		if (piece.dual()) {
			addDualPieceRight(piece, corner);
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
			if (corner.getjPrint() - 1 > rightMostIndex) rightMostIndex = corner.getjPrint() - 1;
		}
		else {
			addNonDualPieceRight(piece, corner);
			collisionMarkRightHorizontal(corner.getiPrint(), corner.getjPrint());
			if (corner.getjPrint() > rightMostIndex) rightMostIndex = corner.getjPrint();
		}
	}
	
	private void addDualPieceUp(Piece piece, Corner corner) {
		print[corner.getiPrint() + 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		print[corner.getiPrint() + 2][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() + 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("up", corner.getiPrint() - 2, corner.getjPrint(), piece));
		addCorner(new Corner("right", corner.getiPrint() + 2, corner.getjPrint() + 4, piece));
	}
	
	private void addNonDualPieceUp(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
	}
	
	private void addPieceUp (Piece piece, Corner corner) {
		if (piece.dual()) {
			addDualPieceUp(piece, corner);
			collisionMarkHorizontalDual(corner.getiPrint() + 2, corner.getjPrint());
			if (corner.getiPrint() + 2 < upMostIndex) upMostIndex = corner.getiPrint() + 2;
		}
		else {
			addNonDualPieceUp(piece, corner);
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
			if (corner.getiPrint() - 2 < upMostIndex) upMostIndex = corner.getiPrint() - 2;
		}
	}
	
	private void addDualPieceDown(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		print[corner.getiPrint() - 2][corner.getjPrint()] = "-";
		print[corner.getiPrint() - 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() - 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("right", corner.getiPrint() - 2, corner.getjPrint() + 4, piece));
		addCorner(new Corner("down", corner.getiPrint() + 2, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceDown(Piece piece, Corner corner) {
		print[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		print[corner.getiPrint()][corner.getjPrint()] = "-";
		print[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
	}
	
	private void addPieceDown(Piece piece, Corner corner) {
		if (piece.dual()) {
			addDualPieceDown(piece, corner);
			collisionMarkHorizontalDual(corner.getiPrint() - 2, corner.getjPrint());
			if (corner.getiPrint() - 2 > downMostIndex) downMostIndex = corner.getiPrint() - 2;
		}
		else {
			addNonDualPieceDown(piece, corner);
			collisionMarkVertical(corner.getiPrint(), corner.getjPrint());
			if (corner.getiPrint() + 2 > downMostIndex) downMostIndex = corner.getiPrint() + 2;
		}
	}
	
	public boolean addPiece(Piece piece, Corner corner) { //returns false if it fails to place piece
		if (corner == null) {
			print[y * 6 - 2][3 * x - 3] = "6";
			print[y * 6][3 * x - 3] = "-";
			print[y * 6 + 2][3 * x - 3]= "6";
			addCorner(new Corner("left", y * 6, 3 * x - 5, piece));
			addCorner(new Corner("up",  y * 6 - 6, 3 * x - 3, piece));
			addCorner(new Corner("right", y * 6, 3 * x, piece));
			addCorner(new Corner("down", y * 6 + 6, 3 * x - 3, piece));
			collisionMarkVertical(y * 6, 3 * x - 3);
			upMostIndex = y * 6 - 2;
			downMostIndex = y * 6 + 2;
			leftMostIndex = 3 * x - 3;
			rightMostIndex = 3 * x - 3;
			return true;
		}
		if (piece.getSideA() == corner.getPiece().getSideA()) {
			piece = new Piece(piece.getSideB(), piece.getSideA());
		}
		boolean dual = piece.dual();
		String direction = corner.getDirection();
		if (direction == "left") {
			int outOfBounds = outOfBoundsLeft(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
				boolean collision;
				if (dual) collision = collisionCheckVerticalDual(corner.getiPrint(), corner.getjPrint());
				else collision = collisionCheckLeft(corner.getiPrint(), corner.getjPrint());
				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
				else if(collision) return handleCollisionLeft(piece, corner);
				addPieceLeft(piece, corner);
			}
			else {
				return handleOutOfBoundsLeft(piece, corner);
			}
		}
		else if (direction == "right") {
			int outOfBounds = outOfBoundsRight(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
				boolean collision;
				if (dual) collision = collisionCheckVerticalDual(corner.getiPrint(), corner.getjPrint());
				else collision = collisionCheckRight(corner.getiPrint(), corner.getjPrint());
				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
				else if(collision) return handleCollisionRight(piece, corner);
				addPieceRight(piece, corner);
			}
			else {
				return handleOutOfBoundsRight(piece, corner);
			}
		}
		else if (direction == "up") {
			int outOfBounds = outOfBoundsUp(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
				boolean collision;
				if (dual) collision = collisionCheckUpDual(corner.getiPrint() + 2, corner.getjPrint());
				else collision = collisionCheckUp(corner.getiPrint(), corner.getjPrint());
				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
				else if(collision) return handleCollisionUp(piece, corner);
				addPieceUp(piece, corner);
			}
			else {
				return handleOutOfBoundsUp(piece, corner);
			}
		}
		else {
			int outOfBounds = outOfBoundsDown(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
				boolean collision;
				if (dual) collision = collisionCheckDownDual(corner.getiPrint() - 2, corner.getjPrint());
				else collision = collisionCheckDown(corner.getiPrint(), corner.getjPrint());
				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
				else if(collision) return handleCollisionDown(piece, corner);
				addPieceDown(piece, corner);
			}
			else {
				return handleOutOfBoundsDown(piece, corner);
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
		if (piece.dual()) return false;
		Corner newCorner = new Corner("up", corner.getiPrint() - 4, corner.getjPrint() + 2, corner.getPiece());
		if (!collisionCheckUp(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) { //if piece is added succecfully
				corners.remove(corner);
				return true;
			}
		}
		newCorner = new Corner("down", newCorner.getiPrint() + 8, newCorner.getjPrint(), newCorner.getPiece());
		if (!collisionCheckDown(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) {
				corners.remove(corner);
				return true;
			}
		}
		return false;
	}
	
	private boolean handleCollisionRight(Piece piece, Corner corner) { //returns false if its not able to place piece
		if (piece.dual()) return false; //duals cant turn
		Corner newCorner = new Corner("up", corner.getiPrint() - 4, corner.getjPrint() - 3, corner.getPiece());
		if (!collisionCheckUp(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) {
				corners.remove(corner);
				return true;
			} 
		}
		newCorner = new Corner("down", corner.getiPrint() + 4, newCorner.getjPrint(), newCorner.getPiece());
		if (!collisionCheckDown(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) {
				corners.remove(corner);
				return true;
			}
		}
		return false;
	}
	
	private boolean handleCollisionUp(Piece piece, Corner corner) { //returns false if its not able to place piece
		if (piece.dual()) return false;
		Corner newCorner = new Corner("left", corner.getiPrint() + 4, corner.getjPrint() - 2, corner.getPiece());
		if (!collisionCheckLeft(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) { //if piece is added succecfully
				corners.remove(corner);
				return true;
			}
		}
		newCorner = new Corner("right", newCorner.getiPrint(), corner.getjPrint() + 3, newCorner.getPiece());
		if (!collisionCheckRight(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) {
				corners.remove(corner);
				return true;
			}
		}
		return false;
	}
	
	private boolean handleCollisionDown(Piece piece, Corner corner) { //returns false if its not able to place piece
		if (piece.dual()) return false;
		corners.remove(corner);
		Corner newCorner = new Corner("left", corner.getiPrint() - 4, corner.getjPrint() - 2, corner.getPiece());
		if (!collisionCheckLeft(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) { //if piece is added succecfully
				corners.remove(corner);
				return true;
			}
		}
		newCorner = new Corner("right", newCorner.getiPrint(), newCorner.getjPrint() + 5, newCorner.getPiece());
		if (!collisionCheckRight(newCorner.getiPrint(), newCorner.getjPrint())) {
			if (addPiece(piece, newCorner)) {
				corners.remove(corner);
				return true;
			}
		}
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
	
	private int outOfBoundsLeft(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getjPrint() - 1 < leftBoundaryIndex) { // a vertical piece would be out of bounds
			if (corner.getjPrint() < leftBoundaryIndex) { // a horizontal piece would be out of bounds
				return 0;
			}
			return 1;
		}
		return 2;
	}

	private int outOfBoundsRight(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getjPrint() > rightBoundaryIndex) { // a vertical piece would be out of bounds
			if (corner.getjPrint() - 1 > rightBoundaryIndex) { // a horizontal piece would be out of bounds
				return 0;
			}
			return 1;
		}
		return 2;
	}
	
	private boolean handleOutOfBoundsUp(Piece piece, Corner corner) {
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
		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
			return handleCollisionDual(piece, corner);
		}
		else { // cant shift and corner piece is not dual
			return handleCollisionUp(piece, corner);
		}
		corners.remove(corner);
		return true;
	}

	private boolean handleOutOfBoundsDown(Piece piece, Corner corner) {
		if (piece.dual() && upMostIndex >= upBoundaryIndex + 2) { // if piece is dual and board can shift for dual vertical
			upBoundaryIndex += 2;
			downBoundaryIndex += 2;
			addPieceDown(piece,corner);
		}
		else if(upMostIndex >= upBoundaryIndex + 6) { // if board can shift for vertical piece
			upBoundaryIndex += 6;
			downBoundaryIndex += 6;
			addPieceDown(piece,corner);
		}
		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
			return handleCollisionDual(piece, corner);
		}
		else { // cant shift and corner piece is not dual
			return handleCollisionDown(piece, corner);
		}
		corners.remove(corner);
		return true;
	}
	
	private boolean handleOutOfBoundsLeft(Piece piece, Corner corner) {
		if (piece.dual() && rightMostIndex <= rightBoundaryIndex - 2) { // if board can shift for dual vertical
			leftBoundaryIndex -= 2;
			rightBoundaryIndex -= 2;
			addPieceLeft(piece,corner);
		}
		else if(rightMostIndex <= rightBoundaryIndex - 3) { // if board can shift for horizontal piece
			leftBoundaryIndex -= 3;
			rightBoundaryIndex -= 3;
			addPieceLeft(piece,corner);
		}
		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
			return handleCollisionDual(piece, corner);
		}
		else { // cant shift and corner piece is not dual
			return handleCollisionLeft(piece, corner);
		}
		corners.remove(corner);
		return true;
	}
	
	private boolean handleOutOfBoundsRight(Piece piece, Corner corner) {
		if (piece.dual() && leftMostIndex >= leftBoundaryIndex + 2) { // if board can shift for dual vertical
			leftBoundaryIndex += 2;
			rightBoundaryIndex += 2;
			addPieceRight(piece,corner);
		}
		else if(leftMostIndex >= leftBoundaryIndex + 3) { // if board can shift for horizontal piece
			leftBoundaryIndex += 3;
			rightBoundaryIndex += 3;
			addPieceRight(piece,corner);
		}
		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
			return handleCollisionDual(piece, corner);
		}
		else { // cant shift and corner piece is not dual
			return handleCollisionRight(piece, corner);
		}
		corners.remove(corner);
		return true;
	}
		
	public static void main(String[] args) {
		Table table = new Table();
		table.addPiece(new Piece(6, 6), null);
		
//		// left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 6));
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3));
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3));
//		table.addPiece(new Piece(7, 3), table.findCorner(6, 3));
//		table.addPiece(new Piece(7, 7), table.findCorner(7, 3));
//		table.addPiece(new Piece(7, 0), table.findCorner(7, 7));
//
//		
//		//up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 6));
//		table.addPiece(new Piece(2, 1), table.findCorner(6, 1));
//
//		
//		//right
//		table.addPiece(new Piece(6, 2), table.findCorner(6, 6));
//		table.addPiece(new Piece(2, 0), table.findCorner(6, 2));
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 0));
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 0));
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 0));
//
//		
//		//down
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6));
//		table.addPiece(new Piece(0, 1), table.findCorner(6, 0));
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0));
//		table.addPiece(new Piece(1, 2), table.findCorner(1, 0));

		//TESTS y = 5; x = 7;
		////////////////////////////////////////////////////////////////
		////border shift and collision tests left and down
		table.addPiece(new Piece(6, 3), table.findCorner(6, 6)); //left
		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
		table.addPiece(new Piece(7, 3), table.findCorner(6, 3)); //left
		
		table.addPiece(new Piece(7, 7), table.findCorner(7, 3)); //left
		table.addPiece(new Piece(7, 0), table.findCorner(7, 7)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
		
		
		table.addPiece(new Piece(6, 1), table.findCorner(6, 6)); //up
		table.addPiece(new Piece(6, 0), table.findCorner(6, 6)); //right but goes down
		table.addPiece(new Piece(0, 1), table.findCorner(6, 0)); //down
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; cant shift; turns left;
		table.addPiece(new Piece(0, 0), table.findCorner(1, 0)); //down; doesnt fit; is not placed;
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; cant shift; turns up;
		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; doesnt fit; turns right;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; doesnt fit; is not placed;
			
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left  doesnt fit; is not placed;
			
//		table.addPiece(new Piece(0, 0), table.findCorner(1, 0)); //down; 
		
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
////		//its working
////		//alternate test, iterates on previous
//		table.addPiece(new Piece(3, 0), table.findCorner(0, 0)); //up
//		//its working
		////////////////////////////////////////////////////////////////
		//corner piece is not double test; y = 5
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
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 6)); //up
//		table.addPiece(new Piece(2, 1), table.findCorner(1, 0)); //up
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 1)); //up
//		table.addPiece(new Piece(3, 0), table.findCorner(0, 2)); //up
//		table.addPiece(new Piece(1, 3), table.findCorner(0, 3)); //up
//		table.addPiece(new Piece(4, 1), table.findCorner(1, 3)); //up
//		table.addPiece(new Piece(0, 4), table.findCorner(4, 1)); //up
//		table.addPiece(new Piece(5, 0), table.findCorner(0, 4)); //up
//		table.addPiece(new Piece(1, 5), table.findCorner(0, 5)); //up
		//its working
		////////////////////////////////////////////////////////////////
		//                     THE MONSTER                            //
		//going up; hits border; shifts till max; dual; turns left and right; and some other stuff;
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //right
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 6)); //up
//		table.addPiece(new Piece(2, 1), table.findCorner(1, 0)); //up
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 1)); //up
//		table.addPiece(new Piece(0, 0), table.findCorner(0, 2)); //up
//		table.addPiece(new Piece(3, 0), table.findCorner(0, 0)); //up
//		table.addPiece(new Piece(1, 3), table.findCorner(0, 3)); //up
//		table.addPiece(new Piece(4, 1), table.findCorner(1, 3)); //up
//		table.addPiece(new Piece(0, 4), table.findCorner(4, 1)); //up
//		table.addPiece(new Piece(5, 0), table.findCorner(0, 4)); //up
//		//its working
//		table.addPiece(new Piece(7, 0), table.findCorner(0, 0)); //up
//		table.addPiece(new Piece(1, 7), table.findCorner(0, 7)); //up
//		table.addPiece(new Piece(8, 1), table.findCorner(1, 7)); //up
//		table.addPiece(new Piece(0, 8), table.findCorner(8, 1)); //up
//		table.addPiece(new Piece(9, 0), table.findCorner(0, 8)); //up
//		//its working
//		table.addPiece(new Piece(5, 5), table.findCorner(0, 5)); //up
//		table.addPiece(new Piece(1, 5), table.findCorner(5, 5)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(1, 5)); //up
//		table.addPiece(new Piece(2, 6), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(2, 3), table.findCorner(2, 6)); //up
//		table.addPiece(new Piece(2, 2), table.findCorner(2, 3)); //up
//		//its working
//		table.addPiece(new Piece(3, 1), table.findCorner(2, 3)); //up
//		table.addPiece(new Piece(1, 7), table.findCorner(1, 3)); //up
//		table.addPiece(new Piece(2, 7), table.findCorner(1, 7)); //up
//		table.addPiece(new Piece(2, 8), table.findCorner(2, 7)); //up
//		table.addPiece(new Piece(3, 8), table.findCorner(8, 2)); //up
//		table.addPiece(new Piece(3, 4), table.findCorner(8, 3)); //up
//		table.addPiece(new Piece(5, 4), table.findCorner(3, 4)); //up
//		table.addPiece(new Piece(5, 6), table.findCorner(4, 5)); //up
//		table.addPiece(new Piece(6, 4), table.findCorner(5, 6)); //up
//		table.addPiece(new Piece(4, 7), table.findCorner(6, 4)); //up //does not fit in anyway
//		//its working
//		table.addPiece(new Piece(9, 9), table.findCorner(0, 9)); //up
//		table.addPiece(new Piece(9, 1), table.findCorner(9, 9)); //u9
//		table.addPiece(new Piece(1, 2), table.findCorner(1, 9)); //up
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 1)); //up
//		table.addPiece(new Piece(0, 1), table.findCorner(0, 2)); //up
//		table.addPiece(new Piece(1, 1), table.findCorner(0, 1)); //up
//		//its working
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 1)); //up //does not fit in anyway
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right; does not fit in anyway
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 3)); //left and right; does not fit in anyway
		////////////////////////////////////////////////////////////////
		//full left; going up; hits border; shifts till max; turns left;
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(1, 3), table.findCorner(3, 6)); //left
//		table.addPiece(new Piece(4, 1), table.findCorner(1, 3)); //left
//		table.addPiece(new Piece(2, 4), table.findCorner(4, 1)); //left
//		table.addPiece(new Piece(5, 2), table.findCorner(2, 4)); //left
//		table.addPiece(new Piece(0, 5), table.findCorner(5, 2)); //left
//		table.addPiece(new Piece(7, 0), table.findCorner(0, 5)); //left
//		table.addPiece(new Piece(8, 7), table.findCorner(0, 7)); //left
//		table.addPiece(new Piece(0, 8), table.findCorner(8, 7)); //left
//		table.addPiece(new Piece(9, 0), table.findCorner(0, 8)); //left
//		
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 6)); //up
//		table.addPiece(new Piece(2, 1), table.findCorner(1, 0)); //up
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 1)); //up
//		table.addPiece(new Piece(3, 0), table.findCorner(0, 2)); //up
//		table.addPiece(new Piece(0, 0), table.findCorner(0, 2)); //up
		//its working
		///////////////////////////////////////////////////////////////
		//going down; hits border; shifts till max; turns left and right;
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(1, 6), table.findCorner(6, 6)); //right
//		table.addPiece(new Piece(2, 6), table.findCorner(6, 6)); //down
//		table.addPiece(new Piece(2, 2), table.findCorner(2, 6)); //down
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 2)); //down
//		table.addPiece(new Piece(1, 2), table.findCorner(2, 2)); //down
//		table.addPiece(new Piece(3, 2), table.findCorner(2, 2)); //down
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 2)); //down
//		table.addPiece(new Piece(3, 1), table.findCorner(0, 1)); //down
//		table.addPiece(new Piece(2, 3), table.findCorner(3, 1)); //down
//		table.addPiece(new Piece(4, 2), table.findCorner(2, 3)); //down
//		table.addPiece(new Piece(4, 1), table.findCorner(2, 1)); //down
//		table.addPiece(new Piece(3, 4), table.findCorner(4, 1)); //down
//		table.addPiece(new Piece(0, 3), table.findCorner(3, 4)); //down
//		table.addPiece(new Piece(4, 0), table.findCorner(0, 3)); //down
		///////////////////////////////////////////////////////////////
		//going down; hits border; shifts till max; turns left and right;
//		table.addPiece(new Piece(3, 6), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 6), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(1, 6), table.findCorner(6, 6)); //right
//		table.addPiece(new Piece(2, 6), table.findCorner(6, 6)); //down
//		table.addPiece(new Piece(2, 2), table.findCorner(2, 6)); //down
//		table.addPiece(new Piece(0, 0), table.findCorner(2, 0)); //down
//		table.addPiece(new Piece(1, 2), table.findCorner(2, 2)); //down
//		table.addPiece(new Piece(3, 2), table.findCorner(2, 2)); //down
//		table.addPiece(new Piece(1, 0), table.findCorner(0, 2)); //down
//		table.addPiece(new Piece(3, 1), table.findCorner(0, 1)); //down
//		table.addPiece(new Piece(2, 3), table.findCorner(3, 1)); //down
//		table.addPiece(new Piece(4, 2), table.findCorner(2, 3)); //down
//		table.addPiece(new Piece(4, 1), table.findCorner(2, 1)); //down
//		table.addPiece(new Piece(3, 4), table.findCorner(4, 1)); //down
//		table.addPiece(new Piece(0, 3), table.findCorner(3, 4)); //down
//		table.addPiece(new Piece(4, 0), table.findCorner(0, 3)); //down
		
		table.printTable();
	}
}
