package eg.edu.guc.santorini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import eg.edu.guc.santorini.adapter.Adapter;

//import javax.swing.border.Border;

@SuppressWarnings("serial")
public class GUIGame extends JFrame implements ActionListener {
	JPanel mainPanel;
	JPanel newGamePanel;
	JPanel OptionPanel;
	private JButton btn;

	public GUIGame() {
		super("Simple GUI");

		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		JPanel x = new JPanel();
		x.setLayout(new FlowLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn = new JButton("New Game");
		btn.setFont(new Font("New Game", 40, 40));
		btn.setBackground(Color.blue);
		btn.setForeground(Color.pink);
		btn.addActionListener(this);
		btn.setActionCommand("Open");
		add(btn , BorderLayout.WEST);
		pack();

		JLabel y = new JLabel("Santorini");
		y.setFont(new Font("Santorini", 60, 60));
		y.setForeground(Color.blue);
		x.setBackground(Color.PINK);
		x.add(y, BorderLayout.CENTER);
		contentPane.add(x, BorderLayout.NORTH);

		JButton button = new JButton("Options");
		button.setFont(new Font("Options", 40, 40));
		button.setBackground(Color.BLUE);
		button.setForeground(Color.pink);
		button.addActionListener(this);
		button.setActionCommand("Openn");
		add(button , BorderLayout.EAST);

		setVisible(true);
		setSize(1000, 1000);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Open")) {
			dispose();
			GUIPlayer Player1 = new GUIPlayer("Player 1", new Adapter());
			// Player Player2 = new Player("Player 2");
		}

		else if (cmd.equals("Openn")) {
			dispose();
			new GUIOptions().setVisible(true);
		} 

	}

	public static void main(String[] args) {
		new GUIGame().setVisible(true);
	}
}
