package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import pong.GUI;


public class PlayField extends JPanel implements KeyListener{
	Graphics g;
	private GUI fr;
	private Player p;
	private int border_to_player = 20;
	private int playerHeight = 100;
	private int score = 10;
	private String difficulty;
	private int playerPosition_start = getHeight()/2-playerHeight/2;
	
	private boolean movingUp = false;
	private boolean movingDown = false;


	public PlayField(GUI fr) {
		super();
		this.fr = fr;
		p = new Player();
	}
	
	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		
		/* START POSITION */

		g.fillRect(border_to_player, p.getPlayerPosition(), 10, playerHeight);  //Player on the left side
		
		g.fillRect((getWidth()-border_to_player)-10, p.getPlayerPosition(), 10, playerHeight);	//Player on the right side
	
		g.fillRect(getWidth()/2, 70, 20, 20); //Ball

		
//		movePlayer(g);
//		drawScore(g, score);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			movingUp = true;
			p.Player(movingUp, movingDown, difficulty);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			movingDown = true;
			p.Player(movingUp, movingDown, difficulty);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			movingUp = false;
			p.Player(movingUp, movingDown, difficulty);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			movingDown = false;
			p.Player(movingUp, movingDown, difficulty);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
