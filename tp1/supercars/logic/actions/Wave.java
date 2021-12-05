package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class Wave implements InstantAction{

	public void execute(Game game) {
		for (int i = game.getVisibility() - 1; i >= 0; i--) {
			for (int j = 0; j < game.getRoadWidth(); j++) {
				if (!game.existsObj(i, j)) {
					for (int x = 0; i < game.numObjInPos(i, j); x++) {
						game.advanceFirstObj(i, j);
					}
				}
			}
		}
		
	}
	
}
