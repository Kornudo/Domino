
public abstract class Player {
	private Piece[] playerHand = new Piece[7];
	
	public abstract void addPiece(Table table);
	
	public boolean handEmpty() {	
		if(playerHand.length == 0) {
			return true;
		}
		return false;
	}
	
	public void removePiece(Piece piece) {
		int playerHandLen = this.playerHand.length;
		Piece[] temp = new Piece[playerHandLen - 1];
		int count = 0;
		for(int i = 0; i < playerHandLen; i++) {
			if(this.playerHand[i]!=piece) temp[count++] = this.playerHand[i];
		}
		this.playerHand = temp;
		return ;	
	}
	
	public void printScore() {
		int sum = 0;;
		for(int i = 0; i < this.playerHand.length; i++) {
			sum+= this.playerHand[i].getSideA();
			sum+= this.playerHand[i].getSideB();
		}
		System.out.println("Pontuacao do jogador: " + this + "->" + sum);
	}
	
	public void printHand() {
		System.out.print("Jogador: " + this + "[");
		for(int i = 0; i < this.playerHand.length; i++) {
			System.out.print("<" + this.playerHand[i].getSideA() + "," + this.playerHand[i].getSideB() + ">");
			if(i+1!=this.playerHand.length) System.out.print(" ");
		}
		System.out.println("]");
	}
	
	public void printPlay(Piece piece, Corner corner) {
		//printHand();	
		System.out.println("Peca jogada: " + "<" + piece.getSideA() + "," + piece.getSideB() + ">");
		System.out.println("Corner jogado: " + "<" + corner.getPiece().getSideA() + "," + corner.getPiece().getSideB() + ">");	
	}
	
	public Piece[] getPlayerHand() {
		return this.playerHand;
	}
	
	public Piece[] setPlayerHand(Piece[] playerHand) {
		this.playerHand = playerHand;
		return playerHand;
	}

}
