package es.ucm.tp1.logic;

public class Coin {
	private int posX;
	
	private int posY;
	
	private Game game;
	
	private boolean alive;
	
	public Coin(int posX, int posY, Game game) {
		this.posX = posX;
		this.posY = posY;
		this.game = game;
		alive = true;
	}
	
	public int getCoinPositionX() {
		return posX;
	}
	
	public int getCoinPositionY() {
		return posY;
	}
	
	public boolean getState() {
		return alive;
	}
	
	public void setState(boolean state) {
		alive = state;
	}
}

