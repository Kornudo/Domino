import java.util.Scanner;
/**
 * Represents a Person in the game
 * 
 * @author José Lopes and João Leandro
 * 
 */
public class Person extends Player {
	
	final int ascii = 48;

	/**
	 * Receives user input to add user chosen piece in a specific corner
	 * 
	 * @param table game table itself
	 * 
	 * @post updates the player hand without the added piece
	 */
	public void addPiece(Table table) {	
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String pieceIn = scan.nextLine();
		if(pieceIn.equals("pass")) {
			this.setPass(true);
			return ;
		}
		
		//String pieceIn = scan.nextLine();
		String cornerIn = scan.nextLine();
		
		int first_in = (int)pieceIn.charAt(1)-ascii;
		int sec_in = (int)pieceIn.charAt(3)-ascii;
		
		Piece p = inHand(first_in, sec_in);
		if(p == null) { // if no piece is registered
			System.out.println("NO PIECE IN HAND! TRY AGAIN!");
			//scan.close();
			addPiece(table);
			return ; 
		}
		
		// if exists piece entered
		// check if exists corner
		
		int loc_A = (int)cornerIn.charAt(1)-ascii;
		int loc_B = (int)cornerIn.charAt(3)-ascii;
//		scan.close();
		
		Corner c = table.findCorner(loc_A, loc_B);	
		if(c == null) {
			System.out.println("NO CORNER IN GAME! TRY AGAIN!");
			addPiece(table);
		}
		
		if(first_in == loc_A || sec_in == loc_B || first_in == loc_B || sec_in == loc_A) {
			if(!table.addPiece(p, c)) {
				System.out.println("PLAY NOT POSSBILE TRY AGAIN OR PASS MTF!");
				addPiece(table);
			}
		} else {
			System.out.println("U TRYNA CHEATING? FUCK OFF");
			addPiece(table);
		}
		
		removePiece(p);
		printPlay(p, c);
		return ;
	}
	
	private Piece inHand(int A, int B) {		
		Piece[] playerHand = getPlayerHand();
		
		for(int i = 0; i < playerHand.length; i++) {
			int sideA = playerHand[i].getSideA();
			int sideB = playerHand[i].getSideB();
			if((A == sideA && B == sideB) || (A == sideB && B == sideA)) {
				return playerHand[i];
			}
		}
		return null;
	}

}
