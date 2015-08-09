package eg.edu.guc.santorini;

import java.util.Stack;

import eg.edu.guc.santorini.tiles.Tile;

public class Cell {
	private Stack<Tile> level = new Stack<Tile>();
	private boolean taken;
	
	public final void addTile(Tile t) {
		level.push(t);
	}
	
	public final int getLevel() {
		return level.size();
	}
	
	public final boolean isTaken() {
		return taken;
	}
	
	public final void setTaken(boolean taken) {
		this.taken = taken;
	}
}

