package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {
	
	public final boolean valid(Location loc1, Location loc2) {
		return  ((loc2.getX() == loc1.getX() + 1 && loc2.getY() == loc1.getY())
				|| (loc2.getX() == loc1.getX() - 1 && loc2.getY() == loc1.getY())
				|| (loc2.getX() == loc1.getX() && loc2.getY() == loc1.getY() + 1)
				|| (loc2.getX() == loc1.getX() && loc2.getY() == loc1.getY() - 1));
	}
	
	
	public final ArrayList<Location> possibleMoves() {
		ArrayList<Location> x = new ArrayList<Location>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					Location loc = new Location(getLocation().getX() + i,
							getLocation().getY() + j);
					if (valid(loc) && valid(getLocation(), loc)) {
						x.add(loc);
					}
				}
			}
		}
		return x;
	}
		
	
}
