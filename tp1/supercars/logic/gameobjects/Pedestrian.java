package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends GameObject{
	
	String direction;
	
	public static int counter;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
		direction = "down";
	}

	@Override
	public boolean doCollision() {
		return true;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setAlive(false);
		player.onDelete();
		player.substractCoins(game.getPlayersCoins());
		onDelete();
		return true;
	}

	@Override
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

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void update() {
		
		if (y == 0) {
			y++;
			direction = "down";
		} 
		else if (y == game.getRoadWidth() - 1) {
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
		counter--;
	}

	@Override
	public int getCounter() {
		return 0;
	}

}

