import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


 //done I think
class PlayerTests {
	@Test
	void testInHand() {
		Player jogador = new Person();
		Player AI1 = new AI();
		Player AI2 = new AI();
		Player AI3 = new AI();
		Player AI4 = new AI();
		Table table = new Table();
		Player[] jogadores1 = {jogador, AI1, AI2, AI3, AI4};
		
		Game game1 = new Game(table, jogadores1);
		game1.createDeck();
		
		Piece[] hand = {new Piece(0, 0), new Piece(1, 1), new Piece(2, 2), new Piece(3, 3), new Piece(4, 4), new Piece(5, 5), new Piece(6, 6)};
		jogadores1[0].setPlayerHand(hand);
		assertTrue(((Person)jogadores1[0]).inHand(0, 0));
		assertFalse(((Person)jogadores1[0]).inHand(0, 1));
	}
	
}
