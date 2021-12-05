package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class Shoot implements InstantAction{
	public void execute (Game game) {
		boolean found = false;
		int x = 0;
		while (!found && x < game.getVisibility()) {
		
			if (game.existsObj(x, game.getPlayerPositionY())) {
				found = game.receiveShoot(x, game.getPlayerPositionY());
			}
			x++;
		}
	}
}
