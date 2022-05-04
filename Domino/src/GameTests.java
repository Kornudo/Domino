import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GameTests {

	@Test
	void test() {
		
		// testing findFirstPlayer
		ArrayList<Player> playerList =  new ArrayList<Player>();
		Player p1 = new Person();
		Player a1 = new AI();
		Player a2 = new AI();
		Player a3 = new AI();
		playerList.add(p1);
		playerList.add(a1);
		playerList.add(a2);
		playerList.add(a3);	
		
		ArrayList<Piece> hand1 = new ArrayList<Piece>();
		Piece p1H1 = new Piece(0, 0); 
		Piece p2H1 = new Piece(0, 1);
		Piece p3H1 = new Piece(0, 2);
		Piece p4H1 = new Piece(0, 3);
		Piece p5H1 = new Piece(0, 4);
		Piece p6H1 = new Piece(0, 5);
		Piece p7H1 = new Piece(0, 6);
		hand1.add(p1H1);
		hand1.add(p2H1);
		hand1.add(p3H1);
		hand1.add(p4H1);
		hand1.add(p5H1);
		hand1.add(p6H1);
		hand1.add(p7H1);
		
		ArrayList<Piece> hand2 = new ArrayList<Piece>();
		Piece p1H2 = new Piece(1, 1); 
		Piece p2H2 = new Piece(1, 2);
		Piece p3H2 = new Piece(1, 3);
		Piece p4H2 = new Piece(1, 4);
		Piece p5H2 = new Piece(1, 5);
		Piece p6H2 = new Piece(1, 6);
		Piece p7H2 = new Piece(2, 2);
		hand2.add(p1H2);
		hand2.add(p2H2);
		hand2.add(p3H2);
		hand2.add(p4H2);
		hand2.add(p5H2);
		hand2.add(p6H2);
		hand2.add(p7H2);
		
		ArrayList<Piece> hand3 = new ArrayList<Piece>();
		Piece p1H3 = new Piece(2, 3); 
		Piece p2H3 = new Piece(2, 4);
		Piece p3H3 = new Piece(2, 5);
		Piece p4H3 = new Piece(2, 6);
		Piece p5H3 = new Piece(3, 3);
		Piece p6H3 = new Piece(3, 4);
		Piece p7H3 = new Piece(3, 5);
		hand3.add(p1H3);
		hand3.add(p2H3);
		hand3.add(p3H3);
		hand3.add(p4H3);
		hand3.add(p5H3);
		hand3.add(p6H3);
		hand3.add(p7H3);
		
		ArrayList<Piece> hand4 = new ArrayList<Piece>();
		Piece p1H4 = new Piece(3, 6); 
		Piece p2H4 = new Piece(4, 4);
		Piece p3H4 = new Piece(4, 5);
		Piece p4H4 = new Piece(4, 6);
		Piece p5H4 = new Piece(5, 5);
		Piece p6H4 = new Piece(5, 6);
		Piece p7H4 = new Piece(6, 6);
		hand4.add(p1H4);
		hand4.add(p2H4);
		hand4.add(p3H4);
		hand4.add(p4H4);
		hand4.add(p5H4);
		hand4.add(p6H4);
		hand4.add(p7H4);
		
		assertEquals(3, findFirstPlayer());
	}

}