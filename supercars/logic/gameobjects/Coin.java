package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject {
	public boolean alive;
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "¢";
	}
	public void onEnter() {
		
	}
	
	public void update() {
		
	}

	public void onDelete() {
		
	}
	
	public boolean doCollision() {
		return true;
	}
	@Override
	public boolean receiveCollision(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
}
