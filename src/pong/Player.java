package pong;

public class Player {
	private PlayField pf;
	private boolean movingUp = false;
	public Player(boolean movingUp) {
		super();
		this.movingUp = movingUp;
		if (movingUp) {
			IsMovingUp();
		} else {
			IsMovingDown();
		}
	}
	
	public void IsMovingUp() {
//		PlayField.movePlayer();
	}
	public void IsMovingDown() {
		
	}
}
