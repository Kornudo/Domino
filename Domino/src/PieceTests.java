import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PieceTests {

	@Test
	void testDual() {
		
		// testing dual
		Piece p1 = new Piece(6, 2);
		Piece p2 = new Piece(3, 3);
		
		// if not dual
		assertEquals(false, p1.dual());
		
		// if dual
		assertEquals(true, p2.dual());
	}

}
