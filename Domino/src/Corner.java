
public class Corner {
	private String direction;
	private int i;
	private int j;
	
	public Corner (String direction, int i, int j) {
		this.direction = direction;
		this.i = i;
		this.j = j;
	}
	
	public int outerSide() {	
		return 0;
	}
}
