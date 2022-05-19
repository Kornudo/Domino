import java.util.Random;

public class AI_Low extends AI {
	private Random rand = new Random();
	
	public boolean addPiece(Table table) {
		
		Piece[] pH = getPlayerHand();
		Piece[] playables = null;
		Corner c1;
		Corner c2;
		
		int count = 0;
		for (int i = 0; i < pH.length; i++) { // get the pieces that can be played on the round
			c1 = table.findCornerAI(pH[i].getSideA());
			c2 = table.findCornerAI(pH[i].getSideB());
			
			if(c1 != null && c2 != null) {
				playables[count++] = pH[i].getSideA(); 
				playables[count++] = pH[i].getSideB();
			}
			
			if(c1 != null) 
				playables[count++] = pH[i].getSideA(); 
			if(c2 != null)
				playables[count++] = pH[i].getSideB(); 
		
		}
		
		if(playables == null) return false; // if there's no possible move
		
		@SuppressWarnings("unused")
		int r = rand.nextInt(count);
		c = table.findCorner(playables[r].getSideA(), playables[r].getSideB());
		
		table.addPiece(c.getI(), c.getJ(), playables[r], c);
		return true;	
	}
	
	private Corner[] givePlayableCorners(Table table) {
		
		for(int i = 0; i < table.get)
		
	}
	
}
