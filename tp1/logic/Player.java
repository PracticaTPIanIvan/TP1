package es.ucm.tp1.logic;

public class Player {
	private int positionX;
	private int positionY;
	private int numCoins;
	
	public Player() {
		this.positionX = 0;
		this.positionY = 1;
		this.numCoins = 0;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public int getCoins() {
		return numCoins;
	}
	
	public void advance() {
		positionX += 1;
	}
	
	public void positionUp() {
		this.positionY--;
	}

	public void positionDown() {
		this.positionY++;
		
	}
}
