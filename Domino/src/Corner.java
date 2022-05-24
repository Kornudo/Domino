/**
 * Represents a Corner
 * Corner has a direction (up,down,left,right), i/jPrint which help with
 * the print stage. It also has a piece dependency and a state for ending the draft game
 * 
 * @author José Lopes and João Leandro
 * 
 */
public class Corner {
	private String direction;
	private int iPrint;
	private int jPrint;
	private Piece piece;
	private int state;
	
	/**
	 * Constructor, sets direction, i/jPrint, piece and sets initial state to 0
	 * 
	 * @param direction Direction of Piece
	 * @param iPrint i from matrix print
	 * @param jPrint j from matrix print
	 * @param Piece Piece that is the Corner
	 * 
	 * @pre 0<=state<=3
	 * 
	 */
	public Corner (String direction, int iPrint, int jPrint, Piece piece) {
		this.direction = direction;
		this.iPrint = iPrint;
		this.jPrint = jPrint;
		this.piece = piece;
		this.state = 0;
	}
	
	/**
	 * Gets the corner direction
	 * 
	 * @return String direction
	 */	
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Gets the Corner Piece
	 * 
	 * @return Piece piece
	 */	
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Gets the value of iPrint
	 * 
	 * @return int iPrint
	 */	
	public int getiPrint() {
		return iPrint;
	}
	
	/**
	 * Gets the value of jPrint
	 * 
	 * @return int jPrint
	 */	
	public int getjPrint() {
		return jPrint;
	}
	
	/**
	 * Gets the value of state
	 * 
	 * @return int iPrint
	 */	
	public int getState() {
		return state;
	}
	
	/**
	 * Sets a new value of Corner state
	 * 
	 * @param state new state to replace the old one
	 * 
	 * @return int state
	 */	
	public int setState(int state) {
		this.state = state;
		return state;
	}
	
}
