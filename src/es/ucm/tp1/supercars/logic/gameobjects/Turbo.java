package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject {
	
	private final static int numPositions = 3;
	
	public final static String INFO = "[TURBO] pushes the car: " + numPositions + " columns";

	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		symbol = ">>>";
	}

	public boolean doCollision() {
		return true;
	}

	public boolean receiveCollision(Player player) {
		player.advancePositions(numPositions);
		return false;
	}

	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveExplosion() {
		return false;
	}
	
	public void receiveThunder() {
		
	}

	public void onEnter() {
		
	}

	public void update() {
		
	}

	public void onDelete() {
		
	}

	

}