import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class TableTests {
	
	@Test
	void testIsPlayable() {
		
		// testing isPlayable
		Player jogador = new Person();
		Player AI1 = new AI();
		Player AI2 = new AI();
		Player AI3 = new AI();
		Table table = new Table();
		table.addPiece(0, 0, new Piece(6, 6));
		table.addCorner(new Corner(null, 0, 0));
		
		Player[] jogadores1 = {jogador, AI1, AI2, AI3};
		
		Piece[] hand = {new Piece(0, 0)};
		jogadores1[0].setPlayerHand(hand);
		jogadores1[1].setPlayerHand(hand);
		jogadores1[2].setPlayerHand(hand);
		jogadores1[3].setPlayerHand(hand);
		
		assertFalse(table.isPlayable(jogadores1));
		
		hand[0] = new Piece(5, 6);
		jogadores1[0].setPlayerHand(hand);
		assertTrue(table.isPlayable(jogadores1));
		
		hand[0] = new Piece(6, 5);
		jogadores1[0].setPlayerHand(hand);
		assertTrue(table.isPlayable(jogadores1));
	}
	
	void testCollision() {
		
		// testing collision
		Table table = new Table();
		table.addPiece(15, 15, null);
		table.addPiece(14, 15, null);
		table.addPiece(15, 16, null);
		Corner corner = new Corner("Left", 14, 16);
		table.addCorner(corner);
		assertFalse(table.collision(corner));
		
		table.addPiece(14, 16, null);
		assertFalse(table.collision(corner));
	}
}
