package eg.edu.guc.santorini.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

import eg.edu.guc.santorini.adapter.Adapter;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class GUIBoard extends JFrame implements ActionListener, MouseListener {

    private JPanel Panel;
    private JPanel Player1;
    private JPanel Player2;
    private JPanel Turmn;
    private JButton btn;
    private JPanel turn = new JPanel();
    private JPanel grid = new JPanel();
    private JButton[][] gridd;
    private Adapter adapter;
    boolean toMove;
    private int lastPiece;
    private int lastMoved;
    private Location lastMovedLoc;
    private Location lastLoc;
    private ImageIcon p1Iconl0;
    private ImageIcon p2Iconl0;
    private ImageIcon p1Iconl1;
    private ImageIcon p2Iconl1;
    private ImageIcon p1Iconl2;
    private ImageIcon p2Iconl2;
    private ImageIcon p1Iconl3;
    private ImageIcon p2Iconl3;
    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    final private ImageIcon l1 = new ImageIcon("lol/l1.png");
    final private ImageIcon l2 = new ImageIcon("lol/2layer.png");
    final private ImageIcon l3 = new ImageIcon("lol/l3.png");
    final private ImageIcon l4 = new ImageIcon("lol/no.png");
    private boolean beforeMove = true;
    private boolean beforePlace = false;
    private boolean whileMoving = true;
    private boolean whilePlacing = false;

    public GUIBoard(int type1, int type2, Adapter a) {
        super("Simple GUI");
        setSize(1000, 1000);

        adapter = a;

        Container contentPane = getContentPane();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());

        turn.setLayout(new GridLayout(2, 1));
        contentPane.add(turn, BorderLayout.EAST);

        grid.setLayout(new GridLayout(5, 5));
        contentPane.add(grid, BorderLayout.CENTER);
        gridd = new JButton[5][5];

        icon = new ImageIcon("lol/white.png");
        icon2 = new ImageIcon("lol/greenCube.png");
        icon1 = new ImageIcon("lol/yellowCube.png");
        icon3 = new ImageIcon("lol/yellowPyr.png");
        icon4 = new ImageIcon("lol/redPyr.png");

        fillBoard(type1, type2);

        btn = new JButton(adapter.getP1Name());
        btn.setFont(new Font("player1", 30, 30));
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.pink);
        btn.addActionListener(this);
        btn.setActionCommand("Open");
        turn.add(btn);
        pack();

        JButton bt = new JButton(adapter.getP2Name());
        bt.setFont(new Font("player2", 30, 30));
        bt.setBackground(Color.blue);
        bt.setForeground(Color.pink);
        bt.addActionListener(this);
        bt.setActionCommand("Openn");
        turn.add(bt);
        pack();

        setVisible(true);
    }

    public final void fillBoard(int type1, int type2) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if ((x == 0 && y == 0) || (x == 4 && y == 1)) {
                    if (type1 == 1) {
                        p1Iconl0 = icon1;
                        p1Iconl1 = new ImageIcon("lol/1layer yell cube.png");
                        p1Iconl2 = new ImageIcon("lol/2layer cube yello.png");
                        p1Iconl3 = new ImageIcon("lol/cube3 yel.png");
                        gridd[y][x] = new JButton(icon1);
                        grid.add(gridd[y][x]);
                    } else {
                        p1Iconl0 = icon3;
                        p1Iconl1 = new ImageIcon("lol/1layer pr yello.png");
                        p1Iconl2 = new ImageIcon("lol/2layers pyramid yell.png");
                        p1Iconl3 = new ImageIcon("lol/3layers pyramid ryellow.png");
                        gridd[y][x] = new JButton(icon3);
                        grid.add(gridd[y][x]);
                    }
                } else if ((x == 0 && y == 3) || (x == 4 && y == 4)) {
                    if (type2 == 1) {
                        p2Iconl0 = icon2;
                        p1Iconl1 = new ImageIcon("lol/1layer cube green.png");
                        p1Iconl2 = new ImageIcon("lol/2layer cube green.png");
                        p1Iconl3 = new ImageIcon("lol/cube3 gren.png");
                        gridd[y][x] = new JButton(icon2);
                        grid.add(gridd[y][x]);
                    } else {
                        p2Iconl0 = icon4;
                        p1Iconl1 = new ImageIcon("lol/1layer pr red.png");
                        p1Iconl2 = new ImageIcon("lol/2layers pyramid red.png");
                        p1Iconl3 = new ImageIcon("lol/3layers pyramid red.png");
                        gridd[y][x] = new JButton(icon4);
                        grid.add(gridd[y][x]);
                    }
                } else {
                    gridd[y][x] = new JButton(icon);
                    grid.add(gridd[y][x]);
                }
                gridd[y][x].addMouseListener(this);
                gridd[y][x].setBackground(Color.WHITE);
            }
        }
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                gridd[x][y].setActionCommand("Command " + y + " " + x);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton) e.getComponent();
        String cmd = b.getActionCommand();

        if (cmd.startsWith("Command")) {
            StringTokenizer st = new StringTokenizer(cmd);
            st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int piece = adapter.whosPiece(new Location(x, y));
            if (whileMoving) {
                move(piece, x, y);
                return;
            }
            if (whilePlacing) {

            }
        }

    }

    public void move(int piece, int x, int y) {
        if (!toMove && adapter.isHisTurn(piece, new Location(x, y))) {
            toMove = true;
            lastPiece = piece;
            lastLoc = new Location(x, y);
            return;
        }
        int lx = lastLoc.getX();
        int ly = lastLoc.getY();
        if (toMove && adapter.isHisTurn(lastPiece, new Location(lx, ly))) {
            try {
                if (lastPiece == 11) {
                    move11(x, y, lx, ly);
                }
                if (lastPiece == 12) {
                    move12(x, y, lx, ly);
                }
                if (lastPiece == 21) {
                    move21(x, y, lx, ly);
                }
                if (lastPiece == 22) {
                    move22(x, y, lx, ly);
                }
                lastMovedLoc = new Location(x, y);
                lastMoved = lastPiece;
            } catch (InvalidMoveException e1) {
                System.out.println("lol");
            }
            whileMoving = false;
            whilePlacing = true;
        }
    }

    public void changePicInMove(int lx, int ly) {
        switch (adapter.getLevel(new Location(lx, ly))) {
            case 0:
                gridd[ly][lx].setIcon(icon);
                break;
            case 1:
                gridd[ly][lx].setIcon(l1);
                break;
            case 2:
                gridd[ly][lx].setIcon(l2);
                break;
            case 3:
                gridd[ly][lx].setIcon(l3);
                break;
            case 4:
                gridd[ly][lx].setIcon(l4);
                break;
        }
    }

    public void move11(int x, int y, int lx, int ly) throws InvalidMoveException {
        adapter.moveP1T1(new Location(x, y));
        toMove = false;
        lastMovedLoc = new Location(x, y);
        changePicInMove(lx, ly);
        switch (adapter.getLevel(new Location(x, y))) {
            case 0:
                gridd[y][x].setIcon(p1Iconl0);
                break;
            case 1:
                gridd[y][x].setIcon(p1Iconl1);
                break;
            case 2:
                gridd[y][x].setIcon(p1Iconl2);
                break;
            case 3:
                gridd[y][x].setIcon(p1Iconl3);
                break;
        }
        beforeMove = false;
        beforePlace = true;
    }

    public void move12(int x, int y, int lx, int ly) throws InvalidMoveException {
        adapter.moveP1T2(new Location(x, y));
        toMove = false;
        lastMovedLoc = new Location(x, y);
        changePicInMove(lx, ly);
        switch (adapter.getLevel(new Location(x, y))) {
            case 0:
                gridd[y][x].setIcon(p1Iconl0);
                break;
            case 1:
                gridd[y][x].setIcon(p1Iconl1);
                break;
            case 2:
                gridd[y][x].setIcon(p1Iconl2);
                break;
            case 3:
                gridd[y][x].setIcon(p1Iconl3);
                break;
        }
        beforeMove = false;
        beforePlace = true;
    }

    public void move21(int x, int y, int lx, int ly) throws InvalidMoveException {
        adapter.moveP2T1(new Location(x, y));
        toMove = false;
        lastMovedLoc = new Location(x, y);
        changePicInMove(lx, ly);
        switch (adapter.getLevel(new Location(x, y))) {
            case 0:
                gridd[y][x].setIcon(p2Iconl0);
                break;
            case 1:
                gridd[y][x].setIcon(p2Iconl1);
                break;
            case 2:
                gridd[y][x].setIcon(p2Iconl2);
                break;
            case 3:
                gridd[y][x].setIcon(p2Iconl3);
                break;
        }
        beforeMove = false;
        beforePlace = true;
    }

    public void move22(int x, int y, int lx, int ly) throws InvalidMoveException {
        adapter.moveP2T2(new Location(x, y));
        toMove = false;
        lastMovedLoc = new Location(x, y);
        changePicInMove(lx, ly);
        switch (adapter.getLevel(new Location(x, y))) {
            case 0:
                gridd[y][x].setIcon(p2Iconl0);
                break;
            case 1:
                gridd[y][x].setIcon(p2Iconl1);
                break;
            case 2:
                gridd[y][x].setIcon(p2Iconl2);
                break;
            case 3:
                gridd[y][x].setIcon(p2Iconl3);
                break;
        }
        beforeMove = false;
        beforePlace = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!toMove) {
            JButton b = (JButton) e.getComponent();
            String cmd = b.getActionCommand();

            if (cmd.startsWith("Command")) {
                StringTokenizer st = new StringTokenizer(cmd);
                st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int piece = adapter.whosPiece(new Location(x, y));
                if (!toMove && beforeMove) {
                    highLightMove(piece);
                    return;
                } else if (beforePlace) {
                    int lx = lastMovedLoc.getX();
                    int ly = lastMovedLoc.getY();
                    System.out.println(!adapter.isHisTurn(lastMoved, new Location(x, y)));
                    System.out.println(!lastMovedLoc.equals(new Location(x, y)));
                    System.out.println(adapter.isTaken(new Location(x, y)));
                    if ((!adapter.isHisTurn(lastMoved, new Location(x, y)) || !lastMovedLoc.equals(new Location(x, y))) && adapter.isTaken(new Location(x, y))) {
                        highLightMove(piece);
                        return;
                    } else if (lastMovedLoc.equals(new Location(x, y))) {

                        HighLightPlacements(lastMoved);
                        return;
                    }

                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton b = (JButton) e.getComponent();
        String cmd = b.getActionCommand();

        if (cmd.startsWith("Command")) {
            StringTokenizer st = new StringTokenizer(cmd);
            st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int piece = adapter.whosPiece(new Location(x, y));

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!adapter.isTaken(new Location(i, j))) {
                        gridd[j][i].setBackground(Color.WHITE);
                        gridd[j][i].setIcon(icon);
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void HighLightPlacements(int piece) {
        if (piece == 11) {
            ArrayList<Location> a = adapter.PlacementsP1T1();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.red);
                gridd[xx][yy].setIcon(new ImageIcon("lol/red.png"));
            }
            return;
        }
        if (piece == 12) {
            ArrayList<Location> a = adapter.PlacementsP1T2();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.red);
                gridd[xx][yy].setIcon(new ImageIcon("lol/red.png"));
            }
            return;
        }
        if (piece == 21) {
            ArrayList<Location> a = adapter.PlacementsP2T1();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.red);
                gridd[xx][yy].setIcon(new ImageIcon("lol/red.png"));
            }
            return;
        }
        if (piece == 22) {
            ArrayList<Location> a = adapter.PlacementsP2T2();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.red);
                gridd[xx][yy].setIcon(new ImageIcon("lol/red.png"));
            }
            return;
        }
    }

    public void highLightMove(int piece) {
        if (piece == 11) {
            ArrayList<Location> a = adapter.movesP1T1();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.yellow);
                gridd[xx][yy].setIcon(new ImageIcon("lol/yellow.png"));
            }
            return;
        }
        if (piece == 12) {
            ArrayList<Location> a = adapter.movesP1T2();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.yellow);
                gridd[xx][yy].setIcon(new ImageIcon("lol/yellow.png"));
            }
            return;
        }
        if (piece == 21) {
            ArrayList<Location> a = adapter.movesP2T1();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.yellow);
                gridd[xx][yy].setIcon(new ImageIcon("lol/yellow.png"));
            }
            return;
        }
        if (piece == 22) {
            ArrayList<Location> a = adapter.movesP2T2();
            for (int i = 0; i < a.size(); i++) {
                int xx = a.get(i).getY();
                int yy = a.get(i).getX();
                gridd[xx][yy].setBackground(Color.yellow);
                gridd[xx][yy].setIcon(new ImageIcon("lol/yellow.png"));
            }
            return;
        }
    }

}
