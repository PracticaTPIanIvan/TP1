package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends GameObject{
	
	public static int counter;
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		symbol = "←";
	}

	@Override
	public boolean doCollision() {
		return true;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		onDelete();
		return true;
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

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void update() {
		if (x > 0) {
			x -= 1;
		}
	}

	@Override
	public void onDelete() {
		alive = false;
		counter--;
	}

	@Override
	public int getCounter() {
		return 0;
	}

}
