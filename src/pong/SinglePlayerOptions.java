package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SinglePlayerOptions extends JPanel implements ActionListener{
	private GUI fr;
	private PlayField pf;
	private JPanel canvas;
	private Player p;
	private Ball b;
	
	
	private JButton b_easy;
	private JButton b_medium;
	private JButton b_hard;

	private boolean EnemyBot = true;
	private String difficulty;
	
	public SinglePlayerOptions(GUI fr) {
		super();
		this.fr = fr;
		this.setLayout(new FlowLayout());
		
		pf = new PlayField(fr);
		this.BotOptions();
	}
	
	public void BotOptions() {
		canvas = new JPanel();
		canvas.setLayout(new BorderLayout());
		canvas.add(pf, BorderLayout.CENTER);
		fr.contentPane.add(canvas);
		
		fr.cl.addLayoutComponent(canvas, "canvas1");
		
		b_easy = new JButton("Easy");
		b_medium = new JButton("Medium");
		b_hard = new JButton("Hard");
		
		b_easy.addActionListener(this);
		b_medium.addActionListener(this);
		b_hard.addActionListener(this);
		
		
		this.setBackground(Color.BLACK);
		this.add(b_easy);
		this.add(b_medium);
		this.add(b_hard);
//		this.setBackground(Color.black);
	}

	public boolean isEnemyBot() {
		return EnemyBot;
	}

	public void setEnemyBot(boolean enemyBot) {
		EnemyBot = enemyBot;
	}
	

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_easy) {
			difficulty = "easy";
		}
		if(e.getSource() == b_medium) {
			difficulty = "medium";
		}
		if(e.getSource() == b_hard) {
			difficulty = "hard";
		}
		pf.requestFocus();

		fr.cl.show(fr.getContentPane(), "canvas1");
		
	}
	
}
