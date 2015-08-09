package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;

public class Player {
	private String name;
	private Piece t1;
	private Piece t2;
	private boolean move = true;

	public final boolean isMove() {
		return move;
	}

	public final void setMove(boolean m) {
		this.move = m;
	}
	
	public Player(String s, int piece) {
		name  = s;
		if (piece == 1) {
			t1 = new Cube();
			t2 = new Cube();
		} else if (piece == 2) {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}
	}
	
	public final boolean equals(Player p) {
		if (name.equals(p.name) && t1.equals(p.t1) && t2.equals(p.t2)) {
			return true;
		}
		return false;
	}
	

	public final String getName() {
		return name;
	}

	public final Piece getT1() {
		return t1;
	}

	public final Piece getT2() {
		return t2;
	}
	
}
