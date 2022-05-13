import java.util.Scanner;

public class Person extends Player {

	public boolean addPiece() {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int first_in = scan.nextInt();
		int sec_in = scan.nextInt();
		
		if(!inHand(first_in, sec_in)) { // if no piece is registered
			System.out.println("NO PIECE IN HAND! TRY AGAIN!");
			return false; 
		}
		
		// if exists piece entered
		// check if exists corner
		
		int loc_A = scan.nextInt();
		int loc_B = scan.nextInt();
		
		if(!Table.existCorner(loc_A, loc_B)) {
			System.out.println("NO CORNER IN GAME! TRY AGAIN!");
			return false; 
		}
		
		
		Pieces[][] pieces = Table.getPieces();
		
		
		
		
		
		
		return false;
	}
	
	public boolean inHand(int A, int B) {
		// TODO Auto-generated method stub
		
		Piece[] pH = getPlayerHand();
		
		for(int i = 0; i < pH.length; i++) {
			int sideA = pH[i].getSideA();
			int sideB = pH[i].getSideB();
			if((A == sideA && B == sideB) || (A == sideB && B == sideA)) {
				return true;
			}
		}
		return false;
	}
}
