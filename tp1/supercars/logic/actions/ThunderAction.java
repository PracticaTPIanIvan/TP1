package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ThunderAction implements InstantAction{
	
	private int x;
	
	private int y;

	@Override
	public void execute(Game game) {
		
		x = game.getPlayerPositionX() + game.getRandomColumn();
		y = game.getRandomLane();
		boolean Hit = false;
		
		String output = "Thunder hit position: (" + (x  - game.getPlayerPositionX())
				+ ", " + y + ")";
	
		Hit = game.receiveExplosion(x, y);
		
		if (Hit) {
			output += " -> ";
			output += game.getFirstObjSymbol(x, y) + " hit";
		}
		
		game.deleteObjects();
		System.out.println(output);
		
	}
	
}
