package es.ucm.tp1.logic;

import es.ucm.tp1.control.Level;

public class Game {
	
	private Player player;
	

	public Game(long seed, Level level) {
		// TODO
		this.player = new Player();
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
	
	public String getGameStatus() {
		return "";
	}

	public String positionToString(int j, int i) { 
		return "";
	}
}

