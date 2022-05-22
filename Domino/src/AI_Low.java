import java.util.ArrayList;
import java.util.Random;

public class AI_Low extends AI {
	private Random rand = new Random();
	
	@SuppressWarnings({ "null", "unused" })
	public boolean addPiece(Table table) {
		
		Piece[] playerHand = getPlayerHand();
		Piece[] randomPiece = null;
		Corner[] randomCorner = null;
		int count = 0;
		int r;
		
		for (int i = 0; i < playerHand.length; i++) { 
			Corner[] piecePlayables = givePlayableCorners(table, playerHand[i]);
			if(piecePlayables!=null) {
				r = rand.nextInt(piecePlayables.length);
				randomPiece[count] = playerHand[i];
				randomCorner[count] = piecePlayables[r];
				count++;
				piecePlayables = null;
			}	
		}
		
		r = rand.nextInt(count);
		if(randomPiece==null) return false; 
		
		if(!table.addPiece(randomPiece[r], randomCorner[r])) return false;
		setPlayerHand(removePiece(randomPiece[r]));
		return true;	
	}
	
	private Corner[] givePlayableCorners(Table table, Piece piece) {	
		Corner[] temp = null;
		Corner toCompareDual = findFirstDualCorner(table);
		ArrayList<Corner> corners = table.getCorners();
		boolean isfirstDual = true;
		int count = 0;
		
		for(int i = 0; i < corners.size(); i++) {
			int oS = corners.get(i).outerSide();	
			if (piece.getSideA() == oS || piece.getSideB() == oS) {
				if (toCompareDual != null && corners.get(i).getPiece().dual()) {
					Corner index = corners.get(i);
					if (!isfirstDual) {
						temp[count++] = index;
						isfirstDual = false;
					}
					if (toCompareDual != index) {
						temp[count++] = index;
						toCompareDual = index;
					}
					continue;
				}
				temp[count++] = corners.get(i);
			}
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
