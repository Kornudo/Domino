
public class AI_High extends AI {

	int[] counterSides = null;
	
	public boolean addPiece(Table table, Person person) {
		
		Piece[] playerHand = getPlayerHand();
		playerHand = prioSort();
		Corner corner = null;
		
		boolean handOfCounters = handOfCounters(table); // if the hand is only with counter moves
		
		int i;
		for(i = 0; i < playerHand.length; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			int prio = playerHand[i].getPrio();			
			boolean isCounterSide = (A == counterSides[i] || B == counterSides[i]) && prio > 999;
			
			if(isCounterSide) { 
				if(A==counterSides[i]) corner = table.findPlayableCounterCorner(B);
				else corner = table.findPlayableCounterCorner(A);
				
				if(corner!=null) break;
				
				if(handOfCounters) {
					if(A==counterSides[i]) corner = table.findPlayableCounterCorner(A);
					else corner = table.findPlayableCounterCorner(B);
				}
				
				if(corner!=null) break;		
				continue;
			}
			
			corner = table.findPlayableCorner(A, B);
			if(corner!=null) break;
		}
		
		if(corner==null) return false;
		
		table.addPiece(corner.getI(), corner.getJ(), playerHand[i], corner);
		resetPrio();
		return true;
	}
	
	private boolean handOfCounters(Table table) {
		
		Piece[] pH = getPlayerHand();
		int length = pH.length;
		if(pH[length].getPrio() > 999) return true;
		
		return false;
	}
	
	private void definePrio(Person person) {
		
		Piece[] playerHand = getPlayerHand();	
		int prio = 0;
		int count = 0;
		int[] prioArr = numberOfSameSides(playerHand);
		int[] prioArrPerson = numberOfSameSides(person.getPlayerHand());
		
		for(int i = 0; i < playerHand.length; i++) { 	
			if(playerHand[i].dual()) prio+=5;
			prio+=prioArr[playerHand[i].getSideA()]; // number of common
			prio+=prioArr[playerHand[i].getSideB()];
			
			for (int j = 0; j < prioArrPerson.length; i++) { // set counter priorities
				if (prioArrPerson[j]==0) { // if player has no piece
					int A = playerHand[i].getSideA();
					int B = playerHand[i].getSideB();
					if (j==A || j==B) {
						prio+=999;
						if(j==A) counterSides[count++] = A;
						else counterSides[count++] = B;
					}
				}
			}
			playerHand[i].setPrio(prio);
			prio=0;
		}
	}
	
}
