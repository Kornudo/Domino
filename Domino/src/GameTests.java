import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GameTests {

	@Test
	void testFindFirstPlayer() {
		
		// testing findFirstPlayer
		Table table = new Table();
	    Player[] players = {new Person(), new AI(), new AI(), new AI()};
	    Game game = new Game(table, players);
	    game.createDeck(); // do I need to initialize this?
	    
	    Piece[] hand0 = {new Piece(0, 0), new Piece(0, 1), new Piece(0, 2), new Piece(0, 3), new Piece(0, 4), new Piece(0, 5), new Piece(0, 6)};
	    Piece[] hand1 = {new Piece(1, 1), new Piece(1, 2), new Piece(1, 3), new Piece(1, 4), new Piece(1, 5), new Piece(1, 6), new Piece(2, 2)};
	    Piece[] hand2 = {new Piece(2, 3), new Piece(2, 4), new Piece(2, 5), new Piece(2, 6), new Piece(3, 3), new Piece(3, 4), new Piece(3, 5)};
	    Piece[] hand3 = {new Piece(3, 6), new Piece(4, 4), new Piece(4, 5), new Piece(4, 6), new Piece(5, 5), new Piece(5, 6), new Piece(6, 6)};
	    
	    players[0].setPlayerHand(hand0);
	    players[1].setPlayerHand(hand1);
	    players[2].setPlayerHand(hand2);
	    players[3].setPlayerHand(hand3);
	    
	    // test position 3 of players array
		assertEquals(3, game.findFirstPlayer());
		
		players[0].setPlayerHand(hand3);
	    players[1].setPlayerHand(hand1);
	    players[2].setPlayerHand(hand2);
	    players[3].setPlayerHand(hand0);
	    
	    // test position 0 of players array
	    assertEquals(0, game.findFirstPlayer());
	}
	
	@Test
	void testDealHand() {
		Table table = new Table();
		Player[] jogadores1 = {new Person(), new AI(), new AI(), new AI()};
		
		Game game1 = new Game(table, jogadores1);
		game1.createDeck();
		game1.dealHand();
		// check if all players have 7 pieces
		for (int i = 0; i < 4; i++) {
			Piece[] hand = jogadores1[i].getPlayerHand();
			assertEquals(7, hand.length);
		}
		
		
		// check if pieces do not repeat between players
		for (int u = 0; u < 4; u++)
			for (int o = 0; o < 4; o++) {
				if (u == o) break;
				for(int i = 0; i < 7; i++) 
					for (int k = 0; k < 7; k++)
						if (jogadores1[u].getPlayerHand()[i].getSideA() == jogadores1[o].getPlayerHand()[k].getSideA() 
							&& jogadores1[u].getPlayerHand()[i].getSideB() == jogadores1[o].getPlayerHand()[k].getSideB())
								fail();
			}
	}
	
	@Test
	void endGame() { 
		Table table = new Table();
		Player[] jogadores1 = {new Person(), new AI(), new AI(), new AI()};
		
		// one player has a piece but cant play it; game ends
		Piece[] hand = {new Piece(5, 3)};
		jogadores1[0].setPlayerHand(hand);
		
		table.addPiece(23, 23, new Piece(6, 6));
		table.addCorner(new Corner("Left", 22, 23));
		
		Game game1 = new Game(table, jogadores1);
		
		assertTrue(game1.endGame());
		
		// there are possible plays; game continues
		hand[0] = new Piece(6, 3);
		jogadores1[0].setPlayerHand(hand);
		game1 = new Game(table, jogadores1);
		
		assertFalse(game1.endGame());
		
		// all hands are empty; game ends
		hand[0] = null;
		jogadores1[0].setPlayerHand(hand);
		game1 = new Game(table, jogadores1);
		
		assertTrue(game1.endGame());
	}

}
