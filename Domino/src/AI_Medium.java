
public class AI_Medium extends AI {

	public boolean addPiece(Table table) {
		
		Piece[] pH = getPlayerHand();
		pH = prioSort(pH);
		Corner c = null;
		int i;
		
		for(i = 0; i < pH.length; i++) {
			c = table.findCorner(pH[i].getSideA(), pH[i].getSideB());
			if(c != null) break;
		}
		
		if(c==null) return false;
		
		table.addPiece(c.getI(), c.getJ(), pH[i], c);
		return true;
	}
	
	private Piece[] prioSort(Piece[] pH) {	
		definePrio();
		
		for (int i = 0; i < pH.length; i++) {
			for (int j = i + 1; j < pH.length; j++) {
				int temp = 0;
				if (pH[i].getPrio() < pH[j].getPrio()) {
					temp = pH[i].getPrio();
					pH[i].setPrio(pH[j].getPrio());
					pH[j].setPrio(temp);
				}
			}
		}
		return pH;
	}
	
	private void definePrio() {
		Piece[] pH = getPlayerHand();
		
		int prio = 0;
		int[] prioArr = howMany(pH);
		
		// prio :
		// duals - +5
		// many - number of common
		// counter - +inf
		for(int i = 0; i < pH.length; i++) { 
			if(pH[i].dual()) { // sets prio to duals
				prio = pH[i].getPrio() + 5;
				pH[i].setPrio(prio);
				
			}			
			prio+= prioArr[pH[i].getSideA()]; // number of common
			prio+= prioArr[pH[i].getSideB()];
			pH[i].setPrio(prio); // stacks with dual if needed
			prio = 0;
		}
	}
	
}
