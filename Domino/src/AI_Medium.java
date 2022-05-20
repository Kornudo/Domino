
public class AI_Medium extends AI {
	
	public boolean addPiece(Table table) {
		
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
		
		if(corner==null) return false;	// if no play can be done	
		
		table.addPiece(corner.getI(), corner.getJ(), playerHand[i], corner);
		resetPrio();
		return true;
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
