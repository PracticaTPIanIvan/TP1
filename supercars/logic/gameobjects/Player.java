package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
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
	
	public int getCoins() {
		return numCoins;
	}
	
	public void positionUp() {
		this.y--;
	}

	public void positionDown() {
		this.y++;
		
	}
	
	public void addCoins() {
		numCoins++;
	}
	
	public void onEnter() {
		
	}

	public void update() {
		this.x += 1;
	}

	public void onDelete() {
		alive = false;
	}
	
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(getPositionX(), getPositionY());
		if (other != null) {
			return other.receiveCollision(this);
		}
		return false;
	}
	
	public boolean receiveCollision(Player player) {
		return false;
	}
}
