package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject {
	
	public static int counter;
	
	public final static String INFO = "[Coin] gives 1 coin to the player";
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "¢";
	}
	
	public void onEnter() {
		counter++;
	}
	
	public void update() {
		
	}

	public void onDelete() {
		
	}
	
	public void kill() {
		alive = false;
		counter--;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public boolean doCollision() {
		return true;
	}
	
	public static void reset() {
		counter = 0;
	}
	
	public boolean receiveCollision(Player player) {
		if (alive) {
			player.addCoins(1);
			alive = false;
			counter--;
		}
		return false;
	}

	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return false;
	}
	
	public void receiveThunder() {
		
	}
}