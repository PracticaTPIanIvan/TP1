package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject{
	private int numCoins;
	private boolean crash;
	private int difficulty;
	public Player(Game game) {
		super(game, 0, game.StartingPos());
		numCoins = 5;
		crash = false;
	}
	
	public int getPositionX() {
		return this.x;
	}
	
	public int getPositionY() {
		return this.y;
	}
	
	public void advance() {
		this.x += 1;
	}
	
	public void positionUp() {
		this.y--;
	}

	public void positionDown() {
		this.y++;
		
	}
	
	public void onEnter() {
		
	}

	public void update() {
		
	}

	public void onDelete() {
		
	}
	
	public boolean doCollision() {
		return false;
	}
	
	public boolean receiveCollision(Player player) {
		return false;
	}
}
