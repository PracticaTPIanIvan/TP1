 package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle{
	
	private int resistance;
	
	public static int counter;
	
	private static final String[] SYMBOLS = new String[] {"░", "▒", "█" };
	
	public final static String INFO = "[WALL] hard obstacle ";
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		resistance = 3;
		symbol = SYMBOLS[resistance - 1];
	}
	public void update() {
		if (resistance != 0) {
			symbol = SYMBOLS[resistance - 1];
		} else {
			alive = false;
		}
	}

	public boolean receiveShoot() {
		resistance--;
		return true;
	}

	public String getSerialInfo() {
		return Integer.toString(resistance);
	}
	
}
