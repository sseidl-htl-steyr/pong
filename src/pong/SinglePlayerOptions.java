package pong;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SinglePlayerOptions extends JPanel{
	private GUI fr;
	private PlayField pf;
	private JPanel canvas;
	
	private JButton b_easy;
	private JButton b_medium;
	private JButton b_hard;

	private boolean EnemyBot;
	
	public SinglePlayerOptions(GUI fr) {
		super();
		this.fr = fr;
		this.setLayout(new BorderLayout());
		
		pf = new PlayField(fr);
		this.BotOptions();
	}
	
	public void BotOptions() {
		canvas = new JPanel();
		canvas.setLayout(new BorderLayout());
		canvas.add(pf, BorderLayout.CENTER);
		fr.contentPane.add(canvas);
		
		fr.cl.addLayoutComponent(canvas, "canvas1");
		fr.cl.show(fr.getContentPane(), "canvas1");
//		this.setBackground(Color.black);
	}

	public boolean isEnemyBot() {
		return EnemyBot;
	}

	public void setEnemyBot(boolean enemyBot) {
		EnemyBot = enemyBot;
	}
	
}
