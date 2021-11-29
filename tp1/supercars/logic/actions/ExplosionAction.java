package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ExplosionAction implements InstantAction{
	
	int grenadeX;
	
	int grenadeY;
	
	public ExplosionAction(int grenadeX, int grenadeY) {
		this.grenadeX = grenadeX;
		this.grenadeY = grenadeY;
	}

	public void execute(Game game) {

		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				game.receiveExplosion(grenadeX + i, grenadeY + j);
				game.deleteObjects();
					
			}
		}
		
	}

}