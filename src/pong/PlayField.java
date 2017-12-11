package pong;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import pong.GUI;


public class PlayField extends JPanel{
	Graphics g;
	private GUI fr;
	private int border_to_player = 20;
	private int playerHeight = 100;
	private int score = 10;
	

	public PlayField(GUI fr) {
		super();
		this.fr = fr;
	}
	
	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		
		g.setColor(Color.BLACK);

		/* START POSITION */
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(border_to_player, (getHeight()-playerHeight)/2, 10, playerHeight);  //Player on the left side
		
		g.fillRect((getWidth()-border_to_player)-10, (getHeight()-playerHeight)/2, 10, playerHeight);	//Player on the right side
		
		g.fillRect(getWidth()/2, 70, 20, 20); //Ball
		
//		drawScore(g, score);
//		repaint();
	}
	
	public void movePlayer() {
		
	}
	
	/*
	public void drawScore(Graphics g, int score) {
		if(score == 0) {
			g.fillOval(getWidth()/2-80, 20, 40, 60);
			g.fillOval(getWidth()/2+60, 20, 40, 60);
			g.setColor(Color.BLACK);
			g.fillOval(getWidth()/2-70, 30, 20, 40);
			g.fillOval(getWidth()/2+70, 30, 20, 40);
		}
		if(score == 1) {
			g.setColor(Color.WHITE);
			g.fillRect(getWidth()/2+60, 20, 10, 60);
			g.fillOval(getWidth()/2-80, 20, 40, 60);
			g.setColor(Color.BLACK);
			g.fillOval(getWidth()/2-70, 30, 20, 40);
		}
		if(score == 10) {
			g.setColor(Color.WHITE);
			g.fillRect(getWidth()/2-80, 20, 10, 60);
			g.fillOval(getWidth()/2+80, 20, 40, 60);
			g.setColor(Color.BLACK);
			g.fillOval(getWidth()/2+90, 30, 20, 40);
		}
	}
	*/
}
