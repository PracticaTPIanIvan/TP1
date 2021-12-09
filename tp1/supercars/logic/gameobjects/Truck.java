package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends Obstacle{
	
	public final static String INFO = "[TRUCK] moves towards the player";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		symbol = "←";
	}

	public void update() {
		x -= 1;
	}
}
