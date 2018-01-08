package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MultiPlayerOptions extends JPanel implements ActionListener {
	private PlayField pf;
	private Player p;
	private Ball b;
	private GUI g;

	private JPanel btnPanel;
	private JButton b_offline;
	private JButton b_online;

	private boolean EnemyBot = true;
	private boolean multiplayer = false;

	public MultiPlayerOptions() {
		super();

		b_offline = new JButton("Play Offline");
		b_online = new JButton("Player Online");

		b_offline.addActionListener(this);
		b_online.addActionListener(this);

		btnPanel = new JPanel(new FlowLayout());
		btnPanel.add(b_offline);
		btnPanel.add(b_online);

		btnPanel.setBackground(Color.black);

		this.setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.CENTER);
	//	add(pf, BorderLayout.CENTER);

	}

	public boolean isEnemyBot() {
		return EnemyBot;
	}

	public void setEnemyBot(boolean enemyBot) {
		EnemyBot = enemyBot;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b_offline) {
		}
		if (e.getSource() == b_online) {
		}

	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}

}
