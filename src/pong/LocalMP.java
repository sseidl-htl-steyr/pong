package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LocalMP extends JPanel {
	private PlayField pf;
	private Player p;
	private Ball b;
	private GUI g;

	private boolean EnemyBot = true;
	private boolean localMultiplayer = false;

	public LocalMP() {
		super();

		pf = new PlayField();

		this.setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(pf, BorderLayout.CENTER);

	}

	public boolean isEnemyBot() {
		return EnemyBot;
	}

	public void setEnemyBot(boolean enemyBot) {
		EnemyBot = enemyBot;
	}

	public boolean isLocalMultiplayer() {
		return localMultiplayer;
	}

	public void setLocalMultiplayer(boolean localMultiplayer) {
		this.localMultiplayer = localMultiplayer;
	}

}
