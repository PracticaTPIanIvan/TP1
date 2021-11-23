package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ExplosionAction implements InstantAction{

	public void execute(Game game) {
		int pos = game.objectPosition(game.getPlayerPositionX() + x, game.getPlayerPositionY());
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				while()
			}
		}
		
	}

}
