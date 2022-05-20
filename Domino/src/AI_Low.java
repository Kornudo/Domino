import java.util.ArrayList;
import java.util.Random;

public class AI_Low extends AI {
	private Random rand = new Random();
	
	public boolean addPiece(Table table) {
		
		Piece[] playerHand = getPlayerHand();
		Piece[] randomPiece = null;
		Corner[] randomCorner = null;
		int count = 0;
		int r;
		
		for (int i = 0; i < playerHand.length; i++) { // get the pieces that can be played on the round
			
			Corner[] piecePlayables = givePlayableCorners(table, playerHand[i]);
			
			if(piecePlayables!=null) {
				r = rand.nextInt(piecePlayables.length);
				randomPiece[count] = playerHand[i];
				randomCorner[count] = piecePlayables[r];
			}
		}
		
		r = rand.nextInt(count);
		if(randomPiece==null) return false; // Piece arr or Corner arr
		
		table.addPiece(randomCorner[r].getI(), randomCorner[r].getJ(), randomPiece[r], randomCorner[r]);
		return true;	
	}
	
	private Corner[] givePlayableCorners(Table table, Piece piece) {	
		Corner[] temp = null;
		Corner toCompareDual = findFirstDualCorner(table);
		ArrayList<Corner> corners = table.getCorners();
		boolean firstDual = false;
		int count = 0;
		
		for(int i = 0; i < corners.size(); i++) {
			int oS = corners.get(i).outerSide();
						
			if(piece.getSideA()==oS || piece.getSideB()==oS)
				
				if(toCompareDual!=null && corners.get(i).getPiece().dual()) { // if exists duals playable corners list
																				// and selected is a dual
					Corner index = corners.get(i);
					
					if(!firstDual) // 1st dual case
						temp[count++] = index;
					
					if(toCompareDual != index) { // 2nd and dif dual corners
						temp[count++] = index;
						toCompareDual = index;
					}
					continue;
				}
				temp[count++] = corners.get(i);
		}
		return temp;
	}
	
	private Corner findFirstDualCorner(Table table) {
		ArrayList<Corner> corners = table.getCorners();
		
		for(int i = 0; i < corners.size(); i++) {
			if(corners.get(i).getPiece().dual())
				return corners.get(i);
		}
		return null;
	}
	
}
