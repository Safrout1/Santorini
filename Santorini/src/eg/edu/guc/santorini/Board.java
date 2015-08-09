package eg.edu.guc.santorini;

import java.util.ArrayList;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.tiles.Tile;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {
        
    
       
        
	private Cell[][] board = new Cell[SIDE][SIDE];
	private Player p1;
	private Player p2;
	private boolean turn = true;
	private Piece lastUsed;

	public Board(Player p11, Player p22) {
		p1 = p11;
		p2 = p22;

		p1.getT1().setLocation(new Location(0, 0));
		p1.getT2().setLocation(new Location(4, 1));
		p2.getT1().setLocation(new Location(0, 3));
		p2.getT2().setLocation(new Location(4, 4));

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				board[i][j] = new Cell();
			}
		}

		board[0][0].setTaken(true);
		board[4][1].setTaken(true);
		board[0][3].setTaken(true);
		board[4][4].setTaken(true);
	}

	public final void move(Piece piece, Location newLocation)
			throws InvalidMoveException {

		if ((piece.equals(p1.getT1()) || (piece.equals(p1.getT2()))) && turn
				&& p1.isMove() && canMove(piece, newLocation) && !isGameOver()) {
			board[piece.getLocation().getX()][piece.getLocation().getY()]
					.setTaken(false);
			piece.setLocation(newLocation);
			board[piece.getLocation().getX()][piece.getLocation().getY()]
					.setTaken(true);
			p1.setMove(false);
			lastUsed = piece;
		}

		else

		if ((piece.equals(p2.getT1()) || (piece.equals(p2.getT2()))) && !turn
				&& p2.isMove() && canMove(piece, newLocation) && !isGameOver()) {
			board[piece.getLocation().getX()][piece.getLocation().getY()]
					.setTaken(false);
			piece.setLocation(newLocation);
			board[piece.getLocation().getX()][piece.getLocation().getY()]
					.setTaken(true);
			p2.setMove(false);
			lastUsed = piece;
		}

		else {
			throw new InvalidMoveException();
		}
	}

	public final void place(Piece piece, Location newLocation)
			throws InvalidPlacementException {

		if ((piece.equals(p1.getT1()) || piece.equals(p1.getT2())) && turn
				&& !p1.isMove() && canPlace(piece, newLocation)
				&& piece.equals(lastUsed) && !isGameOver()) {
			board[newLocation.getX()][newLocation.getY()].addTile(new Tile());
			p1.setMove(true);
			turn = false;
		} else if ((piece.equals(p2.getT1()) || piece.equals(p2.getT2()))
				&& !turn && !p2.isMove() && canPlace(piece, newLocation)
				&& piece.equals(lastUsed) && !isGameOver()) {
			board[newLocation.getX()][newLocation.getY()].addTile(new Tile());
			p2.setMove(true);
			turn = true;
		} else {
			throw new InvalidPlacementException();
		}
	}

	public final boolean isGameOver() {
		if (hasNoMoves(p1) && hasNoMoves(p2)) {
			return true;
		}
		if (isWinner(p1) || isWinner(p2)) {
			return true;
		}
		return false;
	}

	public final boolean isWinner(Player player) {
		Player winner = getWinner();
		if (winner == null) {
			return false;
		}
		return winner.equals(player);
	}

	public final boolean hasNoMoves(Player player) {

		ArrayList<Location> locs1 = player.getT1().possibleMoves();
		boolean has = true;
		for (int i = 0; i < locs1.size(); i++) {
			if (canMove(player.getT1(), locs1.get(i))) {
				has = false;
			}
		}

		ArrayList<Location> locs2 = player.getT2().possibleMoves();
		for (int i = 0; i < locs2.size(); i++) {
			if (canMove(player.getT2(), locs2.get(i))) {
				has = false;
			}
		}

		return has;
	}

	public final Player getWinner() {
		if (board[p1.getT1().getLocation().getX()][p1.getT1().getLocation()
				.getY()].getLevel() == 3
				|| board[p1.getT2().getLocation().getX()][p1.getT2()
						.getLocation().getY()].getLevel() == 3 || (hasNoMoves(p2) && !turn)) {
			return p1;
		} else {
			if (board[p2.getT1().getLocation().getX()][p2.getT1().getLocation()
					.getY()].getLevel() == 3
					|| board[p2.getT2().getLocation().getX()][p2.getT2()
							.getLocation().getY()].getLevel() == 3 || (hasNoMoves(p1) && turn)) {
				return p2;
			} else {
				return null;
			}
		}
	}

	public final boolean canMove(Piece piece, Location location) {
		ArrayList<Location> locs = piece.possibleMoves();
		boolean found = false;
		for (int i = 0; i < locs.size(); i++) {
			if (locs.get(i).equals(location)) {
				found = true;
				break;
			}
		}
		int dif = board[location.getX()][location.getY()].getLevel()
				- board[piece.getLocation().getX()][piece.getLocation().getY()]
						.getLevel();

		if (!found || dif > 1
				|| board[location.getX()][location.getY()].getLevel() > 3
				|| board[location.getX()][location.getY()].isTaken()) {
			return false;
		}
		return true;
	}

	public final boolean canPlace(Piece piece, Location location) {
		ArrayList<Location> locs = piece.possiblePlacements();
		boolean found = false;
		for (int i = 0; i < locs.size(); i++) {
			if (locs.get(i).equals(location)) {
				found = true;
				break;
			}
		}
		if (!found || board[location.getX()][location.getY()].getLevel() >= 4
				|| board[location.getX()][location.getY()].isTaken()) {
			return false;
		}
		return true;
	}

	public Player getTurn() {
		if (turn) {
			return p1;
		} else {
			return p2;
		}
	}

	public final String[][] display() {
		String[][] res = new String[SIDE][SIDE];

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				res[i][j] = "" + board[i][j].getLevel();
			}
		}

		if (p1.getT1() instanceof Cube) {
			if (p1.getT2() instanceof Cube) {
				res[p1.getT1().getLocation().getX()][p1.getT1().getLocation()
						.getY()] += "C1";
				res[p1.getT2().getLocation().getX()][p1.getT2().getLocation()
						.getY()] += "C1";
			}
		} else if (p1.getT1() instanceof Pyramid) {
			if (p1.getT2() instanceof Pyramid) {
				res[p1.getT1().getLocation().getX()][p1.getT1().getLocation()
						.getY()] += "P1";
				res[p1.getT2().getLocation().getX()][p1.getT2().getLocation()
						.getY()] += "P1";
			}
		}

		if (p2.getT1() instanceof Cube) {
			if (p2.getT2() instanceof Cube) {
				res[p2.getT1().getLocation().getX()][p2.getT1().getLocation()
						.getY()] += "C2";
				res[p2.getT2().getLocation().getX()][p2.getT2().getLocation()
						.getY()] += "C2";
			}
		} else if (p2.getT1() instanceof Pyramid) {
			if (p2.getT2() instanceof Pyramid) {
				res[p2.getT1().getLocation().getX()][p2.getT1().getLocation()
						.getY()] += "P2";
				res[p2.getT2().getLocation().getX()][p2.getT2().getLocation()
						.getY()] += "P2";
			}
		}

		return res;
	}
        
        public boolean hasPiece(Location l) {
		return board[l.getX()][l.getY()].isTaken();
	}
        
        public int getLevel(Location l) {
            return board[l.getX()][l.getY()].getLevel();
        }
        
}
