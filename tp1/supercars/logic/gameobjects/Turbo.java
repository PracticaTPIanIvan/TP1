package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject {

	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		symbol = ">>>";
	}

	@Override
	public boolean doCollision() {
		return true;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.advancePositions(3);
		return false;
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

}
