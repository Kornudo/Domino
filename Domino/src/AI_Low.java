import java.util.ArrayList;

/**
 * Represents the Lowest level of AI
 * @author José Lopes and João Leandro
 * 
 */
public class AI_Low extends AI {
	//private Random rand = new Random();
	Corner c = null;
	
	/**
	 * Sets up a random piece to add on the game table
	 * 
	 * @param table game table itself
	 * 
	 * @post updates the player hand without the added piece
	 */
	public void addPiece(Table table) {
		
//		ArrayList<Corner> corners = table.getCorners();
		Piece[] playerHand = getPlayerHand();
		Piece toCompare = null;
		int i = getPlayable(table);
		if(i!=-1)
			toCompare = playerHand[i];
		else {
			this.setPass(true);
			return;
		}
		int count = 0;

		while(true) {
			if(!table.addPiece(playerHand[i], c)) {
				
				int A = c.getPiece().getSideA();
				int B = c.getPiece().getSideB();
				//int stateCorner = c.getState();
				
				Corner corner = table.findCorner(A, B);				
				if(corner==null) {
					this.setPass(true);
					return ;
				}							
				
				shiftRight(playerHand[i], i);
				if(playerHand[i]==toCompare && count>0) {
					this.setPass(true);
					return ;
				}
				count++;
				i = getPlayable(table);
				if(i==-1) {
					this.setPass(true);
					return ;
				}
			}
			else break;
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
	
}
	