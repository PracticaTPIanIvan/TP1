 package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends GameObject{
	
	private int resistance;
	
	public static int counter;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		resistance = 3;
		symbol = "█";
	}


	public boolean doCollision() {
		return true;
	}

	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		return false;
	}

	public void onEnter() {
		counter++;
	}

	public void update() {
		switch(resistance) {
		case 0:
			setAlive(false);
			break;
		case 1:
			symbol = "░";
			break;
		case 2:
			symbol = "▒";
			break;
		}
	}

	public void onDelete() {
		counter--;
	}

	public int getCounter() {
		return counter;
	}


	public boolean receiveShoot() {
		resistance--;
		return true;
	}
	
	public boolean receiveExplosion() {
		setAlive(false);
		return true;
	}

}