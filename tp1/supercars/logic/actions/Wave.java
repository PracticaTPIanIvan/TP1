package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Wave implements InstantAction{

	public void execute(Game game) {
		Collider c;
		
		for (int i = game.getVisibility() - 1; i >= 0; i--) {
			for (int j = 0; j < game.getRoadWidth(); j++) {
				if (game.existsObj(game.getPlayerPositionX() + i, j)) {
					c = game.getFirstColliderInPos(game.getPlayerPositionX() + i, j);
					c.receiveWave();
				}
			}
		}
	}
}
