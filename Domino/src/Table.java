import java.util.ArrayList;
import java.util.Scanner;

public class Table {
	private int TABLE_SIDE = 50; 
	
	Piece[][] pieces = new Piece[31][31];
	String[][] print = new String[TABLE_SIDE][TABLE_SIDE];
	ArrayList<Corner> corners = new ArrayList<Corner>();
	
	public boolean isPlayable(Player[] players) {
		return true;
	}
	
	public boolean collision(Corner corner) {
		return false;
	}
	
	public void printTable() {
		for (int i = 0; i < 100; i++) {
			System.out.print(".");
		}
		
		for (int i = 0; i < 31; i++) 
			for (int j = 0; j < 31; j++) {
				
			}
		
	}
	
	public void addCorner(Corner corner) {
		corners.add(corner);
	}
	
	public void addPiece(int i, int j, Piece piece) {
		pieces[i][j] = piece;
	}
	
	
	public static void main(String[] args) {
		Table table = new Table();
		table.addPiece(15, 15, null);
		table.printTable();
	}
}

