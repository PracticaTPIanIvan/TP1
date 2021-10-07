package es.ucm.tp1.logic;

public class Player {
	private int positionX;
	private int positionY;
	
	public Player() {
		this.positionX = 0;
		this.positionY = 1;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public void positionUp() {
		this.positionY--;
	}

	public void positionDown() {
		this.positionY++;
		
	}
}
