package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends Coin {
	
	public static boolean created = false;
	
	public static boolean used;
	
	public static int coinPosX = -1;
	
	public final static String INFO = "[SUPERCOIN] gives 1000 coins";
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "$";
		used = false;
		coinPosX = getX();
	}
	
	public void onEnter() {
		created = true;
	}
	
	public void onDelete() {
		created = false;
	}
	
	public void update() {
		coinPosX = getX();
	}
	
	public static boolean hasSuperCoin() {
		return created;
	}
	
	public boolean doCollision() {
		return true;
	}
	
	public static void reset() {
		created = false;
	}
	
	public boolean receiveCollision(Player player) {
		if (!used) {
			player.addCoins(1000);
			alive = false;
			used = true;
		}
		return false;
	}
	
}
