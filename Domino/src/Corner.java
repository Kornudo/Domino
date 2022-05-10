
public class Corner {
	private String direction;
	private int i;
	private int j;
	
	public Corner (String direction, int i, int j) {
		this.direction = direction;
		this.i = i;
		this.j = j;
	}
	
	public int outerSide(Piece piece) {
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
