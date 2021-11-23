package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Grenade extends GameObject{
	
	private int countDown;

	public Grenade(Game game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void onEnter() {
		countDown = 3;
	}

	@Override
	public void update() {
		countDown--;
		if(countDown == 0) {
			
		}
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
