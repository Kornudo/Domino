/**
 * Represents the Medium level of AI
 * @author José Lopes and João Leandro
 * 
 */
public class AI_Medium extends AI {
	
	Corner corner = null;
	
	/**
	 * Sets up the best piece (the one with most priority) to add on the game table
	 * 
	 * @param table game table itself
	 * 
	 * @post updates the player hand without the added piece
	 * @post updates the pieces priority accordingly with the game turn
	 */
	public void addPiece(Table table) {
		
		Piece[] playerHand = getPlayerHand();
		Piece compare = null;
		int count = 0;
		definePrio();
		
		int i = checkPlayables(table);
		if(i==-1) return ; 
		
		while(true) {
			if (!table.addPiece(playerHand[i], corner)) {
				playerHand[i].setPrio(0);
				playerHand = setPlayerHand(playerHand);
				
				if (count > 0 && compare == playerHand[i]) return;

				if (count == 0) compare = playerHand[i];
				count++;
				
				i = checkPlayables(table);
				if(i==-1) return ;
			}
			else break;
		}
		
		printPlay(playerHand[i], corner);
		removePiece(playerHand[i]);
		playerHand = getPlayerHand();
		resetPrio();
		return ;
	}
	
	private int checkPlayables(Table table) {
		Piece[] playerHand = getPlayerHand();
		playerHand = setPlayerHand(prioSort());
		
		int i;
		for(i = 0; i < playerHand.length; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			corner = table.findPlayableCorner(A, B);
			if(corner!=null) break;
		}
		
		if(corner==null) return -1;
		else return i;
	}
	
	private void definePrio() {
		
		Piece[] playerHand = getPlayerHand();
		int prio = 0;
		int[] prioArr = numberOfSameSides(playerHand);
		
		for(int i = 0; i < playerHand.length; i++) { 
			if(playerHand[i].dual()) prio+=20;		
			prio+=prioArr[playerHand[i].getSideA()]+7; // number of common
			prio+=prioArr[playerHand[i].getSideB()]+7;
			prio+=playerHand[i].getSideA();
			prio+=playerHand[i].getSideB();
			playerHand[i].setPrio(prio); // stacks with dual if needed
			prio=0;
		}
	}
	
}
