
public class Corner {
	private String direction;
	private int i;
	private int j;
	private int iPrint;
	private int jPrint;
	private Piece piece;

	public Corner (int i, int j, String direction, Piece piece) {
		this.i = i;
		this.j = j;
		this.direction = direction;
		this.piece = piece;
	}
	
	public Corner (String direction, int iPrint, int jPrint, Piece piece) {
		this.direction = direction;
		this.iPrint = iPrint;
		this.jPrint = jPrint;
		this.piece = piece;
	}
	
	public int outerSide() {
		switch (direction) {
			case "left":
				return piece.getSideA();
			case "up":
				return piece.getSideA();
			case "right":
				return piece.getSideB();
			case "down":
				return piece.getSideB();
			default:
				return -1;
		}
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
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
}
