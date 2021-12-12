package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle{
	
	private boolean upwards;
	
	private boolean substractCounterWhenDelete = true;
	
	public final static String INFO = "[PEDESTRIAN] person crossing the road up and down";
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
		upwards = false;
	}
	
	public void onDelete() {
		if (substractCounterWhenDelete) {
			counter--;
		}
	}

	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		return true;
	}

	public boolean receiveShoot() {
		setAlive(false);
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}
	
	public boolean receiveExplosion() {
		setAlive(false);
		substractCounterWhenDelete = false;
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}
	
	public void receiveThunder() {
		setAlive(false);
		System.out.print(" -> " + symbol);
	}
	
	public String getSerialInfo() {
		String res = getSymbol();
		res += " (" + getX() + ", " + getY() + ")";
		if(upwards) {
			res += " up";
		}
		else {
			res += " down";
		}
		return res;
	}

	public void update() {
		
		if (y == 0) {
			y++;
			upwards = false;
		} 
		else if (y == game.getRoadWidth() - 1) {
			y--;
			upwards = true;
		} 
		else {
			if (upwards) {
				y--;
			} else {
				y++;
			}
		}
	}

}

