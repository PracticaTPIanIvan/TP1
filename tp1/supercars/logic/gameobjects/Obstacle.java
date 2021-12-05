package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject {
	
	public static int counter;
	
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
	
	@Override
	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		return false;
	}

	@Override
	public boolean receiveShoot() {
		setAlive(false);
		return true;
	}
	
	public boolean receiveExplosion() {
		setAlive(false);
		return true;
	}

}