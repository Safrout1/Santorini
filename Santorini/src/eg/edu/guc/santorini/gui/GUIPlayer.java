package eg.edu.guc.santorini.gui;

//import java.awt.Color;
import java.awt.*;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import eg.edu.guc.santorini.adapter.Adapter;

//import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GUIPlayer extends JFrame implements ActionListener {

	private static int count = 0;
	private static int type1 = 0;
	private static int type2 = 0;
	private Adapter adapter;
	private String name;

	public GUIPlayer(String x, Adapter a) {

		super("Another GUI");

		adapter = a;

		count++;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());

		JLabel label = new JLabel(x);
		label.setFont(new Font(x, 30, 30));
		label.setBackground(Color.blue);
		label.setForeground(Color.pink);
		getContentPane().add(label, BorderLayout.NORTH);
		pack();

		String[] option = { "OK" };
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("Enter Your Name");
		JTextField name1 = new JTextField(10);
		panel.add(lbl);
		panel.add(name1);
		int selectedOption = JOptionPane.showOptionDialog(null, panel,
				"the name", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

		if (selectedOption == 0) {
			name = name1.getText();
		}

		JPanel buttons = new JPanel();
		getContentPane().add(buttons, BorderLayout.SOUTH);
		buttons.setLayout(new FlowLayout());

		JButton button = new JButton("Cube");
		button.setFont(new Font("Cube", 30, 30));
		button.setBackground(Color.blue);
		button.setForeground(Color.pink);
		button.addActionListener(this);

		buttons.add(button);

		JButton button2 = new JButton("Pyramd");
		button2.setFont(new Font("Pyramd", 30, 30));
		button2.setBackground(Color.blue);
		button2.setForeground(Color.pink);
		button2.addActionListener(this);

		buttons.add(button2);

		setVisible(true);
		setSize(1000, 1000);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Cube")) {

			dispose();
			if (count == 1) {
				type1 = 1;

				adapter.initP1(name, type1);

				new GUIPlayer("Player 2", adapter);
			} else if (count > 1) {
				type2 = 1;

				adapter.initP2(name, type2);
				adapter.initBoard();
				GUIBoard x = new GUIBoard(type1, type2, adapter);
			}

		}
		if (cmd.equals("Pyramd")) {

			dispose();

			if (count == 1) {
				type1 = 2;
				adapter.initP1(name, type1);
				new GUIPlayer("Player 2", adapter);
			} else if (count > 1) {
				type2 = 2;
				adapter.initP2(name, type2);
				adapter.initBoard();
				GUIBoard x = new GUIBoard(type1, type2, adapter);
			}

		}

	}

}
