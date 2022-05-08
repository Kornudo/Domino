import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PieceTests {

	@Test
	void testDual() {
		
		Piece p1 = new Piece(6, 2);
		Piece p2 = new Piece(3, 3);
		
		assertEquals(false, p1.dual());
		assertEquals(true, p2.dual());
	}

}
