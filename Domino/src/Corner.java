
public class Corner {
	private String direction;
	private int i;
	private int j;
	private Piece piece;
	
	public Corner (String direction, int i, int j, Piece piece) {
		this.direction = direction;
		this.i = i;
		this.j = j;
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return this.piece;
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
}
