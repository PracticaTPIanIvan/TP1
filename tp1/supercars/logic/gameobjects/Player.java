package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject{
	private int numCoins;

	public Player(Game game) {
		super(game, 0, game.StartingPos());
		numCoins = 5;
		symbol = ">";
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
	
	public String getSymbol() {
		return symbol;
	}
	
	public boolean moveUp() {
		if(y > 0 && x <= game.getRoadLength()) {
			y--;
			return true;
		}
		
		return false;
	}

	public boolean moveDown() {
		if(y < game.getRoadWidth() - 1 && x <= game.getRoadLength()) {
			y++;
			return true;
		} 
		return false;
		
	}
	
	public boolean advance() {
		if (x < game.getRoadLength()) {
			x++;
			return true;
		}
		return false;
	}
	
	public void addCoins(int coins) {
		numCoins += coins;
	}
	
	public void substractCoins(int coins) {
		numCoins -= coins;
	}
	
	public void advancePositions(int pos) {
		x += pos;
	}
	
	public void onEnter() {
		
	}

	public void onDelete() {
		
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
		Collider other = game.getFirstObjInPos(getPositionX(), getPositionY());
		if (other != null) {
			return other.receiveCollision(this);
		} else {
			alive = true;
		}
		return false;
	}
	
	public boolean receiveCollision(Player player) {
		return false;
	}

	public void update() {
		if (!alive) {
			symbol = "@";
		}
	}

	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return false;
	}
	
	public void receiveThunder() {
		
	}
}