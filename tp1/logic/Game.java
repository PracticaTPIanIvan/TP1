package es.ucm.tp1.logic;

import es.ucm.tp1.control.Level;

public class Game {
	
	private Player player;
	
	private Level level;
	
	private Obstacle obstacle;
	
	private int [] obstacleList;
	
	private static final String PLAYER_ICON = ">";
	
	private static final String OBSTACLE_ICON = "░";

	public Game(long seed, Level level, int obstacleList) {
		this.level = level;
		this.player = new Player();
		this.obstacleList = new int [2];
	}
	
	public int getPlayerPositionX() {
		return player.getPositionX();
	}
	
	public int getPlayerPositionY() {
		return player.getPositionY();
	}
	
	public void moveUp() {
		player.positionUp();	
	}
	
	public void moveDown() {
		player.positionDown();
	}
	
	public void toggleTest() {
		// TODO 
	}
	
	public int getVisibility() {
		return 8;
	}
	
	public int getRoadWidth() {
		return 3;
	}
	
	public double getObstacleFrequency() {
		return level.getObstacleFrequency();
	}
	
	public double getCoinFrequency() {
		return level.getCoinFrequency();
	}
	
	public String getGameStatus() {
		return "";
	}

	public String positionToString(int j, int i) { 
		if(j == player.getPositionX() && i == player.getPositionY()) {
			return PLAYER_ICON;
		}
		else {
			return "";
		}
	}
}
