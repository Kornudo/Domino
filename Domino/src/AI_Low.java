import java.util.ArrayList;
import java.util.Random;

public class AI_Low extends AI {
	private Random rand = new Random();
	
	public void addPiece(Table table) {
		
		Piece[] playerHand = getPlayerHand();
		ArrayList<Piece> randomPiece = new ArrayList<Piece>();
		ArrayList<Corner> randomCorner = new ArrayList<Corner>();
		int r;
		
		for (int i = 0; i < playerHand.length; i++) { 
			ArrayList<Corner> piecePlayables = givePlayableCorners(table, playerHand[i]);
			if(piecePlayables.size()!=0) {
				r = rand.nextInt(piecePlayables.size());
				randomPiece.add(playerHand[i]);
				randomCorner.add(piecePlayables.get(r));
			}	
		}
		
		if(randomPiece.size()==0) return ; 
		
		r = rand.nextInt(randomPiece.size());
		if(!table.addPiece(randomPiece.get(r), randomCorner.get(r))) return ;
		removePiece(randomPiece.get(r));
		printPlay(randomPiece.get(r), randomCorner.get(r));
		return ;	
	}
	
	private ArrayList<Corner> givePlayableCorners(Table table, Piece piece) {	
		ArrayList<Corner> temp = new ArrayList<Corner>(); ;
		ArrayList<Corner> corners = table.getCorners();
		Corner toCompareDual = findFirstDualCorner(table);
		boolean isStarted = false;
		
		for(int i = 0; i < corners.size(); i++) {
			int oS = corners.get(i).getPiece().getSideA();	
			if (piece.getSideA() == oS || piece.getSideB() == oS) {
				if (corners.get(i).getPiece().dual()) {
					Corner index = corners.get(i);				
					int A = toCompareDual.getPiece().getSideA();
					int B = index.getPiece().getSideA();
					
					if(!isStarted && index==corners.get(i)) {
						temp.add(toCompareDual);
						isStarted = true;
					}
					
					if (A!=B) {
						temp.add(index);
						toCompareDual = index;
					}
					continue;
				}
				temp.add(corners.get(i));
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
