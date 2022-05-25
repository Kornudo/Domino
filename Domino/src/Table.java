import java.util.ArrayList;
/**
 * Represents a domino table
 * Table size is variable, its responsible for managing corners (available spaces for pieces),
 * adding pieces to the table, and gameTable them to console.
 * @author José Lopes and João Leandro
 * 
 */
public class Table {
	private int y; //number of vertical pieces
	private int x; //number of horizontal pieces + 6/6
	private int upBoundaryIndex; // first gameTableed index, will change
	private int downBoundaryIndex; // last gameTableed index, will change
	private int downMostIndex; // index of down most gameTable index of down most piece in the table
	private int upMostIndex; // index of up most gameTable index of up most piece in the table
	private int leftBoundaryIndex; // first gameTableed index, will change
	private int rightBoundaryIndex; // last gameTableed index, will change
	private int leftMostIndex; // index of down most gameTable index of down most piece in the table
	private int rightMostIndex; // index of up most gameTable index of up most piece in the table
	private String[][] gameTable; 
	private ArrayList<Corner> corners = new ArrayList<Corner>();

	/**
	 * Constructor, sets size and builds matrix to be gameTableed to console
	 * 
	 * @param y Maximum number of vertical pieces
	 * @param x Maximum number of horizontal pieces and piece 6/6
	 * 
	 * @pre y >= 1
	 * @pre x >= 1
	 * 
	 * @post boundaries are set in function of y and x
	 * @post String matrix gameTable is initialized in function of y and x and filled with spaces
	 */
	public Table (int y, int x) {
		assert y >= 1;
		assert x >= 1;
		this.y = y;
		this.x = x;
		
		upBoundaryIndex = 3*y - 1;
		downBoundaryIndex = 9*y + 1;
		leftBoundaryIndex = (3 * x - 3)/2;
		rightBoundaryIndex = (9*x - 9)/2; 
		gameTable = new String[12 * y + 1][6 * x - 5];
		
		for (int i = 0; i < 12 * y + 1; i++) {
			for (int j = 0; j < 6 * x - 5; j++) {
				gameTable[i][j] = " ";
			}
		}
	}
	
	/**
	 * Gets ArrayList corners
	 * 
	 * @return ArrayList corners
	 */
	public ArrayList<Corner> getCorners() {
		return corners;
	}
	
	/**
	 * FAIS ESSE ZOAO Q EU N SEI
	 */
	public Corner findPlayableCorner(int A, int B) { 
        for(int i = 0; i < corners.size(); i++) {
            int cornerOuterSide = corners.get(i).getPiece().getSideA(); 
            if(cornerOuterSide == A || cornerOuterSide == B) {
                return corners.get(i);
            }
        }
        return null;
    }
	
	/**
	 * FAIS ESSE ZOAO Q EU N SEI
	 */
	public Corner findPlayableCounterCorner(int A) { 
        for(int i = 0; i < corners.size(); i++) {
            int cornerOuterSide = corners.get(i).getPiece().getSideA(); 
            if(cornerOuterSide == A) {
                return corners.get(i);
            }
        }
        return null;
    }
	
	/**
	 * Finds an available corner that accepts a piece with side A or B
	 * 
	 * @param A number on one side of a piece
	 * @param B number on the other side of a piece
	 * 
	 * @pre 0 <= A <= 6
	 * @pre 0 <= B <= 6
	 * 
	 * @return the corner
	 */
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
	
	/**
	 * FAIS ESSE ZOAO Q EU N SEI
	 */
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
	
