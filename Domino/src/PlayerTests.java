import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTests {
		
	    @Test		
		void testhandEmpty() {
			
			// testing handEmpty
			Player player = new Person();
			
			Piece[] hand = { };
			player.setPlayerHand(hand);
			
			//testing if empty
			assertTrue(player.handEmpty());
			
			Piece[] hand1 = {new Piece(0, 0), new Piece(1, 1), new Piece(2, 2), new Piece(3, 3), new Piece(4, 4), new Piece(5, 5), new Piece(6, 6)};
			player.setPlayerHand(hand1);
			
			//testing if not empty
			assertFalse(player.handEmpty());
		}
	
}
