package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class Wave implements InstantAction{

	public void execute(Game game) {
		for(int i = 0; i < game.getContainerSize(); i++) {
			if(game.getPlayerPositionX() < game.getObjectX(i) && game.getPlayerPositionX() + game.getVisibility() > game.getObjectX(i) &&
				game.objectPosition(game.getObjectX(i) + 1, game.getObjectY(i)) == -1) {
				game.setAdvance(i);
			}
		}
		
	}
	
}
