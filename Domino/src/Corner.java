
public class Corner {
	private String direction;
	private int i;
	private int j;
	private Piece piece;
	private int offset; // needed to print because of offset caused by the diffrence in sizes between vertical and horizontal pieces
	
	public Corner (String direction, int i, int j, Piece piece, int offset) {
		this.direction = direction;
		this.i = i;
		this.j = j;
		this.piece = piece;
		this.offset = offset;
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
	
	public int getOffset() {
		return offset;
	}
}
