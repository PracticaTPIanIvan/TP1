package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class ThunderAction implements InstantAction{
	
	private int x;
	
	private int y;

	@Override
	public void execute(Game game) {
		x = game.getPlayerPositionX() + game.getRandomColumn();
		y = game.getRandomLane();
		
		String output = "Thunder hit position: (" + (x  - game.getPlayerPositionX())
				+ ", " + y + ")";
		
		System.out.print(output);
		
		Collider c = game.getFirstColliderInPos(x, y);
		if (c != null) {
			c.receiveThunder();
		}
		System.out.print("\n");
		game.removeDead();
	}
	
}
