package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends GameObject{
	
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
		player.onDelete();
		onDelete();
		return true;
	}

	@Override
	public boolean receiveShoot() {
		onDelete();
		return true;
	}
	
	public boolean receiveExplosion() {
		onDelete();
		return true;
	}

	@Override
	public void onEnter() {
		
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
	}

	@Override
	public int getCounter() {
		return 0;
	}

}
