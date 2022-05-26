import java.util.ArrayList;

/**
 * Represents the Lowest level of AI
 * @author José Lopes and João Leandro
 * 
 */
public class AI_Low extends AI {
	//private Random rand = new Random();
	Corner c = null;
	int index = 0;
	
	/**
	 * Sets up a random piece to add on the game table
	 * 
	 * @param table game table itself
	 * 
	 * @post updates the player hand without the added piece
	 */
	public void addPiece(Table table) {
		
		ArrayList<Corner> corners = table.getCorners();
		Piece[] playerHand = getPlayerHand();
		Piece toCompare = null;
		int count = 0;
		int i = getPlayable(table);
		
		if(i!=-1)
			toCompare = playerHand[i];
		else {
			this.setPass(true);
			return;
		}
	
		while(!table.addPiece(playerHand[i], c)) {

				while(true) { // same piece diff corners	
					int A = playerHand[i].getSideA();
					int B = playerHand[i].getSideB();
					index = findPlayableCornerIndex(table, A, B, index); // finds corners for 1 piece with index incremented
					if(index>=corners.size() || index==-1) break;
					c = corners.get(index);
					if(c==null) break;
					
					if(table.addPiece(playerHand[i], c)) {
						printPlay(playerHand[i], c);
						removePiece(playerHand[i]);
						return ;	
					}
					index++;
				}
				index=0;
				
				shiftRight(playerHand[i], i);
				if(playerHand[i]==toCompare && count>0) {
					this.setPass(true);
					return ;
				}
				count++;				
			}
		
		printPlay(playerHand[i], c);
		removePiece(playerHand[i]);
		return ;	
	}
	
	private void shiftRight(Piece piece, int index) {
		Piece[] playerHand = getPlayerHand();
		for(int i = index; i < playerHand.length-1; i++) {
			Piece temp = playerHand[i];
			playerHand[i] = playerHand[i+1];
			playerHand[i+1] = temp;
		}
	}
	
	private int getPlayable(Table table) {
		Piece[] playerHand = getPlayerHand();
		int i;
		for (i = 0; i < playerHand.length; i++) { 			
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			c = table.findPlayableCorner(A, B);
			if(c!=null) break ;	
		}
		
		if(c==null) return -1;
		else return i;
	}
	
	public int findPlayableCornerIndex(Table table, int A, int B, int index) { 
		ArrayList<Corner> corners = table.getCorners();
        for(int i = index; i < corners.size(); i++) {
            int cornerOuterSide = corners.get(i).getPiece().getSideA(); 
            if(cornerOuterSide == A || cornerOuterSide == B) {
                return i;
            }
        }
        return -1;
    }
	
}
	