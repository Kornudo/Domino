
public class AI extends Player {
	
	protected int[] howMany(Piece[] pH) {
		int[] prioArr = new int[7];
		
		for(int i = 0; i < pH.length; i++) {
			prioArr[pH[i].getSideA()]++;
			prioArr[pH[i].getSideB()]++;
		}
		return prioArr;
	}

}
