package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject {
	
	public static int counter;
	
	public final static String INFO = "[OBSTACLE] hits car";
	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		symbol = "░";
	}
	
	public void onEnter() {
		counter++;
	}
	
	public void update() {
		
	}

	public void onDelete() {

	}
	
	public int getCounter() {
		return counter;
	}
	
	public void kill() {
		alive = false;
		counter--;
	}
	
	public boolean doCollision() {
		return true;
	}

	public static void reset() {
		counter = 0;
	}
	
	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		return false;
	}

	public boolean receiveShoot() {
		setAlive(false);
		counter--;
		return true;
	}
	
	public boolean receiveExplosion() {
		setAlive(false);
		return true;
	}
	
	public void receiveThunder() {
		setAlive(false);
		System.out.print(" -> " + symbol);
		counter--;
	}

}