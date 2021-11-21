package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject{
	private int numCoins;

	public Player(Game game) {
		super(game, 0, game.StartingPos());
		numCoins = 5;
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
	
	public void addCoins(int coins) {
		numCoins += coins;
	}
	
	public void advancePositions(int pos) {
		x += pos;
	}
	
	public void onEnter() {
		
	}

	public void update() {
		this.x += 1;
	}

	public void onDelete() {
		alive = false;
	}
	
	public int getCounter() {
		return 1;
	}
	
	public void reset() {
		this.x = 0;
		this.y = game.StartingPos();
		numCoins = 5;
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

	@Override
	public boolean receiveShoot() {
		return false;
	}
}
