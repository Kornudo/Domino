import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonTests {
		
		@Test	
		void testInHand() {
			
			// testing inHand
			Person player = new Person();			
			Piece[] hand = {new Piece(0, 0), new Piece(1, 1), new Piece(2, 2), new Piece(3, 3), new Piece(4, 4), new Piece(5, 5), new Piece(6, 6)};
			player.setPlayerHand(hand);
			
			// if in hand
			assertTrue(player.inHand(0, 0));
			
			// if not in hand
			assertFalse(player.inHand(0, 1));
		}

}
