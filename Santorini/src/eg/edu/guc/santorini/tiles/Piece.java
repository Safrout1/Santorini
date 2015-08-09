package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	private int type;
	private Location location;

	public Piece() {

	}

	public Piece(int t) {
		type = t;
	}

	public Piece(int t, Location l) {
		type = t;
		location = l;
	}
	
	public final boolean equals(Piece p) {
		if (this.getClass().equals(p.getClass()) && this.getLocation().equals(p.getLocation())) {
			return true;
		}
		return false;
	}
	
	public final boolean valid(Location loc) {
		return !(loc.getX() > 4 || loc.getX() < 0 || loc.getY() > 4
				|| loc.getY() < 0);
	}

	public abstract boolean valid(Location loc1, Location loc2);

	public abstract ArrayList<Location> possibleMoves();

	public final ArrayList<Location> possiblePlacements() {
		ArrayList<Location> x = new ArrayList<Location>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					Location loc = new Location(location.getX() + i,
							location.getY() + j);
					if (valid(loc)) {
						x.add(loc);
					}
				}
			}
		}
		return x;
	}

	/*
	 * public ArrayList<Location> possibleMoves() { ArrayList<Location> x = new
	 * ArrayList<Location>(); if (type == 1) { if (location.getX() == 0) { if
	 * (location.getY() == 0) { x.add(new Location(0, 1)); x.add(new Location(1,
	 * 0)); return x; } else { if (location.getY() == 4) { x.add(new Location(0,
	 * 3)); x.add(new Location(1, 4)); return x; } else { x.add(new
	 * Location(location.getX(), location.getY() - 1)); x.add(new
	 * Location(location.getX(), location.getY() + 1)); x.add(new
	 * Location(location.getX() + 1, location.getY())); return x; } } } else {
	 * if (location.getX() == 4) { if (location.getY() == 0) { x.add(new
	 * Location(3, 0)); x.add(new Location(4, 1)); return x; } else { if
	 * (location.getY() == 4) { x.add(new Location(3, 4)); x.add(new Location(4,
	 * 3)); return x; } else { x.add(new Location(location.getX(),
	 * location.getY() - 1)); x.add(new Location(location.getX(),
	 * location.getY() + 1)); x.add(new Location(location.getX() - 1, location
	 * .getY())); return x; } } } else { if (location.getY() == 0) { x.add(new
	 * Location(location.getX(), location.getY() + 1)); x.add(new
	 * Location(location.getX() + 1, location.getY())); x.add(new
	 * Location(location.getX() - 1, location.getY())); return x; } else { if
	 * (location.getY() == 4) { x.add(new Location(location.getX(),
	 * location.getY() - 1)); x.add(new Location(location.getX() + 1, location
	 * .getY())); x.add(new Location(location.getX() - 1, location .getY()));
	 * return x; } else { x.add(new Location(location.getX() - 1, location
	 * .getY())); x.add(new Location(location.getX() + 1, location .getY()));
	 * x.add(new Location(location.getX(), location.getY() - 1)); x.add(new
	 * Location(location.getX(), location.getY() + 1)); return x; } } } } } else
	 * { if (location.getX() == 0) { if (location.getY() == 0) { x.add(new
	 * Location(1, 1)); return x; } else { if (location.getY() == 4) { x.add(new
	 * Location(1, 3)); return x; } else { x.add(new Location(location.getX() +
	 * 1, location.getY() - 1)); x.add(new Location(location.getX() + 1,
	 * location.getY() + 1)); return x; } } } else { if (location.getX() == 4) {
	 * if (location.getY() == 0) { x.add(new Location(3, 1)); return x; } else {
	 * if (location.getY() == 4) { x.add(new Location(3, 3)); return x; } else {
	 * x.add(new Location(location.getX() - 1, location .getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location .getY() + 1)); return x; } } }
	 * else { if (location.getY() == 0) { x.add(new Location(location.getX() -
	 * 1, location.getY() + 1)); x.add(new Location(location.getX() + 1,
	 * location.getY() + 1)); return x; } else { if (location.getY() == 4) {
	 * x.add(new Location(location.getX() + 1, location .getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location .getY() - 1)); return x; } else {
	 * x.add(new Location(location.getX() - 1, location .getY() + 1)); x.add(new
	 * Location(location.getX() + 1, location .getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location .getY() - 1)); x.add(new
	 * Location(location.getX() + 1, location .getY() + 1)); return x; } } } } }
	 * }
	 * 
	 * public ArrayList<Location> possiblePlacements() { ArrayList<Location> x =
	 * new ArrayList<Location>(); if (location.getX() == 0) { if
	 * (location.getY() == 0) { x.add(new Location(0, 1)); x.add(new Location(1,
	 * 0)); x.add(new Location(1, 1)); return x; } else { if (location.getY() ==
	 * 4) { x.add(new Location(0, 3)); x.add(new Location(1, 4)); x.add(new
	 * Location(1, 3)); return x; } else { x.add(new Location(location.getX() +
	 * 1, location.getY())); x.add(new Location(location.getX(), location.getY()
	 * - 1)); x.add(new Location(location.getX(), location.getY() + 1));
	 * x.add(new Location(location.getX() + 1, location.getY() - 1)); x.add(new
	 * Location(location.getX() + 1, location.getY() + 1)); return x; } } } else
	 * { if (location.getX() == 4) { if (location.getY() == 0) { x.add(new
	 * Location(3, 0)); x.add(new Location(4, 1)); x.add(new Location(3, 1));
	 * return x; } else { if (location.getY() == 4) { x.add(new Location(4, 3));
	 * x.add(new Location(3, 4)); x.add(new Location(3, 3)); return x; } else {
	 * x.add(new Location(location.getX() - 1, location.getY())); x.add(new
	 * Location(location.getX(), location.getY() + 1)); x.add(new
	 * Location(location.getX(), location.getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location.getY() + 1)); x.add(new
	 * Location(location.getX() - 1, location.getY() - 1)); return x; } } } else
	 * { if (location.getY() == 0) { x.add(new Location(location.getX(),
	 * location.getY() + 1)); x.add(new Location(location.getX() - 1,
	 * location.getY())); x.add(new Location(location.getX() + 1,
	 * location.getY())); x.add(new Location(location.getX() + 1,
	 * location.getY() + 1)); x.add(new Location(location.getX() - 1,
	 * location.getY() + 1)); return x; } else { if (location.getY() == 4) {
	 * x.add(new Location(location.getX(), location.getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location.getY())); x.add(new
	 * Location(location.getX() + 1, location.getY())); x.add(new
	 * Location(location.getX() - 1, location.getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location.getY() + 1)); return x; } else {
	 * x.add(new Location(location.getX() - 1, location.getY())); x.add(new
	 * Location(location.getX() + 1, location.getY())); x.add(new
	 * Location(location.getX(), location.getY() - 1)); x.add(new
	 * Location(location.getX(), location.getY() + 1)); x.add(new
	 * Location(location.getX() + 1, location.getY() + 1)); x.add(new
	 * Location(location.getX() - 1, location.getY() - 1)); x.add(new
	 * Location(location.getX() - 1, location.getY() + 1)); x.add(new
	 * Location(location.getX() + 1, location.getY() - 1)); return x; } } } } }
	 */

	public final int getType() {
		return type;
	}

	public final Location getLocation() {
		return location;
	}

	public final void setLocation(Location location) {
		this.location = location;
	}

}
