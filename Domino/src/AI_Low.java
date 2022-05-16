import java.util.Random;

public class AI_Low extends AI {
	private Random rand = new Random();
	
	public boolean addPiece(Table table) {
		
		Piece[] pH = getPlayerHand();
		Piece[] playables = null;
		Corner c;
		
		int count = 0;
		for (int i = 0; i < pH.length; i++) { // get the pieces that can be played on the round
			c = table.findCorner(pH[i].getSideA(), pH[i].getSideB());
			if(c != null) {
				playables[count++] = pH[i]; 
			}
		}
		
		if(playables == null) return false; // if there's no possible move
		
		@SuppressWarnings("unused")
		int r = rand.nextInt(count);
		c = table.findCorner(playables[r].getSideA(), playables[r].getSideB());
		
		table.addPiece(c.getI(), c.getJ(), playables[r], c);
		return true;	
	}
	
}
