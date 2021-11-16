package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends GameObject{
	
	private int resistance;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		resistance = 3;
		symbol = "█";
	}


	public boolean doCollision() {
		
		return false;
	}

	public boolean receiveCollision(Player player) {
		
		return false;
	}

	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	public int getCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

}
