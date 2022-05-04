
public class Piece {
	private int sideA;
	private int sideB;
	
	Piece (int sideA, int sideB) {
		this.sideA = sideA;
		this.sideB = sideB;
	}
	
	public boolean dual() {
		return false;
	}
	
	public int getSideA() {
		return sideA;
	}
	
	public int getSideB() {
		return sideB;
	}
	
}
