
public class Person extends Player {

	public boolean addPiece() {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public boolean inHand(int A, int B) {
		// TODO Auto-generated method stub
		
		Piece[] pH = getPlayerHand();
		
		for(int i = 0; i < pH.length; i++) {
			if((A == pH[i].getSideA() && B == pH[i].getSideB()) || (A == pH[i].getSideB() && B == pH[i].getSideA())) {
				return true;
			}
		}
		return false;
	}
}
