import java.util.Random;

public class AI_Medium extends AI {
	private Random rand = new Random();
	
	public boolean addPiece(Table table) {
		
		Piece[] pH = getPlayerHand();
		pH = prioSort(pH);
		Corner c1 = null;
		Corner c2 = null;
 		int i;
		
		for(i = 0; i < pH.length; i++) {
			c1 = table.findCornerAI(pH[i].getSideA());
			c2 = table.findCornerAI(pH[i].getSideB());
			if(c1 != null || c2 != null) break;
		}
		
		if(c1==null && c2==null) return false;	// if no play can be done	
		
		int random = rand.nextInt(1);
		if(c1!=null && c2!=null) // if can be played with both sides
			if(random==1)
				c1 = table.findCornerAI(pH[i].getSideA());
			else 
				c2 = table.findCornerAI(pH[i].getSideB());
		
		if(c1 != null) // only 1 side can be played
			table.addPiece(c1.getI(), c1.getJ(), pH[i], c1);
		
		if(c2 != null)
			table.addPiece(c2.getI(), c2.getJ(), pH[i], c2);
		
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
