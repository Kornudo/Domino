/**
 * Represents a domino Piece
 * Piece has two sides and a priority in case of the player
 * being an AI
 * 
 * @author José Lopes and João Leandro
 * 
 */
public class Piece {
	private int sideA;
	private int sideB;
	private int priority;
	
	/**
	 * Constructor, sets sides and priority to 0
	 * 
	 * @param sideA one side of piece
	 * @param sideB one side of piece
	 * 
	 * @pre 0<=sideA<=6
	 * @pre 0<=sideB<=6
	 * 
	 */
	public Piece(int sideA, int sideB) {
		this.sideA = sideA;
		this.sideB = sideB;
		this.priority = 0;
	}
	
	/**
	 * Checks if the Piece is a dual
	 * 
	 * @return boolean value
	 */
	public boolean dual() {
		if(sideA == sideB) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the value of sideA
	 * 
	 * @return int sideA
	 */	
	public int getSideA() {
		return sideA;
	}
	
	/**
	 * Gets the value of sideB
	 * 
	 * @return int sideB
	 */	
	public int getSideB() {
		return sideB;
	}
	
	/**
	 * Gets the value of prio
	 * 
	 * @return int prio
	 */	
	public int getPrio() {
		return priority;
	}
	
	/**
	 * Sets a new value of Piece prio
	 * 
	 * @param prio new prio to replace the old one
	 * 
	 * @return int prio
	 */	
	public int setPrio(int prio) {
		this.priority = prio;
		return priority;
	}
	
}
