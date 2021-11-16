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
		player.onDelete();
		return false;
	}

	public void onEnter() {
		counter++;
	}

	public void update() {
		switch(resistance) {
		case 0:
			counter--;
			onDelete();
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
		alive = false;
		
	}

	public int getCounter() {
		return counter;
	}


	public boolean receiveShoot() {
		resistance--;
		update();
		return true;
	}

}
