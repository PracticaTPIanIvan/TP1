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
		alive = false;
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
		player.onDelete();
		return false;
	}

	@Override
	public boolean receiveShoot() {
		counter--;
		onDelete();
		return true;
	}

}
