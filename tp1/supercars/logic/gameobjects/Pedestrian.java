package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends GameObject{
	
	String direction;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
	}

	@Override
	public boolean doCollision() {
		return true;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.onDelete();
		player.substractCoins(game.getPlayersCoins());
		onDelete();
		return true;
	}

	@Override
	public boolean receiveShoot() {
		onDelete();
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}
	
	public boolean receiveExplosion() {
		onDelete();
		game.substractPlayerCoins(game.getPlayersCoins());
		return true;
	}

	@Override
	public void onEnter() {
		if (y == 0) {
			direction = "down";
		} else {
			direction = "up";
		}
	}

	@Override
	public void update() {
		
		if (y == 0) {
			y++;
			direction = "down";
		} 
		else if (y == game.getRoadWidth() - 1
				) {
			y--;
			direction = "up";
		} 
		else {
			if (direction == "up") {
				y--;
			} else {
				y++;
			}
		}
	}

	@Override
	public void onDelete() {
		alive = false;
	}

	@Override
	public int getCounter() {
		return 0;
	}

}

