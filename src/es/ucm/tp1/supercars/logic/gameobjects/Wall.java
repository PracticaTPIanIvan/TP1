 package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle{
	
	private int resistance;
	
	private static final String[] SYMBOLS = new String[] {"░", "▒", "█" };
	
	private static final int COINS_WHEN_DESTROYED = 5;
	
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
			counter--;
		}
	}
	
	public void kill() {
		alive = false;
		counter--;
		game.giveCoins(COINS_WHEN_DESTROYED);
	}

	public boolean receiveShoot() {
		resistance--;
		if (resistance == 0) {
			game.giveCoins(COINS_WHEN_DESTROYED);
		}
		return true;
	}
	
	public void receiveThunder() {
		setAlive(false);
		System.out.print(" -> " + symbol);
		counter--;
		game.giveCoins(COINS_WHEN_DESTROYED);
	}
	
	public String getSerialInfo() {
		String res = getSymbol();
		res += " (" + getX() + ", " + getY() + ")";
		res += resistance;
		return res;
	}

}