package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;

public class Grenade extends GameObject{
	
	private int countDown;
	
	private boolean firstLoop;
	
	public final static String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";

	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		symbol = "ð";
		firstLoop = true;
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	public boolean receiveCollision(Player player) {
		return false;
	}

	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return true;
	}
	
	public void receiveThunder() {

	}
	
	public String getSerialInfo() {
		String res = getSymbol();
		res += " (" + getX() + ", " + getY() + ")";
		res += countDown;
		return res;
	}

	public void onEnter() {
		countDown = 3;
		symbol = "ð[" + countDown + "]";
	}

	public void update() {
		if (!firstLoop) {
			countDown--;
			symbol = "ð[" + countDown + "]";
			if(countDown == 0) {
				game.executeInstantAction(new ExplosionAction(x, y));
				alive = false;
			}
		} else {
			firstLoop = false;
		}
	}

	public void onDelete() {
		
	}

	public int getCounter() {
		return 0;
	}
	
}