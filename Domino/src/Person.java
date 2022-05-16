import java.util.Scanner;

public class Person extends Player {

	public boolean addPiece(Table table) {		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int first_in = scan.nextInt();
		int sec_in = scan.nextInt();
		
		Piece p = inHand(first_in, sec_in);
		if(p == null) { // if no piece is registered
			System.out.println("NO PIECE IN HAND! TRY AGAIN!");
			return false; 
		}
		
		// if exists piece entered
		// check if exists corner
		
		int loc_A = scan.nextInt();
		int loc_B = scan.nextInt();
		
		Corner c = table.findCorner(loc_A, loc_B);	
		if(c == null) {
			System.out.println("NO CORNER IN GAME! TRY AGAIN!");
			return false; 
		}
		
		table.addPiece(c.getI(), c.getJ(), p, c);
		return true;
	}
	
	public Piece inHand(int A, int B) {		
		Piece[] pH = getPlayerHand();
		
		for(int i = 0; i < pH.length; i++) {
			int sideA = pH[i].getSideA();
			int sideB = pH[i].getSideB();
			if((A == sideA && B == sideB) || (A == sideB && B == sideA)) {
				return pH[i];
			}
		}
		return null;
	}
}
