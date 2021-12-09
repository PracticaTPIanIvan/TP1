package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Shoot implements InstantAction{
	public void execute (Game game) {
		Collider c;
		boolean found = false;
		int x = 0;
		
		while (!found && x < game.getVisibility()) {
			c = game.getFirstColliderInPos(x, game.getPlayerPositionY());
			if (c != null) {
				found = c.receiveShoot();
			}
			x++;
		}
	}
}
