package pong;

public class Bot {
	private SinglePlayerOptions spo;
	private int x_Bot = 20;
	
	public Bot() {
		super();
		
		this.SetBot();
	}
	
	public void SetBot() {
		if(spo.getDifficulty() == "easy") {
			this.Bot_Easy();
		}
		if(spo.getDifficulty() == "medium") {
			this.Bot_Medium();
		}
		if(spo.getDifficulty() == "hard") {
			this.Bot_Hard();
		}
	}
	
	public void Bot_Easy() {

	}
	public void Bot_Medium() {
		
	}
	public void Bot_Hard() {
		
	}

	public int getX_Bot() {
		return x_Bot;
	}

	public void setX_Bot(int x_Bot) {
		this.x_Bot = x_Bot;
	}
	
	
}
