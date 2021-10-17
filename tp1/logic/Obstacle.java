package es.ucm.tp1.logic;

public class Obstacle {
	
	private int posX;
	
	private int posY;
	
	private int cont;
	
	private int resistance;
	
	private Game game;
	
	
	public Obstacle(int posX, int posY, Game game) {
		this.posX = posX;
		this.posY = posY;
		this.game = game;
	}
	
	public int getObstaclePositionX() {
		return posX;
	}
	
	public int getObstaclePositionY() {
		return posY;
	}
}

