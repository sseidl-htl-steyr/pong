package pong;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Player {
	private PlayField pf;
	private SinglePlayerOptions spo;
	private Bot b;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private Component base;

	private int playerPosition = 200;
	private int player2_Position = 200;

	private Timer t;
	
	public void Player(boolean movingUp, boolean movingDown, String difficulty) {
		this.movingUp = movingUp;
		this.movingDown = movingDown;
		
		if(spo.isEnemyBot()) {
			b.Bot(difficulty);
		}
		
		t = new Timer(20, Move());
		t.start();
	}
	
	public ActionListener Move() {
		t.restart();
		if(movingUp) {
			playerPosition = playerPosition - 5;
		} else if(movingDown) {
			playerPosition = playerPosition + 5;
		}
		pf.repaint();

			
		return null;
	}

	public int getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayer2_Position() {
		return player2_Position;
	}

	public void setPlayer2_Position(int player2_Position) {
		this.player2_Position = player2_Position;
	}
	
	
	
}
