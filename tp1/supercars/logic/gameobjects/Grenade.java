package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;

public class Grenade extends GameObject{
	
	private int countDown;
	
	private boolean firstLoop;

	public Grenade(Game game, int x, int y) {
		super(game, game.getPlayerPositionX() + x, y);
		symbol = "ð";
		firstLoop = true;
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
	
	public boolean receiveExplosion() {
		return true;
	}

	@Override
	public void onEnter() {
		countDown = 3;
		symbol = "ð[" + countDown + "]";
	}

	@Override
	public void update() {
		if (!firstLoop) {
			countDown--;
			symbol = "ð[" + countDown + "]";
			if(countDown == 0) {
				game.executeInstantAction(new ExplosionAction(x, y));
			}
		} else {
			firstLoop = false;
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