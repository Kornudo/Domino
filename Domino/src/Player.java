/**
 * Represents a player in the game being a human or AI
 * It's responsible for managing the player hand and the pieces in it
 * 
 * @author José Lopes and João Leandro
 * 
 */
public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	
	public abstract void addPiece(Table table);
	
	/**
	 * Checks if there's no pieces in hand
	 * 
	 * @pre playerHand!=null
	 * 
	 * @return boolean
	 */
	public boolean handEmpty() {	
		if(playerHand.length == 0) {
			return true;
		}
		return false;
	}

	protected void removePiece(Piece piece) {
		int playerHandLen = this.playerHand.length;
		Piece[] temp = new Piece[playerHandLen - 1];
		int count = 0;
		for(int i = 0; i < playerHandLen; i++) {
			if(this.playerHand[i]!=piece) temp[count++] = this.playerHand[i];
		}
		this.playerHand = temp;
		return ;	
	}
	
	/**
	 * Prints score
	 * 
	 * @post print to console
	 */
	public void printScore() {
		int sum = 0;;
		for(int i = 0; i < this.playerHand.length; i++) {
			sum+= this.playerHand[i].getSideA();
			sum+= this.playerHand[i].getSideB();
		}
		System.out.println("Pontuacao do jogador: " + this + "->" + sum);
	}
	
	/**
	 * Prints player hand
	 * 
	 * @post print to console
	 */
	public void printHand() {
		System.out.print("Jogador: " + this + "[");
		for(int i = 0; i < this.playerHand.length; i++) {
			System.out.print("<" + this.playerHand[i].getSideA() + "," + this.playerHand[i].getSideB() + ">");
			if(i+1!=this.playerHand.length) System.out.print(" ");
		}
		System.out.println("]");
	}
	
	/**
	 * Prints not only the player hand but the sucess play aswell
	 * 
	 * @param piece added piece to the table
	 * @param corner corner which the piece was add
	 * 
	 * @post print to console
	 */
	public void printPlay(Piece piece, Corner corner) {
		printHand();	
		System.out.println("Peca jogada: " + "<" + piece.getSideA() + "," + piece.getSideB() + ">");
		System.out.println("Corner jogado: " + "<" + corner.getPiece().getSideA() + "," + corner.getPiece().getSideB() + ">");	
	}
	
	/**
	 * Gets the current player hand
	 * 
	 * @return Piece[] playerHand
	 */	
	public Piece[] getPlayerHand() {
		return this.playerHand;
	}
	
	/**
	 * Sets the current player hand with a new one
	 * 
	 * @param playerHand new playerHand to replace the old one
	 * 
	 * @return Piece[] playerHand
	 */	
	public Piece[] setPlayerHand(Piece[] playerHand) {
		this.playerHand = playerHand;
		return playerHand;
	}

}
