import java.util.ArrayList;
import java.util.Random;
/**
 * Represents the Lowest level of AI
 * @author José Lopes and João Leandro
 * 
 */
public class AI_Low extends AI {
	private Random rand = new Random();
	
	/**
	 * Sets up a random piece to add on the game table
	 * 
	 * @param table game table itself
	 * 
	 * @post updates the player hand without the added piece
	 */
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
		if(!table.addPiece(randomPiece.get(r), randomCorner.get(r))) {
			
			int A = randomCorner.get(r).getPiece().getSideA();
			int B = randomCorner.get(r).getPiece().getSideB();
			int stateCorner = randomCorner.get(r).getState();
			
			Corner corner = table.findCorner(A, B);
			if(!randomPiece.get(r).dual()) 
				corner.setState(1);
			else if(randomPiece.get(r).dual())
				corner.setState(2);
			else if((randomPiece.get(r).dual() && stateCorner==1) || (!randomPiece.get(r).dual() && stateCorner==2))
				corner.setState(3);
			
			return ;
		}
		printPlay(randomPiece.get(r), randomCorner.get(r));
		removePiece(randomPiece.get(r));
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
