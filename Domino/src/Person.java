import java.util.ArrayList;
import java.util.Scanner;

public class Person extends Player {

	public void addPiece(Table table) {	
		
		//if(!possiblePlay(table)) return ;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String choice = scan.nextLine();
		if(choice.equals("pass")) return ;
		
		String pieceIn = scan.nextLine();
		String cornerIn = scan.nextLine();
		
		int first_in = (int)pieceIn.charAt(1);
		int sec_in = (int)pieceIn.charAt(3);
		
		Piece p = inHand(first_in, sec_in);
		if(p == null) { // if no piece is registered
			System.out.println("NO PIECE IN HAND! TRY AGAIN!");
			//scan.close();
			addPiece(table);
			return ; 
		}
		
		// if exists piece entered
		// check if exists corner
		
		int loc_A = (int)cornerIn.charAt(1);
		int loc_B = (int)cornerIn.charAt(3);
//		scan.close();
		
		Corner c = table.findCorner(loc_A, loc_B);	
		if(c == null) {
			System.out.println("NO CORNER IN GAME! TRY AGAIN!");
			addPiece(table);
		}
		
		if(!table.addPiece(p, c)) {
			System.out.println("PLAY NOT POSSBILE TRY AGAIN!");
			addPiece(table);
		}
		
		removePiece(p);
		printPlay(p, c);
		return ;
	}
	
	public Piece inHand(int A, int B) {		
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
	
	public boolean possiblePlay(Table table) {
		Piece[] playerHand = getPlayerHand();
		ArrayList<Corner> corners = table.getCorners();

		for(int i = 0; i < playerHand.length; i++) {
			for(int j = 0; j < corners.size(); j++) {
				int A = playerHand[i].getSideA();
				int B = playerHand[i].getSideB();
				int cornerSide = corners.get(j).getPiece().getSideA();
				if(A==cornerSide||B==cornerSide) return true;
			}
		}
		return false;
	}
}
