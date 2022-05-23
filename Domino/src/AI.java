
public abstract class AI extends Player {
	
	public abstract void addPiece(Table table);
	
	
	
	protected Piece[] prioSort() {	
		Piece[] playerHand = getPlayerHand();
		for (int i = 0; i < playerHand.length; i++) {
			for (int j = i + 1; j < playerHand.length; j++) {
				int temp;
				if (playerHand[i].getPrio() < playerHand[j].getPrio()) {
					temp = playerHand[i].getPrio();
					playerHand[i].setPrio(playerHand[j].getPrio());
					playerHand[j].setPrio(temp);
				}
			}
		}
		return playerHand;
	}
	
	protected int[] numberOfSameSides(Piece[] playerHand) {
		int[] prioArr = new int[7];
		
		for(int i = 0; i < playerHand.length; i++) {
			if(playerHand[i].dual()) {
				prioArr[playerHand[i].getSideA()]++;
				continue;
			}
			prioArr[playerHand[i].getSideA()]++;
			prioArr[playerHand[i].getSideB()]++;
		}
		return prioArr;
	}
	
	protected void resetPrio() {
		Piece[] playerHand = getPlayerHand();
		
		for(int i = 0; i < playerHand.length; i++)
			playerHand[i].setPrio(0);
 		
		return ;
	}

}
