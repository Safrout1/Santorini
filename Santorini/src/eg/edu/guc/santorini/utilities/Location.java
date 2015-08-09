package eg.edu.guc.santorini.utilities;

public class Location {
	private int x;
	private int y;
	public Location(int xx, int yy) {
		x = xx;
		y = yy;
	}
	public final int getX() {
		return x;
	}
	public final int getY() {
		return y;
	}
	public final boolean equals(Location l) {
		return x == l.x && y == l.y;
	}
}

