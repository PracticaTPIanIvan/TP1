package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle{
	
	boolean upwards;
	
	public final static String INFO = "[PEDESTRIAN] person crossing the road up and down";
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
		upwards = false;
	}

	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		player.substractCoins(game.getPlayersCoins());
		onDelete();
		return true;
	}

	public boolean receiveShoot() {
		setAlive(false);
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}
	
	public boolean receiveExplosion() {
		setAlive(false);
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}
	
	public void receiveThunder() {
		setAlive(false);
		System.out.print(" -> " + symbol);
		game.substractPlayerCoins(game.getPlayersCoins());
	}
	
	
	public void onEnter() {
		counter++;  
	}

	@Override
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

	public String getSerialInfo() {
		if(upwards) {
			return "up";
		}
		else {
			return "down";
		}
	}
	
}
