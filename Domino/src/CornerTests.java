import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CornerTests {

	@Test
	void testOuterSide() {
		
		Piece p1 = new Piece(6, 2);
		Piece p2 = new Piece(3, 5);
		Piece p3 = new Piece(1, 4);
		Piece p4 = new Piece(3, 2);
		
		Corner c1 = new Corner("left", 10, 5);
		Corner c2 = new Corner("right", 1, 0);
		Corner c3 = new Corner("up", 5, 8);
		Corner c4 = new Corner("down", 7, 3);
		
		// sideA -> left/up
		// sideB -> right/down
		assertEquals(p1.getSideA(), c1.outerSide());
		assertEquals(p2.getSideB(), c2.outerSide());
		assertEquals(p3.getSideA(), c3.outerSide());
		assertEquals(p4.getSideB(), c4.outerSide());
	}

}
