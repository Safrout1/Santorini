package eg.edu.guc.santorini.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUIOptions extends JFrame implements ActionListener {
	public GUIOptions() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		
		JButton newGame = new JButton("new game");
		
		getContentPane().add(newGame);
		
		newGame.addActionListener(this);
		setVisible(true);
		setSize(1000, 1000);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("new game")) {
			new GUIGame().setVisible(true);
		}
		
	}
}
