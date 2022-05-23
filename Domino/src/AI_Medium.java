
public class AI_Medium extends AI {
	
	Piece compare;
	int count = 0;
	
	public void addPiece(Table table) {
		
		Piece[] playerHand = getPlayerHand();
		Corner corner = null;
		definePrio();
		playerHand = prioSort();
		
		int i;
		for(i = 0; i < playerHand.length; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			corner = table.findPlayableCorner(A, B);
			if(corner!=null) break;
		}
		
		if(corner==null) return ;	// if no play can be done	
		
		if(!table.addPiece(playerHand[i], corner)) {
			playerHand[i].setPrio(0);
			if(count>1 && compare==playerHand[i]) return ;
			
			if(count==0) compare = playerHand[i];
			count++;
			addPiece(table);
			return ;
		}
		printPlay(playerHand[i], corner);
		removePiece(playerHand[i]);
		playerHand = getPlayerHand();
		resetPrio();
		return ;
	}
	
	private void definePrio() {
		
		Piece[] playerHand = getPlayerHand();
		int prio = 0;
		int[] prioArr = numberOfSameSides(playerHand);
		
		for(int i = 0; i < playerHand.length; i++) { 
			if(playerHand[i].dual()) prio+=5;		
			prio+=prioArr[playerHand[i].getSideA()]; // number of common
			prio+=prioArr[playerHand[i].getSideB()];
			playerHand[i].setPrio(prio); // stacks with dual if needed
			prio=0;
		}
	}
	
}
