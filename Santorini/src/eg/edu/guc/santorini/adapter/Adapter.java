package eg.edu.guc.santorini.adapter;

import java.util.ArrayList;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.utilities.Location;

public class Adapter {

    private Player p1;
    private Player p2;
    private Board board;

    public void initP1(String name, int type) {
        p1 = new Player(name, type);
    }

    public void initP2(String name, int type) {
        p2 = new Player(name, type);
    }

    public void initBoard() {
        board = new Board(p1, p2);
    }

    public String getP1Name() {
        return p1.getName();
    }

    public String getP2Name() {
        return p2.getName();
    }

    public boolean isHisTurn(int piece, Location l) {
        return (piece / 10 == 1 && p1.getT1().getLocation().equals(l)) || (piece / 10 == 1 && p1.getT2().getLocation().equals(l)) ? true
                : (piece / 10 == 2 && p2.getT1().getLocation().equals(l)) || (piece / 10 == 2 && p2.getT2().getLocation().equals(l)) ? true
                : false;
    }

    public int whosPiece(Location l) {
        if (p1.getT1().getLocation().equals(l)) {
            return 11;
        }
        if (p1.getT2().getLocation().equals(l)) {
            return 12;
        }
        if (p2.getT1().getLocation().equals(l)) {
            return 21;
        }
        if (p2.getT2().getLocation().equals(l)) {
            return 22;
        }
        return 0;
    }

    public ArrayList<Location> movesP1T1() {
        ArrayList<Location> a = p1.getT1().possibleMoves();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canMove(p1.getT1(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> movesP1T2() {
        ArrayList<Location> a = p1.getT2().possibleMoves();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canMove(p1.getT2(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> movesP2T1() {
        ArrayList<Location> a = p2.getT1().possibleMoves();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canMove(p2.getT1(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> movesP2T2() {
        ArrayList<Location> a = p2.getT2().possibleMoves();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canMove(p2.getT2(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public void moveP1T1(Location l) throws InvalidMoveException {
        board.move(p1.getT1(), l);
    }

    public void moveP1T2(Location l) throws InvalidMoveException {
        board.move(p1.getT2(), l);
    }

    public void moveP2T1(Location l) throws InvalidMoveException {
        board.move(p2.getT1(), l);
    }

    public void moveP2T2(Location l) throws InvalidMoveException {
        board.move(p2.getT2(), l);
    }

    public ArrayList<Location> PlacementsP1T1() {
        ArrayList<Location> a = p1.getT1().possiblePlacements();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canPlace(p1.getT1(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> PlacementsP1T2() {
        ArrayList<Location> a = p1.getT2().possiblePlacements();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canPlace(p1.getT2(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> PlacementsP2T1() {
        ArrayList<Location> a = p2.getT1().possiblePlacements();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canPlace(p2.getT1(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public ArrayList<Location> PlacementsP2T2() {
        ArrayList<Location> a = p2.getT2().possiblePlacements();

        ArrayList<Location> res = new ArrayList<Location>();

        for (int i = 0; i < a.size(); i++) {
            if (board.canPlace(p2.getT2(), a.get(i))) {
                res.add(a.get(i));
            }
        }
        return res;
    }

    public boolean isTaken(Location l) {
        return board.hasPiece(l);
    }
    
    public int getLevel(Location l) {
        return board.getLevel(l);
    }
}
