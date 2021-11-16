package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class Shoot implements InstantAction{
	public void execute (Game game) {
		boolean found = false;
		int x = 0;
		while (!found && x < 8) {
			//Encapsulación
			int pos = game.objectPosition(game.getPlayerPositionX() + x, game.getPlayerPositionY());
			if (pos !=  -1) {
				found = game.receiveShoot(pos);
			}
			x++;
		}
	}
}
