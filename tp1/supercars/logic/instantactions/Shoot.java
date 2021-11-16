package es.ucm.tp1.supercars.logic.instantactions;

import es.ucm.tp1.supercars.logic.InstantAction;
import es.ucm.tp1.supercars.logic.Game;

public class Shoot implements InstantAction{
	public void execute (Game game) {
		boolean found = false;
		int x = 0;
		
		
		while (!found && x < 8) {
			//Encapsulación
			if (game.objectPosition(game.getPlayerPositionX() + x, game.getPlayerPositionY()) !=  -1) {
				game.
			}
			x++;
		}
	}
}