	/**
	 * FAIS ESSE ZOAO Q EU N SEI
	 */
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
			gameTable[y - 3][x] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 2; i++) {
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
				gameTable[y + 3][x + i] = "x";
			}
			gameTable[y - 2][x - 1] = "x";
			gameTable[y - 2][x + 1] = "x";
			gameTable[y][x - 1] = "x";
			gameTable[y][x + 1] = "x";
			gameTable[y + 2][x - 1] = "x";
			gameTable[y + 2][x + 1] = "x";
			return;
		}
		try {
			gameTable[y + 3][x] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 2; i++) {
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
			}
			gameTable[y - 2][x - 1] = "x";
			gameTable[y - 2][x + 1] = "x";
			gameTable[y][x - 1] = "x";
			gameTable[y][x + 1] = "x";
			gameTable[y + 2][x - 1] = "x";
			gameTable[y + 2][x + 1] = "x";
			return;
		}
		try {
			gameTable[y][x - 1] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = 0; i < 2; i++) {
				gameTable[y - 3][x + i] = "x";
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
				gameTable[y + 3][x + i] = "x";
			}
			gameTable[y - 2][x + 1] = "x";
			gameTable[y][x + 1] = "x";
			gameTable[y + 2][x + 1] = "x";
			return;
		}
		try {
			gameTable[y][x + 1] = "x";
		} catch(IndexOutOfBoundsException e) {
			for(int i = -1; i < 1; i++) {
				gameTable[y - 3][x + i] = "x";
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
				gameTable[y + 3][x + i] = "x";
			}
			gameTable[y - 2][x - 1] = "x";
			gameTable[y][x - 1] = "x";
			gameTable[y + 2][x - 1] = "x";
			return;
		}

		for(int i = -1; i < 2; i++) {
			gameTable[y - 3][x + i] = "x";
			gameTable[y - 1][x + i] = "x";
			gameTable[y + 1][x + i] = "x";
			gameTable[y + 3][x + i] = "x";
		}
		gameTable[y - 2][x - 1] = "x";
		gameTable[y - 2][x + 1] = "x";
		gameTable[y + 2][x - 1] = "x";
		gameTable[y + 2][x + 1] = "x";
	}
	
	private void collisionMarkLeftHorizontal(int y, int x) {
		try {
			for(int i = 1; i > -3; i--) {
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
			}
			gameTable[y][x - 2] = "x";
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private void collisionMarkRightHorizontal(int y, int x) {
		try {
			for(int i = -2; i < 2; i++) {
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
			}
			gameTable[y][x + 1] = "x"; 
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private void collisionMarkHorizontalDual(int y, int x) {
		try {
			for(int i = -2; i < 3; i++) {
				gameTable[y - 1][x + i] = "x";
				gameTable[y + 1][x + i] = "x";
			}
			gameTable[y][x - 2] = "x";
			gameTable[y][x + 2] = "x";
		} catch(IndexOutOfBoundsException e) {};
	}
	
	private boolean collisionCheckUp(int y, int x) { //returns true if there is collision
		try {
			for(int i = y - 3; i < y + 3; i++) {
				if (gameTable[i][x] == "x") return true; 
			}
		} catch (IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckDown(int y, int x) { //returns true if there is collision
//		if (x - 1 == 0) {
//			for(int i = y - 2; i < y + 4; i++) {
//				if (gameTable[i][x] == "x" || gameTable[i][x - 1] == "x" || gameTable[i][x + 1] == "x") return true; 
//			}
//		}
//		
		try {
			for(int i = y - 2; i < y + 4; i++) {
				if (gameTable[i][x] == "x" || gameTable[i][x - 1] == "x" || gameTable[i][x + 1] == "x") return true; 
			}
		} catch (IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckLeft(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 2; j < x + 1; j++) {
				if (gameTable[y][j] == "x" || gameTable[y - 1][j] == "x" || gameTable[y + 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckRight(int y, int x) { //returns true if there is collision
		try {
			for(int j = x - 1; j < x + 2; j++) {
				if (gameTable[y][j] == "x" || gameTable[y - 1][j] == "x" || gameTable[y + 1][j] == "x") return true; 
			}
		} catch(IndexOutOfBoundsException e) {return true;}
		return false;
	}
	
	private boolean collisionCheckLeftDual(int y, int x) { //returns true if there is collision
		if (y < upBoundaryIndex + 2 || y > downBoundaryIndex - 2) return true;
		if (x - 1 == leftBoundaryIndex - 1) { //avoid exception
			for(int i = y - 3; i < y + 4; i++) {
				if (gameTable[i][x] == "x") return true;
			}
		}
		else {
			for(int i = y - 3; i < y + 4; i++) {
				if (gameTable[i][x - 1] == "x" || gameTable[i][x] == "x") return true;
			}
		}
		return false;
	}
	
	private boolean collisionCheckRightDual(int y, int x) { //returns true if there is collision
		if (y < upBoundaryIndex + 2 || y > downBoundaryIndex - 2) return true;
		if (x + 1 == rightBoundaryIndex + 1) { //avoid exception
			for(int i = y - 3; i < y + 4; i++) {
				if (gameTable[i][x] == "x") return true;
			}
		}
		else {
			for(int i = y - 3; i < y + 4; i++) {
				if (gameTable[i][x + 1] == "x" || gameTable[i][x] == "x") return true;
			}
		}
		return false;
	}
	
	private boolean collisionCheckUpDual(int y, int x) { //returns true if there is collision
		if (x == leftBoundaryIndex || x == rightBoundaryIndex) return true;
		for(int j = x - 2; j < x + 3; j++) {
			if (gameTable[y][j] == "x") return true; 
			if (gameTable[y - 1][j] == "x") return true; 
		}
		return false;
	}
	
	private boolean collisionCheckDownDual(int y, int x) { //returns true if there is collision
		if (x == leftBoundaryIndex || x == rightBoundaryIndex) return true;
		for(int j = x - 2; j < x + 3; j++) {
			if (gameTable[y][j] == "x") return true; 
			if (gameTable[y + 1][j] == "x") return true; 
		}
		return false;
	}
	
	/**
	 * Prints the table to console
	 */
	public void printTable() { // CAN BE OPTIMIZED
		System.out.print("#");
		for (int j = 0; j < x * 3 - 2; j++) System.out.print("#");
		System.out.println("#");
		for (int i = upBoundaryIndex; i < downBoundaryIndex + 1; i++) {
			System.out.print("#");
			for (int j = leftBoundaryIndex; j < rightBoundaryIndex + 1; j++) {
				if (gameTable[i][j] != "x") System.out.print(gameTable[i][j]);
				else System.out.print(" ");
			}
			System.out.println("#");
		}
		System.out.print("#");
		for (int j = 0; j < x * 3 - 2; j++) System.out.print("#");
		System.out.println("#");
	}
	
	/**
	 * Adds a corner to the ArrayList of corners
	 * 
	 * @param corner
	 * 
	 * @post corner is added
	 */
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	private void addDualPieceLeft (Piece piece, Corner corner) {
		gameTable[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint()][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceLeft (Piece piece, Corner corner) {
		gameTable[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideB());
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
		gameTable[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint()][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint(), corner.getjPrint() - 2, piece));
		addCorner(new Corner("up", corner.getiPrint() - 6, corner.getjPrint(), piece));
		addCorner(new Corner("down", corner.getiPrint() + 6, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceRight (Piece piece, Corner corner) {
		gameTable[corner.getiPrint()][corner.getjPrint() - 1] = String.valueOf(piece.getSideB());
		gameTable[corner.getiPrint()][corner.getjPrint()] = String.valueOf(piece.getSideA());
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
		gameTable[corner.getiPrint() + 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint() + 2][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() + 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() + 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("up", corner.getiPrint() - 2, corner.getjPrint(), piece));
		addCorner(new Corner("right", corner.getiPrint() + 2, corner.getjPrint() + 4, piece));
	}
	
	private void addNonDualPieceUp(Piece piece, Corner corner) {
		gameTable[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint()][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
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
		gameTable[corner.getiPrint() - 2][corner.getjPrint() - 1] = String.valueOf(piece.getSideA());
		gameTable[corner.getiPrint() - 2][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() - 2][corner.getjPrint() + 1] = String.valueOf(piece.getSideB());
		addCorner(new Corner("left", corner.getiPrint() - 2, corner.getjPrint() - 3, piece));
		addCorner(new Corner("right", corner.getiPrint() - 2, corner.getjPrint() + 4, piece));
		addCorner(new Corner("down", corner.getiPrint() + 2, corner.getjPrint(), piece));
	}
	
	private void addNonDualPieceDown(Piece piece, Corner corner) {
		gameTable[corner.getiPrint() - 2][corner.getjPrint()] = String.valueOf(piece.getSideB());
		gameTable[corner.getiPrint()][corner.getjPrint()] = "-";
		gameTable[corner.getiPrint() + 2][corner.getjPrint()] = String.valueOf(piece.getSideA());
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
	
	/**
	 * Adds a piece to the table
	 * 
	 * @param piece Piece to be added
	 * @param corner Corner where piece will be added
	 * 
	 * @post piece is added if possible
	 * @post boundaries are altered if necessary
	 * @post extreme indexes are altered if necessary
	 * 
	 * @return true if piece is successfully added, false if not
	 */
	public boolean addPiece(Piece piece, Corner corner) { //returns false if it fails to place piece
		if (corner == null) {
			gameTable[y * 6 - 2][3 * x - 3] = "6";
			gameTable[y * 6][3 * x - 3] = "-";
			gameTable[y * 6 + 2][3 * x - 3]= "6";
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
			if (dual && !collisionCheckLeftDual(corner.getiPrint(), corner.getjPrint())) {
				if(outOfBoundsLeftDual(corner)) 
					if(!handleOutOfBoundsLeftDual(piece, corner)) return false;
				if(outOfBoundsUp(corner)) 
					if(!handleOutOfBoundsUp(piece, corner)) return false;
				else if(outOfBoundsDown(corner))
					if(!handleOutOfBoundsDown(piece, corner)) return false;
				addPieceLeft(piece, corner);
				corners.remove(corner);
				return true;
			}
			else if (dual) return false; //if dual colides, the piece cant be placed
			
			if (collisionCheckLeft(corner.getiPrint(), corner.getjPrint())){
				if (corner.getPiece().dual()) {
					return handleCollisionDual(piece, corner);
				}
				else return handleCollisionLeft(piece, corner);
			}
			
			if (outOfBoundsLeft(corner)) handleOutOfBoundsLeft(piece, corner);
			addPieceLeft(piece, corner);
			
			
			
//			int outOfBounds = outOfBoundsLeft(corner);
//			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
//				boolean collision;
//				if (dual) collision = collisionCheckLeftDual(corner.getiPrint(), corner.getjPrint());
//				else collision = collisionCheckLeft(corner.getiPrint(), corner.getjPrint());
//				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
//				else if(collision) return handleCollisionLeft(piece, corner);
//				addPieceLeft(piece, corner);
//			}
//			else {
//				return handleOutOfBoundsLeft(piece, corner);
//			}
		}
		else if (direction == "right") {
			int outOfBounds = outOfBoundsRight(corner);
			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
				boolean collision;
				if (dual) collision = collisionCheckRightDual(corner.getiPrint(), corner.getjPrint());
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
//			int outOfBounds = outOfBoundsUp(corner);
//			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
//				boolean collision;
//				if (dual) collision = collisionCheckUpDual(corner.getiPrint() + 2, corner.getjPrint());
//				else collision = collisionCheckUp(corner.getiPrint(), corner.getjPrint());
//				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
//				else if(collision) return handleCollisionUp(piece, corner);
//				addPieceUp(piece, corner);
//			}
//			else {
//				return handleOutOfBoundsUp(piece, corner);
//			}
		}
		else {
//			int outOfBounds = outOfBoundsDown(corner);
//			if (outOfBounds == 2 || (outOfBounds == 1 && dual)) {
//				boolean collision;
//				if (dual) collision = collisionCheckDownDual(corner.getiPrint() - 2, corner.getjPrint());
//				else collision = collisionCheckDown(corner.getiPrint(), corner.getjPrint());
//				if (collision && corner.getPiece().dual()) return handleCollisionDual(piece, corner);
//				else if(collision) return handleCollisionDown(piece, corner);
//				addPieceDown(piece, corner);
//			}
//			else {
//				return handleOutOfBoundsDown(piece, corner);
//			}
			
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
	
	private boolean outOfBoundsUp(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getiPrint() - 2 < upBoundaryIndex) { // a vertical piece would be out of bounds
			return true;
		}
		return false;
	}
	
	private boolean outOfBoundsDown(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getiPrint() + 2 > downBoundaryIndex) { // a vertical piece would be out of bounds
			return true;
		}
		return false;
	}
	
	private boolean outOfBoundsLeftDual(Corner corner) { //true if out of bounds
		if (corner.getjPrint() < leftBoundaryIndex) {
			return true;
		}
		return false;
	}

	
	private boolean outOfBoundsLeft(Corner corner) { // returns 0 if no pieces fit, 1 if only an horizontal piece fits, 2 if both vertical and horizontal pieces fit
		if (corner.getjPrint() - 1 < leftBoundaryIndex) { // a horizontal piece would be out of bounds
			return true;
		}
		return false;
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
	
	private boolean handleOutOfBoundsLeftDual(Piece piece, Corner corner) {
		if (rightMostIndex <= rightBoundaryIndex - 2) { // if board can shift for dual vertical
			leftBoundaryIndex -= 2;
			rightBoundaryIndex -= 2;
			return true;
		}
		return false;
	}
	
	private boolean handleOutOfBoundsUp(Piece piece, Corner corner) { //returns false if cant shift
		if (piece.dual() && downMostIndex <= downBoundaryIndex - 2) { // if board can shift for dual vertical
			upBoundaryIndex -= 2;
			downBoundaryIndex -= 2;
//			addPieceUp(piece,corner);
			return true; //new
		}
		if(downMostIndex <= downBoundaryIndex - 6) { // if board can shift for vertical piece
			upBoundaryIndex -= 6;
			downBoundaryIndex -= 6;
//			addPieceUp(piece,corner);
			return true; //new
		}
//		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
//			return handleCollisionDual(piece, corner);
//		}
//		else { // cant shift and corner piece is not dual
//			return handleCollisionUp(piece, corner);
//		}
//		corners.remove(corner);
//		return true;
		return false; //new
	}

	private boolean handleOutOfBoundsDown(Piece piece, Corner corner) {
		if (piece.dual() && upMostIndex >= upBoundaryIndex + 2) { // if piece is dual and board can shift for dual vertical
			upBoundaryIndex += 2;
			downBoundaryIndex += 2;
//			addPieceDown(piece,corner);
			return true;
		}
		else if(upMostIndex >= upBoundaryIndex + 6) { // if board can shift for vertical piece
			upBoundaryIndex += 6;
			downBoundaryIndex += 6;
//			addPieceDown(piece,corner);
			return true;
		}
//		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
//			return handleCollisionDual(piece, corner);
//		}
//		else { // cant shift and corner piece is not dual
//			return handleCollisionDown(piece, corner);
//		}
//		corners.remove(corner);
		return false;
	}
	
	private boolean handleOutOfBoundsLeft(Piece piece, Corner corner) {
		if (piece.dual() && rightMostIndex <= rightBoundaryIndex - 2) { // if board can shift for dual vertical
			leftBoundaryIndex -= 2;
			rightBoundaryIndex -= 2;
//			addPieceLeft(piece,corner);
			return true;
		}
		else if(rightMostIndex <= rightBoundaryIndex - 3) { // if board can shift for horizontal piece
			leftBoundaryIndex -= 3;
			rightBoundaryIndex -= 3;
//			addPieceLeft(piece,corner);
			return true;
		}
//		else if (corner.getPiece().dual()){ // cant shift and corner piece is dual
//			return handleCollisionDual(piece, corner);
//		}
//		else { // cant shift and corner piece is not dual
//			return handleCollisionLeft(piece, corner);
//		}
//		corners.remove(corner);
		return false;
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
		Table table = new Table(5, 7);
		table.addPiece(new Piece(6, 6), null);
		
//		// left
		table.addPiece(new Piece(6, 3), table.findCorner(6, 6));
		table.addPiece(new Piece(6, 3), table.findCorner(6, 3));
		table.addPiece(new Piece(6, 3), table.findCorner(6, 3));
		table.addPiece(new Piece(7, 3), table.findCorner(6, 3));
		table.addPiece(new Piece(7, 7), table.findCorner(7, 3));
		table.addPiece(new Piece(7, 0), table.findCorner(7, 7));
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
//		table.addPiece(new Piece(2, 2), table.findCorner(2, 0)); 
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
		//UNSOLVED BUG
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 6));
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 6));
//		table.addPiece(new Piece(6, 2), table.findCorner(6, 6));
//		table.addPiece(new Piece(2, 0), table.findCorner(6, 2));
//		table.addPiece(new Piece(0, 2), table.findCorner(2, 0));
//		table.addPiece(new Piece(2, 2), table.findCorner(2, 0)); //BUG
		////////////////////////////////////////////////////////////////
		////border shift and collision tests left and down
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(7, 3), table.findCorner(6, 3)); //left
//		
//		table.addPiece(new Piece(7, 7), table.findCorner(7, 3)); //left
//		table.addPiece(new Piece(7, 0), table.findCorner(7, 7)); //left
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
		
		
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6)); //right but goes down
//		table.addPiece(new Piece(0, 1), table.findCorner(6, 0)); //down
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; cant shift; turns left;
//		table.addPiece(new Piece(0, 0), table.findCorner(1, 0)); //down; doesnt fit; is not placed;
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; cant shift; turns up;
//		table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; doesnt fit; turns right;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
//			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down;
////			table.addPiece(new Piece(0, 1), table.findCorner(1, 0)); //down; doesnt fit; is not placed;
//			
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left
//			table.addPiece(new Piece(7, 0), table.findCorner(7, 0)); //left  doesnt fit; is not placed;
			
//		table.addPiece(new Piece(0, 0), table.findCorner(1, 0)); //down; 
		
		////////////////////////////////////////////////////////////////
		////border shift and collision tests up
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up
//		table.addPiece(new Piece(6, 1), table.findCorner(6, 1)); //up //ERRO
		////////////////////////////////////////////////////////////////
		////border shift left and add double up next to border
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 3), table.findCorner(6, 3)); //left
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 0), table.findCorner(6, 0)); //left
		//its working
		////////////////////////////////////////////////////////////////
		////up until border, place dual, go right, try to place dual
//		table.addPiece(new Piece(6, 0), table.findCorner(6, 6)); //left
//		table.addPiece(new Piece(0, 1), table.findCorner(6, 6)); //up
//		table.addPiece(new Piece(0, 1), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(0, 0), table.findCorner(0, 1)); //up
//		table.addPiece(new Piece(0, 1), table.findCorner(0, 0)); //up
//		table.addPiece(new Piece(1, 1), table.findCorner(0, 1)); //up
		
		//its working
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
