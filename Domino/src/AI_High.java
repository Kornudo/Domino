
public class AI_High extends AI {

	public boolean addPiece(Table table, Person person) {
		
		Piece[] pH = getPlayerHand();
		pH = prioSort(pH, person);
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
	
	private Piece[] prioSort(Piece[] pH, Person person) {	
		definePrio(person);
		
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
	
	private void definePrio(Person person) {
		Piece[] pH = getPlayerHand();
		
		int prio = 0;
		int[] prioArr = howMany(pH);
		int[] prioArrPerson = howMany(person.getPlayerHand());
		
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
			
			if (pH[i].getPrio() != 999) { // condition for not updating this prio along the game continues
				for (int j = 0; j < prioArrPerson.length; i++) { // set counter priorities
					if (prioArrPerson[j] == 0) { // if player has no piece
						int A = pH[i].getSideA();
						int B = pH[i].getSideB();
						if (j == A || j == B) {
							pH[i].setPrio(999);
						}
					}
				}
			}
		}
	}
	
}
