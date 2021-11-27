package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject {
	
	public static boolean created;
	
	public static boolean used;
	
	public static int coinPosX;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "$";
		created = false;
		used = false;
	}
	
	public void onEnter() {
		created = true;
	}
	
	public void update() {
		coinPosX = getX();
	}

	public void onDelete() {
		alive = false;
	}
	
	public int getCounter() {
		return 0;
	}
	
	public boolean doCollision() {
		return true;
	}
	
	public static void reset() {
		created = false;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		if (!used) {
			player.addCoins(1000);
			used = true;
		}
		return false;
	}

	
	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return false;
	}
	
}
