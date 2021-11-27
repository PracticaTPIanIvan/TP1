package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ThunderAction implements InstantAction{
	
	private int x;
	
	private int y;

	@Override
	public void execute(Game game) {
		
		x = game.getPlayerPositionX() + game.getRandomColumn();
		y = game.getRandomLane();
		boolean noHit = true;
		
		String output = "Thunder hit position: (" + (x  - game.getPlayerPositionX())
				+ ", " + y + ")";
		int index = game.objectPosition(x, y);
		
		if (index != -1) {
			
		}
		
		while(index != -1) {
			if (game.receiveExplosion(index)) {
				if (noHit) {
					output += " -> ";
					noHit = false;
				}
				output += game.getSymbol(index) + " hit";
				game.killObject(index);
			}

			index = game.nextObjPosition(index, x, y);
			game.deleteObjects();
		}
		
		System.out.println(output);
		
	}
	
}
