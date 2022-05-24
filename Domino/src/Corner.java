/**
 * Represents a Corner
 * Corner has a direction (up,down,left,right), i/jPrint which help with
 * the 
 * adding pieces to the table, and gameTable them to console.
 * @author José Lopes and João Leandro
 * 
 */
public class Corner {
	private String direction;
	private int iPrint;
	private int jPrint;
	private Piece piece;
	private int state;
	
	public Corner (String direction, int iPrint, int jPrint, Piece piece) {
		this.direction = direction;
		this.iPrint = iPrint;
		this.jPrint = jPrint;
		this.piece = piece;
		this.state = 0;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getiPrint() {
		return iPrint;
	}
	
	public int getjPrint() {
		return jPrint;
	}
	
	public int getState() {
		return state;
	}
	
	public int setState(int state) {
		this.state = state;
		return state;
	}
	
}
